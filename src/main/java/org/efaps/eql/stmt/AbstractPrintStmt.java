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

import java.util.List;

import org.efaps.eql.stmt.parts.AbstractSelectStmtPart;
import org.efaps.eql.stmt.parts.select.ExecSelect;


/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public abstract class AbstractPrintStmt
    extends AbstractSelectStmtPart
    implements IPrintStmt
{
    @Override
    public void addSelect(final String _className,
                          final List<String> _parameters,
                          final String _alias)
        throws Exception
    {
        String alias;
        if (_alias == null) {
            alias = Integer.valueOf(getAlias2Selects().size() + 1).toString();
        } else {
            alias = _alias;
        }
        getAlias2Selects().put(alias, new ExecSelect(_className, _parameters));
    }
}
