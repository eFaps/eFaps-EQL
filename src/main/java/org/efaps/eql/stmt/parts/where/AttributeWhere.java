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
 * The Class AttributeWhere.
 *
 * @author The eFaps Team
 */
public class AttributeWhere
    extends AbstractValueWhere
{

    /** The attribute. */
    private String attribute;

    /**
     * Getter method for the instance variable {@link #attribute}.
     *
     * @return value of instance variable {@link #attribute}
     */
    public String getAttribute()
    {
        return this.attribute;
    }

    /**
     * Setter method for instance variable {@link #attribute}.
     *
     * @param _attribute value for instance variable {@link #attribute}
     * @return the where
     */
    public AttributeWhere setAttribute(final String _attribute)
    {
        this.attribute = _attribute;
        return this;
    }
}
