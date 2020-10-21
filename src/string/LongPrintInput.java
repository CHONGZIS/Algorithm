package string;

public class LongPrintInput {

    public static void main(String[] args) {
        String name = "vtkgn";
        String typed = "vttkgnn";
        System.out.println(isLongPressedName(name, typed));
    }

    public static boolean isLongPressedName(String name, String typed) {

        char[] names = name.toCharArray();
        char[] typeds = typed.toCharArray();
        int i = 0, j = 0;
        while (i < names.length && j < typeds.length) {
            if (names[i] == typeds[j]) {
                j++;
            } else {
                int h = i > 0 ? i - 1 : i;
                if (names[h] == typeds[j]) {
                    while (j < typeds.length && names[h] == typeds[j]) {
                        j++;
                    }
                } else {
                    return false;
                }
                i--;
            }
            i++;
        }
        while (j != typeds.length && names[names.length - 1] == typeds[j]) {
            j++;
        }
        return i == names.length && j == typeds.length;
    }

    public boolean isLongPressedNameOptimal(String name, String typed) {
        // 看了题解发现自己思路太蠢，name[i]!=typed[j]的时候，typed完全可以typed[j] == typed[j-1]，只j++往右走，
        // 有可能i到头了，j还剩下一大串，所以while判断j<typed.length，
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
