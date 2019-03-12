package swordOffer;

/**
 * @author:create by jinhuajina
 * @Time : 2019/3/12 21:08
 * @Blog : buxuandeweilai
 */
/**
 *题目描述
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class UrglyNumber {
    public int GetUglyNumber_Solution(int index) {
        // 只含质因子 2 3 5 丑数 ， 丑数肯定由丑数乘上 2 ，3 5得到的，开辟一个长度为index+1的数组来放丑数
        // 第一个丑数是1，放入数组，第二个丑数是min(1*2，1*3 ，1*5) = 2, 2放入集合
        // 维护三个队列
        // 2(a[0]*2) 4(a[1]*2) 4(a[1]*2) 6(a[2]*2) 8(a[3]*2)
        // 3(a[0]*3) 3(a[0]*3) 6(a[1]*3) 6(a[1]*3) 9(a[2]*3)
        // 5(a[0]*5) 5(a[0]*5) 5(a[0]*5) 5(a[0]*5) 10(a[1]*5)
        //丑数 2=a[1]    3=a[2]     4=a[3]     5=a[4]   6=a[5]
        if(index<0) throw new RuntimeException("input is invalid");
        int[] arr = new int[index+1];
        int p2 = 1, p3 = 1, p5 = 1;
        if(index>0) arr[1] = 1;
        for(int i=2 ;i<=index;i++){
            arr[i] = Math.min(arr[p2]*2,Math.min(arr[p3]*3,arr[p5]*5));
            if(arr[i]==arr[p2]*2) p2++;
            if(arr[i]==arr[p3]*3) p3++;
            if(arr[i]==arr[p5]*5) p5++;
        }
        return arr[index];
    }
}
