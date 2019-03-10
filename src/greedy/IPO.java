package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * IPO问题，若你是公司投资决策者，公司现有资金W，市场上现在有N个项目，已知每个项目所需的投资金额和净利润，要求每次只能做投资金额小于现有资金W的项目，每做完一个项目，现有资金等于本金+利润，一次只能做一个项目，
 * 问最多只能做K个项目的情况下，如何决策做项目的先后顺序使得收益最大，并求出最终获得的最大钱数
 * 输入 各项目所需资金数组，各项目利润数组 ，最多能做项目k,现有资金w
 * 输出 最大钱数
 *
 * 解题思路：保证每次根据公司现有的资金做利润最大的项目，利用大小根堆，1.先把项目所需花费小于现有资金W的所有项目按照项目花费金额构建小根堆，依此弹出堆头部并根据利润大小构建大根堆，
 * 2.做掉大根堆堆顶的项目;3.此时现有资金等于原资金+利润，重复1，2步骤，直至做到K个项目，注意有可能做不了K个项目
 *
 * */

public class IPO {

    //为每个项目构建数据结构，类似链表节点
    public static class Node{
        public int c;//每个项目花费
        public int p;//每个项目利润

        public Node(int p,int c) {
            this.c = c;
            this.p = p;
        }
    }

    public static int findMaxCapital(int k,int w, int[] profits,int [] capitals){
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(profits[i],capitals[i]);
        }
        //构建小根堆
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.c - o2.c;//根据项目花费升序
            }
        });
        //构建大根堆
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((o1,o2) ->{
            return o2.p - o1.p;//根据项目利润降序
        });
        for (int i = 0; i < nodes.length ; i++) {
            minHeap.add(nodes[i]);//先将所有项目根据花费值放进小根堆

        }
        for(int i = 0;i<k;i++){//最多做K个项目
            while(!minHeap.isEmpty() && minHeap.peek().c <=w) {//确保有项目，防止堆为空，造成空指针异常，把当前资金能做的项目都弹出，压入根据利润排序的大根堆
                maxHeap.add(minHeap.poll());
            }
            if(maxHeap.isEmpty()){//如果大根堆没有项目，说明没有当前资金能做的项目或者项目都做完了，直接返回，不用在做k个项目就提前结束了
                return w;
            }
            w += maxHeap.poll().p;//此时说明有项目可做，做大根堆堆顶的项目，此项目收益最大，做完后弹出大根堆并更新资金

        }

        return w;
    }

    public static void main(String[] args) {
        int [] profits = {7,8,11,15};//利润
        int [] capital = {13,34,2,9};//花费
        int w = 10, k = 3;
        System.out.println(findMaxCapital(k,w,profits,capital));
    }
}
