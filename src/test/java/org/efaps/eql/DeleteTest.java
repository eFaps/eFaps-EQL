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

import org.efaps.eql.eQL.Comparison;
import org.efaps.eql.eQL.DeletePart;
import org.efaps.eql.eQL.Statement;
import org.efaps.eql.eQL.WherePart;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * TODO comment!
 *
 * @author The eFaps Team
 */
public class DeleteTest
    extends AbstractTest
{
    @Test(description = "delete 124.879")
    public void delete()
        throws Exception
    {
        final Statement stmt = getStatement("delete 124.879");
        final DeletePart delete = stmt.getDeletePart();
        Assert.assertEquals(delete.getOid(), "124.879");
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test(description = "delete obj 124.879")
    public void deleteObj()
        throws Exception
    {
        final Statement stmt = getStatement("delete obj 124.879");
        final DeletePart delete = stmt.getDeletePart();
        Assert.assertEquals(delete.getOid(), "124.879");
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test(description = "delete object 124.879")
    public void deleteObject()
        throws Exception
    {
        final Statement stmt = getStatement("delete object 124.879");
        final DeletePart delete = stmt.getDeletePart();
        Assert.assertEquals(delete.getOid(), "124.879");
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test(description = "delete LIST")
    public void deleteListOne()
        throws Exception
    {
        final Statement stmt = getStatement("delete list (124.879)");
        final DeletePart delete = stmt.getDeletePart();
        Assert.assertEquals(delete.getOids().toArray(), new String[] { "124.879" });
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test(description = "delete LISTMANY")
    public void deleteListMany()
        throws Exception
    {
        final Statement stmt = getStatement("delete list (124.879, 123.456, 786.999)");
        final DeletePart delete = stmt.getDeletePart();
        Assert.assertEquals(delete.getOids().toArray(), new String[] { "124.879", "123.456", "786.999" });
        Assert.assertTrue(getSyntaxErrors().isEmpty());
    }

    @Test(description = "delete QUERY")
    public void deleteQuery()
        throws Exception
    {
        final Statement stmt = getStatement("delete query type Sales_Invoice");
        final DeletePart delete = stmt.getDeletePart();
        Assert.assertEquals(delete.getQueryPart().getTypes().toArray(), new String[] { "Sales_Invoice" });
    }

    @Test(description = "delete QUERY WHERE")
    public void deleteQueryWhere()
        throws Exception
    {
        final Statement stmt = getStatement("delete query type Sales_Invoice where Name == 3");
        final DeletePart delete = stmt.getDeletePart();

        Assert.assertEquals(delete.getQueryPart().getTypes().toArray(), new String[] { "Sales_Invoice" });
        Assert.assertEquals(delete.getQueryPart().getWherePart().getWheres().get(0).getAttribute(), "Name");
        Assert.assertEquals(delete.getQueryPart().getWherePart().getWheres().get(0).getComparison(), Comparison.EQUAL);
        Assert.assertEquals(delete.getQueryPart().getWherePart().getWheres().get(0).getValue(), "3");
    }

    @Test(description = "delete QUERY WHERE ")
    public void deleteQueryEqLessGreaterUnequalLike()
        throws Exception
    {
        final Statement stmt = getStatement("delete query type Sales_Invoice where DocumentLink == 4 and Description < 567 and House like \"%Blaues Hause\" and HouseNumber > 459");
        final DeletePart delete = stmt.getDeletePart();
        final WherePart where = delete.getQueryPart().getWherePart();
        Assert.assertEquals(delete.getQueryPart().getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(where.getWheres().get(0).getAttribute(), "DocumentLink");
        Assert.assertEquals(where.getWheres().get(0).getComparison(), Comparison.EQUAL);
        Assert.assertEquals(where.getWheres().get(0).getValue(), "4");
        Assert.assertEquals(where.getWheres().get(1).getAttribute(), "Description");
        Assert.assertEquals(where.getWheres().get(1).getComparison(), Comparison.LESS);
        Assert.assertEquals(where.getWheres().get(1).getValue(), "567");
        Assert.assertEquals(where.getWheres().get(2).getAttribute(), "House");
        Assert.assertEquals(where.getWheres().get(2).getComparison(), Comparison.LIKE);
        Assert.assertEquals(where.getWheres().get(2).getValue(), "%Blaues Hause");
        Assert.assertEquals(where.getWheres().get(3).getAttribute(), "HouseNumber");
        Assert.assertEquals(where.getWheres().get(3).getComparison(), Comparison.GREATER);
        Assert.assertEquals(where.getWheres().get(3).getValue(), "459");
    }
}
