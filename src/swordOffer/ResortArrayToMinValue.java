package swordOffer;

/**
 * @author:create by jinhuajina
 * @Time : 2019/3/11 20:45
 * @Blog : buxuandeweilai
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
 * 则打印出这三个数字能排成的最小数字为321323。
 */
public class ResortArrayToMinValue {

    public String PrintMinNumber_0(int [] numbers) {
        //把每个元素转为字符串，依此添加进ArrayList，用Collections.sort给List集合的元素按照 ["已组成数"+"当前元素"，"已组成数"+"当前元素"]
        //较小的组合在前的方式排列，如"23"+"5"="235" "5"+"23" = "523"，选择较小的"235"顺序
        //if(numbers==null || numbers.length<1) return null;
        // 当numbers.length=0时，数组为{}不是null，这两种情况都值得考虑,但是为{}时应该返回""，不能返回null
        if(numbers==null) return null;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            arrayList.add(numbers[i]);
        }

        Collections.sort(arrayList,((o1, o2) -> {
            String s1 = o1 + "" + o2;
            String s2 = o2 + "" + o1;
            return s1.compareTo(s2);//两种排列的字符串比较，小的放在前面,比较规则？应该是字典序
        }));

        String s = "";
        Iterator iterator = arrayList.iterator();
        while(iterator.hasNext()){
            s += iterator.next();
        }
        return s;
    }

    public String PrintMinNumber(int [] numbers) {
        //自己实现字典序
        if(numbers==null) return null;


        return "dictionary";
    }

    public static void main(String[] args) {
        int [] arr = {0,21,132,9};
        System.out.println(new ResortArrayToMinValue().PrintMinNumber_0(arr));
    }
}
