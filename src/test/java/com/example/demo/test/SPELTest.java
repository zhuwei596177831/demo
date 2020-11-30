package com.example.demo.test;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author 朱伟伟
 * @date 2020-11-02 10:58:16
 * @description
 */
public class SPELTest {
    @Test
    public void test() {
        String expressionString = "14+1";
//        String expressionString = "#{14+1}";
//        String expressionString = "#{#user}";
        ExpressionParser expressionParser = new SpelExpressionParser();
//        ParserContext parserContext = new TemplateParserContext("#{", "}");
//        ParserContext parserContext = ParserContext.TEMPLATE_EXPRESSION;
//        Expression expression = expressionParser.parseExpression(expressionString, parserContext);
        Expression expression = expressionParser.parseExpression(expressionString);
        System.out.println(expression.getValue(String.class));
//        EvaluationContext evaluationContext = new StandardEvaluationContext();
//        evaluationContext.setVariable("user", "朱伟伟");
//        System.out.println(expression.getValue(evaluationContext));
    }
}
