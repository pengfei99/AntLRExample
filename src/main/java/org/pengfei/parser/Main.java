package org.pengfei.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    public static void main(String[] args){
        String expression = "2 * (3 + 4)";
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expression));
        CalculatorParser parser = new CalculatorParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.start();
        Integer answer = (Integer) new MyCalcVisitor().visit(tree);
        System.out.printf("%s = %s\n", expression, answer);
    }
}
