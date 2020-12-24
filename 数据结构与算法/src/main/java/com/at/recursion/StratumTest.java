package com.at.recursion;

/**
 * @author zero
 * @create 2020-12-12 12:16
 */
public class StratumTest {

    /**
     * 求 4*3*2*1
     */

    public static void main(String[] args) {

        StratumTest s = new StratumTest();

        /*

        设置栈空间的大小 -Xmn100m

        递归需要遵守的重要规则

        执行一个方法时，就创建一个新的受保护的独立空间(栈空间)
        方法的局部变量是独立的，不会相互影响, 比如n变量
        如果方法中使用的是引用类型变量(比如数组)，就会共享该引用类型的数据.
        递归必须向退出递归的条件逼近，否则就是无限递归,出现StackOverflowError，死龟了:)
        当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕。

         */
        System.out.println(s.stratum(5));

    }

    public int stratum(int n) {
        return n == 1 ? 1 : n * stratum(n - 1);
    }

}
