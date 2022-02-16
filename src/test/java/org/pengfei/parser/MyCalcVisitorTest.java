package org.pengfei.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class MyCalcVisitorTest {

    private MyCalcVisitor calculator;

    @BeforeEach
    public void setup() {
        calculator = new MyCalcVisitor();
    }

    @Test
    public void testEvaluate() {
        String expression = "100 * (6 + 2)";
        int expected = 800;
        int result = calculator.evaluate(expression);
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void testEvaluateWithBadSyntax(){
        assertThatThrownBy(() -> {
            String expression = "100 * (6 + toto)";
            int result = calculator.evaluate(expression);
        }).isInstanceOf(NullPointerException.class);

    }

}
