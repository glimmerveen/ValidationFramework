package com.github.validationframework.base.rule;

import com.github.validationframework.api.rule.TypedDataRule;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * @author: arnoud
 */
public class AndCompositeTypedDataBooleanRuleTest extends AbstractCompositeTypedDataBooleanRuleTest {

    @Parameterized.Parameters
    public static List<Object[]> getTestData() {
        Object[][] data = new Object[][] {
                //             TypedDataRules (Object data, Boolean result)                                     Result
                new Object[] { new TypedDataRule[] { createTypedDataRule(true), createTypedDataRule(true) },    true },
                new Object[] { new TypedDataRule[] { createTypedDataRule(true) },                               true },
                new Object[] { new TypedDataRule[] { },                                                         true },
                new Object[] { new TypedDataRule[] { createTypedDataRule(true), createTypedDataRule(false) },   false },
                new Object[] { new TypedDataRule[] { createTypedDataRule(false), createTypedDataRule(true) },   false },
                new Object[] { new TypedDataRule[] { createTypedDataRule(false), createTypedDataRule(false) },  false }
        };
        return Arrays.asList(data);
    }

    public AndCompositeTypedDataBooleanRuleTest(TypedDataRule<Object, Boolean>[] rules, boolean result) {
        super(rules, result);
    }

    @Override
    protected AbstractCompositeTypedDataRule<Object, Boolean> createCompositeTypedDataBooleanRule() {
        return new AndCompositeTypedDataBooleanRule<Object>(rules);
    }

}
