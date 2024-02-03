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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class AbstractValueWhere.
 *
 * @author The eFaps Team
 */
public abstract class AbstractValueWhere
    extends AbstractWhere
{

    /** The values. */
    private final List<String> values = new ArrayList<>();;

    /**
     * Getter method for the instance variable {@link #value}.
     *
     * @return value of instance variable {@link #value}
     */
    public List<String> getValues()
    {
        return this.values;
    }

    /**
     * Setter method for instance variable {@link #value}.
     *
     * @param _value value for instance variable {@link #value}
     * @return the where
     */
    public AbstractValueWhere addValue(final String... _value)
    {
        this.values.addAll(Arrays.asList(_value));
        return this;
    }
}
