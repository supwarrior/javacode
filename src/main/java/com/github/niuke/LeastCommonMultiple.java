package com.github.niuke;

import java.util.Scanner;

/**
 * 最小公倍数
 * 48 32 96
 * @author 康盼Java开发工程师
 */
public class LeastCommonMultiple {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(a * b / greatestCommonDivisor(a, b));
    }


    private static int greatestCommonDivisor(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a > b) {
            int c = a - b;
            return greatestCommonDivisor(b, c);
        } else {
            int c = b - a;
            return greatestCommonDivisor(a, c);
        }
    }

    private static int greatestCommonDivisor2(int a, int b) {
        if (a < b) {
            int c = a;
            a = b;
            b = c;
        }
        int k;
        while (b != 0) {
            k = a % b;
            a = b;
            b = k;
        }
        return a;
    }
}
