package dev.ryotaro;

public class Main2 {

    public static void main(String[] args) {
        var a = distance(1, ((long) 1<<63)-1);
        System.out.println("Hello, World!" + a);
    }

    static long distance(long a, long b) {
        long distance = a < b ? b - a : a - b;
        if ((distance & (1L << 62)) != 0) {
            distance = -distance - Long.MIN_VALUE;
        }
        // For the -2^63..2^63-1 range, the code would be
        // if (distance < 0) {
        //    distance = -distance;
        // }
        return distance;
    }
}
