package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReversePairs {
    public static void main(String[] args) {
      /*  int[] nums = {1,3,2,3,1};
        new ReversePairs().reversePairs(nums);*/
        String[] word1 = {"ab", "c"};
        String[] word2 = {"a", "bc"};
        int[] nums = {2,1,6,4};
        int res = new ReversePairs().waysToMakeFair1(nums);
        System.out.println(res);
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (String word : word1) {
            s1.append(word);
        }


        for (String word : word2) {
            s2.append(word);
        }
        return s1.toString().equals(s2.toString()) ;
    }
    public int reversePairs(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        // 归并排序
        if (nums.length == 0) {
            return 0;
        }
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(0);
        return 0;
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);


        // 排序
        int[] sort = new int[right - left + 1];
        int cur = 0;
        int l = left;
        int r = mid + 1;
        while (l <= mid && r <= right) {
            sort[cur++] = nums[l] <= nums[r] ? nums[l++] : nums[r++];
        }
        while (l <= mid) {
            sort[cur++] = nums[l++];
        }
        while (r <= right) {
            sort[cur++] = nums[r++];
        }
        System.arraycopy(sort, 0, nums, left, sort.length);
    }


    // 前缀和后缀和
    public int waysToMakeFair1(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }
        int[] preSum = new int[len];
        int[] pre1 = new int[len];
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                pre += nums[i];
                pre1[i] = pre;
            }
            preSum[i] = i == 0 ? nums[i] : preSum[i-1] + nums[i];
        }

        int[] tailSum = new int[len];
        int[] tail = new int[len];
        pre = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] % 2 != 0) {
                pre += nums[i];
                tail[i] = pre;
            }
            tailSum[i] = i == len - 1 ? nums[i] : tailSum[i+1] + nums[i];
        }

        int sum1 = 0;
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                sum = tailSum[1];
                sum1 = sum - pre1[1];
            } else if (i == len - 1) {
                sum = len - 2 >= 0 ? preSum[len - 2] : 0;
                sum1 = pre1[len - 2] == 0 ? pre1[len - 3] : pre1[len - 2];
            } else {
                if (i % 2 != 0) {
                    sum1 = i+1 < len ? tailSum[i + 1] - tail[i + 1] : 0;
                    sum1 += i - 2 >= 0 ? preSum[i - 2] : 0;
                } else {
                   sum1 = i - 2 >= 0 ? preSum[i - 2] : 0;
                   sum1 += i+1 <len ? tailSum[i + 1] - tail[i + 1] : 0;
                }
            }
            cnt = sum1 * 2 == sum ? cnt + 1 : cnt;
        }

        return cnt;
    }
}
