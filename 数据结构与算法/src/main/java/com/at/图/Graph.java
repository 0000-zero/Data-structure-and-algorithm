package com.at.图;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zero
 * @create 2020-12-19 12:53
 */
public class Graph {

    //所有顶点集合
    ArrayList<String> vertexList;
    //邻接矩阵
    int[][] edges;
    //边的数量
    int numOfEdges;

    //保存当前顶点是否访问过
    boolean[] isVisited;

    public static void main(String[] args) {


        Graph graph = new Graph(9 );

//        graph.insertVertex("A");
//        graph.insertVertex("B");
//        graph.insertVertex("C");
//        graph.insertVertex("D");
//        graph.insertVertex("E");
//
//        graph.insertEdges(0,1,1);
//        graph.insertEdges(0,2,1);
//        graph.insertEdges(1,2,1);
//        graph.insertEdges(1,3,1);
//        graph.insertEdges(1,4,1);
//
//        graph.insertEdges(2,4,1);

        for (int i = 0; i < 9; i++) {
            graph.insertVertex((i)+"");
        }
//
//        graph.insertEdges(0, 1, 1);
//        graph.insertEdges(0, 2, 1);
//        graph.insertEdges(1, 3, 1);
//        graph.insertEdges(1, 4, 1);
//        graph.insertEdges(3, 7, 1);
//        graph.insertEdges(4, 7, 1);
//        graph.insertEdges(2, 5, 1);
//        graph.insertEdges(2, 6, 1);
//        graph.insertEdges(5, 6, 1);

        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);
        graph.insertEdges(3,7,1);
        graph.insertEdges(4,7,1);
        graph.insertEdges(7,8,1);
        graph.insertEdges(2,5,1);
        graph.insertEdges(2,6,1);
        graph.insertEdges(5,6,1);


        graph.show();

        System.out.println("----------------------");
        System.out.println("深度优先：");
        graph.dfs();

        System.out.println("\n----------------------");
        System.out.println("广度优先：");
        graph.bfs();


    }

    //获取index的第一个可被访问的邻接节点的下标w
    public int getFirstNeighbor(int index){
//        for (int i = index+1; i < vertexList.size(); i++) {
//            if(edges[index][i] > 0){
//                return i;
//            }
//        }
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //获取下一个邻接节点的下标
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexList.size() ; i++) {
            if(edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /*
    深度优先遍历算法步骤
        1.访问初始结点v，并标记结点v为已访问。
        2.查找结点v的第一个邻接结点w。
        3.若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个结点继续。
        4.若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v，然后进行步骤123）。
        5.查找结点v的w邻接结点的下一个邻接结点，转到步骤3。
     */
    public void dfs(boolean[] isVisited,int index){
        //访问初始节点 并将其标记为以访问
        System.out.print(getVertex(index)+"-->");
        isVisited[index] = true;

        //获取当前访问的节点的第一个邻接节点
        int w = getFirstNeighbor(index);

        while (w != -1){ //存在
            //判断有没有被访问
            if(!isVisited[w]){
                //没有被访问
                //对该节点进行深度遍历
                dfs(isVisited,w);
            }
            //如果该节点以被访问则获取上一个节点的的下一个邻接节点
            w = getNextNeighbor(index,w);
        }

    }

    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }


    /*
    广度优先遍历算法步骤
        1.访问初始结点v并标记结点v为已访问。
        2.结点v入队列
        3.当队列非空时，继续执行，否则算法结束。
        4.出队列，取得队头结点u。
        5.查找结点u的第一个邻接结点w。
        6.若结点u的邻接结点w不存在，则转到步骤3；否则循环执行以下三个步骤：
            6.1 若结点w尚未被访问，则访问结点w并标记为已访问。
            6.2 结点w入队列
            6.3 查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6。
     */
    public void bfs(boolean[] isVisited,int index){

        //队列头节点下标
        int u;
        //邻接节点
        int w;
        //队列记录访问次序
        LinkedList<Integer> queue = new LinkedList<>();

        //访问节点 标记为以访问 并加入队列
        System.out.print(getVertex(index)+"==》");
        isVisited[index] = true;
        queue.addLast(index);

        while (!queue.isEmpty()){
            //取出队列的头节点
            u = queue.removeFirst();
            //获取到u的第一个邻接节点
            w = getFirstNeighbor(u);

            while (w != -1){
                //找到
                if(!isVisited[w]){
                    System.out.print(getVertex(w)+"==》");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);
            }

        }

    }

    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }



    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges=0;
    }

    //添加顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边
    public void insertEdges(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //获取指定索引下的顶点
    public String getVertex(int index){
        return vertexList.get(index);
    }

    //遍历邻接矩阵
    public void show(){
        int i = 0;
        for (int[] edge : edges) {
            System.out.println((i++)+":"+Arrays.toString(edge));
        }
    }
    //获取边的数量
    public int getNumOfEdges(){
        return numOfEdges;
    }

}
