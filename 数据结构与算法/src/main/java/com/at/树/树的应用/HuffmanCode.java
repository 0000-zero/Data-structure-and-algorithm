package com.at.树.树的应用;


import java.io.*;
import java.util.*;

/**
 * @author zero
 * @create 2020-12-16 10:31
 */
public class HuffmanCode {

    //文件压缩
    public static void zipFile(String scrFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;

        //文件输出流
        FileInputStream is = null;

        try {

            is = new FileInputStream(scrFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];

            //读取文件
            is.read(b);
            //压缩文件
            byte[] zip = zipData(b);

            //创建文件的输出流，存放文件
            os = new FileOutputStream(dstFile);
            //创建文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //将赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(zip);
            //将编码表也写入压缩文件
            oos.writeObject(hufCode);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void unZipFile(String zipFile, String dstFile) {

        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);

            //读取压缩后的数组
            byte[] zipcode = (byte[]) ois.readObject();
            //获取编码表
            Map<Byte, String> code = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] decode = decode(code, zipcode);

            //将解码后的byte写入dstFile文件
            os = new FileOutputStream(dstFile);
            os.write(decode);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }


    }


    public static void main(String[] args) {

        zipFile("D:\\Snipaste_2020-06-04_00-13-24.jpg","D:\\1.zip");

        unZipFile("D:\\1.zip","D\\1.jpg");
        System.out.println("ok~~");


//        String str = "i like like like java do you like a java";
//        byte[] bytes = "i like like like java do you like a java".getBytes();

        /*
        1.根据赫夫曼编码压缩数据的原理，创建 "i like like like java do you like a java" 对应的赫夫曼树
        2.生成赫夫曼树对应的赫夫曼编码  , 如下表: =01 a=100 d=11000 ...
        3.使用赫夫曼编码来生成赫夫曼编码数据 ,即按照上面的赫夫曼编码，将"i like like like java do you like a java"   字符串生成对应的编码数据, 形式如下.

         */

//        //创建赫夫曼树
//        CodeNode root = getHuffmanThree(bytes);
//        preOrder(root);
//
//        //通过赫夫曼树获取编码表
//        getHuffmanCode(root);
//
//        //压缩数据
//        byte[] zip = zip(bytes);
//        System.out.println(Arrays.toString(zip));

        /*  压缩 */
//        byte[] zipData = zipData(bytes);
//        System.out.println("压缩后的发送的数据:" + Arrays.toString(zipData));
//        System.out.println("编码表:" + hufCode);
//        System.out.println(stringBuilder);

        /* 压缩 */



        /* 解压 */
//        byteToBitString((byte)-1);
//        byte[] decode = decode(hufCode, zipData);
//        System.out.println(new String(decode));



        /* 解压 */


        //============================
//        byte[] zipData = zipData(str);
//        System.out.println(Arrays.toString(zipData));

    }

    /* =====================解压======================= */
    //1.将 [-88, -65, ...] 逆转为 二进制字符串
    //2.通过二进制字符串与编码表获取源数据的一个字节数组

    /**
     * @param hufCode 编码表
     * @param zipCode 压缩后得到的字节数组
     * @return
     */
    private static byte[] decode(Map<Byte, String> hufCode, byte[] zipCode) {

        StringBuilder bitString = new StringBuilder();

        //通过压缩后最终的字节数组逆转回二进制字符串
        for (int i = 0; i < zipCode.length; i++) {

            boolean flag = i == zipCode.length - 1;

            bitString.append(byteToBitString(!flag, zipCode[i]));
        }


        //反转编码表
        HashMap<String, Byte> code = new HashMap<>();
        for (Map.Entry<Byte, String> entry : hufCode.entrySet()) {
            code.put(entry.getValue(), entry.getKey());
        }


        List<Byte> bytes = new ArrayList<>();
        for (int i = 0; i < bitString.length(); ) {

            boolean flag = true;
            int count = 1;
            Byte aByte = null;

            while (flag) {
                String key = bitString.substring(i, i + count);
                aByte = code.get(key);
                if (aByte == null) {
                    //没有匹配到
                    count++;
                } else {
                    //匹配到了
                    flag = false;
                }
            }

            bytes.add(aByte);
            i += count;

        }

        byte[] bys = new byte[bytes.size()];

        for (int i = 0; i < bys.length; i++) {
            bys[i] = bytes.get(i);
        }

        return bys;

    }


    /**
     * 将一个 byte 转成一个二进制字符串
     *
     * @param b    传入需要转换的 byte
     * @param flag 标志是否需要补高位
     * @return 该 byte 对应的二进制的字符串（补码形式返回）
     */
    private static String byteToBitString(boolean flag, byte b) {

        int temp = b;//将 byte 转 int

        if (flag) {//如果是最后一个字节就无需补高位
            temp |= 256; //按位与 256  1 0000 0000 | 0000 0001 =》 1 0000 00001
        }

        String str = Integer.toBinaryString(temp);

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    /* =====================解压======================= */

    /* =====================压缩======================= */

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

    /* =====================压缩======================= */


    public static void preOrder(CodeNode root) {
        if (root == null) {
            System.out.println("空空!!!");
            return;
        }

        root.preOrder();
    }


}


class CodeNode extends HNode {

    //data 数据
    Byte data;

    public CodeNode(int val) {
        super(val);
    }

    public CodeNode(Byte data, int val) {
        super(val);
        this.data = data;
    }


    @Override
    public String toString() {
        return "CodeNode[" +
                "data=" + data +
                ", val=" + val +
                ']';
    }
}
