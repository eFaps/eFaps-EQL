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
package org.efaps.eql.stmt;

import org.efaps.eql.eQL.CINature;

/**
 * The Class AbstractCIStmt.
 *
 * @author The eFaps Team
 */
public abstract class AbstractCIStmt
    extends AbstractEQLStmt
    implements ICIStmt
{

    /** The ci nature. */
    private CINature ciNature;

    /** The ci. */
    private String ci;

    @Override
    public String getCI()
    {
        return this.ci;
    }

    /**
     * Sets the ci.
     *
     * @param _ci the new ci
     */
    public void setCI(final String _ci)
    {
        this.ci = _ci;
    }

    @Override
    public CINature getCINature()
    {
        return this.ciNature;
    }

    /**
     * Sets the CI nature.
     *
     * @param _ciNature the new CI nature
     */
    public void setCINature(final CINature _ciNature)
    {
        this.ciNature = _ciNature;
    }
}
