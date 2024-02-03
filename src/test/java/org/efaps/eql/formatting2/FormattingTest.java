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
package org.efaps.eql.formatting2;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.xtext.formatting2.FormatterRequest;
import org.eclipse.xtext.formatting2.IFormatter2;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.TextRegionAccessBuilder;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.StringInputStream;
import org.efaps.eql.AbstractTest;
import org.efaps.eql.EQLStandaloneSetup;
import org.testng.annotations.Test;

import com.google.inject.Inject;

/**
 * The Class FormattingTest.
 *
 * @author The eFaps Team
 */
public class FormattingTest
    extends AbstractTest
{

    @Inject
    private TextRegionAccessBuilder regionBuilder;

    @Inject
    private IFormatter2 formatter;

    @Test(description = "delete 124.879")
    public void delete()
        throws Exception
    {
        final FormatterRequest request = new FormatterRequest();

        final XtextResource resource = new XtextResource();

        EQLStandaloneSetup.doSetup(resource);
        final Map<Object, Object> map = new HashMap<>();
        map.put(XtextResource.OPTION_ENCODING, "UTF-8");
        resource.load(new StringInputStream("print query t"), null);

        // final ITextRegionAccess ll =
        // regionBuilder.forNodeModel(resource).create();
        // request.setTextRegionAccess(ll);

        // ((EQLFormatter) formatter).format(request);
    }
}
