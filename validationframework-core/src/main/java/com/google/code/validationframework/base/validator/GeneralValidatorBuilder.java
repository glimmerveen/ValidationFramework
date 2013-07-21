/*
 * Copyright (c) 2013, Patrick Moawad
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.google.code.validationframework.base.validator;

import com.google.code.validationframework.api.dataprovider.DataProvider;
import com.google.code.validationframework.api.trigger.Trigger;
import com.google.code.validationframework.base.resulthandler.ResultCollector;
import com.google.code.validationframework.base.validator.dsl.generalvalidator
        .MultipleResultCollectorContext;
import com.google.code.validationframework.base.validator.dsl.generalvalidator.SingleResultCollectorContext;
import com.google.code.validationframework.base.validator.dsl.generalvalidator.TriggerContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * General validator builder that can be used to construct a {@link GeneralValidator} using a DSL.<br>Note that the
 * {@link GeneralValidator} will be constructed and effective only after the addition of the first result handler.
 *
 * @see GeneralValidator
 */
public final class GeneralValidatorBuilder {

    /**
     * Private constructor for utility class.
     */
    private GeneralValidatorBuilder() {
        // Nothing to be done
    }

    /**
     * Adds the specified trigger to the validator under construction.
     *
     * @param trigger Trigger to be added.
     *
     * @return Context allowing further construction of the validator using the DSL.
     */
    public static TriggerContext on(final Trigger trigger) {
        final List<Trigger> addedTriggers = new ArrayList<Trigger>();
        if (trigger != null) {
            addedTriggers.add(trigger);
        }
        return new TriggerContext(addedTriggers);
    }

    /**
     * Adds the specified triggers to the validator under construction.
     *
     * @param triggers Triggers to be added.
     *
     * @return Context allowing further construction of the validator using the DSL.
     */
    public static TriggerContext on(final Collection<Trigger> triggers) {
        final List<Trigger> addedTriggers = new ArrayList<Trigger>();
        if (triggers != null) {
            addedTriggers.addAll(triggers);
        }
        return new TriggerContext(addedTriggers);
    }

    /**
     * Adds the specified result collector as trigger and data provider to the validator under construction.
     *
     * @param resultCollector Result collector to be added.
     * @param <DPO>           Type of data provider output.
     *
     * @return Context allowing further construction of the validator using the DSL.
     */
    public static <DPO> SingleResultCollectorContext<DPO> collect(final ResultCollector<?, DPO> resultCollector) {
        final List<Trigger> addedTriggers = new ArrayList<Trigger>();
        final List<DataProvider<DPO>> addedDataProviders = new ArrayList<DataProvider<DPO>>();
        if (resultCollector != null) {
            addedTriggers.add(resultCollector);
            addedDataProviders.add(resultCollector);
        }

        return new SingleResultCollectorContext<DPO>(addedTriggers, addedDataProviders);
    }

    /**
     * Adds the specified result collectors as triggers and data providers to the validator under construction.
     *
     * @param resultCollectors Result collectors to be added.
     * @param <DPO>            Type of data provider output.
     *
     * @return Context allowing further construction of the validator using the DSL.
     */
    public static <DPO> MultipleResultCollectorContext<DPO> collect(final Collection<ResultCollector<?,
            DPO>> resultCollectors) {
        final List<Trigger> addedTriggers = new ArrayList<Trigger>();
        final List<DataProvider<DPO>> addedDataProviders = new ArrayList<DataProvider<DPO>>();
        if (resultCollectors != null) {
            addedTriggers.addAll(resultCollectors);
            addedDataProviders.addAll(resultCollectors);
        }

        return new MultipleResultCollectorContext<DPO>(addedTriggers, addedDataProviders);
    }
}
