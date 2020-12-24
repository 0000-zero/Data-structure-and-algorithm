---
typora-root-url: document
---

# Data-structure-and-algorithm
数据结构与算法



### Stack

#### 	中缀表达式

```
中缀表达示求值 ex:9+20*6-902+90
	1.定义一个符号栈，一个数值栈
	2.向左依次检索表达式
		2.1：如果为数值
			判断表达式的下一位是不是数字
				不是  直接入栈
				是	 拼接多位数	 
		2.2：如果为符号
			符号栈为空或当前符号优先级大于栈顶的符号直接入栈
             优先级小或等
             	pop 出数栈Nstack中的两个数和pop出符号栈顶的符号 
             	将数与符号操作后的结果压入数栈中
             	再将符号压入符号栈中
     3.最后数值栈中剩余的数即为结果
```

#### 	

#### 	后缀表达式

```
一：中缀表达是转后缀表达式 ex:1+((20+3)x4)-5
	1.将表达式字符串转为一个ArrayList集合 [1,+,(,(,20,+,3,),x,4,),-,5]
	2.将表达式集合转为一个后缀表达式
		2.1：操作数 直接压入操作数栈
		2.2：符号
			当前符号为")",当前符号优先级小于符号栈栈顶符号，其他情况
			2.2.1：其他情况
                       符号栈为空
                       或当符号为"("
                       或者符号栈栈顶为"("
                       或当符号的优先级大于栈顶的符号
                       直接入栈
		    2.2.2：当前符号为")",当前符号优先级小于或等于符号栈栈顶符号
		    		  1.如果当前符号为")"，pop出符号栈中的符号加入操作数栈中，
		    		  	直到符号栈为空或符号栈中的符号为"(",最后pop出"("
		    		  2.当前符号优先级小于符号栈栈顶符号
		    		  	pop出符号栈中的符号压入操作数栈中，直到符号栈为空或符号栈中的符号优先
		    		  	级大于等于当前符号
		    2.2.1：将符号栈中的符号依次pop出压入操作数栈		  		

二：逆波兰表达式的计算 [1, 20, 3, +, 4, x, +, 5, -]
	遍历逆波兰表达式，如果是数字则压入栈中，如果是符号pop出栈中的两个数，将数与符号操作后的结果压
	入数栈中，最后栈中的数值就为结果
	
```



### 递归

​	自己调用自己，目标朝着退出的条件逼近

​	阶乘, 迷宫,8皇后 , 汉诺塔...

  ```
8皇后（两个皇后不能在同一行同一列同一斜线）
 {0 , 4, 7, 5, 2, 6, 1, 3} 
八皇后问题算法思路分析
第一个皇后先放第一行第一列
第二个皇后放在第二行第一列、然后判断是否OK， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适位置
继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
说明：
	理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题. arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3} //对应arr 下标 表示第几行，即第几个皇后，arr[i] = val , val 表示第i+1个皇后，放在第i+1行的第val+1列

	public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }
    //放置第n个皇后
    public boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //arr[n] == arr[i] 同列
            //Math.abs(n-i)==Math.abs(arr[n]-arr[i]) 对角线
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

贪心算法优化

  ```



### 排序

![34567890987654321](/imgs/34567890987654321.png)

![Snipaste_2020-12-13_12-46-21](/imgs/Snipaste_2020-12-13_12-46-21.jpg)





#### 交换排序(冒泡&快排)

冒泡

​	相邻元素比较，如果第一个大于第二个，交换位置
​	每次都会找出最大的哪一个

![849589-20171015223238449-2146169197](/imgs/849589-20171015223238449-2146169197.gif)

```java
for (int i = 0; i < arr.length - 1; i++) {
  //标识变量，表示是否进行过交换
  boolean flag = false;
  // 内存循环的次数依次减少，每次大的循环都会将最大的数排在最右侧，所以在下次排序是就无需比较这些数
  for (int j = 0; j < arr.length - 1 - i; j++) {
    // 如果为逆序(从左至右)则调换两者的位置
    if (arr[j] > arr[j + 1]) {
      //如果进行过交换则将标识变量置为false
      flag = true;
      int temp = arr[j + 1];
      arr[j + 1] = arr[j];
      arr[j] = temp;
    }
  }
  //如果 flag 为false 则表示在一次的的循环中没有交换一次数据  则可以表明该数组已经为有序排列了就无需再次比较
  if (!flag) {
    break;
  } 

}
```



快速排序

快速排序（Quicksort）是对冒泡排序的一种改进。

基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列

![Snipaste_2020-12-12_20-46-00](/imgs/Snipaste_2020-12-12_20-46-00.jpg)

```java
public static void quick(int[] arr, int left, int right) {

  int l = left;// 左下标
  int r = right;// 右下标
  int pivot = arr[(left + right) / 2];// 中轴值

  int temp = 0;

  while (l < r) {

    // 在 pivot 的左边一直找，直到找到大于等于 pivot 的值才退出
    while (arr[l] < pivot) {
      l++;
    }

    // 在 pivot 的右边一直找，直到找到小于等于 pivot 的值才退出
    while (arr[r] > pivot) {
      r--;
    }

    // 如果 l>=r 说明 pivot 的左右两边的值，已经按照左边全是小于 pivot 的值，右边全是大于等于 pivot 的值
    if (l >= r) {
      break;
    }

    temp = arr[r];
    arr[r] = arr[l];
    arr[l] = temp;

    // 交换完后 如果 arr[r] = pivot 让 l 向后移动
    if (arr[r] == pivot) {
      l++;
    }

    // 交换完后 如果 arr[l] = pivot 让 r 向前移动
    if (arr[l] == pivot) {
      r--;
    }
  }

  // 如果 l=r 必须 l++ r-- 否则会出现栈溢出
  if (l == r) {
    l++;
    r--;
  }

  // 向右递归
  if (left < r) {
    quick(arr, left, r);
    System.out.println(Arrays.toString(arr));
  }

  // 向左递归
  if (right > l) {
    quick(arr, l, right);
    System.out.println(Arrays.toString(arr));
  }

}
```



#### 插入排序(直接插入&希尔)

直接插入

把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表

![849589-20171015225645277-1151100000](/imgs/849589-20171015225645277-1151100000.gif)

```java
for (int i = 1; i < arr.length; i++) {
  // 需要插入的数据 或 需要排序的数
  int insertVal = arr[i];
  // 插入的数据的前一个数的索引 即要插入的元素与前面要比较的元素的索引
  int insertIndex = i - 1;

  /*
   * 1.insertIndex >= 0 保证在查找插入位置过程中下标不越界 <br>
   * 2.insertVal < arr[insertIndex]待插入的位置的数大于要插入的数 <br>
   */
  while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
    // 3.将待插入位置的数后移
    arr[insertIndex + 1] = arr[insertIndex];
    // 4.将索引向前移动 继续寻在
    insertIndex--;
  }

  // 当循环结束即 insertIndex 的后一个位置就为要插入的位置
  if (insertIndex != i - 1) {
    arr[++insertIndex] = insertVal;
  }
}
```

希尔排序

希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止

![234564321343221](/imgs/234564321343221.png)

```java
// gap 为每次的步长  gap/= 2 为分组数
for (int gap = arr.length / 2; gap > 0; gap /= 2) {
  for (int i = gap; i < arr.length; i++) {
    // 遍历各组中所有的元素(共 5 组，每组两个元素)，步长为 5 即(8,3)(9,5)(1,4)(7,6)(2,0)
    for (int j = i - gap; j >= 0; j -= gap) {
      if (arr[j] > arr[j + gap]) {
        temp = arr[j];
        arr[j] = arr[j + gap];
        arr[j + gap] = temp;
      }
    }
  }
}
```

#### 选择排序(简单选择&堆排序)

选择排序

基本思想是：第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…, 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列。

![849589-20171015224719590-1433219824](/imgs/849589-20171015224719590-1433219824.gif)

```java
for (int i = 0; i < arr.length - 1; i++) {
  int minIndex = i;
  int minVal = arr[minIndex];
  for (int j = i + 1; j < arr.length; j++) {
    if (minVal < arr[j]) {
      minIndex = j;
      minVal = arr[j];
    }
  }
  if (minIndex != i) {
    arr[minIndex] = arr[i];
    arr[i] = minVal;
  }

}
```



堆排序

- 将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
- 将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
- 由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。

![849589-20171015231308699-356134237](/imgs/849589-20171015231308699-356134237.gif)



```java
/*
堆排序的基本思想是：
将待排序序列构造成一个大顶堆
此时，整个序列的最大值就是堆顶的根节点。
将其与末尾元素进行交换，此时末尾就为最大值。
然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
*/

public static void heapSort(int[] arr) {

  //将待排序序列构造成一个大顶堆
  for (int i = arr.length / 2 - 1; i >= 0; i--) {
    heap(arr, i, arr.length);
  }

  int temp = 0;
  //交换 此时根节点为最大的节点 将其与末尾元素进行交换，让末尾为最大值
  for (int j = arr.length - 1; j > 0; j--) {
    temp = arr[0];
    arr[0] = arr[j];
    arr[j] = temp;
    //继续调整
    heap(arr, 0, j);
  }
}

/**
     * @param arr
     * @param i      调整的起始位置
     * @param length 需要调整的长度
     */
public static void heap(int[] arr, int i, int length) {
  int temp = arr[i];
  //从树的最后一个非叶子节点(arr.length/2-1) 开始调整从下往上
  for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
    //如果当前节点的右节点大于左节点 则让k指向右节点
    if (k + 1 < length && arr[k + 1] > arr[k]) {
      k++;
    }
    //叶子节点大于非叶子节点，叶子节点上移，成为非叶子节点
    if (arr[k] > temp) {
      arr[i] = arr[k];
      i = k;
    } else {
      break;
    }
  }
  arr[i] = temp;
}


```





#### 归并排序

算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。

![9876543256789](/imgs/9876543256789.png)

![Snipaste_2020-12-12_21-03-02](/imgs/Snipaste_2020-12-12_21-03-02.jpg)



![849589-20171015230557043-37375010](/imgs/849589-20171015230557043-37375010.gif)

```java

/**
     * 分 + 合
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
public static void mergetSort(int[] arr,int left,int right,int[] temp) {
  if(left < right) {
    int mid = (left + right) / 2;//中间索引
    //向左递归
    mergetSort(arr, left, mid, temp);
    //向右递归
    mergetSort(arr, mid + 1, right, temp);
    //每次分完都合并
    merget(arr, left, mid, right, temp);
  }
}

/**
     * 合并的方法
     *
     * @param arr 原始数组
     * @param left	左边有序序列的初始索引
     * @param mid	中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
public static void merget(int[] arr,int left,int mid,int right,int[] temp) {

  int i = left;//初始化 i 左边有序序列的初始索引
  int j = mid + 1;//初始化 j 右边有序序列的初始索引
  int t = 0;//指向 temp 数组的当前索引

  //第一步
  //先将左右两边(有序)的数据按照规则填充到 temp 数组  直到左右两边的有序序列有一边处理完毕
  while(i <= mid && j <= right) {
    if(arr[i] <= arr[j]) {
      //左边小于右边的，将左边的填充到 temp 数组中 t,i 分别后移
      temp[t] = arr[i];
      i++;
      t++;
    }else {
      //反之 左边大于右边的，将右边的填充到 temp 数组中 t,j 分别后移
      temp[t] = arr[j];
      j++;
      t++;
    }
  }

  //第二步
  //将剩余数据的一边的数据依次全部填充到temp中
  while(i <= mid) {//左边有剩余
    temp[t] = arr[i];
    t++;
    i++;
  }
  while(j <= right) {//右边有剩余
    temp[t] = arr[j];
    t++;
    j++;
  }

  //第三步
  //将temp数组的元素拷贝到 arr 数组中     注意:并不是每次都拷贝所有
  t = 0;
  int tempLeft = left;

  /*
         * 第一次合并：tempLeft = 0,right = 1
         * 第二次合并：tempLeft = 2,right = 3
         * 第三次合并：tempLeft = 0,right = 3
         *
         * 第四次合并：tempLeft = 4,right = 5
         * 第四次合并：tempLeft = 6,right = 7
         * 第四次合并：tempLeft = 4,right = 7
         *
         * 第四次合并：tempLeft = 0,right = 7
         *
         */

  while(tempLeft <= right) {
    arr[tempLeft] = temp[t];
    t++;
    tempLeft++;
  }
}

```



#### 基数排序(桶排序)

将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列

![Snipaste_2020-12-12_21-07-44](/imgs/Snipaste_2020-12-12_21-07-44.jpg)

```java
public static void bucket(int[] arr) {
  int[][] bucket = new int[10][arr.length];
  // 为记录每个桶中实际存放了多少数据，定义一个一维数组来记录各个桶的每次放入的数据个数
  int[] bucketElementCounts = new int[10];

  //获取最大数的位数
  int max = arr[0];
  for (int i = 1; i < arr.length; i++) {
    if (arr[i] > max) {
      max = arr[i];
    }
  }
  int maxLength = (max + "").length();


  for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

    for (int j = 0; j < arr.length; j++) {

      int digitOfElement = arr[j] / n % 10;
      // 将数据放入桶中
      bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
      bucketElementCounts[digitOfElement]++;
    }

    // 按照桶的顺序(一维数组的下标依次取出数据，放入原来数组)
    int index = 0;
    for (int k = 0; k < bucketElementCounts.length; k++) {

      // 如果桶中有数据，才将其放回原数组
      if (bucketElementCounts[k] != 0) {
        // 循环该桶，将数据返回数组
        for (int l = 0; l < bucketElementCounts[k]; l++) {
          // 取出元素放入数组
          arr[index++] = bucket[k][l];
        }
      }
      // 将桶中数据返回原数组后需要将桶清空 bucketElementCounts[k] = 0
      bucketElementCounts[k] = 0;

    }
  }
}
```



### 查找

#### 顺序查找

​	从前向后或从后向前一个一个查找，找到返回下标，没有找到则返回-1

#### 二分查找

**有序**

```java
public static int binarySearch(int[] arr, int left, int right, int val) {
    if (left > right) {
        return -1;
    }
    int mid = (left + right) / 2;
    int midVal = arr[mid];
    if (val > midVal) {// 向右查找
        return binarySearch(arr, mid + 1, right, val);
    } else if (val < midVal) {
        return binarySearch(arr, left, mid - 1, val);
    } else {
        return mid;
    }
}
```

#### 插值查找

**有序  分布较均匀**

![Snipaste_2020-12-14_18-33-39](/imgs/Snipaste_2020-12-14_18-33-39.jpg)

```java
public static int interpolationSearch(int[] arr, int left, int right, int val) {
    if (left > right || val < arr[left] || val > arr[right]) {
        return -1;
    }

    int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);

    if (val < arr[mid]) {
        return interpolationSearch(arr, left, mid - 1, val);
    } else if (val > arr[mid]) {
        return interpolationSearch(arr, mid + 1, right, val);
    } else {
        return mid;
    }
}
```



#### 斐波那契查找

**有序**

![Snipaste_2020-12-14_18-35-23](/imgs/Snipaste_2020-12-14_18-35-23.jpg)



### 哈希表

![Snipaste_2020-12-14_19-58-01](/imgs/Snipaste_2020-12-14_19-58-01.jpg)



```
有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,名字,住址..),当输入该员工的id时,要求查找到该员工的 所有信息.

要求: 
不使用数据库,,速度越快越好=>哈希表(散列)
添加时，保证按照id从低到高插入  [思考：如果id不是从低到高插入，但要求各条链表仍是从低到高，怎么解决?]
使用链表来实现哈希表, 该链表不带表头[即: 链表的第一个结点就存放雇员信息] 
思路分析并画出示意图
代码实现[增删改查(显示所有员工，按id查询)]

```

![Snipaste_2020-12-14_19-59-09](/imgs/Snipaste_2020-12-14_19-59-09.jpg)



详情参考HashMap



### 二叉树

#### 遍历

前序遍历: 先输出父节点，再遍历左子树和右子树

中序遍历: 先遍历左子树，再输出父节点，再遍历右子树

后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点

```java
//前序遍历  ==>   先输出父节点，再遍历左子树和右子树
public void preOrder(){
    System.out.println(this);
    //向左遍历
    if(this.left != null){
        this.left.preOrder();
    }
    //向右遍历
    if(this.right != null){
        this.right.preOrder();
    }
}
//中序遍历 ==>  先遍历左子树，再输出父节点，再遍历右子树
public void infixOrder(){
    //向左遍历
    if(this.left != null){
        this.left.infixOrder();
    }
    System.out.println(this);
    //向右遍历
    if(this.right != null){
        this.right.infixOrder();
    }
}
//后序遍历 ==>  先遍历左子树，再遍历右子树，最后输出父节点
public void postOrder(){
    //向左遍历
    if(this.left != null){
        this.left.postOrder();
    }
    //向右遍历
    if(this.right != null){
        this.right.postOrder();
    }
    System.out.println(this);
}
```



#### 查找

```java
//前
public HNode preSearch(int no){
    //判断当前节点的 no 是否等于要查找的 no
    if(this.no == no){
        return this;
    }
    HNode res = null;
    //向左
    if(this.left != null){
        res = this.left.preSearch(no);
    }
    // 如果在左子树中查找到了 则 resNode 的结果就不为 null 将其返回
    if (res != null) {
        return res;
    }
    //向右
    if(this.right != null){
        res = this.right.preSearch(no);
    }
    return res;
}

//中
public HNode infixSearch(int no){
    HNode res = null;
    //左
    if(this.left != null){
        res = this.left.infixSearch(no);
    }
    // 判断左子树是否查找到结果
    if(res != null){
        return res;
    }
    // 判断当前节点是不是要查找的节点
    if(this.no == no){
        return this;
    }
    //右
    if(this.right != null){
        res = this.right.infixSearch(no);
    }
    return res;

}

//后
public HNode postSearch(int no){
    HNode res = null;
    if (this.left != null){
        res = this.left.postSearch(no);
    }
    if(res != null){
        return res;
    }
    if(this.right != null){
        res = this.right.postSearch(no);
    }
    if(res != null){
        return res;
    }
    if(this.no == no){
        return this;
    }
    return res;
}
```



#### 删除节点

####  

#### 顺序存储二叉树

![Snipaste_2020-12-15_11-38-57](/imgs/Snipaste_2020-12-15_11-38-57.jpg)

```java
public void preOrderStore(int index) {
    if (isEmpty()) return;

    /*
        第n个元素的左子节点为  2 * n + 1
        第n个元素的右子节点为  2 * n + 2
        第n个元素的父节点为  (n-1) / 2
     */

    System.out.println("要存储的元素：" + arr[index]);

    if ((2 * index + 1) < arr.length) {
        preOrderStore(index * 2 + 1);
    }
    if ((2 * index + 2) < arr.length) {
        preOrderStore(2 * index + 2);
    }
}
```



#### 线索化二叉树

https://blog.csdn.net/weixin_44760145/article/details/106065880







### 树结构应用

#### 堆排序



#### 赫夫曼树

```
构成赫夫曼树的步骤：
    1.从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
    2.取出根节点权值最小的两颗二叉树
    3.组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
    4.再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
```

```java
public static HNode huffmanThree(int[] arr){

  ArrayList<HNode> list = new ArrayList<>();

  for (int i = 0; i < arr.length; i++) {
    list.add(new HNode(arr[i]));
  }


  while (list.size() > 1) {
    //排序
    Collections.sort(list);

    //从有序的集合中取出最小的两个节点
    HNode leftN = list.get(0);
    HNode rightN = list.get(1);

    //构建最小的两个节点的父节点，并把他们构建成一颗树
    HNode parent = new HNode(leftN.val + rightN.val);
    parent.left = leftN;
    parent.right = rightN;

    //移除两个最小的节点并把新构建的二叉树的父节点加入集合
    list.remove(leftN);
    list.remove(rightN);
    list.add(parent);

    //一直循环直到list 中只剩一个节点 即树的根节点
  }

  return list.get(0);

}	
```



#### 赫夫曼编码

1.先构建一颗赫夫曼树

2.获取编码表

```java
static Map<Byte, String> hufCode = new HashMap<>();
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
```

**压缩**

压缩数据，通过编码表获取一个二进制字符串,根据二进制字符串将其转化为字节数组,即压缩后的数据

```java
static StringBuilder stringBuilder = new StringBuilder();
//压缩数据
public static byte[] zip(byte[] bytes) {
    //通过bytes 获取数据
    if (bytes == null) return null;
    //10101000....
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
```

**解压**

1.根据压缩后的的字节数组逆转为二进制字符串

```java
/**
 * 将一个 byte 转成一个二进制字符串
 *
 * @param b    传入需要转换的 byte
 * @param flag 标志是否需要补高位
 * @return 该 byte 对应的二进制的字符串（补码形式返回）
 */
private static String byteToBitString(boolean flag, byte b) {

    int temp = b;
	//如果是最后一个字节就无需补高位
    if (flag) {
        temp |= 256; //按位与256  1 0000 0000 | 0000 0001 => 1 0000 00001
    }

    String str = Integer.toBinaryString(temp);

    if (flag) {
        return str.substring(str.length() - 8);
    } else {
        return str;
    }
}
```

```java
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
```



### 二叉排序树

二叉排序树：BST:(Binary Sort(Search) Tree), 对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。

特别说明：如果有相同的值，可以将该节点放在左子节点或右子节点

**创建**

```java
public void bstAdd(HNode node) {
    //当前节点为空直接返回
    if (null == node) return;

    //左小右大

    //不为空添加
    if (this.val > node.val) {
        //如果当前节点的左子节点为空 则直接挂在左子节点
        if (null == this.left) this.left = node;
            //递归的向左子树添加
        else this.left.bstAdd(node);
    } else {
        if (null == this.right) this.right = node;
        else this.right.bstAdd(node);
    }
}
```

**删除**

删除叶子节点

      思路
        (1) 需求先去找到要删除的结点  targetNode
        (2)  找到targetNode 的 父结点 parent
        (3)  确定 targetNode 是 parent的左子结点 还是右子结点
        (4)  根据前面的情况来对应删除
            左子结点 parent.left = null
            右子结点 parent.right = null;
第二种情况: 删除只有一颗子树的节点 

      思路
        (1) 需求先去找到要删除的结点  targetNode
        (2)  找到targetNode 的 父结点 parent
        (3) 确定targetNode 的子结点是左子结点还是右子结点
        (4) targetNode 是 parent 的左子结点还是右子结点
        (5) 如果targetNode 有左子结点
            5. 1 如果 targetNode 是 parent 的左子结点
            parent.left = targetNode.left;
            5.2  如果 targetNode 是 parent 的右子结点
        	parent.right = targetNode.left;
        (6) 如果targetNode 有右子结点
            6.1 如果 targetNode 是 parent 的左子结点
            parent.left = targetNode.right;
            6.2 如果 targetNode 是 parent 的右子结点
            parent.right = targetNode.right		
情况三 ： 删除有两颗子树的节点. (比如：7, 3，10 )

    思路
    (1) 需求先去找到要删除的结点  targetNode
    (2)  找到targetNode 的 父结点 parent
    (3)  从targetNode 的右子树找到最小的结点(右子树找最大的节点)
    (4) 用一个临时变量，将 最小(最大)结点的值保存 temp = 11
    (5)  删除该最小结点
    (6)  targetNode.value = temp
```java
/* 查找某个节点 */
public HNode getTarget(int target) {
    if (this.val == target) {
        //当前节点就为要删除的节点
        return this;
    } else if (this.val > target) {
        //查找的值小与当前节点
        //当前节点的左节点不为空 向左递归寻找
        if (this.left == null) return null;
        return this.left.getTarget(target);
    } else {
        //查找的值大于或等于当前节点
        //向右递归查找
        if (this.right == null) return null;
        return this.right.getTarget(target);
    }
}
```

```java
/* 查找某个节点的直接父节点 */
public HNode getDirectParent(int target) {
    //当前节点即为要查找的节点的父节点
    if ((this.left != null && this.left.val == target) || (this.right != null && this.right.val == target)) {
        return this;
    } else {
        if (this.val > target && this.left != null) {
            //小于当前节点的值，向左递归查找
            return this.left.getDirectParent(target);
        } else if (this.val <= target && this.right != null) {
            //大于或等于当前节点的值
            return this.right.getDirectParent(target);
        } else {
            return null;
        }
    }

}
```



```java
public void delNode(int val) {
    if (isEmpty(root)) return;
    /*
        删除节点
        思路
            (1) 需求先去找到要删除的结点  targetNode
            (2) 找到targetNode 的 父结点 parent

     */
    HNode tar = getTar(val);
    HNode parent = getDirtParent(val);

    //没有找到要删除的节点
    if (isEmpty(tar)) return;

    //当二叉树只有一个节点并且这个节点还是要删除的节带点直接将root置空
    if(root.left == null && root.right == null) {
        root= null;
        return;
    }

    if (tar.left == null && tar.right == null) {
        /*
         删除叶子节点 (比如：2, 5, 9, 12)
            思路
            (1) 需求先去找到要删除的结点  targetNode
            (2)  找到targetNode 的 父结点 parent
            (3)  确定 targetNode 是 parent的左子结点 还是右子结点
            (4)  根据前面的情况来对应删除
                左子结点 parent.left = null
                右子结点 parent.right = null;
         */

        //左右节点都为空
        //判断要删除的节点时左节点还时右节点
        if (parent.left != null && parent.left.val == tar.val) {
            //要删除的时左子节点
            parent.left = null;
        } else if (parent.right != null && parent.right.val == tar.val) {
            //要删除的节点在右
            parent.right = null;
        }
    } else if (tar.left != null && tar.right != null) {
        /*
        情况三 ： 删除有两颗子树的节点. (比如：7, 3，10 )
            思路
            (1) 需求先去找到要删除的结点  targetNode
            (2)  找到targetNode 的 父结点 parent
            (3)  从targetNode 的右子树找到最小的结点(右子树找最大的节点)
            (4) 用一个临时变量，将 最小(最大)结点的值保存 temp = 11
            (5)  删除该最小结点
            (6)  targetNode.value = temp
         */

        //获取到当前节点的右子树上的最小节点并将其删除
        int temp = getRightTreeMin(tar.right);
        tar.val = temp;
    } else {
        /*
        第二种情况: 删除只有一颗子树的节点 比如 1
            思路
            (1) 需求先去找到要删除的结点  targetNode
            (2)  找到targetNode 的 父结点 parent
            (3) 确定targetNode 的子结点是左子结点还是右子结点
            (4) targetNode 是 parent 的左子结点还是右子结点
            (5) 如果targetNode 有左子结点
                5. 1 如果 targetNode 是 parent 的左子结点
                parent.left = targetNode.left;
                5.2  如果 targetNode 是 parent 的右子结点
                parent.right = targetNode.left;
            (6) 如果targetNode 有右子结点
                6.1 如果 targetNode 是 parent 的左子结点
                parent.left = targetNode.right;
                6.2 如果 targetNode 是 parent 的右子结点
                parent.right = targetNode.right
         */
        if (tar.left != null) {
            //要删除的节点只有左子树
            if(parent != null){

                if (parent.left.val == tar.val) {
                    //要删除的节点是parent的左节点
                    parent.left = tar.left;
                } else {
                    //要删除的节点是parent的右节点
                    parent.right = tar.left;
                }
            }else{
                root = tar.left;
            }
        } else {
            //要删除的节点只有右子树
            if(parent != null){
                if (parent.left.val == tar.val) {
                    parent.left = tar.right;
                } else {
                    parent.right = tar.right;
                }
            }else{
                root = tar.right;
            }
        }
    }
}
```



### 平衡二叉树

二叉排序树存在的问题

![Snipaste_2020-12-18_23-07-07](/imgs/Snipaste_2020-12-18_23-07-07.jpg)





```java
/*  统计树的高度 */
public int treeHeight() {
    return Math.max(left == null ? 0 : ((AVLNode) left).treeHeight(), right == null ? 0 : ((AVLNode) right).treeHeight()) + 1;
}

/* 计算左子树高度 */
public int leftHeight() {
    if (left == null) return 0;
    return ((AVLNode) left).treeHeight();
}

/* 计算右子树高度 */
public int rightHeight() {
    if (right == null) return 0;
    return ((AVLNode) right).treeHeight();
}

/* 左旋转 移动右边的向左去平衡*/
public void leftRotate() {

    //创建一个新的节点等于当前节点的值
    AVLNode newNode = new AVLNode(val);
    //将新节点的左节点设置为当前节点的左节点
    newNode.left = left;
    //将新节点的右节点设置为当前节点的右节点的左节点
    newNode.right = right.left;
    //将当前节点的权值设置为当前节点的右节点的权值
    val = right.val;
    //将当前节点的左节点设置为新的节点
    left = newNode;
    //将当前节点的右节点设置为当前节点的右节点的右节点
    right = right.right;

}


/* 右旋转 移动左边的向右去平衡*/
public void rightRotate() {

    //创建一个新节点
    AVLNode newNode = new AVLNode(val);
    //将新节点的右节点设置为当前节点的右节点
    newNode.right = right;
    //将新节点的左节点的设置为当前节点的左节点的右节点
    newNode.left = left.right;
    //将当前节点的权值设置为左节点的权值
    val = left.val;
    //将当前节点的右节点设置为新的节点
    right = newNode;
    //将当前节点的左节点设置为当前节点的左节点的左节点
    left = left.left;

}


/* 平衡二叉树添加 */
public void avlAdd(AVLNode node) {
    if (this.val > node.val) {
        if (this.left == null) this.left = node;
        else ((AVLNode) this.left).avlAdd(node);
    } else {
        if (this.right == null) this.right = node;
        else ((AVLNode) this.right).avlAdd(node);
    }

    //添加一个节点后判断是否左右子树的高度差大于1
    int i = rightHeight();
    int i1 = leftHeight();

    //右大于左向左旋转
    if (rightHeight() - leftHeight() > 1) {
        //如果它的右子树的左子树高度大于它的右子树的右子树高度
        if(right != null && ((AVLNode)right).leftHeight() > ((AVLNode)right).rightHeight()){
            //先对右子树进行右旋转
            ((AVLNode) right).rightRotate();
        }

        leftRotate();
        return;
    }

    //左大于右向右旋转
    if (leftHeight() - rightHeight() > 1) {
         /*
            1. 当符号右旋转的条件时
            2. 如果它的左子树的右子树高度大于它的左子树的高度
            3. 先对当前这个结点的左节点进行左旋转
            4. 在对当前结点进行右旋转的操作即可
         */
         //如它的左子树的右子树高度大与左子树的高度
         if(left != null && ((AVLNode)left).rightHeight() > ((AVLNode)left).leftHeight()){
             //先对当前节点的左子树左旋转
             ((AVLNode)left).leftRotate();
         }
         //然后在对当前节点右旋转
        rightRotate();
    }


}
```





### 图

#### 深度优先

#### 广度优先

































































































