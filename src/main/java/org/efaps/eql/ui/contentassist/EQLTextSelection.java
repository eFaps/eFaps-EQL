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



/**
 * The Class EQLTextSelection.
 *
 * @author The eFaps Team
 */
public class EQLTextSelection

{

    /** The offset. */
    private final int offset;

    /** The txt. */
    private final String txt;

    /**
     * Instantiates a new EQL text selection.
     *
     * @param _txt the txt
     * @param _offset the offset
     */
    public EQLTextSelection(final String _txt,
                            final int _offset)
    {
        this.txt = _txt;
        this.offset = _offset;
    }

}
