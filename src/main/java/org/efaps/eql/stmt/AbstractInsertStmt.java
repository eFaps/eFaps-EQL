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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 */
public abstract class AbstractInsertStmt
    extends AbstractEQLStmt
    implements IInsertStmt
{

    /**
     * Tyep to be inserted.
     */
    private String type;

    /**
     * Mapping of Attribute to Value.
     */
    private final Map<String, String> attr2Value = new LinkedHashMap<>();

    /** The instance. */
    private String instance;

    @Override
    public String getInstance()
    {
        return this.instance;
    }

    /**
     * Setter method for instance variable {@link #instance}.
     *
     * @param _instance value for instance variable {@link #instance}
     */
    public void setInstance(final String _instance)
    {
        this.instance = _instance;
    }

    @Override
    public void setType(final String _type)
        throws Exception
    {
        this.type = _type;
    }

    @Override
    public void addAttribute(final String _attribute,
                             final String _value)
        throws Exception
    {
        this.attr2Value.put(_attribute, _value);
    }

    /**
     * Getter method for the instance variable {@link #attr2Value}.
     *
     * @return value of instance variable {@link #attr2Value}
     */
    public Map<String, String> getAttr2Value()
    {
        return this.attr2Value;
    }

    /**
     * Getter method for the instance variable {@link #type}.
     *
     * @return value of instance variable {@link #type}
     */
    public String getType()
    {
        return this.type;
    }
}
