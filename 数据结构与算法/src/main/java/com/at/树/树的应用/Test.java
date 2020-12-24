package com.at.树.树的应用;

import java.util.*;

/**
 * @author zero
 * @create 2020-12-16 14:44
 */
public class Test {
    public static void main(String[] args) {

//        String str =  "10101000";
//        byte i = (byte) Integer.parseInt(str, 2);
//        System.out.println(i);

        String s = Integer.toBinaryString(-1);
        System.out.println(s.substring(s.length()-8));
        String s1 = Integer.toBinaryString(1|256);
        System.out.println(s1);



    }


    /**
     * 压缩
     *
     * @param bytes
     * @return
     */
    public static byte[] zipData(byte[] bytes) {
        CodeNode root = getHuffmanThree(bytes);
        getHuffmanCode(root);
        return zip(bytes);
    }


    //压缩数据
    public static byte[] zip(byte[] bytes) {

        //通过bytes 获取数据
        if (bytes == null) return null;

        //1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        for (byte b : bytes) {
            stringBuilder.append(hufCode.get(b));
        }

        //将 stringBuilder 转成为一个byte数组
        int len = (stringBuilder.length() + 7) / 8;

        byte[] code = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {

            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将strByte转为一个byte
            code[index++] = (byte) Integer.parseInt(strByte, 2);

        }

        return code;

    }


    //将赫夫曼编码存入一个集合中  {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> hufCode = new HashMap<>();
    // 2.在生成赫夫曼编码时表示需要拼接的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //获取编码表
    private static void getHuffmanCode(CodeNode root) {

        if (root == null) return;

        getHuffmanCode(root, "", new StringBuilder());

    }

    private static void getHuffmanCode(CodeNode node, String route, StringBuilder builderRoute) {

        /**
         * 路径：规定 左为1 右为0
         */

        StringBuilder builder = new StringBuilder(builderRoute);
        builder.append(route);

        if (node != null) {
            if (node.data == null) {
                //非叶子节点
                //向左
                getHuffmanCode((CodeNode) node.left, "0", builder);
                //向右
                getHuffmanCode((CodeNode) node.right, "1", builder);
            } else {
                //叶子节点
                hufCode.put(node.data, builder.toString());
            }
        }


    }

    //构建一颗赫夫曼树
    public static CodeNode getHuffmanThree(byte[] bytes) {

        //获取对应数据的权值键值对
        //HashMap<数据,数据出现的次数权值> {32=9, 97=5, 100=1, 101=4, 117=1, 118=2, 105=5, 121=1, 106=2, 107=4, 108=4, 111=2}
        HashMap<Byte, Integer> map = new HashMap<>();

        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }

        //构建
        List<CodeNode> nodeList = new ArrayList<>();

        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            CodeNode codeNode = new CodeNode(entry.getKey(), entry.getValue());
            nodeList.add(codeNode);
        }

        return createHuffmanThree(nodeList);

    }

    public static CodeNode createHuffmanThree(List<CodeNode> nodes) {
        //构建二叉树
        while (nodes.size() > 1) {

            Collections.sort(nodes);

            CodeNode leftN = nodes.get(0);
            CodeNode rightN = nodes.get(1);

            //父节点的data域为空
            CodeNode parent = new CodeNode(null, leftN.val + rightN.val);
            parent.left = leftN;
            parent.right = rightN;

            nodes.remove(leftN);
            nodes.remove(rightN);

            nodes.add(parent);

        }

        return nodes.get(0);
    }


    public static void preOrder(CodeNode root) {
        if (root == null) {
            System.out.println("空空!!!");
            return;
        }

        root.preOrder();
    }
}
