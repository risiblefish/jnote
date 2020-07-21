import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: java-simple-demos
 * @description:
 * @author: Unuts
 * @create: 2020-06-26 09:30
 **/

public class Demo2 {


    public static void main(String[] args) {
        printTriangle(5);
    }


    public static void printArgs(int a, double b, String ...c){
        System.out.println(a);
        System.out.println(b);
        System.out.println(Arrays.toString(c));
    }

    public static  void printNineNineTable() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                int curr = i * j;
                System.out.print(String.format("%s * %s = %s ", i, j, i * j));
                if (curr < 10) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 输入三角形的高度
     *
     * @param height
     */
    public static void printTriangle(int height) {
        //当前层数左边空格数 = 总层数 - 当前层数，即：height - curr
        //当前层数非空格数 = 2 * 当前层数 - 1
        //i表示当前层数
        for (int i = 1; i <= height; i++) {
            printLine(height - i, i + i - 1);
        }
    }

    public static void printLine(int leftBlankCount, int count) {
        //每次 = 左边空格 + 实体 + 右边空格，其中左边空格数量=右边空格
        StringBuilder sb = new StringBuilder();
        StringBuilder leftBlank = new StringBuilder();
        for (int i = 0; i < leftBlankCount; i++) {
            leftBlank.append(" ");
        }
        sb.append(leftBlank);
        for (int i = 0; i < count; i++) {
            sb.append("*");
        }
        sb.append(leftBlank);
        System.out.println(sb.toString());
    }
}
