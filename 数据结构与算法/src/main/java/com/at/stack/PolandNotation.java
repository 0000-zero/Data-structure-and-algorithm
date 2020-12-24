package com.at.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zero
 * @create 2020-12-11 16:09
 * <p>
 * 中缀表达式转逆波兰表达式
 */
public class PolandNotation {
    public static void main(String[] args) {

        String infix = "1+((2+3)x4)-5";
//        String infix = "((4-10)x7)x(2x6/3/2+2)";

        List<String> list = infixToSuffix(infix);

        System.out.println(list+"="+cal(list));
    }

    public static int cal(List<String> list){
        Stack<String> stack = new Stack<>();

        for (String s : list) {
            if(s.matches("\\d+")){
                stack.push(s);
            }else{
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());

                int result = 0;
                if(s.equals("+")) {
                    result = num2 + num1;
                }else if(s.equals("-")) {
                    result = num2 - num1;
                }else if(s.equals("x")) {
                    result = num2 * num1;
                }else if(s.equals("/")){
                    result = num2 / num1;
                }else {
                    throw new RuntimeException("运算符不合法");
                }

                stack.push(result+"");

            }
        }

        return Integer.parseInt(stack.pop());
    }


    /**
     * 中缀表达式转后缀表达式
     *
     * @return
     */
    public static List<String> infixToSuffix(String infix) {

        List<String> infixList = getInfixList(infix);

        System.out.println(infixList);


        /*
            1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
            2) 从左至右扫描中缀表达式；

            3) 遇到操作数时，将其压s2；
            4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
            1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
            2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
            3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
            5) 遇到括号时：(1) 如果是左括号“(”，则直接压入s1(2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
            6) 重复步骤2至5，直到表达式的最右边
            7) 将s1中剩余的运算符依次弹出并压入s2
            8)  依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式 [10, +, (, (, 2, +, 3, ), ×, 40, ), -, 50]
         */

        Stack<String> s1 = new Stack<>(); //符号
        Stack<String> s2 = new Stack<>(); //数字 [4, 10, -, 7, x, 2, 6, 3, 2, /, /, x, 2, +, x]=-588

        for (String item : infixList) {

            //遇到操作数时，将其压s2；
            if(item.matches("\\d+")){
                s2.add(item);
            }else{
                //运算符 s1为空 栈顶的为（或优先级大于栈顶直接加入
                if(s1.isEmpty() || item.equals("(")|| s1.peek().equals("(") || Operation.getValue(item)>Operation.getValue(s1.peek())){
                    s1.push(item);
                }else{
                    //优先级小 和 ）
                    if(item.equals(")")){
                        while (s1.size()>=0 && !s1.peek().equals("(")){
                            s2.push(s1.pop());
                        }
                        s1.pop();
                    }else if(Operation.getValue(item)<=Operation.getValue(s1.peek())){
                        while (s1.size() !=0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                            s2.push(s1.pop());
                        }
                        s1.push(item);
                    }
                }
            }
        }

        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }


        return s2;
    }

    /**
     * 将字符串该成集合
     *
     * @return
     */
    public static List<String> getInfixList(String infix) {
        ArrayList<String> infixList = new ArrayList<>();
        char[] chars = infix.toCharArray();
        String str = "";
        for (int i = 0; i < chars.length; i++) {

            //非数字直接加入集合
            if (chars[i] < 48 || chars[i] > 57) {
                infixList.add("" + chars[i]);
            } else {
                //数字
                str += chars[i] + "";
                //判断下一位是不是最后一位并且是不是数字
                if (i >= chars.length - 1 || !(chars[i + 1] >= 48 && chars[i + 1] <= 57)) {
                    //下一位不是数字
                    infixList.add(str);
                    str = "";
                }
            }
        }
        return infixList;

    }


}
//定义一个类判断优先级的高低
class Operation{
    private static final int ADD = 1;// +
    private static final int SUB = 1;// -
    private static final int MUL = 2;// *
    private static final int DIV = 2;// /

    // 写一个方法判断优先级的高低
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "x":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;

            default:
                System.out.println("不存在该运算符："+operation);
                break;
        }
        return result;
    }

}
