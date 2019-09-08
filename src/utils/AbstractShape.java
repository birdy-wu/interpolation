package utils;

import java.text.DecimalFormat;

public abstract class AbstractShape {
    double x_0, y_0; // 起始点
    double x_1, y_1; // 终止点
    double step; // 考虑到步长也可能是小于 1 的小数
    double xe, ye; // 标准平移后的终点
    double xi, yi; // 中间点
    double x, y; // 真实点
    double fxy; // 偏差判别式的值
    int x_dir, y_dir; // 脉冲的方向
    double dx, dy; // x,y 方向上的脉冲
    DecimalFormat df=new DecimalFormat("0.0000");
    
    public abstract void interpolation();// 插补过程

    public abstract void impulse();// 往目的地走一个步长
    

    public void getdir() {
        if (x_1 >= x_0)
            x_dir = 1;
        else
            x_dir = -1;
        if (y_1 >= y_0)
            y_dir = 1;
        else
            y_dir = -1;

    }

}

