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

import org.efaps.eql.eQL.Comparison;


/**
 * The Class AbstractWhere.
 *
 * @author The eFaps Team
 */
public abstract class AbstractWhere
{

    /** The comparison. */
    private Comparison comparison;

    /**
     * Getter method for the instance variable {@link #comparison}.
     *
     * @return value of instance variable {@link #comparison}
     */
    public Comparison getComparison()
    {
        return this.comparison;
    }

    /**
     * Setter method for instance variable {@link #comparison}.
     *
     * @param _comparison value for instance variable {@link #comparison}
     * @return the where
     */
    public AbstractWhere setComparison(final Comparison _comparison)
    {
        this.comparison = _comparison;
        return this;
    }
}
