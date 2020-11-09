package Completion;

import java.util.*;

public class MaxProfit {
    public static void main(String[] args) {
        int[] inventory = //{2,8,4,10,6};
        {565259708,715164401,716563713,958255469,844600740,823949511,180479359,287829385,164248818,73361150,230686692,322986846,598720034,338241127,748922260,181241085,833659853,509571179,250093451,690995620,703292727,595636202};
//             [565259708,715164401,716563713,958255469,844600740,823949511,180479359,287829385,164248818,73361150,230686692,322986846,598720034,338241127,748922260,181241085,833659853,509571179,250093451,690995620,703292727,595636202]
//650114768
        int orders = 650114768;


        new MaxProfit().maxProfit(inventory, orders);
    }
  /*  public int maxProfit(int[] inventory, int orders) {
        // 逆序卖球，能卖最多的就卖最多的，当第一个球的价值小于第二多的球，就开始卖第二多的球，依次类推
        // 本题可以用堆,发现超时，还需要等差数列求和公式
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int invent : inventory) {
            queue.add(invent);
        }
        long ans = 0;
        while (orders > 0 && !queue.isEmpty()) {

            int first = queue.poll();
            int second = queue.isEmpty() ? first - orders : queue.peek();
            int num = first - second;
            orders -= num;
            ans += num / 2 * ((first + second + 1) % Math.pow(10, 9) + 7);
            ans %= Math.pow(10, 9) + 7;
            queue.add(second);
        }
        return (int)ans ;
    }*/

    public int maxProfit(int[] inventory, int orders) {
        // 贪心，使用堆模拟会超时，看题解发现二分查找也可行
        // 二分查找：找到最后一次卖出时球的价值x ：理由按照题意要先卖多的，当最多的颜色和次多的颜色个数相同时，随机选择一个，每次卖出的价值总是数组中所有颜色现存个数最大的颜色.
        // 此时价值分为两种情况:
        // 1.inventory[i] >= x , 从inventory[i] 减到x,可以看到这是等差数组，使用求和公式计算这部分价值
        // 2.当情况1中所有球的个数<orders,可以卖等于x的颜色的球补组未达到orders球个数，此时每个球的价格x，
        // 讨论二分查找
        // 达到最后一次卖出球价值时，到达该价值的总球数不能大于orders, 也就是第一个<=orders的
        // l = 0; r = 数组中同一颜色最多的球的个数
        int mod = (int) (Math.pow(10, 9) + 7);
        int maxNum = Arrays.stream(inventory).max().getAsInt();
        int l = 0;
        int r = maxNum;
        while (l < r) {
            int mid = (l + r) >>> 1;  // 从右往左收缩，需要取右中位数，否则容易死循环
            if (lessThanOrders(mid, inventory, orders)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        long ans = 0;
        for (int num : inventory) {
            if (num > l) {
                ans += calculateSum(l, num) % mod;
                ans %= mod;
                orders -= (num - l) ;
            }
        }
        ans += (long)orders * (long)l;
        ans %= mod;
        return (int)ans;
    }

    public boolean lessThanOrders(int mid, int[] inventory, int orders) {
        int sum = 0;
        for (int num : inventory) {
            sum += Math.max(0, num - mid);
            if (sum > orders) { // 卖出的个数已经超过总数
                return false;
            }
        }
        return true;
    }

    public long calculateSum(int l, int r) {
        return (long)(l + 1 + r) * (long)(r - l) / 2;  // 等差数列求和公式
    }
}
