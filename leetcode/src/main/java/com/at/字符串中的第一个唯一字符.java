package com.at;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 * @create 2020-12-23 19:30
 */
public class 字符串中的第一个唯一字符 {


    /*
        387. 字符串中的第一个唯一字符
        给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。



        示例：

        s = "leetcode"
        返回 0

        s = "loveleetcode"
        返回 2


        提示：你可以假定该字符串只包含小写字母。
     */
    public static void main(String[] args) {

        System.out.println(firstUniqChar2("loveleetcode"));

    }

    public static int firstUniqChar2(String s) {

        //可改成数组
        HashMap<Character,Integer> fre = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            fre.put(s.charAt(i),fre.getOrDefault(s.charAt(i),0)+1);
        }

        for (int i = 0; i < s.length(); i++) {
            if(fre.get(s.charAt(i)) == 1){
                return i;
            }
        }

        return -1;
    }

    /*
    执行用时：37 ms , 在所有 Java 提交中击败了26.02%的用户
    内存消耗：38.9 MB, 在所有 Java 提交中击败了75.44%的用户
     */
    public static int firstUniqChar1(String s) {

        for (int i = 0; i <s.length(); i++) {
            if(s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))){
                return i;
            }
        }

        return -1;

    }

    /*
        执行用时：285 ms, 在所有 Java 提交中击败了5.02%的用户
        内存消耗：47.9 MB , 在所有 Java 提交中击败了 5.04%的用户
     */
    public static int firstUniqChar(String s) {

        Map<Integer, ArrayList<Character>> all = new HashMap<>();
        ArrayList<Character> characters = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            characters.add(s.charAt(i));
            ArrayList list = new ArrayList();
            list.add(s.charAt(i));
            all.put(i, list);
        }


        for (Integer key : all.keySet()) {

            if (characters.size() == 0) break;

            int start = characters.size();
            characters.removeAll(all.get(key));
            int end = characters.size();

            if ((start - end) == 1) {
                return key;
            }
        }

        return -1;

    }
}
