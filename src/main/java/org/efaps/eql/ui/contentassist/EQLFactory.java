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
package org.efaps.eql.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;

/**
 * A factory for creating EQL objects.
 */
@SuppressWarnings("checkstyle:abstractclassname")
@Deprecated
public class EQLFactory
{

    protected void initializeAndAdjustCompletionOffset(final int _offset)
    {
    }

    public String getNodeTextUpToCompletionOffset(final INode _currentNode)
    {
        return null;
    }

    @SuppressWarnings("checkstyle:returncount")
    protected void createContextsForLastCompleteNode(final EObject _previousModel,
                                                     final boolean _strict)
    {
    }

    protected void handleLastCompleteNodeIsAtEndOfDatatypeNode()
    {
    }
}
