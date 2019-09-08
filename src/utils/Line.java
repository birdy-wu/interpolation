package utils;

public class Line extends AbstractShape {
    
    
    public Line() {
    }

    public Line(double x_0, double y_0, double x_1, double y_1, double step) {
        this.x_0 = x_0;
        this.y_0 = y_0;
        this.x_1 = x_1;
        this.y_1 = y_1;
        this.step = step;
        this.x = x_0;
        this.y = y_0;
    }

    public void interpolation() {
        getdir();
        xe = x_1 - x_0;
        ye = y_1 - y_0;
        xi = yi = 0.0;
        System.out.println("For code Line with step:" + this.step);
        System.out.println("Going from (" + x_0 + "," + y_0 + ") to " + "("
                + x_1 + "," + y_1 + "):");
        System.out.println("Xi\tYi\tFxy\tDx\tDy\tX\tY");
        while ((xi < xe) || (yi < ye)) {
            fxy = Math.abs(xe) * Math.abs(yi) - Math.abs(ye) * Math.abs(xi);
            impulse();
        }
        System.out.println(df.format(xi) + "\t" + df.format(yi) + "\t\t\t\t" + df.format(x) + "\t" + df.format(y));
    }

    public void impulse() {
        if (fxy >= 0) {
            dx = x_dir * step;
            dy = 0;

        } else {
            dx = 0;
            dy = y_dir * step;

        }
        System.out.println(df.format(xi) + "\t" + df.format(yi) + "\t" + df.format(fxy) + "\t" + df.format(dx) + "\t" + df.format(dy)
                + "\t" + df.format(x) + "\t" + df.format(y));
        xi = xi + dx;
        yi = yi + dy;
        x = x + dx;//
        y = y + dy;
    }
}

