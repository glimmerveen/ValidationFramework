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

package com.google.code.validationframework.base.rule.number;

import com.google.code.validationframework.base.rule.number.NumberLessThanRule;
import junit.framework.Assert;
import org.junit.Test;

public class NumberLessThanRuleTest {

    @Test
    public void testDouble0() {
        final NumberLessThanRule<Double> rule = new NumberLessThanRule<Double>(0.0);

        Assert.assertEquals(Boolean.FALSE, rule.validate(0.0));
        Assert.assertEquals(Boolean.FALSE, rule.validate(65.453));
        Assert.assertEquals(Boolean.TRUE, rule.validate(-1.12));
        Assert.assertEquals(Boolean.FALSE, rule.validate(Double.NaN)); // Default behavior of Double
        Assert.assertEquals(Boolean.TRUE, rule.validate(null));
    }

    @Test
    public void testDoubleNaN() {
        final NumberLessThanRule<Double> rule = new NumberLessThanRule<Double>(Double.NaN);

        Assert.assertEquals(Boolean.TRUE, rule.validate(0.0)); // Default behavior of Double
        Assert.assertEquals(Boolean.TRUE, rule.validate(65.453)); // Default behavior of Double
        Assert.assertEquals(Boolean.TRUE, rule.validate(-1.12)); // Default behavior of Double
        Assert.assertEquals(Boolean.FALSE, rule.validate(Double.NaN)); // Default behavior of Double
        Assert.assertEquals(Boolean.TRUE, rule.validate(null));
    }

    @Test
    public void testDoubleNull() {
        final NumberLessThanRule<Double> rule = new NumberLessThanRule<Double>(null);

        Assert.assertEquals(Boolean.FALSE, rule.validate(0.0));
        Assert.assertEquals(Boolean.FALSE, rule.validate(65.453));
        Assert.assertEquals(Boolean.FALSE, rule.validate(-1.12));
        Assert.assertEquals(Boolean.FALSE, rule.validate(Double.NaN));
        Assert.assertEquals(Boolean.FALSE, rule.validate(null));
    }
}