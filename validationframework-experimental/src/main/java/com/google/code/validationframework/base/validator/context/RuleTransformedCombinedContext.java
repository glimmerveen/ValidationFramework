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
import com.google.code.validationframework.api.resulthandler.ResultHandler;
import com.google.code.validationframework.api.rule.Rule;
import com.google.code.validationframework.api.trigger.Trigger;
import com.google.code.validationframework.base.transform.Transformer;
import com.google.code.validationframework.base.validator.GeneralValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RuleTransformedCombinedContext<DPO, RI, RO, TRO> {

    private final List<Trigger> triggers;
    private final List<DataProvider<DPO>> dataProviders;
    private final List<Transformer> dataProvidersOutputTransformers;
    private final GeneralValidator.DataProviderToRuleMapping dataProviderToRuleMapping;
    private final List<Transformer> combinedDataProvidersOutputTransformers;
    private final List<Rule<RI, RO>> rules;
    private final List<Transformer> rulesOutputTransformers;
    private final GeneralValidator.RuleToResultHandlerMapping ruleToResultHandlerMapping;

    public RuleTransformedCombinedContext(final List<Trigger> triggers, //
                                          final List<DataProvider<DPO>> dataProviders, //
                                          final List<Transformer> dataProvidersOutputTransformers, //
                                          final GeneralValidator.DataProviderToRuleMapping dataProviderToRuleMapping, //
                                          final List<Transformer> combinedDataProvidersOutputTransformers, //
                                          final List<Rule<RI, RO>> rules, //
                                          final List<Transformer> rulesOutputTransformers, //
                                          final GeneralValidator.RuleToResultHandlerMapping
                                                  ruleToResultHandlerMapping) {
        this.triggers = triggers;
        this.dataProviders = dataProviders;
        this.dataProvidersOutputTransformers = dataProvidersOutputTransformers;
        this.dataProviderToRuleMapping = dataProviderToRuleMapping;
        this.combinedDataProvidersOutputTransformers = combinedDataProvidersOutputTransformers;
        this.rules = rules;
        this.rulesOutputTransformers = rulesOutputTransformers;
        this.ruleToResultHandlerMapping = ruleToResultHandlerMapping;
    }

    public <TTRO> RuleTransformedCombinedTransformedContext<DPO, RI, RO,
            TTRO> transform(final Transformer<Collection<TRO>, TTRO> rulesOutputToResultHandlerInputTransformer) {
        final List<Transformer> transformers = new ArrayList<Transformer>();
        if (rulesOutputToResultHandlerInputTransformer != null) {
            transformers.add(rulesOutputToResultHandlerInputTransformer);
        }

        // Change context
        return new RuleTransformedCombinedTransformedContext<DPO, RI, RO, TTRO>(triggers, dataProviders,
                dataProvidersOutputTransformers, dataProviderToRuleMapping, combinedDataProvidersOutputTransformers,
                rules, rulesOutputTransformers, GeneralValidator.RuleToResultHandlerMapping.ALL_TO_EACH, transformers);
    }

    public ResultHandlerContext<DPO, RI, RO, Collection<TRO>> handleWith(final ResultHandler<Collection<TRO>>
                                                                                 resultHandler) {
        final List<ResultHandler<Collection<TRO>>> resultHandlers = new ArrayList<ResultHandler<Collection<TRO>>>();
        if (resultHandler != null) {
            resultHandlers.add(resultHandler);
        }

        // Change context
        return new ResultHandlerContext<DPO, RI, RO, Collection<TRO>>(triggers, dataProviders, dataProvidersOutputTransformers, dataProviderToRuleMapping, combinedDataProvidersOutputTransformers, rules, rulesOutputTransformers, ruleToResultHandlerMapping, null, resultHandlers);
    }
}
