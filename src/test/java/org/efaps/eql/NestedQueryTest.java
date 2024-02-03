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
package org.efaps.eql;

import org.eclipse.emf.common.util.EList;
import org.efaps.eql.eQL.NestedQueryPart;
import org.efaps.eql.eQL.OneWhere;
import org.efaps.eql.eQL.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NestedQueryTest
    extends AbstractTest
{

    @Test(description = "print query type Sales_Invoice select one Attribute")
    public void subQuery()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_InvoicePosition where Invoice in "
                        + "( query type Sales_Invoice select attribute[ID] )");
        final EList<OneWhere> wheres = stmt.getQueryPart().getWherePart().getWheres();
        final NestedQueryPart subQuery = wheres.get(0).getNestedQueryPart();

        Assert.assertEquals(subQuery.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(subQuery.getSelectPart().getSelects().get(0).getSelect(), "attribute[ID]");
    }


    @Test(description = "print query type Sales_Invoice select one Attribute")
    public void subQueryWithWhere()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_InvoicePosition where Invoice in "
                        + "( query type Sales_Invoice where attribute[ID] == 15 select attribute[ID] )");
        final EList<OneWhere> wheres = stmt.getQueryPart().getWherePart().getWheres();
        final NestedQueryPart subQuery = wheres.get(0).getNestedQueryPart();

        Assert.assertEquals(subQuery.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(subQuery.getSelectPart().getSelects().get(0).getSelect(), "attribute[ID]");
    }

    @Test(description = "print query type Sales_Invoice select one Attribute")
    public void subQueryWithoutSelect()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_InvoicePosition where Invoice in "
                        + "( query type Sales_Invoice )");
        final EList<OneWhere> wheres = stmt.getQueryPart().getWherePart().getWheres();
        final NestedQueryPart subQuery = wheres.get(0).getNestedQueryPart();

        Assert.assertEquals(subQuery.getTypes().get(0), "Sales_Invoice");
        Assert.assertTrue(subQuery.getSelectPart() == null);
    }


}
