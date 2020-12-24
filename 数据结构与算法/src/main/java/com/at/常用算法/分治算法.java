package com.at.常用算法;

/**
 * @author zero
 * @create 2020-12-20 13:57
 */
public class 分治算法 {

    /*
    if |P|≤n0
        then return(ADHOC(P))
    //将P分解为较小的子问题 P1 ,P2 ,…,Pk
    for i←1 to k
    do yi ← Divide-and-Conquer(Pi)   递归解决Pi
    T ← MERGE(y1,y2,…,yk)   合并子问题
    return(T)

    其中|P|表示问题P的规模；n0为一阈值，表示当问题P的规模不超过n0时，问题已容易直接解出，不必再继续分解。
    ADHOC(P)是该分治法中的基本子算法，用于直接解小规模的问题P。
    因此，当P的规模不超过n0时直接用算法ADHOC(P)求解。
    算法MERGE(y1,y2,…,yk)是该分治法中的合并子算法，用于将P的子问题P1 ,P2 ,…,Pk的相应的解y1,y2,…,yk合并为P的解

     */

    static long n = 0;

    public static void main(String[] args) {

        hanoiTower(32,'A','B','C');
        System.out.println("共移动："+n);

    }

    /**
     * 汉诺塔
     *      如果是有一个盘， A->C
     *      如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘 2. 上面的盘
     *          先把 最上面的盘 A->B
     *          把最下边的盘 A->C
     *          把B塔的所有盘 从 B->C
     *
     */
    public static void hanoiTower(int num, char a, char b, char c) {

        //一个盘
        if (num == 1) {
            n++;
//            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //多个盘

            //将最下面的一个盘与上面的盘看成两个部分
            //先将上面的num-1 个盘全部移到B
            hanoiTower(num - 1, a, c, b);
            //再将第num个盘从A移到C
            n++;
//            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //最后将B 上的全部移到C
            hanoiTower(num - 1, b, a, c);
        }

    }


}
