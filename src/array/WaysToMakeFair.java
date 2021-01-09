package array;

public class WaysToMakeFair {
    /**
     * 利用前缀和，主不过这里要想到奇数前缀和偶数前缀和，最不绕的两个前缀和
     * 删除的是位置i（i之后的偶数变奇数，奇数变偶数）
     * 奇数和 = i之前前的奇数前缀和 + i后的偶数前缀和
     * 偶数和 = i之前的偶数前缀和 + i后的奇数前缀和
     * 具体计算方式
     * 删除的i位置为奇数
     * oddSum = preOddSum[i](0...i-1之和) + (preEventSum[n] - preEventSum[i] - nums[i] )(i + 1 .... n 之和)
     * eventSum = preEvenSum[i] + (preOddSum[n] - preOddSum[i]
     *
     * 删除的i位置为偶数
     * oddSum = preOddSum[i](0...i-1之和)  + (preEventSum[n] - preEventSum[i])
     * eventSum = preEvenSum[i]  + (preOddSum[n] - preOddSum[i] - num[i])
     */
    public int waysToMakeFair(int[] nums) {
        int n = nums.length + 1;
        int[] preOddSum = new int[n];
        int[] preEventSum = new int[n];

        for (int i = 1; i < n; i++) {
            if ((i - 1 & 1) == 0) { // 奇数
                preOddSum[i] = preOddSum[i - 1] + nums[i - 1];
                preEventSum[i] = preEventSum[i - 1]; // 注意：奇数位置i给他填充i-1的偶数前缀和便于和nums[i]计算 preEvent[i * 2 - 1] =  [i * 2 - 2]
            } else {
                preOddSum[i] = preOddSum[i - 1] ;
                preEventSum[i] = preEventSum[i - 1] + nums[i - 1] ;
            }
        }

        int count = 0;

        n--;
        for (int i = 0; i < n; i++) {
            int oddSum = preOddSum[i] + preEventSum[n] - preEventSum[i+1];
            int eventSum = preEventSum[i] + preOddSum[n] - preOddSum[i+1];
            /*if ((i & 1) != 0 ) { // 删除偶数索引i
                oddSum = preOddSum[i] + (preEventSum[n] - preEventSum[i] - nums[i]);
                eventSum = preEventSum[i] + (preOddSum[n] - preOddSum[i]);
            } else {
                oddSum = preOddSum[i] + (preEventSum[n] - preEventSum[i]);
                eventSum = preEventSum[i] + (preOddSum[n] - preOddSum[i] - nums[i]);
            }*/
            count = oddSum == eventSum ? count + 1 : count;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,6,4};
        int res = new WaysToMakeFair().waysToMakeFair(nums);
        System.out.println(res);
    }
}
