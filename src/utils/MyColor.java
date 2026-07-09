package utils;

public class MyColor {
    private int r;
    private int g;
    private int b;

    public MyColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public MyColor() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public void colorFilter() {
        if (r < 0) r = 0;
        if (r > 255) r = 255;
        if (g < 0) g = 0;
        if (g > 255) g = 255;
        if (b < 0) b = 0;
        if (b > 255) b = 255;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "r: " + r + ", g: " + g + ", b: " + b;
    }
}
