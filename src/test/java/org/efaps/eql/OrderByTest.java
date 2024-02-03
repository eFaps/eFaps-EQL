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

import java.util.ArrayList;
import java.util.List;

import org.efaps.eql.eQL.OneOrder;
import org.efaps.eql.eQL.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public class OrderByTest
    extends AbstractTest
{

    @Test()
    public void oneNumericKey()
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] order by 2");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(0).getKey(), "2");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(0).isDesc(), false);
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test()
    public void twoNumericKey()
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] order by 2, 5");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(0).getKey(), "2");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(0).isDesc(), false);
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(1).getKey(), "5");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(1).isDesc(), false);
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test()
    public void manyNumericKey()
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] order by 2, 4, 7");
        final List<String> keys = new ArrayList<>();
        final List<Boolean> desc = new ArrayList<>();
        for (final OneOrder part : stmt.getQueryPart().getOrderPart().getOneOrder()) {
            keys.add(part.getKey());
            desc.add(part.isDesc());
        }

        Assert.assertEquals(new String[] { "2", "4", "7" }, keys.toArray());
        Assert.assertEquals(new Boolean[] { false, false, false }, desc.toArray());
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test()
    public void oneNumericKeyAsc()
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] order by 2 asc");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(0).getKey(), "2");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(0).isDesc(), false);
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test()
    public void oneNumericKeyDesc()
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] order by 2 desc");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(0).getKey(), "2");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(0).isDesc(), true);
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test()
    public void oneAlphanumericKey()
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] order by demo");
        Assert.assertEquals(stmt.getQueryPart().getOrderPart().getOneOrder().get(0).getKey(), "demo");
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }
}
