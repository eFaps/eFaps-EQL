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
 * The Class AbstractNestedQueryStmtPart.
 *
 * @author The eFaps Team
 */
public abstract class AbstractNestedQueryStmtPart
    extends AbstractQueryPart
    implements INestedQueryStmtPart
{

    /** The select. */
    private String select = "attribute[ID]";

    /**
     * Gets the select.
     *
     * @return the select
     */
    public String getSelect()
    {
        return this.select;
    }

    /**
     * @param _select Select to be added to the Statement
     * @param _alias alias for the related select
     * @throws Exception on error
     */
    @Override
    public void setSelect(final String _select)
        throws Exception
    {
        this.select = _select;
    }
}
