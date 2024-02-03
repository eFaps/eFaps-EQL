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
package org.efaps.eql.stmt;

import org.efaps.eql.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public class InvokeTest
    extends AbstractTest
{

    @Test
    public void printOnly()
        throws Exception
    {
        final PrintStmt printStmt = (PrintStmt) getInvoker().invoke("print 12312.2342");
        Assert.assertEquals(printStmt.getInstances().toArray(), new String[] { "12312.2342" });
    }

    @Test
    public void printWithSelect()
        throws Exception
    {
        final PrintStmt printStmt = (PrintStmt) getInvoker().invoke("print 12312.2342 select attribute[Name] as name");
        Assert.assertEquals(printStmt.getInstances().toArray(), new String[] { "12312.2342" });
        Assert.assertEquals(printStmt.getAlias2Selects().keySet().toArray(), new String[] { "name" });
        Assert.assertEquals(printStmt.getAlias2Selects().values().iterator().next().getSelect(), "attribute[Name]");
    }

    @Test
    public void queryWithSelect()
        throws Exception
    {
        final PrintStmt queryStmt = (PrintStmt) getInvoker().invoke(
                        "query type Sales_Invoice select attribute[Name] as name");
        Assert.assertEquals(queryStmt.getAlias2Selects().keySet().toArray(), new String[] { "name" });
        Assert.assertEquals(queryStmt.getAlias2Selects().values().iterator().next().getSelect(), "attribute[Name]" );
        Assert.assertEquals(queryStmt.getTypes().toArray(), new String[] { "Sales_Invoice" });
    }

    @Test
    public void queryWithWhereAndSelect()
        throws Exception
    {
        final PrintStmt queryStmt = (PrintStmt) getInvoker().invoke(
                        "query type Sales_Invoice select attribute[Name] as name");
        Assert.assertEquals(queryStmt.getAlias2Selects().keySet().toArray(), new String[] { "name" });
        Assert.assertEquals(queryStmt.getAlias2Selects().values().iterator().next().getSelect(), "attribute[Name]" );
        Assert.assertEquals(queryStmt.getTypes().toArray(), new String[] { "Sales_Invoice" });
    }
}
