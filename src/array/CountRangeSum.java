package array;

/**
 *327. 区间和的个数
 * https://leetcode-cn.com/problems/count-of-range-sum/
 * */
public class CountRangeSum {
    public static void main(String[] args) {
        int[] nums = {-2147483647,0,-2147483647,2147483647};
        int lower = -564;
        int upper = 3864;
        new CountRangeSum().countRangeSum(nums, lower, upper);
    }
    public int countRangeSum(int[] nums, int lower, int upper) {
        // 任意两点的区间和暴力法需要O(n^2)，改用前缀和，区间和可以转为前缀和之差，sum[i...j] = preSum(i) - preSum(j),时间复杂度O(1)即可解
        // 本题的题意就是 lower <= preSum(i) - preSum(j) <= upper，
        // 找出前缀和数组上复合条件的（i.j）对的个数，逐一判断还是O(n^2)
        // 借助归并排序，达到时间复杂度可优化为O(nlogn)，
        // 注意排序的是前缀和数组，至于为什么可以使用归并排序，因为每个i,都需要和数组上其他的所有数一一比较，所以在递归的每一层调换位置并不影响结果，具体看题解（）
        // 归并的过程，利用同一数组的有序性，省去了在同一半数组间的重复比较，

        int len = nums.length;
        long[] preSum = new long[len + 1]; // 必须多申请一位，preSum[1] - preSum[0]表示num[0,0]的区间和
        for (int i = 1; i < len + 1; i++) {
            preSum[i] = preSum[i-1] + nums[i - 1];
        }
        int res =
        countRangeSumRecur(preSum, lower, upper, 0, len);
        System.out.println(res);
        return  res;
    }

    public int countRangeSumRecur(long[] preSum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2 ;
        int leftRes = countRangeSumRecur(preSum, lower, upper, left, mid);
        int rightRes = countRangeSumRecur(preSum, lower, upper, mid + 1, right);
        int res = leftRes + rightRes;

        // 计算复合条件的(i,j)对
        int l = left;
        while (l <= mid) { // 遍历左边的每一个i
            int rFirst = mid + 1;
            int rSecond =  mid + 1;

            // 找到右边复合条件的区间
            while (rFirst <= right && preSum[rFirst] - preSum[l] < lower) {
                rFirst++;
            }
            while (rSecond <= right && preSum[rSecond] - preSum[l] <= upper) { // 思考为什么是 <=
                rSecond++;
            }
            res += rSecond - rFirst;
            l++;
        }

        // 排序
        long[] sort = new long[right - left + 1];
        l = left;
        int r = mid + 1;
        int i = 0;
        while (l <= mid && r <= right) {
            if (preSum[l] <= preSum[r]) {
                sort[i++] = preSum[l++];
            } else {
                sort[i++] = preSum[r++];
            }
        }
        while (l <= mid) {
            sort[i++] = preSum[l++];
        }
        while (r <= right) {
            sort[i++] = preSum[r++];
        }
        System.arraycopy(sort, 0, preSum, left, sort.length);
        return res;
    }
}
