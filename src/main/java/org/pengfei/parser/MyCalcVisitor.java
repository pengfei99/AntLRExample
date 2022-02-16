package org.pengfei.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class MyCalcVisitor extends CalculatorBaseVisitor{

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public Integer visitStart(CalculatorParser.StartContext ctx) {
        return (Integer) this.visit(ctx.expr());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Integer visitOpExpr(CalculatorParser.OpExprContext ctx) {
        int left = (int) visit(ctx.left);
        int right = (int) visit(ctx.right);
        String op = ctx.op.getText();
        switch (op.charAt(0)) {
            case '*': return left * right;
            case '/': return left / right;
            case '+': return left + right;
            case '-': return left - right;
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Integer visitAtomExpr(CalculatorParser.AtomExprContext ctx) {
        // the ctx will be a number (e.g. 0~9), with getText() we will get this number in string format
        // then we cast it ot int and return it
        return Integer.valueOf(ctx.getText());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Integer visitParenExpr(CalculatorParser.ParenExprContext ctx) {
        return (Integer) this.visit(ctx.expr());
    }


}
