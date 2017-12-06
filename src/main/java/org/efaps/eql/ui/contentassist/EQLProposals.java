/*
 * Copyright 2003 - 2015 The eFaps Team
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
 *
 */
package org.efaps.eql.ui.contentassist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class EQLProposals.
 *
 * @author The eFaps Team
 */
@Deprecated
public final class EQLProposals
{

    /** The log. */
    private static Logger LOG = LoggerFactory.getLogger(EQLProposals.class);

    /** The cinameproviders. */
    private static Set<ICINameProvider> CINAMEPROVIDERS = new HashSet<>();

    /**
     * Instantiates a new EQL proposals.
     */
    private EQLProposals()
    {
    }

    /**
     * Gets the CI name providers.
     *
     * @param _provider the provider
     */
    public static void registerCINameProviders(final ICINameProvider _provider)
    {
        CINAMEPROVIDERS.add(_provider);
    }

    /**
     * Gets the CI name providers.
     *
     * @return the CI name providers
     */
    public static Set<ICINameProvider> getCINameProviders()
    {
        return CINAMEPROVIDERS;
    }

    /**
     * Gets the proposal list.
     *
     * @param _text the text
     * @return the proposal list
     */
    @SuppressWarnings("checkstyle:illegalcatch")
    public static List<String> getProposalList(final String _text)
    {
        return null;
    }
}
