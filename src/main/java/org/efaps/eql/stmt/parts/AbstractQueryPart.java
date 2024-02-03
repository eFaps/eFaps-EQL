/*
 * Copyright © 2003 - 2024 The eFaps Team (-)
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
 */
package org.efaps.eql.stmt.parts;

import java.util.ArrayList;
import java.util.List;

import org.efaps.eql.eQL.Comparison;
import org.efaps.eql.stmt.parts.where.AbstractWhere;
import org.efaps.eql.stmt.parts.where.AttrQueryWhere;
import org.efaps.eql.stmt.parts.where.AttributeWhere;
import org.efaps.eql.stmt.parts.where.SelectQueryWhere;
import org.efaps.eql.stmt.parts.where.SelectWhere;

/**
 * The Class AbstractQueryPart.
 *
 * @author The eFaps Team
 */
public abstract class AbstractQueryPart
    extends AbstractObjectPart
    implements IQueryPart
{

    /**
     * List of types for the Query.
     */
    private final List<String> types = new ArrayList<>();

    /** The wheres. */
    private final List<AbstractWhere> wheres = new ArrayList<>();

    @Override
    public void addType(final String _type)
        throws Exception
    {
        this.types.add(_type);
    }

    @Override
    public void addWhereAttrEq(final String _attr,
                               final String _value)
        throws Exception
    {
        this.wheres.add(new AttributeWhere().setAttribute(_attr).addValue(_value).setComparison(Comparison.EQUAL));
    }

    @Override
    public void addWhereAttrNotEq(final String _attr,
                                  final String _value)
        throws Exception
    {
        this.wheres.add(new AttributeWhere().setAttribute(_attr).addValue(_value).setComparison(Comparison.UNEQUAL));
    }

    @Override
    public void addWhereAttrGreater(final String _attr,
                                    final String _value)
        throws Exception
    {
        this.wheres.add(new AttributeWhere().setAttribute(_attr).addValue(_value).setComparison(Comparison.GREATER));
    }

    @Override
    public void addWhereAttrLess(final String _attr,
                                 final String _value)
        throws Exception
    {
        this.wheres.add(new AttributeWhere().setAttribute(_attr).addValue(_value).setComparison(Comparison.LESS));
    }

    @Override
    public void addWhereAttrLike(final String _attr,
                                 final String _value)
        throws Exception
    {
        this.wheres.add(new AttributeWhere().setAttribute(_attr).addValue(_value).setComparison(Comparison.LIKE));
    }

    @Override
    public void addWhereAttrIn(final String _attr,
                               final String... _values)
        throws Exception
    {
        this.wheres.add(new AttributeWhere().setAttribute(_attr).addValue(_values).setComparison(Comparison.IN));
    }

    @Override
    public void addWhereAttrNotIn(final String _attr,
                                  final String... _values)
        throws Exception
    {
        this.wheres.add(new AttributeWhere().setAttribute(_attr).addValue(_values).setComparison(Comparison.NOTIN));
    }

    @Override
    public void addWhereAttrIn(final String _attr,
                               final INestedQueryStmtPart _nestedQueryStmtPart)
        throws Exception
    {
        this.wheres.add(new AttrQueryWhere().setAttribute(_attr).setQuery(_nestedQueryStmtPart)
                        .setComparison(Comparison.IN));
    }

    @Override
    public void addWhereAttrNotIn(final String _attr,
                                  final INestedQueryStmtPart _nestedQueryStmtPart)
        throws Exception
    {
        this.wheres.add(new AttrQueryWhere().setAttribute(_attr).setQuery(_nestedQueryStmtPart)
                        .setComparison(Comparison.NOTIN));
    }

    @Override
    public void addWhereSelectEq(final String _select,
                                 final String _value)
        throws Exception
    {
        this.wheres.add(new SelectWhere().setSelect(_select).addValue(_value).setComparison(Comparison.EQUAL));
    }

    @Override
    public void addWhereSelectLike(final String _select,
                                   final String _value)
        throws Exception
    {
        this.wheres.add(new SelectWhere().setSelect(_select).addValue(_value).setComparison(Comparison.LIKE));
    }

    @Override
    public void addWhereSelectGreater(final String _select,
                                      final String _value)
        throws Exception
    {
        this.wheres.add(new SelectWhere().setSelect(_select).addValue(_value).setComparison(Comparison.GREATER));
    }

    @Override
    public void addWhereSelectLess(final String _select,
                                   final String _value)
        throws Exception
    {
        this.wheres.add(new SelectWhere().setSelect(_select).addValue(_value).setComparison(Comparison.LESS));
    }

    /**
     * Getter method for the instance variable {@link #types}.
     *
     * @return value of instance variable {@link #types}
     */
    public List<String> getTypes()
    {
        return this.types;
    }

    /**
     * Getter method for the instance variable {@link #wheres}.
     *
     * @return value of instance variable {@link #wheres}
     */
    public List<AbstractWhere> getWheres()
    {
        return this.wheres;
    }

    @Override
    public void addWhereSelectIn(final String _select,
                                 final INestedQueryStmtPart _nestedQueryStmtPart)
        throws Exception
    {
        this.wheres.add(new SelectQueryWhere().setSelect(_select).setQuery(_nestedQueryStmtPart)
                        .setComparison(Comparison.IN));
    }

    @Override
    public void addWhereSelectNotIn(final String _select,
                                    final INestedQueryStmtPart _nestedQueryStmtPart)
        throws Exception
    {
        this.wheres.add(new SelectQueryWhere().setSelect(_select).setQuery(_nestedQueryStmtPart)
                        .setComparison(Comparison.NOTIN));
    }

    @Override
    public void addWhereSelectIn(final String _select,
                                 final String... _values)
        throws Exception
    {
        this.wheres.add(new SelectWhere().setSelect(_select).addValue(_values).setComparison(Comparison.IN));
    }

    @Override
    public void addWhereSelectNotIn(final String _select,
                                    final String... _values)
        throws Exception
    {
        this.wheres.add(new SelectWhere().setSelect(_select).addValue(_values).setComparison(Comparison.NOTIN));
    }
}
