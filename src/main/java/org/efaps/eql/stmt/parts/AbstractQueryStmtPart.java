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
 * TODO comment!
 *
 * @author The eFaps Team
 */
public abstract class AbstractQueryStmtPart
    extends AbstractQueryPart
    implements IQueryStmtPart
{
    /** The limit. */
    private int limit = -1;

    /**
     * Getter method for the instance variable {@link #limit}.
     *
     * @return value of instance variable {@link #limit}
     */
    public int getLimit()
    {
        return this.limit;
    }

    @Override
    public void setLimit(final String _limit)
        throws Exception
    {
        this.limit = Integer.parseInt(_limit);
    }
}
