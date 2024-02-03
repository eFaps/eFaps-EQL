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

import org.efaps.eql.eQL.Comparison;
import org.efaps.eql.eQL.OneUpdate;
import org.efaps.eql.eQL.Statement;
import org.efaps.eql.eQL.UpdatePart;
import org.efaps.eql.eQL.WherePart;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public class UpdateTest
    extends AbstractTest
{

    @Test(description = "update 124.879")
    public void update()
        throws Exception
    {
        final Statement stmt = getStatement("update 124.879");
        final UpdatePart update = stmt.getUpdatePart();
        Assert.assertEquals(update.getOid(), "124.879");
        Assert.assertEquals(getSyntaxErrors().toArray(), new String[] { "mismatched input '<EOF>' expecting 'set'" });
    }

    @Test(description = "update 124.879 set ATTR=NUM")
    public void setAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("update 124.879 set Code=22");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(update.getOid(), "124.879");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "22" });
    }

    @Test(description = "update obj 124.879 set ATTR=NUM")
    public void objSetAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("update obj 124.879 set Code=22");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(update.getOid(), "124.879");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "22" });
    }

    @Test(description = "update object 124.879 set ATTR=NUM")
    public void objectSetAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("update object 124.879 set Code=22");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(update.getOid(), "124.879");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "22" });
    }

    @Test(description = "update 124.879 set ATTR=NUM")
    public void setManyAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("update 124.879 set Code=22, Name=234234,Level=3453");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(update.getOid(), "124.879");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code", "Name", "Level" });
        Assert.assertEquals(values.toArray(), new String[] { "22", "234234", "3453" });
    }

    @Test(description = "update 124.879 set ATTR=STR")
    public void setAttrStr()
        throws Exception
    {
        final Statement stmt = getStatement("update 124.879 set Code=\"asdasd2\"");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(update.getOid(), "124.879");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "asdasd2" });
    }

    @Test(description = "update 124.879 set ATTR=Str, ATRR=STR2")
    public void setManyAttrStr()
        throws Exception
    {
        final Statement stmt = getStatement("update 124.879 set Code=\"asdsd\", Name=\"asdaddds\",Level=\"welt\"");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(update.getOid(), "124.879");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code", "Name", "Level" });
        Assert.assertEquals(values.toArray(), new String[] { "asdsd", "asdaddds", "welt" });
    }

    @Test(description = "update LIST set ATTR=NUM")
    public void setListAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("update list (124.879) set Code=22");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(update.getOids().toArray(), new String[] { "124.879" });
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "22" });
    }

    @Test(description = "update LIST set ATTR=NUM")
    public void setListManyAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("update list (124.879, 546.234, 646.77) set Code=22");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(update.getOids().toArray(), new String[] { "124.879", "546.234", "646.77" });
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "22" });
    }

    @Test(description = "update QUERY set ATTR=NUM")
    public void querySetAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("update query type Sales_Invoice set Code=22");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }
        Assert.assertEquals(update.getQueryPart().getTypes().toArray(), new String[] { "Sales_Invoice" });
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "22" });
    }

    @Test(description = "update QUERY WHERE set ATTR=NUM")
    public void queryWhereSetAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("update query type Sales_Invoice where Name == 3 set Code=22");
        final UpdatePart update = stmt.getUpdatePart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }
        Assert.assertEquals(update.getQueryPart().getTypes().toArray(), new String[] { "Sales_Invoice" });
        Assert.assertEquals(update.getQueryPart().getWherePart().getWheres().get(0).getAttribute(), "Name");
        Assert.assertEquals(update.getQueryPart().getWherePart().getWheres().get(0).getComparison(), Comparison.EQUAL);
        Assert.assertEquals(update.getQueryPart().getWherePart().getWheres().get(0).getValue(), "3");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "22" });
    }


    @Test(description = "update QUERY WHERE set ATTR=NUM")
    public void eqLessGreaterUnequalLike()
        throws Exception
    {
        final Statement stmt = getStatement("update query type Sales_Invoice where DocumentLink == 4 and Description < 567 and House like \"%Blaues Hause\" and HouseNumber > 459 set Code=22");
        final UpdatePart update = stmt.getUpdatePart();
        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        final WherePart where = update.getQueryPart().getWherePart();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }
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
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "22" });
    }

    @Test(description = "update 5710.4137 set Name = \"001-064056\"")
    public void setAttributeWithHyphen()
        throws Exception
    {
        final Statement stmt = getStatement("update 5710.4137 set Name = \"001-064056\"");
        final UpdatePart update = stmt.getUpdatePart();
        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : update.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }
        Assert.assertEquals(attributes.toArray(), new String[] { "Name" });
        Assert.assertEquals(values.toArray(), new String[] { "001-064056" });
    }
}
