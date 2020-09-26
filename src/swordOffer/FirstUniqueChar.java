package swordOffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author:create by jinhuajina
 * @Time : 2019/3/13 20:28
 * @Blog : buxuandeweilai
 */
/**
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 * 思路：固定套路，遍历一遍字符串数组，把每个元素和对应出现的次数存进hashMap,再遍历一次hashMap，找到第一个value==1的字符的索引，
 * 新解利用ArrayList按序存放的特点，遍历数组，判断元素不在List中的，直接放入，已经在List中的，删除List中的相同元素，
 * 利用ArrayList的特点，前面的元素被删，后面的整体向前移，所以list中如果不为空，则list的第一位肯定是第一次出现且只出现一次的字符
 */


public class FirstUniqueChar {
    public int FirstNotRepeatingChar(String str) {
        // hashMap 先过一遍key 值 value 次数
        if(str==null || str.length()<1) return -1;
        HashMap<Character,Integer> map = new HashMap<>();
        char[] arr = str.toCharArray();
        for(int i=0;i<arr.length;i++){// 必须 遍历一遍才能知道是否只出现一次
            if(map.containsKey(arr[i])){
                map.put(arr[i],map.get(arr[i])+1);
            }else{
                map.put(arr[i],1);
            }
        }
        int fir = -1;
        for(int i=0;i<arr.length;i++){
            if(map.get(arr[i])==1){
                fir = i;
                break;
            }
        }
        return fir;
    }
    public int FirstNotRepeatingChar_1(String str) {
        if(str==null || str.length()==0) return -1;
        List<Character> list = new ArrayList<>();
        for(int i=0;i<str.length();i++){
            if(list.contains(str.charAt(i))){
                list.remove(Character.valueOf(str.charAt(i)));//删的时候要封装，不然不存在这个
               //list.remove(str.charAt(i));
                // ClassCastException if the type of the specified element//     *         is incompatible with this list
            }else{
                list.add(str.charAt(i));//装的时候可以自动装箱?
            }

        }
        return list.isEmpty() ? -1 : str.indexOf(list.get(0));
    }

    public static void main(String[] args) {
        FirstUniqueChar f = new FirstUniqueChar();
        String s = "google";
        int res = f.FirstNotRepeatingChar_1(s);
        System.out.println(res);
    }
}
