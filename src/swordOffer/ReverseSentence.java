package swordOffer;

/**
 * @author:create by jinhuajina
 * @Time : 2019/3/26 23:36
 * @Blog : buxuandeweilai
 */
public class ReverseSentence {

    //把整个字符串翻转，然后把每个单词翻转（需要定位到每个单词的start和end索引）
    public String reverseSentence(String str){
        if(str==null || str.length()<1) return str;
        char [] chars = str.toCharArray();
        reverse(chars,0,chars.length-1);
        int start = 0, end = 0;
        while(start<chars.length){
            if(chars[start]==' '){
                start++;
                end++;
            }else if(chars[end]==' '){
                reverse(chars,start,--end);
                start = ++end;
            }else if(end==chars.length-1){
                reverse(chars,start,end);
                start = ++end;
            }else end++;
        }
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        while(start<end){
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
//            System.out.println(0);
        }
    }

    public static void main(String[] args) {
        String res = new ReverseSentence().reverseSentence("student! a am I");
        System.out.println(res);
    }
}
