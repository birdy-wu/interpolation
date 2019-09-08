package Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.AbstractShape;
import utils.Circle;
import utils.Line;

public class MyTest {
    private static double x_0; // 起始点
    private static double y_0;
    private static double x_1; // 终止点
    private static double y_1;
    private static double step; // 考虑到步长也可能是小于 1 的小数
    private static AbstractShape shape;// 直线或者四分之一圆弧

    private static void initData(String dataStr) {
        String[] lineDataStrs = dataStr.split(",");
        x_0 = Double.parseDouble(lineDataStrs[0]);
        y_0 = Double.parseDouble(lineDataStrs[1]);
        x_1 = Double.parseDouble(lineDataStrs[2]);
        y_1 = Double.parseDouble(lineDataStrs[3]);
        step = Double.parseDouble(lineDataStrs[4]);
    }

    private static void testInterpolation(String lineDataStr) {// ie: 'G01
                                                                // (2,3,7,8) 1'

        // 初始化数据
        String[] lineDataStrs = lineDataStr.split("\\s+");

        String type = lineDataStrs[0];

        Pattern pattern = Pattern.compile("^\\(|\\)$");
        Matcher matcher = pattern.matcher(lineDataStrs[1]);
        String[] dataStrs = matcher.replaceAll("").split(",");
        x_0 = Double.parseDouble(dataStrs[0]);
        y_0 = Double.parseDouble(dataStrs[1]);
        x_1 = Double.parseDouble(dataStrs[2]);
        y_1 = Double.parseDouble(dataStrs[3]);

        step = Double.parseDouble(lineDataStrs[2]);

        // 计算
        if (type.equalsIgnoreCase("G01")) {
            shape = new Line(x_0, y_0, x_1, y_1, step);// 多态
        } else if (type.equalsIgnoreCase("G02")) {
            shape = new Circle(x_0, y_0, x_1, y_1, step, 1);
        } else {
            shape = new Circle(x_0, y_0, x_1, y_1, step, 0);
        }
        shape.interpolation();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please input command line (ie: 'G01 (2,3,7,8) 1):");
        testInterpolation(in.nextLine());
        in.close();
    }

}

