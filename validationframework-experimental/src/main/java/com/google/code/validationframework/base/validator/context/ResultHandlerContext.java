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

import java.util.List;

public class ResultHandlerContext<DPO, RI, RO, RHI> {

    private final List<Trigger> triggers;
    private final List<DataProvider<DPO>> dataProviders;
    private final List<Transformer> dataProvidersOutputTransformers;
    private final GeneralValidator.DataProviderToRuleMapping dataProviderToRuleMapping;
    private final List<Transformer> combinedDataProvidersOutputTransformers;
    private final List<Rule<RI, RO>> rules;
    private final List<Transformer> rulesOutputTransformers;
    private final GeneralValidator.RuleToResultHandlerMapping ruleToResultHandlerMapping;
    private final List<Transformer> combinedRulesOutputTransformers;
    private final List<ResultHandler<RHI>> resultHandlers;

    public ResultHandlerContext(final List<Trigger> triggers, //
                                final List<DataProvider<DPO>> dataProviders, //
                                final List<Transformer> dataProvidersOutputTransformers, //
                                final GeneralValidator.DataProviderToRuleMapping dataProviderToRuleMapping, //
                                final List<Transformer> combinedDataProvidersOutputTransformers, //
                                final List<Rule<RI, RO>> rules, //
                                final List<Transformer> rulesOutputTransformers, //
                                final GeneralValidator.RuleToResultHandlerMapping ruleToResultHandlerMapping, //
                                final List<Transformer> combinedRulesOutputTransformers, //
                                final List<ResultHandler<RHI>> resultHandlers) {
        this.triggers = triggers;
        this.dataProviders = dataProviders;
        this.dataProvidersOutputTransformers = dataProvidersOutputTransformers;
        this.dataProviderToRuleMapping = dataProviderToRuleMapping;
        this.combinedDataProvidersOutputTransformers = combinedDataProvidersOutputTransformers;
        this.rules = rules;
        this.rulesOutputTransformers = rulesOutputTransformers;
        this.ruleToResultHandlerMapping = ruleToResultHandlerMapping;
        this.combinedRulesOutputTransformers = combinedRulesOutputTransformers;
        this.resultHandlers = resultHandlers;
    }

    public ResultHandlerContext<DPO, RI, RO, RHI> handleWith(final ResultHandler<RHI> resultHandler) {
        if (resultHandler != null) {
            resultHandlers.add(resultHandler);
        }

        // Stay in the same context and re-use the same instance because no type has changed
        return this;
    }

    public GeneralValidator<DPO, RI, RO, RHI> build() {
        // Create validator
        final GeneralValidator<DPO, RI, RO, RHI> validator = new GeneralValidator<DPO, RI, RO,
                RHI>(dataProviderToRuleMapping, ruleToResultHandlerMapping);

        // Add triggers
        for (final Trigger trigger : triggers) {
            validator.addTrigger(trigger);
        }

        // Add data providers
        for (final DataProvider<DPO> dataProvider : dataProviders) {
            validator.addDataProvider(dataProvider);
        }

        // TODO Map data providers output to rules input
        validator.mapDataProvidersToRules(dataProviderToRuleMapping);

        // Add rules
        for (final Rule<RI, RO> rule : rules) {
            validator.addRule(rule);
        }

        // TODO Map rules output to result handlers input
        validator.mapRulesToResultHandlers(ruleToResultHandlerMapping);

        // Add result handlers
        for (final ResultHandler<RHI> resultHandler : resultHandlers) {
            validator.addResultHandler(resultHandler);
        }

        return validator;
    }
}
