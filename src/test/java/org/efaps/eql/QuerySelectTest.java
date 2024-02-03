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

import org.efaps.eql.eQL.QueryPart;
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
public class QuerySelectTest extends AbstractTest
{

    @Test()
    public void oneTypeOneAttribute()
    {
        final Statement stmt = getStatement("query type Sales_Invoice select attribute[Name]");
        final QueryPart query = stmt.getQueryPart();
        final SelectPart select = query.getSelectPart();
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
    }

    @Test
    public void printOneTypeOneAttribute()
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name]");
        final QueryPart query = stmt.getQueryPart();
        final SelectPart select = query.getSelectPart();
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
    }

    @Test
    public void twoTypesWithTwoeSelect()
    {
        final Statement stmt = getStatement("query type Sales_Invoice, Contacts_Contact select attribute[Name], linkto[DocLink].instance");
        final QueryPart query = stmt.getQueryPart();
        final SelectPart select = query.getSelectPart();
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(query.getTypes().get(1), "Contacts_Contact");
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
        Assert.assertEquals(select.getSelects().get(1).getSelect(), "linkto[DocLink].instance");
    }


    @Test
    public void oneTypeOneAttributeWithAlias()
    {
        final Statement stmt = getStatement("query type Sales_Invoice select attribute[Name] as Name");
        final QueryPart query = stmt.getQueryPart();
        final SelectPart select = query.getSelectPart();
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
        Assert.assertEquals(select.getSelects().get(0).getAlias(), "Name");
    }

    @Test
    public void oneTypeTwoAttributeWithAlias()
    {
        final Statement stmt = getStatement("query type Sales_Invoice select attribute[Name] as Name, linkto[DocumentLink].instance as instance");
        final QueryPart query = stmt.getQueryPart();
        final SelectPart select = query.getSelectPart();
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
        Assert.assertEquals(select.getSelects().get(0).getAlias(), "Name");
        Assert.assertEquals(select.getSelects().get(1).getSelect(), "linkto[DocumentLink].instance");
        Assert.assertEquals(select.getSelects().get(1).getAlias(), "instance");
    }

    @Test(description = "query with a limit")
    public void limit()
    {
        final Statement stmt = getStatement("query type Sales_Invoice, Contacts_Contact, HumanResource_Employee limit 10 select attribute[Name] as Name, linkto[DocumentLink].instance as instance");
        final QueryPart query = stmt.getQueryPart();
        final SelectPart select = query.getSelectPart();
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(query.getTypes().get(1), "Contacts_Contact");
        Assert.assertEquals(query.getTypes().get(2), "HumanResource_Employee");
        Assert.assertEquals(query.getLimitPart().getValue(), "10");
        Assert.assertEquals(select.getSelects().get(0).getSelect(), "attribute[Name]");
        Assert.assertEquals(select.getSelects().get(0).getAlias(), "Name");
        Assert.assertEquals(select.getSelects().get(1).getSelect(), "linkto[DocumentLink].instance");
        Assert.assertEquals(select.getSelects().get(1).getAlias(), "instance");
    }

}
