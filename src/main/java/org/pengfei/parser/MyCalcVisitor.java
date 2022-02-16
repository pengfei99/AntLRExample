package org.pengfei.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class MyCalcVisitor extends CalculatorBaseVisitor {

    /**
     * This method is called when the root of the syntax tree is visited. We just continue to visit the child node of
     * the root
     * @param ctx is the context of the visited node, contains all information about the node.
     */
    @Override
    public Integer visitStart(CalculatorParser.StartContext ctx) {
        return (Integer) this.visit(ctx.expr());
    }

    /**
     * This method is called when a OpExpr node in the parsed syntax tree is visited. As in Calculator.g4 we defined name
     * left, right and op for sub expression. We can use context to access the value of sub context. For example ctx.left
     * will return the expression of the left-hand side.
     *
     * @param ctx is the context of the visited node, contains all information about the node.
     */
    @Override
    public Integer visitOpExpr(CalculatorParser.OpExprContext ctx) {
        int left = (int) visit(ctx.left);
        int right = (int) visit(ctx.right);
        String op = ctx.op.getText();
        switch (op.charAt(0)) {
            case '*':
                return left * right;
            case '/':
                return left / right;
            case '+':
                return left + right;
            case '-':
                return left - right;
            default:
                throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    /**
     * This method is called when a AtomExpr node (e.g. digit such as 4) in the parsed syntax tree is visited.
     *
     * @param ctx is the context of the visited node, contains all information about the node.
     */
    @Override
    public Integer visitAtomExpr(CalculatorParser.AtomExprContext ctx) {
        // the ctx will be a number (e.g. 0~9), with getText() we will get this number in string format
        // then we cast it ot int and return it
        return Integer.valueOf(ctx.getText());
    }

    /**
     * This method is called when a ParenExpr node (e.g. ( expr )) in the parsed syntax tree is visited.
     *
     * @param ctx is the context of the visited node, contains all information about the node.
     */
    @Override
    public Integer visitParenExpr(CalculatorParser.ParenExprContext ctx) {
        return (Integer) this.visit(ctx.expr());
    }

    /**
     * Returns an integer which is the result of expression evaluation.
     * <p>
     * This method will raise a NullPointerException if the input expression does not match required syntax
     *
     * @param expression an absolute URL giving the base location of the image
     * @return the result of expression evaluation
     */
    public Integer evaluate(String expression) {
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expression));
        CalculatorParser parser = new CalculatorParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.start();
        return (Integer) this.visit(tree);
    }


}
