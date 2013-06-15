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
import java.util.List;

public class RuleTransformedContext<DPO, RI, RO, TRO> {

    private final List<Trigger> triggers;
    private final List<DataProvider<DPO>> dataProviders;
    private final GeneralValidator.DataProviderToRuleMapping dataProviderToRuleMapping;
    private final List<Rule<RI, RO>> rules;
    private final List<Transformer> rulesOutputTransformers;

    public RuleTransformedContext(final List<Trigger> triggers, final List<DataProvider<DPO>> dataProviders,
                                  final GeneralValidator.DataProviderToRuleMapping dataProviderToRuleMapping,
                                  final List<Rule<RI, RO>> rules, final List<Transformer> rulesOutputTransformers) {
        this.triggers = triggers;
        this.dataProviders = dataProviders;
        this.dataProviderToRuleMapping = dataProviderToRuleMapping;
        this.rules = rules;
        if (rulesOutputTransformers == null) {
            this.rulesOutputTransformers = new ArrayList<Transformer>();
        } else {
            this.rulesOutputTransformers = rulesOutputTransformers;
        }
    }

    public <TTRO> RuleTransformedContext<DPO, RI, RO, TTRO> transform(final Transformer<TRO,
            TTRO> rulesOutputTransformer) {
        rulesOutputTransformers.add(rulesOutputTransformer);
        return new RuleTransformedContext<DPO, RI, RO, TTRO>(triggers, dataProviders, dataProviderToRuleMapping,
                rules, rulesOutputTransformers);
    }

    public RuleTransformedCombinedContext<DPO, RI, RO, TRO> combine() {
        return new RuleTransformedCombinedContext<DPO, RI, RO, TRO>(triggers, dataProviders,
                dataProviderToRuleMapping, rules, null, GeneralValidator.RuleToResultHandlerMapping.ALL_TO_EACH);
    }

    public ResultHandlerContext<DPO, RI, RO, TRO> handleWith(final ResultHandler<TRO> resultHandler) {
        return new ResultHandlerContext<DPO, RI, RO, TRO>(triggers, dataProviders, dataProviderToRuleMapping, rules,
                rulesOutputTransformers, GeneralValidator.RuleToResultHandlerMapping.EACH_TO_EACH, rulesOutputTransformers, resultHandler);
    }
}