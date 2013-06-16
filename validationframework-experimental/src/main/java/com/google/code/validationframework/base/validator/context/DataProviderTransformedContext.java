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

package com.google.code.validationframework.base.validator.context;

import com.google.code.validationframework.api.dataprovider.DataProvider;
import com.google.code.validationframework.api.rule.Rule;
import com.google.code.validationframework.api.trigger.Trigger;
import com.google.code.validationframework.base.transform.Transformer;
import com.google.code.validationframework.base.validator.GeneralValidator;

import java.util.ArrayList;
import java.util.List;

public class DataProviderTransformedContext<DPO, TDPO> {

    private final List<Trigger> triggers;
    private final List<DataProvider<DPO>> dataProviders;
    private final List<Transformer> dataProvidersOutputTransformers;

    public DataProviderTransformedContext(final List<Trigger> triggers, //
                                          final List<DataProvider<DPO>> dataProviders, //
                                          final List<Transformer> dataProvidersOutputTransformers) {
        this.triggers = triggers;
        this.dataProviders = dataProviders;
        this.dataProvidersOutputTransformers = dataProvidersOutputTransformers;
    }

    public <TTDPO> DataProviderTransformedContext<DPO, TTDPO> transform(final Transformer<TDPO,
            TTDPO> dataProvidersOutputTransformer) {
        if (dataProvidersOutputTransformer != null) {
            dataProvidersOutputTransformers.add(dataProvidersOutputTransformer);
        }

        // Stay in the same context, but create a new instance because the output type has changed
        return new DataProviderTransformedContext<DPO, TTDPO>(triggers, dataProviders, dataProvidersOutputTransformers);
    }

    public DataProviderTransformedCombinedContext<DPO, TDPO> combine() {
        // Change context
        return new DataProviderTransformedCombinedContext<DPO, TDPO>(triggers, dataProviders,
                dataProvidersOutputTransformers, GeneralValidator.DataProviderToRuleMapping.ALL_TO_EACH);
    }

    public <RO> RuleContext<DPO, TDPO, RO> check(final Rule<TDPO, RO> rule) {
        final List<Rule<TDPO, RO>> rules = new ArrayList<Rule<TDPO, RO>>();
        if (rule != null) {
            rules.add(rule);
        }

        // Change context
        return new RuleContext<DPO, TDPO, RO>(triggers, dataProviders, dataProvidersOutputTransformers,
                GeneralValidator.DataProviderToRuleMapping.EACH_TO_EACH, null, rules);
    }
}