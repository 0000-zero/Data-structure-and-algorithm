package com.at.哈希表;

import java.util.Scanner;

/**
 * @author zero
 * @create 2020-12-14 19:08
 */
public class HashTabTest {

    public static void main(String[] args) {

        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add：添加");
            System.out.println("list：显示");
            System.out.println("update：更新");
            System.out.println("delete：删除");
            System.out.println("exit：退出");

            key = scanner.next();
            int id = -1;
            String name = "";
            switch (key) {
                case "add":
                    System.out.println("输入id:");
                    id = scanner.nextInt();
                    System.out.println("输入 name：");
                    name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "update":
                    System.out.println("要修改的 emp 的 id：");
                    id = scanner.nextInt();
                    System.out.println("输入 name：");
                    name = scanner.next();
                    hashTab.update(id, name);
                    break;
                case "delete":
                    System.out.println("要删除的 emp 的 id：");
                    id = scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    break;
            }

        }

    }
}

class HashTab {

    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        // 初始化 EmpLinkedList[] 数组的大小
        empLinkedListArray = new EmpLinkedList[size];

        // 初始化每一个 EmpLinkedList
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }

    }

    // 散列函数
    public int empFun(int id) {
        return id % size;
    }

    // 添加
    public void add(Emp emp) {
        // 通过散列函数定位要添加到第几条链
        int empLinkedNo = empFun(emp.getId());

        empLinkedListArray[empLinkedNo].add(emp);

    }

    public Emp get(int id) {
        int empLinkedNo = empFun(id);
        Emp emp = empLinkedListArray[empLinkedNo].get(id);
        return emp;
    }

    //修改
    public void update(int id, String name) {

//		Emp emp = get(id);
//
//		if(emp == null) {
//			System.out.println("没有该 id 的 emp ，修改失败");
//		}

        int empLinkedNo = empFun(id);


        boolean update = empLinkedListArray[empLinkedNo].update(id, name);

        if (update) {
            System.out.println("修改成功：" + get(id));
        } else {
            System.out.println("修改失败");
        }

    }

    /**
     * @param id 要删除的 emp 的 id
     */
    public void delete(int id) {

        int empLinkedNo = empFun(id);

        boolean delete = empLinkedListArray[empLinkedNo].delete(id);

        if (delete) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }

    }

    // 遍历
    public void list() {

        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

}

/**
 * 创建一个 Emp 链表 管理当前链的 Emp
 *
 * @author wen
 */
class EmpLinkedList {
    // 每行 Emp 的头指针，指向该链的第一个 Emp
    private Emp head = null;
//	Emp first = null;

    /**
     * 添加 Emp 每一条链都按 Emp 的 id 大小排列(小--》大)
     *
     * @param emp
     */
    public void add(Emp emp) {

        // 如果是添加第一个，直接用头节点指向该节点
//		if (first == null) {
//			first = emp;
//			head = emp;
//			return;
//		}
        if (head == null) {
//			first = emp;
            head = emp;
            return;
        }

        Emp tEmp = head;

        while (true) {

            // 要添加的 Emp 的 id 小于头节点的 id
//			if (first.getId() > emp.getId()) {
//				emp.setNext(first);
//				first = emp;
//				head = emp;
//				break;
//			}
            if (head.getId() > emp.getId()) {
                emp.setNext(head);
//				first = emp;
                head = emp;
                break;
            }


            // 链表中间
            if (tEmp.getNext() != null && tEmp.getNext().getId() > emp.getId()) {
                emp.setNext(tEmp.getNext());
                tEmp.setNext(emp);
                break;
            }

            // 链表末尾
            if (tEmp.getNext() == null) {
                tEmp.setNext(emp);
                break;
            }


            tEmp = tEmp.getNext();

        }

    }

    //通过 id 获取指定 Emp
    public Emp get(int id) {

        if (head == null) {
            return null;
        }

        Emp tEmp = head;

        while (true) {

            if (tEmp.getId() == id) {
                break;
            }

            if (tEmp.getNext() == null) {
                tEmp = null;
                break;
            }

            tEmp = tEmp.getNext();
        }

        return tEmp;
    }

    /**
     * @param id   需要修改 Emp 的 id
     * @param name 需要修改为的 name
     * @return
     */
    public boolean update(int id, String name) {

        Emp emp = get(id);

        if (emp != null) {
            emp.setName(name);
            return true;
        } else return false;

    }


    /**
     * @param id
     * @return
     */
    public boolean delete(int id) {

        Emp emp = get(id);

        // 没有该id 的 emp
        if (emp == null) {
            return false;
        }

        // 有对应的 emp
        Emp tEmp = head;

        while (true) {
            // 如果删除的是头结点
            if (emp == head) {
//				if (emp.getNext() != null) {
////					first = emp.getNext();
//					head = emp.getNext();
//				} else {
////					first = null;
//					head = null;
//				}
                head = emp.getNext();
                break;
            }

            //因为一定有要删除的 emp 所以不存在死循环情况
//			if (tEmp.getNext() != null && tEmp.getNext().getId() == id) {
            if (tEmp.getNext().getId() == id) {
                tEmp.setNext(tEmp.getNext().getNext());
                break;
            }

            tEmp = tEmp.getNext();

        }

        return true;
    }


    /**
     * 遍历
     *
     * @param no
     */
    public void list(int no) {
        // 判断是否为空
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }

        Emp tempEmp = head;
        System.out.print("第" + (no + 1) + "条链表：");

        while (true) {
            //能到这一步说明至少有一个(head)数据
            System.out.print("--> " + tempEmp);

            if (tempEmp.getNext() == null) {
                break;
            }

            tempEmp = tempEmp.getNext();

        }
        System.out.println();
    }

}

/**
 * 创建一个 Emp 类
 *
 * @author wen
 */
class Emp {

    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp [id=" + id + ", name=" + name + "]";
    }

}