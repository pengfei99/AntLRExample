package org.pengfei.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    public static void main(String[] args){
        String expression = "2 * (3 + 4)";
        MyCalcVisitor calculator=new MyCalcVisitor();
        Integer answer = calculator.evaluate(expression);
        System.out.printf("%s = %s\n", expression, answer);
    }
}
