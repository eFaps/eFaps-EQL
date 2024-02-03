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
package org.efaps.eql.stmt.parts.where;

import org.efaps.eql.stmt.parts.INestedQueryStmtPart;

/**
 * The Class AbstractQueryWhere.
 *
 * @author The eFaps Team
 */
public abstract class AbstractQueryWhere
    extends AbstractWhere
{

    /** The nested query. */
    private INestedQueryStmtPart nestedQuery;

    /**
     * Getter method for the instance variable {@link #nestedQuery}.
     *
     * @return value of instance variable {@link #nestedQuery}
     */
    public INestedQueryStmtPart getQuery()
    {
        return this.nestedQuery;
    }

    /**
     * Sets the query.
     *
     * @param _nestedQueryStmtPart the _nested query stmt part
     * @return the attr query where
     */
    public AbstractQueryWhere setQuery(final INestedQueryStmtPart _nestedQueryStmtPart)
    {
        this.nestedQuery = _nestedQueryStmtPart;
        return this;
    }
}
