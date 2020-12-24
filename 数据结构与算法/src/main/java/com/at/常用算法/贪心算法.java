package com.at.常用算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author zero
 * @create 2020-12-22 20:36
 */
public class 贪心算法 {

    /*
    贪心算法介绍
        贪婪算法(贪心算法)是指在对问题进行求解时，在每一步选择中都采取最好或者最优(即最有利)的选择，
        从而希望能够导致结果是最好或者最优的算法
        贪婪算法所得到的结果不一定是最优的结果(有时候会是最优解)，但是都是相对近似(接近)最优解的结果

     */

    //贪心算法--集合覆盖问题
    public static void main(String[] args) {


        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("k1", hashSet1);
        broadcasts.put("k2", hashSet2);
        broadcasts.put("k3", hashSet3);
        broadcasts.put("k4", hashSet4);
        broadcasts.put("k5", hashSet5);

        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");




        //存放与allAreas匹配最多的那个key
        String maxKey = null;
        //
        HashSet<String> temp = new HashSet<>();
        //存放选中的
        ArrayList<String> select = new ArrayList<>();

        while (allAreas.size() != 0){

            maxKey = null;

            for (String key : broadcasts.keySet()) {

                temp.clear();

                //获取当前key中所有的数据
                HashSet<String> hashSet = broadcasts.get(key);
                //将其数据加入到一个临时数据中
                temp.addAll(hashSet);
                //再与allAreas取交集,交集会存入temp中
                temp.retainAll(allAreas);

                //判断此时的temp与maxKey那个数据多
                if(temp.size() > 0 && (maxKey == null || temp.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }

            if(maxKey != null){
                select.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }

        System.out.println(select);





    }


}
