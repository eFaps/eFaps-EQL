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

/**
 * The Interface ISubQueryStmtPart.
 *
 * @author The eFaps Team
 */
public interface IQueryPart
{

    /**
     * Adds the type.
     *
     * @param _type Type to be added to the Statement
     * @throws Exception the exception
     */
    void addType(final String _type)
        throws Exception;

    /**
     * Adds the where attr eq.
     *
     * @param _attr Select to be added to the Statement
     * @param _value alias for the related select
     * @throws Exception the exception
     */
    void addWhereAttrEq(final String _attr,
                        final String _value)
        throws Exception;

    /**
     * Adds the where attr not eq.
     *
     * @param _attr Select to be added to the Statement
     * @param _value alias for the related select
     * @throws Exception the exception
     */
    void addWhereAttrNotEq(final String _attr,
                           final String _value)
        throws Exception;

    /**
     * Adds the where attr greater.
     *
     * @param _attr the _attr
     * @param _value the _value
     * @throws Exception the exception
     */
    void addWhereAttrGreater(final String _attr,
                             final String _value)
        throws Exception;

    /**
     * Adds the where attr less.
     *
     * @param _attr the _attr
     * @param _value the _value
     * @throws Exception the exception
     */
    void addWhereAttrLess(final String _attr,
                          final String _value)
        throws Exception;

    /**
     * Adds the where attr like.
     *
     * @param _attr the _attr
     * @param _value the _value
     * @throws Exception the exception
     */
    void addWhereAttrLike(final String _attr,
                          final String _value)
        throws Exception;

    /**
     * Adds the where attr in.
     *
     * @param _attr Name of the Attribute
     * @param _values list of values
     * @throws Exception the exception
     */
    void addWhereAttrIn(final String _attr,
                        final String... _values)
        throws Exception;

    /**
     * Adds the where attr in.
     *
     * @param _attr Name of the Attribute
     * @param _values list of values
     * @throws Exception the exception
     */
    void addWhereAttrNotIn(final String _attr,
                        final String... _values)
        throws Exception;

    /**
     * Adds the where attr in.
     *
     * @param _attr the _attr
     * @param _nestedQueryStmtPart the _nested query stmt part
     * @throws Exception the exception
     */
    void addWhereAttrIn(final String _attr,
                        final INestedQueryStmtPart _nestedQueryStmtPart)
        throws Exception;

    /**
     * Adds the where attr in.
     *
     * @param _attr the _attr
     * @param _nestedQueryStmtPart the _nested query stmt part
     * @throws Exception the exception
     */
    void addWhereAttrNotIn(final String _attr,
                        final INestedQueryStmtPart _nestedQueryStmtPart)
        throws Exception;

    /**
     * Adds the where select eq.
     *
     * @param _select the _select
     * @param _value the _value
     * @throws Exception the exception
     */
    void addWhereSelectEq(final String _select,
                          final String _value)
        throws Exception;

    /**
     * Adds the where select like.
     *
     * @param _select the _select
     * @param _value the _value
     * @throws Exception the exception
     */
    void addWhereSelectLike(final String _select,
                            final String _value)
        throws Exception;

    /**
     * Adds the where select greater.
     *
     * @param _select the _select
     * @param _value the _value
     * @throws Exception the exception
     */
    void addWhereSelectGreater(final String _select,
                               final String _value)
        throws Exception;

    /**
     * Adds the where select less.
     *
     * @param _select the _select
     * @param _value the _value
     * @throws Exception the exception
     */
    void addWhereSelectLess(final String _select,
                            final String _value)
        throws Exception;

    /**
     * Adds the where attr in.
     *
     * @param the _select
     * @param _nestedQueryStmtPart the _nested query stmt part
     * @throws Exception the exception
     */
    void addWhereSelectIn(final String _select,
                          final INestedQueryStmtPart _nestedQueryStmtPart)
        throws Exception;

    /**
     * Adds the where attr in.
     *
     * @param the _select
     * @param _nestedQueryStmtPart the _nested query stmt part
     * @throws Exception the exception
     */
    void addWhereSelectNotIn(final String _select,
                          final INestedQueryStmtPart _nestedQueryStmtPart)
        throws Exception;

    /**
     * Adds the where attr in.
     *
     * @param _select the _select
     * @param _values list of values
     * @throws Exception the exception
     */
    void addWhereSelectIn(final String _select,
                          final String... _values)
        throws Exception;

    /**
     * Adds the where attr in.
     *
     * @param _select the _select
     * @param _values list of values
     * @throws Exception the exception
     */
    void addWhereSelectNotIn(final String _select,
                             final String... _values)
        throws Exception;
}
