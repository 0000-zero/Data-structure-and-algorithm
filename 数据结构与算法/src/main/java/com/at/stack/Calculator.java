package com.at.stack;

/**
 * @author zero
 * @create 2020-12-11 14:38
 */
public class Calculator {
    public static void main(String[] args) {

        String exception = "9+20*6-902+90";

        //创建两个栈
        ArrStack Nstack = new ArrStack(10);
        ArrStack Ostack = new ArrStack(10);

        int index = 0;
        char c;
        int res;

        cal(exception, Nstack, Ostack, index);

        // 当前表达式扫描完毕，就顺序的从数栈和符号栈中 pop 出相应的数和符号，并进行运算
        while (true) {
            // 如果符号栈为空则计算到最后，数栈中只有一个数也就是结果
            if (Ostack.isEmpty()) {
                break;
            }

            int num1 = Nstack.pop();
            int num2 = Nstack.pop();
            int oper = Ostack.pop();
            res = Nstack.cal(num1, num2, oper);
            // 将得到的结果入数栈
            Nstack.push(res);

        }

        System.out.printf("表达式 %s = %d", exception, Nstack.pop());
    }

    private static void cal(String exception, ArrStack nstack, ArrStack ostack, int index) {
        char c;
        int res;
        String keepNum= "";
        while (true) {
            c = exception.substring(index, index + 1).charAt(0);
            //判断该c是数字还是符号
            if (ostack.isOper(c)) {
                //符号
                //判断Ostack是否为空
                if (ostack.isEmpty()) {
                    //为空直接加入
                    ostack.push(c);
                } else {
                    //不为空先判断优先级
                    if (ostack.priority(c) > ostack.priority(ostack.peek())) {
                        //当前优先级大 直接入栈
                        ostack.push(c);
                    } else {
                        //计算
                        //先pop出Nstack中的两个数，计算后在压入Nstack栈中
                        int num1 = nstack.pop();
                        int num2 = nstack.pop();
                        int oper = ostack.pop();
                        //计算
                        res = nstack.cal(num1, num2, oper);
                        //结果符号入栈
                        nstack.push(res);
                        ostack.push(c);
                    }
                }
            } else {
                //数字 字符与数字相差48
//                nstack.push(c - 48);

                //如果数字的后一位还是数字那么就需要拼接多位数
                keepNum+=c;

                //判断是不是最后一位
                if(index==exception.length()-1){
                    nstack.push(Integer.parseInt(keepNum));
                }else{
                    if(ostack.isOper(exception.substring(index+1,index+2).charAt(0))){
                        //操作符
                        nstack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }

            }

            // index 自增
            index++;
            if (index >= exception.length()) {
                break;
            }

        }
    }


}

/**
 * 使用一个数组模拟栈
 */
class ArrStack {

    int maxSize;
    int top;
    int[] stack;

    public ArrStack(int maxSize) {
        this.maxSize = maxSize;
        top = -1;
        stack = new int[maxSize];
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //查看栈顶元素
    public int peek() {
        if (isEmpty()) throw new RuntimeException("栈空");
        return stack[top];
    }

    //存入
    public void push(int i) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = i;
    }

    //取
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }

    //查看栈中的元素
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 返回优先级，优先级用数字来表示 数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }


    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;// 用于存放计算的结果

        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;

            default:
                break;
        }

        return res;

    }


}
