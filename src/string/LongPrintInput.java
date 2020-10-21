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
}
