package com.example.demo.algorithm.year24.demo2407;


import java.util.Stack;

public class Demo0710 {

    public static void main(String[] args) {
        calculate calculate = new calculate();
        int value = calculate.calculate("1+1");
        System.out.println(value);
    }


}

// 224 AC
//    给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
//    注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
// (1+(4+5+2)-3)+(6+8)

//1-(600-(400-(51+27))-(3+2))+(6+8)  1-600+400-51-27+5+6+8
class calculate {

    public int calculate(String s) {
        int flag = 1;
        int result = 0;
        int length = s.length();

        Stack<Integer> flagStack = new Stack<>();
        flagStack.push(flag);

        for (int i = 0; i < length; ) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
            } else if (c == '-') {
                flag = -flagStack.peek();
                i++;
            } else if (c == '+') {
                flag = flagStack.peek();
                i++;
            } else if (c == '(') {
                flagStack.push(flag);
                i++;
            } else if (c == ')') {
                flagStack.pop();
                i++;
            } else {
                long tempVal = 0;
                while (i < length && Character.isDigit(s.charAt(i))) {
                    tempVal = tempVal * 10 + s.charAt(i) - '0';
                    i++;
                }
                result += (int) (flag * tempVal);
            }
        }

        return result;
    }



    /*
     *)
     *8
     *+
     *6
     *(
     * +
     * )
     * 3
     * -
     * )
     * 2
     * +
     * 5
     * +
     * 4
     * (
     * +
     * 1
     * (
     * */
}
