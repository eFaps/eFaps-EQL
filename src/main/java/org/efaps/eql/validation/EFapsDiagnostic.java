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
package org.efaps.eql.validation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.xtext.diagnostics.Severity;

/**
 * TODO comment!
 *
 * @author The eFaps Team
 * @version $Id: $
 */
public class EFapsDiagnostic
    implements Diagnostic
{

    private final Severity severity;
    private final String issueCode;
    private String message;

    private final String[] data;

    /**
     * @param _severity
     */
    public EFapsDiagnostic(final Severity _severity,
                           final String _message,
                           final String _code,
                           final String... _issueData)
    {
        this.severity = _severity;
        this.issueCode = _code;
        this.message = _message;
        this.data = _issueData;
    }

    @Override
    public int getSeverity()
    {
        int ret = 0;
        switch (this.severity) {
            case ERROR:
                ret = Diagnostic.ERROR;
                break;
            case INFO:
                ret = Diagnostic.INFO;
                break;
            case WARNING:
                ret = Diagnostic.WARNING;
                break;
            default:
                ret = Diagnostic.OK;
                break;
        }
        return ret;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }

    @Override
    public String getSource()
    {
        return null;
    }

    @Override
    public int getCode()
    {
        return 0;
    }

    @Override
    public Throwable getException()
    {
        return null;
    }

    @Override
    public List<?> getData()
    {
        List<String> ret;
        if (this.data != null) {
            ret = Arrays.asList(this.data);
        } else {
            ret = Collections.emptyList();
        }
        return ret;
    }

    @Override
    public List<Diagnostic> getChildren()
    {
        return null;
    }

    /**
     * Getter method for the instance variable {@link #issueCode}.
     *
     * @return value of instance variable {@link #issueCode}
     */
    public String getIssueCode()
    {
        return this.issueCode;
    }

    /**
     * Setter method for instance variable {@link #message}.
     *
     * @param _message value for instance variable {@link #message}
     */
    public void setMessage(final String _message)
    {
        this.message = _message;
    }
}
