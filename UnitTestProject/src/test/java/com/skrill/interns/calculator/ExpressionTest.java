package com.skrill.interns.calculator;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExpressionTest {

    private Expression expr;

    @BeforeMethod
    public void setup() throws Exception {
        expr = spy(new Expression());
    }

    //Test for method Expression.parse

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void when_the_string_has_double_number_with_more_than_10_symbols_in_integer_part_parse_return_exception() throws Exception {
        //GIVEN
        String input = "12345678910.000/12.34289942842=";
        //WHEN
        expr = Expression.parse(input);
        //THEN
        //throws IllegalArgumentException
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void when_the_string_has_double_number_with_more_than_20_symbols_in_fraction_parse_return_exception() throws Exception {
        //GIVEN
        String input = "123.123456789123456789123+12=";
        //WHEN
        expr = Expression.parse(input);
        //THEN
        //throws IllegalArgumentException
    }

    @Test
    public void when_the_string_is_expression_with_two_zeros_parse_doesnt_throws_exception() throws Exception {
        //GIVEN
        Expression newExpr = null;
        String input = "0.0000000000000/0.000000000000=";
        //WHEN
        newExpr = Expression.parse(input);
        //THEN
        assertNotNull(newExpr);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void when_the_string_is_expression_with_only_one_number_parse_thorws_exception() throws Exception {
        //GIVEN
        String input = "123.0=";
        //WHEN
        expr = Expression.parse(input);
        //THEN
        //throws IllegalArgumentException
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void when_the_string_is_null_parse_throws_exception() throws Exception {
        //GIVEN
        String input = null;
        //WHEN
        expr = Expression.parse(input);
        //THEN
        //throws IllegalArgumentException
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void when_the_string_contain_different_symbols_than_numbers_and_operations_parse_throws_exception() throws Exception {
        //GIVEN
        String input = "123.12345 + 12 =";
        //WHEN
        expr = Expression.parse(input);
        //THEN
        //throws IllegalArgumentException
    }

    //Tests for method Expression.getOperand

    @Test
    public void when_have_expression_getOperand_returns_left_operand() throws Exception {
        // GIVEN
        String input = "12213.148198491*2=";
        // WHEN
        expr.setExpression(input);
        BigDecimal operand = expr.getOperand();
        // THEN	
        assertEquals(expr.getOperation(), '*');
        assertEquals(expr.getPosition(), 16);
        assertEquals(operand.compareTo(new BigDecimal("12213.148198491")), 0);
        assertFalse(expr.getFlagMinus());
    }

    //Tests for method Expression.selectOption

    @Test
    public void when_selectOption_has_plus_operation() throws Exception {
        // GIVEN
        String input = "1219384.1049410+1984941.109410401=";
        // WHEN
        expr.setExpression(input);
        BigDecimal operand = expr.getOperand();
        BigDecimal result = expr.selectOperation(operand);
        // THEN
        verify(expr).add(new BigDecimal("1219384.1049410"));
        verify(expr).calculate();
        verify(expr).selectOperation(new BigDecimal("1984941.109410401"));

        assertEquals(expr.getOperation(), '=');
        assertEquals(expr.getPosition(), input.length());
        assertEquals(result.compareTo(new BigDecimal("1219384.1049410").add(new BigDecimal("1984941.109410401"))), 0);
    }

    @Test
    public void when_selectOption_has_minus_operation() throws Exception {
        // GIVEN
        String input = "15.12345-1031=";
        // WHEN
        expr.setExpression(input);
        BigDecimal operand = expr.getOperand();
        BigDecimal result = expr.selectOperation(operand);
        // THEN
        verify(expr).subtract(new BigDecimal("15.12345"));
        verify(expr).calculate();
        verify(expr).selectOperation(new BigDecimal("1031"));

        assertEquals(expr.getOperation(), '=');
        assertEquals(expr.getPosition(), input.length());
        assertEquals(result.compareTo(new BigDecimal("15.12345").subtract(new BigDecimal("1031"))), 0);
    }

    @Test
    public void when_selectOption_has_mult_operation() throws Exception {
        // GIVEN
        String input = "712489.9281*33598253.4323=";
        // WHEN	
        expr.setExpression(input);
        BigDecimal operand = expr.getOperand();
        BigDecimal result = expr.selectOperation(operand);
        // THEN
        verify(expr).mult(new BigDecimal("712489.9281"));
        verify(expr).selectOperation(new BigDecimal("712489.9281").multiply(new BigDecimal("33598253.4323")));

        assertEquals(expr.getOperation(), '=');
        assertEquals(expr.getPosition(), input.length());
        assertEquals(result.compareTo(new BigDecimal("712489.9281").multiply(new BigDecimal("33598253.4323"))), 0);
    }

    @Test
    public void when_selectOption_has_divide_operation() throws Exception {
        // GIVEN
        String input = "2817512.4242/24021495.93853=";
        // WHEN	
        expr.setExpression(input);
        BigDecimal operand = expr.getOperand();
        BigDecimal result = expr.selectOperation(operand);
        //THEN	
        verify(expr).divide(new BigDecimal("2817512.4242"));
        verify(expr).selectOperation(new BigDecimal("2817512.4242").divide(new BigDecimal("24021495.93853"), 20, BigDecimal.ROUND_HALF_UP));

        assertEquals(expr.getOperation(), '=');
        assertEquals(expr.getPosition(), input.length());
        assertEquals(result.compareTo(new BigDecimal("2817512.4242").divide(new BigDecimal("24021495.93853"), 20, BigDecimal.ROUND_HALF_UP)), 0);
    }

    //Test for method Expression.add

    @Test
    public void when_call_add_method_it_returns_total() throws Exception {
        // GIVEN
        String input = "123.294842+109401.01491=";
        // WHEN	
        expr.setExpression(input);
        expr.getOperand();
        BigDecimal result = expr.add(new BigDecimal("123.294842"));
        // THEN	
        verify(expr).calculate();
        verify(expr).selectOperation(new BigDecimal("109401.01491"));

        assertEquals(expr.getPosition(), input.length());
        assertFalse(expr.getFlagMinus());
        assertEquals(result.compareTo(new BigDecimal("123.294842").add(new BigDecimal("109401.01491"))), 0);
    }

    //Test for method Expression.subtract

    @Test
    public void when_call_subtract_method_it_returns_subtraction() throws Exception {
        // GIVEN
        String input = "123.294842-109401.01491=";
        // WHEN	
        expr.setExpression(input);
        expr.getOperand();
        BigDecimal result = expr.subtract(BigDecimal.valueOf(123.294842));
        // THEN
        verify(expr).calculate();
        verify(expr).selectOperation(new BigDecimal("109401.01491"));

        assertEquals(expr.getPosition(), input.length());
        assertTrue(expr.getFlagMinus());
        assertEquals(result.compareTo(new BigDecimal("123.294842").subtract(new BigDecimal("109401.01491"))), 0);
    }

    //Test for method Expression.mult

    @Test
    public void when_call_mult_method_it_returns_multiplication() throws Exception {
        // GIVEN
        String input = "123.294842*109401.01491=";
        // WHEN
        expr.setExpression(input);
        expr.getOperand();
        BigDecimal result = expr.mult(new BigDecimal("123.294842"));
        // THEN
        verify(expr).selectOperation(new BigDecimal("123.294842").multiply(new BigDecimal("109401.01491")));

        assertEquals(expr.getPosition(), input.length());
        assertFalse(expr.getFlagMinus());
        assertEquals(result.compareTo(new BigDecimal("123.294842").multiply(new BigDecimal("109401.01491"))), 0);
    }

    //Test for method Expression.devide

    @Test
    public void when_call_devide_method_it_returns_quotient() throws Exception {
        // GIVEN
        String input = "123.294842*109401.01491=";
        // WHEN
        expr.setExpression(input);
        expr.getOperand();
        BigDecimal result = expr.divide(new BigDecimal("123.294842"));
        // THEN
        verify(expr).selectOperation(new BigDecimal("123.294842").divide(new BigDecimal("109401.01491"), 20, BigDecimal.ROUND_HALF_UP));

        assertEquals(expr.getPosition(), input.length());
        assertFalse(expr.getFlagMinus());
        assertEquals(result.compareTo(new BigDecimal("123.294842").divide(new BigDecimal("109401.01491"), 20, BigDecimal.ROUND_HALF_UP)), 0);
    }

    
     @Test (expectedExceptions = ArithmeticException.class)
     public void when_call_devide_method_and_second_operand_is_0_it_returns_exceptions() throws Exception {
    	 // GIVEN
    	 String input = "123.294842/0=";
    	 //WHEN
    	 expr.setExpression(input);
    	 expr.getOperand();
    	 expr.divide(new BigDecimal("123.294842"));
    	 // THEN
    	 // throws AritmethicException
    } 

    //Test for method Expression.calculate

    @Test
    public void when_the_string_is_correct_expression_calculate_calculated_it_test1() throws Exception {
        // GIVEN
        String input = "12-0.34-0.66-100.5*2*3+12000/2/5/10/3/4+7=";
        // WHEN
        expr.setExpression(input);
        BigDecimal result = expr.calculate();
        // THEN
        verify(expr).selectOperation(new BigDecimal("12"));

        assertEquals(result.compareTo(new BigDecimal("-575")), 0);
    }

    @Test
    public void when_the_string_is_correct_expression_calculate_calculated_it_test2() throws Exception {
        // GIVEN
        String input = "0.45*2+0.1+1*2*3*4-0-5-4-3-2-1-10/2*12/3/2*8-1+23+10.2=";
        // WHEN
        expr.setExpression(input);
        BigDecimal result = expr.calculate();
        // THEN
        verify(expr).selectOperation(new BigDecimal("0.45"));

        assertEquals(result.compareTo(new BigDecimal("-37.8")), 0);
    }

    @Test
    public void when_the_string_is_correct_expression_calculate_calculated_it_test3() throws Exception {
        //GIVEN
        String input = "100+20.05*1*2*4-10-20+5.91841123143235521-5.91841123143235521/1+100000000.5*2*2*4-1600000008-2*3*4*1*1*6/2/3/4-1.4354356365+0.4354356365-24/6/2*13-12=";
        //WHEN
        expr.setExpression(input);
        BigDecimal result = expr.calculate();
        //THEN
        verify(expr).selectOperation(new BigDecimal("100"));

        assertEquals(result.compareTo(new BigDecimal("185.4")), 0);
    }
}
