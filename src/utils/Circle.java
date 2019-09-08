package utils;

public class Circle extends AbstractShape {
    private double rad; // 圆弧的半径
    private int cw; // 表示顺逆时针
    private boolean requirement = true;
    private int check;
    private int p2x; // 圆弧: 当 fxy 为正时，是否在 x 轴上移动

    public Circle() {
        // TODO Auto-generated constructor stub
    }

    public Circle(double x_0, double y_0, double x_1, double y_1, double step) {
        this.x_0 = x_0;
        this.y_0 = y_0;
        this.x_1 = x_1;
        this.y_1 = y_1;
        this.step = step;
        this.x = x_0;
        this.y = y_0;
        this.cw = 1;
    }

    public Circle(double x_0, double y_0, double x_1, double y_1, double step,
            int cw) {
        this.x_0 = x_0;
        this.y_0 = y_0;
        this.x_1 = x_1;
        this.y_1 = y_1;
        this.step = step;
        this.x = x_0;
        this.y = y_0;
        this.cw = cw;
    }

    public void interpolation() {
        // TODO Auto-generated method stub
        getdir();
        rad = Math.abs(x_1 - x_0);
        if (cw == 1) {
            clockwise(); // 顺时针情况下的象限判定
            System.out.println("For code clockwise Circle with step:"
                    + this.step);
        } else {
            counterclockwise();
            System.out.println("For code anticlockwise Circle with step:"
                    + this.step);
        }
        System.out.println("Going from (" + x_0 + "," + y_0 + ") to " + "("
                + x_1 + "," + y_1 + "):");
        System.out.println("Xi\tYi\tFxy\tDx\tDy\tX\tY");
        while (requirement) {
            fxy = (xi * xi) + (yi * yi) - (rad * rad);
            impulse();
            switch (check) {
            case 1:
                if ((xi <= xe) && (yi >= ye))
                    requirement = false;
                break;
            case 2:
                if ((xi >= xe) && (yi >= ye))
                    requirement = false;
                break;
            case 3:
                if ((xi >= xe) && (yi <= ye))
                    requirement = false;
                break;
            case 4:
                if ((xi <= xe) && (yi <= ye))
                    requirement = false;
                break;
            }
        }
        System.out.println(df.format(xi) + "\t" + df.format(yi) + "\t\t\t\t"
                + df.format(x) + "\t" + df.format(y));
    }

    private void counterclockwise() {
        if ((x_1 < x_0) && (y_1 > y_0)) {
            p2x = 1;
            xe = 0.0;
            ye = rad;
            xi = rad;
            yi = 0.0;
            check = 1;
        }
        if ((x_1 < x_0) && (y_1 < y_0)) {
            p2x = 0;
            xe = -rad;
            ye = 0.0;
            xi = 0.0;
            yi = rad;
            check = 4;
        }
        if ((x_1 > x_0) && (y_1 < y_0)) {
            p2x = 1;
            xe = -rad;
            ye = 0.0;
            xi = 0.0;
            yi = -rad;
            check = 3;
        }
        if ((x_1 > x_0) && (y_1 > y_0)) {
            p2x = 0;
            xe = rad;
            ye = 0.0;
            xi = 0.0;
            yi = -rad;
            check = 2;
        }

    }

    private void clockwise() {
        if ((x_1 > x_0) && (y_1 < y_0)) {
            p2x = 0;
            xe = rad;
            ye = 0.0;
            xi = 0.0;
            yi = rad;
            check = 3;
        }
        if ((x_1 > x_0) && (y_1 > y_0)) {
            p2x = 1;
            xe = 0.0;
            ye = rad;
            xi = -rad;
            yi = 0.0;
            check = 2;
        }
        if ((x_1 < x_0) && (y_1 > y_0)) {
            p2x = 0;
            xe = -rad;
            ye = 0.0;
            xi = 0.0;
            yi = -rad;
            check = 1;
        }
        if ((x_1 < x_0) && (y_1 < y_0)) {
            p2x = 1;
            xe = 0.0;
            ye = -rad;
            xi = rad;
            yi = 0.0;
            check = 4;
        }

    }

    public void impulse() {
        if (fxy >= 0) {
            if (p2x == 1) {// 得到方向正确的脉冲步长
                dx = x_dir * step;
                dy = 0;
            } else {
                dx = 0;
                dy = y_dir * step;
            }
        } else {
            if (p2x == 1) {
                dx = 0;
                dy = y_dir * step;
            } else {
                dx = x_dir * step;
                dy = 0;
            }

        }
        System.out.println(df.format(xi) + "\t" + df.format(yi) + "\t"
                + df.format(fxy) + "\t" + df.format(dx) + "\t" + df.format(dy)
                + "\t" + df.format(x) + "\t" + df.format(y));
        xi = xi + dx;
        yi = yi + dy;
        x = x + dx;
        y = y + dy;
    }
}

