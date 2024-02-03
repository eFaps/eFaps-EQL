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

import java.util.LinkedHashMap;
import java.util.Map;

import org.efaps.eql.stmt.parts.select.AbstractSelect;
import org.efaps.eql.stmt.parts.select.SimpleSelect;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 */
public abstract class AbstractSelectStmtPart
    extends AbstractQueryStmtPart
    implements ISelectStmtPart
{

    /** The alias2 selects. */
    private final Map<String, AbstractSelect> alias2Selects = new LinkedHashMap<>();

    /** The sort key2desc. */
    private final Map<String, Boolean> sortKey2desc = new LinkedHashMap<>();

    @Override
    public void addSelect(final String _select,
                          final String _alias)
        throws Exception
    {
        String alias;
        if (_alias == null) {
            alias = Integer.valueOf(getAlias2Selects().size() + 1).toString();
        } else {
            alias = _alias;
        }
        getAlias2Selects().put(alias, new SimpleSelect(_select));
    }

    @Override
    public Map<String, AbstractSelect> getAlias2Selects()
        throws Exception
    {
        return this.alias2Selects;
    }

    @Override
    public Map<String, Boolean> getSortKey2desc()
        throws Exception
    {
        return this.sortKey2desc;
    }

    @Override
    public void addOrder(final String _key,
                         final Boolean _desc)
                             throws Exception
    {
        getSortKey2desc().put(_key, _desc);
    }
}
