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
import org.efaps.eql.eQL.QueryPart;
import org.efaps.eql.eQL.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public class QueryTest
    extends AbstractTest
{

    @Test()
    public void oneType()
    {
        final Statement stmt = getStatement("query type Sales_Invoice");
        final QueryPart query = stmt.getQueryPart();
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(getSyntaxErrors().toArray(), new String[] { "mismatched input '<EOF>' expecting 'select'" });
    }

    @Test()
    public void twoTypes()
    {
        final Statement stmt = getStatement("query type Sales_Invoice, Contacts_Contact");
        final QueryPart query = stmt.getQueryPart();
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(query.getTypes().get(1), "Contacts_Contact");
        Assert.assertEquals(getSyntaxErrors().toArray(), new String[] { "mismatched input '<EOF>' expecting 'select'" });
    }

    @Test()
    public void manyTypes()
    {
        final Statement stmt = getStatement("query type Sales_Invoice, Contacts_Contact, HumanResource_Employee");
        final QueryPart query = stmt.getQueryPart();
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(query.getTypes().get(1), "Contacts_Contact");
        Assert.assertEquals(query.getTypes().get(2), "HumanResource_Employee");
        Assert.assertEquals(getSyntaxErrors().toArray(), new String[] { "mismatched input '<EOF>' expecting 'select'" });
    }

    @Test(description = "Type contains a number in the name")
    public void typeWithNumberInName()
        throws Exception
    {
        final Statement stmt = getStatement("query type Sales_Invoice2DeliveryNote select linkto[ToLink].attribute[Name] as name;");
        final QueryPart query = stmt.getQueryPart();
        final List<String> selects = new ArrayList<>();

        for (final OneSelect part : query.getSelectPart().getSelects()) {
            selects.add(part.getSelect());
        }
        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice2DeliveryNote");
        Assert.assertEquals(new String[] { "linkto[ToLink].attribute[Name]" }, selects.toArray());
    }

    @Test(description = "query with a limit")
    public void limit()
    {
        final Statement stmt = getStatement("query type Sales_Invoice, Contacts_Contact, HumanResource_Employee limit 10");
        final QueryPart query = stmt.getQueryPart();

        Assert.assertEquals(query.getTypes().get(0), "Sales_Invoice");
        Assert.assertEquals(query.getTypes().get(1), "Contacts_Contact");
        Assert.assertEquals(query.getTypes().get(2), "HumanResource_Employee");
        Assert.assertEquals(query.getLimitPart().getValue(), "10");
        Assert.assertEquals(getSyntaxErrors().toArray(), new String[] { "mismatched input '<EOF>' expecting 'select'" });
    }

    @Test(description = "query with UUIDs for the type")
    public void typeUUID()
    {
        final Statement stmt = getStatement("query type 0029a77d-65ca-4564-968c-2ef6adf72033, Contacts_Contact, ce71ffa1-98f2-49b4-b892-246cd407b520, afc188bb-96a6-40ac-a48b-cfba985474c5");
        final QueryPart query = stmt.getQueryPart();

        Assert.assertEquals(query.getTypes().get(0), "0029a77d-65ca-4564-968c-2ef6adf72033");
        Assert.assertEquals(query.getTypes().get(1), "Contacts_Contact");
        Assert.assertEquals(query.getTypes().get(2), "ce71ffa1-98f2-49b4-b892-246cd407b520");
        Assert.assertEquals(query.getTypes().get(3), "afc188bb-96a6-40ac-a48b-cfba985474c5");
        Assert.assertEquals(getSyntaxErrors().toArray(), new String[] { "mismatched input '<EOF>' expecting 'select'" });
    }
}
