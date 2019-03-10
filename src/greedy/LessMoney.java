package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 切割黄金所需要的最小代价，切成指定的大小和块数，利用哈夫曼编码
 * 举例：把12切成{3，4，5}，切法1：切成 7 和5 ，再将7切成3和4，需要小号 7 + 5 + 3 +4 = 19
 * 切法2：切成9 和 3， 再把9切成4和5，需要花费 9+3+4+5 = 21
 * 求最小切法 输入一个数组，输出最小代价
 * 思路：哈夫曼编码生成的方式逆推，利用小根堆结构，每次只结合堆中最小的两个，两个小的合成的值放进堆中，继续从堆中找出最小的两个合成，直至堆中只剩一个，也就是最初的整块金币的数量
 * */
public class LessMoney {

    public static void main(String[] args) {
        int [] arr = {3,4,5};//需要切成的4块
        System.out.println(lessMoney(arr));

        int []  arrForHeap = {8,3,5,2,7,0,1,6,4};//学习创建大/小根堆PriorityQueue

        PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
        for (int i = 0; i <arrForHeap.length ; i++) {
            minQ1.add(arrForHeap[i]);
        }
        while(!minQ1.isEmpty()){
            System.out.print(minQ1.poll()+" ");//默认堆 升序
        }
        System.out.println();

        //大根堆如何创建？
        //构造器里传入实现了Comparator比较器的对象，重写了compare方法
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        for (int i = 0; i <arrForHeap.length ; i++) {
            maxHeap.add(arrForHeap[i]);
        }
        while(!maxHeap.isEmpty()){
            System.out.print(maxHeap.poll()+" ");//大根堆 降序
        }
        System.out.println();

        //利用匿名内部类
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i <arrForHeap.length ; i++) {
            minHeap.add(arrForHeap[i]);
        }
        while(!minHeap.isEmpty()){
            System.out.print(minHeap.poll()+" ");//大根堆 降序
        }
        System.out.println();

        //利用lambda表达式构建大根堆，直接从重写方法的参数部分开始写
        PriorityQueue<Integer> maxHeap1 = new PriorityQueue<>((o1, o2) ->{
            return o2 > o1 ? 1 : o2 < o1 ? -1 : 0;// 谁小谁放前面，小的标准是两数比较返回负数，就是小, o2 > 01时返回负数，o2被认为是小，放前面，实际上o2大于o1,所以是降序
        });
        for (int i = 0; i <arrForHeap.length ; i++) {
            maxHeap1.add(arrForHeap[i]);
        }
        while(!maxHeap1.isEmpty()){
            System.out.print(maxHeap1.poll()+" ");//大根堆 降序
        }
        System.out.println();

    }
    public static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1; //负数放在前面，也就是大的
        }
    }

    private static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();//默认是小根堆
        int sum = 0,merge;
        for(int i=0;i<arr.length;i++){
            pQ.add(arr[i]);
        }
        while(pQ.size()>1){//一直到堆中只剩最后一个，也就是未切分的总数
          merge = pQ.poll() + pQ.poll();//最小的两个合并，应该底层是数组实现？每次从堆顶弹出
          sum += merge;
          pQ.add(merge);//合并值入堆
        }
        return sum;
    }
}
