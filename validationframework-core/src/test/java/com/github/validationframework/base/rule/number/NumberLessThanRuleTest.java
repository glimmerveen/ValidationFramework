/*
 * Copyright (c) 2012, Patrick Moawad
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

package com.github.validationframework.base.rule.number;

import junit.framework.Assert;
import org.junit.Test;

public class NumberLessThanRuleTest {

	@Test
	public void testInteger() {
		final NumberLessThanRule<Integer> rule = new NumberLessThanRule<Integer>(0);

		Assert.assertEquals(Boolean.FALSE, rule.validate(0));
		Assert.assertEquals(Boolean.FALSE, rule.validate(5));
		Assert.assertEquals(Boolean.TRUE, rule.validate(-1));
		Assert.assertEquals(Boolean.FALSE, rule.validate(null));

		final NumberLessThanRule<Integer> rule2 = new NumberLessThanRule<Integer>(null);

		Assert.assertEquals(Boolean.TRUE, rule2.validate(0));
		Assert.assertEquals(Boolean.TRUE, rule2.validate(5));
		Assert.assertEquals(Boolean.TRUE, rule2.validate(-1));
		Assert.assertEquals(Boolean.FALSE, rule2.validate(null));
	}
}
