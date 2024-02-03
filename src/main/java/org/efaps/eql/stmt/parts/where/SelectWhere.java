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

/**
 * The Class SelectWhere.
 *
 * @author The eFaps Team
 */
public class SelectWhere
    extends AbstractValueWhere
{

    /** The select. */
    private String select;

    /**
     * Getter method for the instance variable {@link #select}.
     *
     * @return value of instance variable {@link #select}
     */
    public String getSelect()
    {
        return this.select;
    }

    /**
     * Setter method for instance variable {@link #select}.
     *
     * @param _select value for instance variable {@link #select}
     * @return the where
     */
    public SelectWhere setSelect(final String _select)
    {
        this.select = _select;
        return this;
    }
}
