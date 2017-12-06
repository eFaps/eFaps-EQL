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
 * Revision:        $Rev$
 * Last Changed:    $Date$
 * Last Changed By: $Author$
 */

package org.efaps.eql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.validation.AbstractInjectableValidator;
import org.efaps.eql.eQL.Statement;
import org.efaps.eql.parser.antlr.EQLParser;
import org.efaps.eql.stmt.AbstractPrintStmt;
import org.efaps.eql.stmt.PrintStmt;
import org.efaps.eql.validation.EQLJavaValidator;
import org.testng.annotations.BeforeClass;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public abstract class AbstractTest
{

    private EQLInvoker invoker;

    private BasicDiagnostic diagnostic;

    private final List<String> syntaxErrors = new ArrayList<>();

    @BeforeClass
    public void setupParser()
    {
        this.invoker = new EQLInvoker()
        {
            @Override
            protected AbstractPrintStmt getPrint()
            {
                return new PrintStmt();
            }
        };
    }

    protected final Statement getStatement(final CharSequence _stmt)
    {
        final IParseResult result = getParser().doParse(_stmt);
        this.syntaxErrors.clear();
        if (result.hasSyntaxErrors()) {
            final Iterator<INode> iter = result.getSyntaxErrors().iterator();
            while (iter.hasNext()) {
                final INode node = iter.next();
                this.syntaxErrors.add(node.getSyntaxErrorMessage().getMessage());
            }

        }
        final Statement ret = (Statement) result.getRootASTElement();
        if (ret != null) {
            setDiagnostic(new BasicDiagnostic());
            final Map<Object, Object> context = new HashMap<>();
            context.put(AbstractInjectableValidator.CURRENT_LANGUAGE_NAME, "org.efaps.eql.EQL");
            //getInvoker().getValidator().validate(ret, getDiagnostic(), context);
            final TreeIterator<EObject> iterator = ret.eAllContents();
            while (iterator.hasNext()) {
                final EObject nextObj = iterator.next();
                if (nextObj != null) {
              //      getInvoker().getValidator().validate(nextObj, getDiagnostic(), context);
                }
            }
        }
        return ret;
    }

    /**
     * Getter method for the instance variable {@link #parser}.
     *
     * @return value of instance variable {@link #parser}
     */
    public EQLParser getParser()
    {
        return getInvoker().getParser();
    }

    /**
     * Getter method for the instance variable {@link #validator}.
     *
     * @return value of instance variable {@link #validator}
     */
    public EQLJavaValidator getValidator()
    {
        return getInvoker().getValidator();
    }

    /**
     * Getter method for the instance variable {@link #diagnostic}.
     *
     * @return value of instance variable {@link #diagnostic}
     */
    public BasicDiagnostic getDiagnostic()
    {
        return this.diagnostic;
    }

    /**
     * Setter method for instance variable {@link #diagnostic}.
     *
     * @param _diagnostic value for instance variable {@link #diagnostic}
     */
    public void setDiagnostic(final BasicDiagnostic _diagnostic)
    {
        this.diagnostic = _diagnostic;
    }

    /**
     * Getter method for the instance variable {@link #invoker}.
     *
     * @return value of instance variable {@link #invoker}
     */
    public EQLInvoker getInvoker()
    {
        return this.invoker;
    }

    /**
     * Setter method for instance variable {@link #invoker}.
     *
     * @param _invoker value for instance variable {@link #invoker}
     */
    public void setInvoker(final EQLInvoker _invoker)
    {
        this.invoker = _invoker;
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
