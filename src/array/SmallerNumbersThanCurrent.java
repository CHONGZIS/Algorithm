package array;

public class SmallerNumbersThanCurrent {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int maxValOfNums = 0;
        for (int i = 0 ; i < nums.length; i++) {
            maxValOfNums = Math.max(maxValOfNums, nums[i]);
        }
        int[] barrel = new int[maxValOfNums + 1];
        for (int i = 0; i < nums.length; i++) {
            barrel[nums[i]]++;
        }

        // [0,1,2,1,0,0,0,0,1] 放在桶中barrel[nums[i]]表示nums[i]出现的次数
        // [0,1,3,4,4,4,4,4,5] 小于nums[i] 的个数的等于barrel[0,...nums[i-1]]之和
        for (int i = 1; i < barrel.length; i++) {
            barrel[i] += barrel[i - 1];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] != 0 ? barrel[nums[i] - 1] : 0;
        }
        return result;
    }
}
