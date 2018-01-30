/*
 * Copyright 2003 - 2015 The eFaps Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.efaps.eql;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.validation.AbstractInjectableValidator;
import org.efaps.eql.eQL.CIPrintPart;
import org.efaps.eql.eQL.DeletePart;
import org.efaps.eql.eQL.ExecPart;
import org.efaps.eql.eQL.ExecSelect;
import org.efaps.eql.eQL.InsertPart;
import org.efaps.eql.eQL.NestedQueryPart;
import org.efaps.eql.eQL.OneOrder;
import org.efaps.eql.eQL.OneSelect;
import org.efaps.eql.eQL.OneUpdate;
import org.efaps.eql.eQL.OneWhere;
import org.efaps.eql.eQL.OrderPart;
import org.efaps.eql.eQL.PrintPart;
import org.efaps.eql.eQL.QueryPart;
import org.efaps.eql.eQL.SelectPart;
import org.efaps.eql.eQL.Statement;
import org.efaps.eql.eQL.UpdatePart;
import org.efaps.eql.eQL.WherePart;
import org.efaps.eql.parser.antlr.EQLParser;
import org.efaps.eql.stmt.AbstractCIPrintStmt;
import org.efaps.eql.stmt.AbstractDeleteStmt;
import org.efaps.eql.stmt.AbstractExecStmt;
import org.efaps.eql.stmt.AbstractInsertStmt;
import org.efaps.eql.stmt.AbstractPrintStmt;
import org.efaps.eql.stmt.AbstractUpdateStmt;
import org.efaps.eql.stmt.IEQLStmt;
import org.efaps.eql.stmt.impl.NonOpCIPrint;
import org.efaps.eql.stmt.impl.NonOpDelete;
import org.efaps.eql.stmt.impl.NonOpExec;
import org.efaps.eql.stmt.impl.NonOpInsert;
import org.efaps.eql.stmt.impl.NonOpNestedQuery;
import org.efaps.eql.stmt.impl.NonOpPrint;
import org.efaps.eql.stmt.impl.NonOpUpdate;
import org.efaps.eql.stmt.parts.INestedQueryStmtPart;
import org.efaps.eql.stmt.parts.IQueryPart;
import org.efaps.eql.validation.EQLJavaValidator;
/**
 * TODO comment!
 *
 * @author The eFaps Team
 */
public class EQLInvoker
{

    /** The parser. */
    @Inject
    private EQLParser parser;

    /** The validator. */
    @Inject
    private EQLJavaValidator validator;

    /** The syntax errors. */
    private final List<String> syntaxErrors = new ArrayList<>();

    /**
     *
     */
    public EQLInvoker()
    {
        EQLStandaloneSetup.doSetup(this);
    }

    /**
     * Getter method for the instance variable {@link #parser}.
     *
     * @return value of instance variable {@link #parser}
     */
    public EQLParser getParser()
    {
        return this.parser;
    }

    /**
     * Invoke.
     *
     * @param _stmt the _stmt
     * @return the IEQL stmt
     * @throws Exception the exception
     */
    public IEQLStmt invoke(final String _stmt)
        throws Exception
    {
        IEQLStmt ret = null;
        final IParseResult result = getParser().doParse(_stmt);
        this.syntaxErrors.clear();
        if (result.hasSyntaxErrors()) {
            final Iterator<INode> iter = result.getSyntaxErrors().iterator();
            while (iter.hasNext()) {
                final INode node = iter.next();
                this.syntaxErrors.add(node.getSyntaxErrorMessage().getMessage());
            }
        }
        final Statement stmt = (Statement) result.getRootASTElement();
        if (stmt != null) {
            final List<Diagnostic> diagnostics = validate(stmt);
            if (diagnostics.isEmpty()) {
                if (stmt.getPrintPart() != null) {
                    final PrintPart printPart = stmt.getPrintPart();
                    final AbstractPrintStmt print = getPrint();
                    print.addInstance(printPart.getOid());
                    ret = print;
                    if (printPart.getSelectPart() != null) {
                        final SelectPart selectPart = printPart.getSelectPart();
                        for (final OneSelect sel : selectPart.getSelects()) {
                            if (sel.getSelect() != null) {
                                print.addSelect(sel.getSelect(), sel.getAlias());
                            } else if (sel.getExecSelect() != null) {
                                print.addSelect(sel.getExecSelect().getClassName(), sel.getExecSelect().getParameters(),
                                                sel.getAlias());
                            }
                        }
                    }
                    if (printPart.getOrderPart() != null) {
                        final OrderPart orderPart = printPart.getOrderPart();
                        for (final OneOrder oneOrder : orderPart.getOneOrder()) {
                            print.addOrder(oneOrder.getKey(), oneOrder.isDesc());
                        }
                    }
                } else if (stmt.getExecPart() != null) {
                    final ExecPart execPart = stmt.getExecPart();
                    final AbstractExecStmt exec = getExec();
                    exec.setESJPName(execPart.getClassName());
                    for (final String parameter : execPart.getParameters()) {
                        exec.addParameter(parameter);
                    }
                    for (final ExecSelect exSel : execPart.getExecSelect()) {
                        exec.addSelect(exSel.getSelect(), exSel.getAlias());
                    }
                    if (execPart.getOrderPart() != null) {
                        final OrderPart orderPart = execPart.getOrderPart();
                        for (final OneOrder oneOrder : orderPart.getOneOrder()) {
                            exec.addOrder(oneOrder.getKey(), oneOrder.isDesc());
                        }
                    }
                    ret = exec;
                } else if (stmt.getQueryPart() != null) {
                    final QueryPart queryPart = stmt.getQueryPart();
                    final AbstractPrintStmt print = getPrint();
                    for (final String type : queryPart.getTypes()) {
                        print.addType(type);
                    }
                    ret = print;

                    addWherePart(print, queryPart.getWherePart());

                    if (queryPart.getSelectPart() != null) {
                        final SelectPart selectPart = queryPart.getSelectPart();
                        for (final OneSelect sel : selectPart.getSelects()) {
                            if (sel.getSelect() != null) {
                                print.addSelect(sel.getSelect(), sel.getAlias());
                            } else if (sel.getExecSelect() != null) {
                                print.addSelect(sel.getExecSelect().getClassName(), sel.getExecSelect().getParameters(),
                                                sel.getAlias());
                            }
                        }
                    }
                    if (queryPart.getOrderPart() != null) {
                        final OrderPart orderPart = queryPart.getOrderPart();
                        for (final OneOrder oneOrder : orderPart.getOneOrder()) {
                            print.addOrder(oneOrder.getKey(), oneOrder.isDesc());
                        }
                    }
                    if (queryPart.getLimitPart() != null) {
                        print.setLimit(queryPart.getLimitPart().getValue());
                    }

                } else if (stmt.getUpdatePart() != null) {
                    final UpdatePart updatePart = stmt.getUpdatePart();
                    final AbstractUpdateStmt update = getUpdate();
                    if (updatePart.getOid() != null) {
                        update.addInstance(updatePart.getOid());
                    }
                    if (updatePart.getOids() != null && !updatePart.getOids().isEmpty()) {
                        update.addInstance(updatePart.getOids().toArray(new String[updatePart.getOids().size()]));
                    }
                    if (updatePart.getQueryPart() != null) {
                        for (final String type : updatePart.getQueryPart().getTypes()) {
                            update.addType(type);
                        }
                        addWherePart(update, updatePart.getQueryPart().getWherePart());
                    }
                    for (final OneUpdate oneUpdate : updatePart.getUpdates()) {
                        update.addAttribute(oneUpdate.getAttribute(), oneUpdate.getValue());
                    }
                    ret = update;
                } else if (stmt.getInsertPart() != null) {
                    final InsertPart insertPart = stmt.getInsertPart();
                    final AbstractInsertStmt insert = getInsert();
                    insert.setType(insertPart.getType());
                    for (final OneUpdate oneUpdate : insertPart.getUpdates()) {
                        insert.addAttribute(oneUpdate.getAttribute(), oneUpdate.getValue());
                    }
                    ret = insert;
                } else if (stmt.getDeletePart() != null) {
                    final DeletePart deletePart = stmt.getDeletePart();
                    final AbstractDeleteStmt delete = getDelete();
                    if (deletePart.getOid() != null) {
                        delete.addInstance(deletePart.getOid());
                    }
                    if (deletePart.getOids() != null && !deletePart.getOids().isEmpty()) {
                        delete.addInstance(deletePart.getOids().toArray(new String[deletePart.getOids().size()]));
                    }
                    if (deletePart.getQueryPart() != null) {
                        for (final String type : deletePart.getQueryPart().getTypes()) {
                            delete.addType(type);
                        }
                        addWherePart(delete, deletePart.getQueryPart().getWherePart());
                    }
                    ret = delete;
                } else if (stmt.getCiPrintPart() != null) {
                    final CIPrintPart ciPrintPart = stmt.getCiPrintPart();
                    final AbstractCIPrintStmt ciPrint = getCIPrint();
                    if (ciPrintPart.getCiNature() != null) {
                        ciPrint.setCINature(ciPrintPart.getCiNature());
                    }
                    if (ciPrintPart.getCi() != null) {
                        ciPrint.setCI(ciPrintPart.getCi());
                    }
                    ret = ciPrint;
                }
            } else {
                ret = () -> diagnostics;
            }
        }
        return ret;
    }

    /**
     * Adds the where part.
     *
     * @param _queryPart the _query part
     * @param _wherePart the _where part
     * @throws Exception the exception
     */
    protected void addWherePart(final IQueryPart _queryPart,
                                final WherePart _wherePart)
        throws Exception
    {
        if (_wherePart != null) {
            for (final OneWhere oneWhere : _wherePart.getWheres()) {
                if (oneWhere.getAttribute() != null) {
                    switch (oneWhere.getComparison()) {
                        case EQUAL:
                            _queryPart.addWhereAttrEq(oneWhere.getAttribute(), oneWhere.getValue());
                            break;
                        case GREATER:
                            _queryPart.addWhereAttrGreater(oneWhere.getAttribute(), oneWhere.getValue());
                            break;
                        case LESS:
                            _queryPart.addWhereAttrLess(oneWhere.getAttribute(), oneWhere.getValue());
                            break;
                        case LIKE:
                            _queryPart.addWhereAttrLike(oneWhere.getAttribute(), oneWhere.getValue());
                            break;
                        case UNEQUAL:
                            _queryPart.addWhereAttrNotEq(oneWhere.getAttribute(), oneWhere.getValue());
                            break;
                        case IN:
                            if (oneWhere.getNestedQueryPart() != null) {
                                _queryPart.addWhereAttrIn(oneWhere.getAttribute(),
                                                getNestedQuery(oneWhere.getNestedQueryPart()));
                            } else {
                                _queryPart.addWhereAttrIn(oneWhere.getAttribute(),
                                            oneWhere.getValues().toArray(new String[ oneWhere.getValues().size()]));
                            }
                            break;
                        case NOTIN:
                            if (oneWhere.getNestedQueryPart() != null) {
                                _queryPart.addWhereAttrNotIn(oneWhere.getAttribute(),
                                                getNestedQuery(oneWhere.getNestedQueryPart()));
                            } else {
                                _queryPart.addWhereAttrNotIn(oneWhere.getAttribute(),
                                            oneWhere.getValues().toArray(new String[ oneWhere.getValues().size()]));
                            }
                            break;
                        default:
                            break;
                    }
                } else if (oneWhere.getSelect() != null) {
                    switch (oneWhere.getComparison()) {
                        case EQUAL:
                            _queryPart.addWhereSelectEq(oneWhere.getSelect(), oneWhere.getValue());
                            break;
                        case GREATER:
                            _queryPart.addWhereSelectGreater(oneWhere.getSelect(), oneWhere.getValue());
                            break;
                        case LESS:
                            _queryPart.addWhereSelectLess(oneWhere.getSelect(), oneWhere.getValue());
                            break;
                        case LIKE:
                            _queryPart.addWhereSelectLike(oneWhere.getSelect(), oneWhere.getValue());
                            break;
                        case IN:
                            if (oneWhere.getNestedQueryPart() != null) {
                                _queryPart.addWhereSelectIn(oneWhere.getSelect(),
                                                getNestedQuery(oneWhere.getNestedQueryPart()));
                            } else {
                                _queryPart.addWhereSelectIn(oneWhere.getSelect(),
                                            oneWhere.getValues().toArray(new String[ oneWhere.getValues().size()]));
                            }
                            break;
                        case NOTIN:
                            if (oneWhere.getNestedQueryPart() != null) {
                                _queryPart.addWhereSelectNotIn(oneWhere.getSelect(),
                                                getNestedQuery(oneWhere.getNestedQueryPart()));
                            } else {
                                _queryPart.addWhereSelectNotIn(oneWhere.getSelect(),
                                            oneWhere.getValues().toArray(new String[ oneWhere.getValues().size()]));
                            }
                            break;
                        case UNEQUAL:
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * Gets the nested query.
     *
     * @param _nestedQueryPart the _nested query part
     * @return the nested query
     * @throws Exception the exception
     */
    protected INestedQueryStmtPart getNestedQuery(final NestedQueryPart _nestedQueryPart)
        throws Exception
    {
        final INestedQueryStmtPart ret = getNestedQuery();
        for (final String type : _nestedQueryPart.getTypes()) {
            ret.addType(type);
        }

        addWherePart(ret, _nestedQueryPart.getWherePart());

        if (_nestedQueryPart.getSelectPart() != null) {
            ret.setSelect(_nestedQueryPart.getSelectPart().getSelects().get(0).getSelect());
        }
        return ret;
    }

    /**
     * Validate.
     *
     * @param _stmt stmt to be validated
     * @return the list
     */
    public List<Diagnostic> validate(final String _stmt)
    {
        final IParseResult result = getParser().doParse(_stmt);
        final Statement stmt = (Statement) result.getRootASTElement();
        return validate(stmt);
    }

    /**
     * Validate.
     *
     * @param _stmt stmt to be validated
     * @return the list
     */
    public List<Diagnostic> validate(final Statement _stmt)
    {
        List<Diagnostic> ret = new ArrayList<>();
        if (_stmt != null) {
            final BasicDiagnostic diagnosticList = new BasicDiagnostic();
            final Map<Object, Object> context = new HashMap<>();
            context.put(AbstractInjectableValidator.CURRENT_LANGUAGE_NAME, "org.efaps.eql.EQL");
            //getValidator().validate(_stmt, diagnosticList, context);
            final TreeIterator<EObject> iterator = _stmt.eAllContents();
            while (iterator.hasNext()) {
                final EObject nextObj = iterator.next();
                if (nextObj != null) {
              //      getValidator().validate(nextObj, diagnosticList, context);
                }
            }
            ret = diagnosticList.getChildren();
        }
        return ret;
    }

    /**
     * Getter method for the instance variable {@link #validator}.
     *
     * @return value of instance variable {@link #validator}
     */
    protected EQLJavaValidator getValidator()
    {
        return this.validator;
    }

    /**
     * Gets the prints the.
     *
     * @return the prints the
     */
    protected AbstractPrintStmt getPrint()
    {
        return new NonOpPrint();
    }

    /**
     * Gets the prints the.
     *
     * @return the prints the
     */
    protected AbstractCIPrintStmt getCIPrint()
    {
        return new NonOpCIPrint();
    }

    /**
     * Gets the exec.
     *
     * @return the exec
     */
    protected AbstractExecStmt getExec()
    {
        return new NonOpExec();
    }

    /**
     * Gets the update.
     *
     * @return the update
     */
    protected AbstractUpdateStmt getUpdate()
    {
        return new NonOpUpdate();
    }

    /**
     * Gets the delete.
     *
     * @return the delete
     */
    protected AbstractDeleteStmt getDelete()
    {
        return new NonOpDelete();
    }

    /**
     * Gets the insert.
     *
     * @return the insert
     */
    protected AbstractInsertStmt getInsert()
    {
        return new NonOpInsert();
    }

    /**
     * Gets the nested query.
     *
     * @return the nested query
     */
    protected INestedQueryStmtPart getNestedQuery()
    {
        return new NonOpNestedQuery();
    }

    /**
     * Getter method for the instance variable {@link #syntaxErrors}.
     *
     * @return value of instance variable {@link #syntaxErrors}
     */
    public List<String> getSyntaxErrors()
    {
        return this.syntaxErrors;
    }
}
