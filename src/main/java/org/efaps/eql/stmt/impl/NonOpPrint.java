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
package org.efaps.eql.stmt.impl;

import java.util.List;
import java.util.Map;

import org.efaps.eql.stmt.AbstractPrintStmt;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public class NonOpPrint
    extends AbstractPrintStmt
{

    @Override
    public List<Map<String, Object>> getData()
    {
        // Non operational implementation
        return null;
    }
}
