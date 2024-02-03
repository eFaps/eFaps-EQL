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
import org.efaps.eql.eQL.SelectPart;
import org.efaps.eql.eQL.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectTest
    extends AbstractTest
{

    @Test(description = "print query type Sales_Invoice select one Attribute")
    public void oneAttribute()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name]");
        final SelectPart select = stmt.getQueryPart().getSelectPart();
        Assert.assertEquals("attribute[Name]", select.getSelects().get(0).getSelect());
    }

    @Test(description = "print query type Sales_Invoice select one Attribute with Alias")
    public void oneAttributeWithAlias()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] as name");
        final SelectPart select = stmt.getQueryPart().getSelectPart();
        Assert.assertEquals("attribute[Name]", select.getSelects().get(0).getSelect());
        Assert.assertEquals("name", select.getSelects().get(0).getAlias());
    }

    @Test(description = "print query type Sales_Invoice select one Attribute with capitalized Alias")
    public void oneAttributeWithAliasCapitalize()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] as Name");
        final SelectPart select = stmt.getQueryPart().getSelectPart();
        Assert.assertEquals("attribute[Name]", select.getSelects().get(0).getSelect());
        Assert.assertEquals("Name", select.getSelects().get(0).getAlias());
    }

    @Test(description = "print query type Sales_Invoice select one type.label with Alias")
    public void oneTypeLabel()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select type.label");
        final SelectPart select = stmt.getQueryPart().getSelectPart();
        Assert.assertEquals("type.label", select.getSelects().get(0).getSelect());
    }

    @Test(description = "print query type Sales_Invoice select two type.label with Alias")
    public void twoTypeLabel()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select status.label, type.name");
        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            selects.add(part.getSelect());
        }
        Assert.assertEquals(new String[] { "status.label", "type.name" }, selects.toArray());
    }

    @Test(description = "many different selects without Alias")
    public void manySelects()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select type.label, attribute[Name], linkto[Object].attribute[Code]");
        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            selects.add(part.getSelect());
        }
        Assert.assertEquals(new String[] { "type.label", "attribute[Name]", "linkto[Object].attribute[Code]" },
                        selects.toArray());
    }

    @Test(description = "many different selects with Alias")
    public void manySelectsWithAlias()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select type.label as Type, attribute[Name] as Name, linkto[Object].attribute[Code] as code");
        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();
        final List<String> alias = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            selects.add(part.getSelect());
            alias.add(part.getAlias());
        }
        Assert.assertEquals(new String[] { "type.label", "attribute[Name]", "linkto[Object].attribute[Code]" },
                        selects.toArray());
        Assert.assertEquals(new String[] { "Type", "Name", "code" }, alias.toArray());
    }

    @Test(description = "Format select")
    public void formatSelects()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Date].format[YYYY-MM-dd], linkto[DeliveryNote].attribute[Date].format[YYYY/MM/dd]");
        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            selects.add(part.getSelect());
        }
        Assert.assertEquals(new String[] { "attribute[Date].format[YYYY-MM-dd]", "linkto[DeliveryNote].attribute[Date].format[YYYY/MM/dd]" },
                        selects.toArray());
    }

    @Test(description = "type.label select")
    public void typeLabelSelects()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select type.label as TypeName");
        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();
        final List<String> alias = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            selects.add(part.getSelect());
            alias.add(part.getAlias());
        }
        Assert.assertEquals(selects.toArray(), new String[] { "type.label" });
        Assert.assertEquals(alias.toArray(), new String[] { "TypeName" });
    }

    @Test(description = "type.label select")
    public void typeLabelSelectsInvalidMapping()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select type.label as type");

        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();
        final List<String> alias = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            selects.add(part.getSelect());
            alias.add(part.getAlias());
        }
        Assert.assertEquals(selects.toArray(), new String[] { "type.label" });
        Assert.assertEquals(getSyntaxErrors().toArray(), new String[] { "mismatched input 'type' expecting RULE_ALIAS" });
    }

    /**
     * Exec Select.
     *
     * @throws Exception the exception
     */
    @Test(description = "exec select")
    public void execSelect1()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] as name, exec org.efaps.Demo as Two");

        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();
        final List<String> alias = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            if (part.getSelect() != null) {
                selects.add(part.getSelect());
            } else if (part.getExecSelect() != null) {
                selects.add(part.getExecSelect().getClassName());
            }
            alias.add(part.getAlias());
        }
        Assert.assertEquals(selects.toArray(), new String[] { "attribute[Name]", "org.efaps.Demo" });
        Assert.assertEquals(alias.toArray(), new String[] { "name", "Two" });

    }

    @Test(description = "exec select")
    public void execSelect2()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] as name, exec org.efaps.Demo as Two, exec org.efaps.esjp.Versuch as Three");

        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();
        final List<String> alias = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            if (part.getSelect() != null) {
                selects.add(part.getSelect());
            } else if (part.getExecSelect() != null) {
                selects.add(part.getExecSelect().getClassName());
            }
            alias.add(part.getAlias());
        }
        Assert.assertEquals(selects.toArray(), new String[] { "attribute[Name]", "org.efaps.Demo", "org.efaps.esjp.Versuch" });
        Assert.assertEquals(alias.toArray(), new String[] { "name", "Two", "Three"});

    }

    @Test(description = "select with exec and select")
    public void execSelect3()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] as name, exec org.efaps.Demo as Two, exec org.efaps.esjp.Versuch as Three, linkto[AbstractLink].attribute[Name] as linkname");

        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();
        final List<String> alias = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            if (part.getSelect() != null) {
                selects.add(part.getSelect());
            } else if (part.getExecSelect() != null) {
                selects.add(part.getExecSelect().getClassName());
            }
            alias.add(part.getAlias());
        }
        Assert.assertEquals(selects.toArray(), new String[] { "attribute[Name]", "org.efaps.Demo", "org.efaps.esjp.Versuch", "linkto[AbstractLink].attribute[Name]" });
        Assert.assertEquals(alias.toArray(), new String[] { "name", "Two", "Three", "linkname"});
    }

    @Test(description = "select with exec with parameters and select")
    public void execSelect4()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] as name, exec org.efaps.demo.Test \"Param1 with space\", 24 as Two, exec org.efaps.esjp.Versuch as Three, linkto[AbstractLink].attribute[Name] as linkname");

        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();
        final List<String> alias = new ArrayList<>();
        final List<String> parameters = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            if (part.getSelect() != null) {
                selects.add(part.getSelect());
            } else if (part.getExecSelect() != null) {
                selects.add(part.getExecSelect().getClassName());
                parameters.addAll(part.getExecSelect().getParameters());
            }
            alias.add(part.getAlias());
        }
        Assert.assertEquals(selects.toArray(), new String[] { "attribute[Name]", "org.efaps.demo.Test", "org.efaps.esjp.Versuch", "linkto[AbstractLink].attribute[Name]" });
        Assert.assertEquals(alias.toArray(), new String[] { "name", "Two", "Three", "linkname"});
        Assert.assertEquals(parameters.toArray(), new String[] {"Param1 with space", "24"});
    }

    @Test(description = "select with exec with parameters and select")
    public void execSelect5()
        throws Exception
    {
        final Statement stmt = getStatement("print query type Sales_Invoice select attribute[Name] as name, exec org.efaps.demo.Test \"linkto[Attribute].instance\", 24 as Two, exec org.efaps.esjp.Versuch as Three, linkto[AbstractLink].attribute[Name] as linkname");

        final SelectPart select = stmt.getQueryPart().getSelectPart();
        final List<String> selects = new ArrayList<>();
        final List<String> alias = new ArrayList<>();
        final List<String> parameters = new ArrayList<>();

        for (final OneSelect part : select.getSelects()) {
            if (part.getSelect() != null) {
                selects.add(part.getSelect());
            } else if (part.getExecSelect() != null) {
                selects.add(part.getExecSelect().getClassName());
                parameters.addAll(part.getExecSelect().getParameters());
            }
            alias.add(part.getAlias());
        }
        Assert.assertEquals(selects.toArray(), new String[] { "attribute[Name]", "org.efaps.demo.Test", "org.efaps.esjp.Versuch", "linkto[AbstractLink].attribute[Name]" });
        Assert.assertEquals(alias.toArray(), new String[] { "name", "Two", "Three", "linkname"});
        Assert.assertEquals(parameters.toArray(), new String[] {"linkto[Attribute].instance", "24"});
    }


}
