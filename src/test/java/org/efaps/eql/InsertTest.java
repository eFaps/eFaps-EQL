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

import org.efaps.eql.eQL.InsertPart;
import org.efaps.eql.eQL.OneUpdate;
import org.efaps.eql.eQL.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public class InsertTest
    extends AbstractTest
{

    @Test(description = "insert type ")
    public void insert()
        throws Exception
    {
        final Statement stmt = getStatement("insert type Sales_Invoice");
        final InsertPart insert = stmt.getInsertPart();
        Assert.assertEquals(insert.getType(), "Sales_Invoice");
        Assert.assertEquals(getSyntaxErrors().toArray(), new String[] { "mismatched input '<EOF>' expecting 'set'" });
    }

    @Test(description = "insert type TYPE set ATTR=NUM")
    public void setAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("insert type Sales_Invoice set Code=22");
        final InsertPart insert = stmt.getInsertPart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : insert.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(insert.getType(), "Sales_Invoice");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "22" });
    }


    @Test(description = "insert type TYPE  set ATTR=NUM,ATTR=NUM,ATTR=NUM")
    public void setManyAttrNum()
        throws Exception
    {
        final Statement stmt = getStatement("insert type Sales_Invoice  set Code=22, Name=234234,Level=3453");
        final InsertPart insert = stmt.getInsertPart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : insert.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(insert.getType(), "Sales_Invoice");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code", "Name", "Level" });
        Assert.assertEquals(values.toArray(), new String[] { "22", "234234", "3453" });
    }

    @Test(description = "insert type TYPE set ATTR=STR")
    public void setAttrStr()
        throws Exception
    {
        final Statement stmt = getStatement("insert type Sales_Invoice  set Code=\"asdasd2\"");
        final InsertPart insert = stmt.getInsertPart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : insert.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(insert.getType(), "Sales_Invoice");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code" });
        Assert.assertEquals(values.toArray(), new String[] { "asdasd2" });
    }

    @Test(description = "insert type TYPE set ATTR=Str, ATRR=STR2")
    public void setManyAttrStr()
        throws Exception
    {
        final Statement stmt = getStatement("insert type Sales_Invoice set Code=\"asdsd\", Name=\"asdaddds\",Level=\"welt\"");
        final InsertPart insert = stmt.getInsertPart();

        final List<String> attributes = new ArrayList<>();
        final List<String> values = new ArrayList<>();
        for (final OneUpdate oneUpdate : insert.getUpdates()) {
            attributes.add(oneUpdate.getAttribute());
            values.add(oneUpdate.getValue());
        }

        Assert.assertEquals(insert.getType(), "Sales_Invoice");
        Assert.assertEquals(attributes.toArray(), new String[] { "Code", "Name", "Level" });
        Assert.assertEquals(values.toArray(), new String[] { "asdsd", "asdaddds", "welt" });
    }
}
