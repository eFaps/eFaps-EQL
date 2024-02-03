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

import java.util.List;
import java.util.Map;

import org.efaps.eql.stmt.IEQLStmt;
import org.efaps.eql.stmt.parts.select.AbstractSelect;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 */
public interface ISelectStmtPart
    extends IEQLStmt
{
    /**
     * @param _select Select to be added to the Statement
     * @param _alias alias for the related select
     * @throws Exception on error
     */
    void addSelect(final String _select,
                   final String _alias)
        throws Exception;

    /**
     * @param _select Select to be added to the Statement
     * @param _alias alias for the related select
     * @throws Exception on error
     */
    void addSelect(final String _className,
                   final List<String> _parameters,
                   final String _alias)
        throws Exception;

    /**
     * Get the Mapping between alias and selects.
     *
     * @return mapping
     * @throws Exception on error
     */
    Map<String, AbstractSelect> getAlias2Selects()
        throws Exception;

    /**
     * Get the Data.
     *
     * @return List of maps containing the data
     * @throws Exception on error
     */
    List<Map<String, Object>> getData()
        throws Exception;

    /**
     * @return map of alias/key to be ordered by with asc or desc
     * @throws Exception on error
     */
    Map<String, Boolean> getSortKey2desc()
        throws Exception;

    /**
     * @param _key  key to add to the order map
     * @param _desc desc or asc
     * @throws Exception on error
     */
    void addOrder(final String _key,
                  final Boolean _desc)
        throws Exception;

}
