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
package org.efaps.eql.stmt.parts.select;

import java.util.List;

/**
 * The Class SimpleSelect.
 *
 * @author The eFaps Team
 */
public class ExecSelect
    extends AbstractSelect
{

    /** The parameters. */
    private final List<String> parameters;

    /**
     * Instantiates a new exec select.
     *
     * @param _className the _class name
     * @param _parameters the _parameters
     */
    public ExecSelect(final String _className,
                      final List<String> _parameters)
    {
        super(_className);
        this.parameters = _parameters;
    }

    /**
     * Gets the class name.
     *
     * @return the class name
     */
    public String getClassName()
    {
        return super.getSelect();
    }

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public List<String> getParameters()
    {
        return this.parameters;
    }

}
