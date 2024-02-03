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

import org.efaps.eql.eQL.OneSelect;
import org.efaps.eql.eQL.OrderPart;
import org.efaps.eql.eQL.PrintPart;
import org.efaps.eql.eQL.SelectPart;
import org.efaps.eql.eQL.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public class PrintTest
    extends AbstractTest
{

    @Test()
    public void simplePrint()
    {
        final Statement stmt = getStatement("print 12312.2342");
        final PrintPart print = stmt.getPrintPart();
        Assert.assertEquals(print.getOid(), "12312.2342");
    }

    @Test
    public void printWithSelect()
    {
        final Statement stmt = getStatement("print 123.456 select attribute[Name]");
        final PrintPart print = stmt.getPrintPart();
        final SelectPart select = print.getSelectPart();
        Assert.assertEquals(print.getOid(), "123.456");
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
    }

    @Test
    public void printObjWithSelect()
    {
        final Statement stmt = getStatement("print obj 123.456 select attribute[Name]");
        final PrintPart print = stmt.getPrintPart();
        final SelectPart select = print.getSelectPart();
        Assert.assertEquals(print.getOid(), "123.456");
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
    }

    @Test
    public void printObjectWithSelect()
    {
        final Statement stmt = getStatement("print object 123.456 select attribute[Name]");
        final PrintPart print = stmt.getPrintPart();
        final SelectPart select = print.getSelectPart();
        Assert.assertEquals(print.getOid(), "123.456");
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
    }

    @Test
    public void printWithMultipleSelects()

    {
        final Statement stmt = getStatement("print 234123.456 select type.label as Type, attribute[Name] as Name, linkto[Object].attribute[Code] as code");
        final PrintPart print = stmt.getPrintPart();
        final SelectPart select = print.getSelectPart();
        final List<String> selects = new ArrayList<>();
        final List<String> alias = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            selects.add(part.getSelect());
            alias.add(part.getAlias());
        }
        Assert.assertEquals(print.getOid(), "234123.456");

        Assert.assertEquals(new String[] { "type.label", "attribute[Name]", "linkto[Object].attribute[Code]" },
                        selects.toArray());
        Assert.assertEquals(new String[] { "Type", "Name", "code" }, alias.toArray());
    }

    @Test
    public void printListWithSelect()
    {
        final Statement stmt = getStatement("print list (124.879 select attribute[Name]");
        final PrintPart print = stmt.getPrintPart();
        final SelectPart select = print.getSelectPart();
        Assert.assertEquals(print.getOids().toArray(), new String[] { "124.879" });
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
    }

    @Test
    public void printListManyWithSelect()
    {
        final Statement stmt = getStatement("print list (124.879, 546.234, 646.77) select attribute[Name]");
        final PrintPart print = stmt.getPrintPart();
        final SelectPart select = print.getSelectPart();
        Assert.assertEquals(print.getOids().toArray(), new String[] { "124.879", "546.234", "646.77" });
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
    }

    @Test
    public void printListManyWithSelectOrder()
    {
        final Statement stmt = getStatement("print list (124.879, 546.234, 646.77) select attribute[Name] as name order by name");
        final PrintPart print = stmt.getPrintPart();
        final SelectPart select = print.getSelectPart();
        final OrderPart order = print.getOrderPart();
        Assert.assertEquals(print.getOids().toArray(), new String[] { "124.879", "546.234", "646.77" });
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
        Assert.assertEquals(order.getOneOrder().get(0).getKey(), "name");
        Assert.assertEquals(order.getOneOrder().get(0).isDesc(), false);
    }
}
