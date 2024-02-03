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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.Check;
import org.efaps.eql.eQL.Comparison;
import org.efaps.eql.eQL.OneWhere;
import org.efaps.eql.eQL.QueryPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom validation rules.
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
public class EQLJavaValidator

{

    /**
     * Logging instance used in this class.
     */
    private static final Logger LOG = LoggerFactory.getLogger(EQLJavaValidator.class);

    /** The diagnostic clazz. */
    private Class<? extends EFapsDiagnostic> diagnosticClazz = EFapsDiagnostic.class;

    /** The validations. */
    private final Map<String, Collection<IValidation>> validations = new HashMap<>();

    /**
     * In must not be empty.
     *
     * @param _oneWhere the _one where
     */
    @Check
    public void inMustNotBeEmpty(final OneWhere _oneWhere)
    {
        if (Comparison.IN.equals(_oneWhere.getComparison()) && _oneWhere.getValues().isEmpty()
                        && _oneWhere.getNestedQueryPart() == null) {
        }
    }

    /**
     * Type.
     *
     * @param _queryPart the _query part
     */
    @Check
    public void type(final QueryPart _queryPart)
    {
        for (final String type : _queryPart.getTypes()) {
            if (getValidations().containsKey("EQLJavaValidator.type")) {
                for (final IValidation validator : getValidations().get("EQLJavaValidator.type")) {
                    if (!validator.validate(type)) {
                    }
                }
            }
        }
    }


    protected Diagnostic createDiagnostic(final Severity _severity,
                                          final String _message,
                                          final EObject _object,
                                          final int _offset,
                                          final int _length,
                                          final String _code,
                                          final String... _issueData)
    {
        return createDiagnostic(_severity, _message, _object, (EStructuralFeature) null, 0, _code, _issueData);
    }


    protected Diagnostic createDiagnostic(final Severity _severity,
                                          final String _message,
                                          final EObject _object,
                                          final EStructuralFeature _feature,
                                          final int _index,
                                          final String _code,
                                          final String... _issueData)
    {
        EFapsDiagnostic ret = null;
        try {
            final Constructor<? extends EFapsDiagnostic> constructor = getDiagnosticClazz().getConstructor(
                            Severity.class, String.class, String.class, String[].class);
            ret = constructor.newInstance(_severity, _message, _code, _issueData);
        } catch (final NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException e) {
            LOG.error("Catched error", e);
        }
        return ret;
    }

    /**
     * Getter method for the instance variable {@link #diagnosticClazz}.
     *
     * @return value of instance variable {@link #diagnosticClazz}
     */
    public Class<? extends EFapsDiagnostic> getDiagnosticClazz()
    {
        return this.diagnosticClazz;
    }

    /**
     * Setter method for instance variable {@link #diagnosticClazz}.
     *
     * @param _diagnosticClazz value for instance variable
     *            {@link #diagnosticClazz}
     */
    public void setDiagnosticClazz(final Class<? extends EFapsDiagnostic> _diagnosticClazz)
    {
        this.diagnosticClazz = _diagnosticClazz;
    }

    /**
     * Getter method for the instance variable {@link #validators}.
     *
     * @return value of instance variable {@link #validators}
     */
    public Map<String, Collection<IValidation>> getValidations()
    {
        return this.validations;
    }

    /**
     * Getter method for the instance variable {@link #validators}.
     *
     * @param _key the _key
     * @param _validate the _validate
     */
    public void addValidation(final String _key,
                              final IValidation _validate)
    {
        Collection<IValidation> validates;
        if (this.validations.containsKey(_key)) {
            validates = this.validations.get(_key);
        } else {
            validates = new ArrayList<>();
            this.validations.put(_key, validates);
        }
        validates.add(_validate);
    }
}
