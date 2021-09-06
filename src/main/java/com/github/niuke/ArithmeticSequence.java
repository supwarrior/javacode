package com.github.niuke;

import java.util.Scanner;

/**
 * 等差数列 2，5，8，11，14
 * @author 康盼Java开发工程师
 */
public class ArithmeticSequence {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            System.out.println(fn(n));
        }
    }
    private static int f(int i) {
        return (i * 3) - 1;
    }
    private static int fn(int n) {
        // n = 2
        int sum = 0;
        for(int i = n ; i >= 1 ; i--) {
            // 5
            sum += f(i);
        }
        return sum;
    }

    // f(1) = 1*3 - 1; f(2) = 2*3 - 1; f(3) = 3*3 - 1;
    // fn(1) = f(1); fn(2) = f(2) + f(1); fn(3) = f(3) + f(2) + f(1);
    // 2 5 8 11 14
    // 2 7 15 26 40
    //
    // fx(n) = (3*n+1)*n >> 1

    // 1 + 2 + 3 + 4 + 5 + 6 + ... + (n - 1) + n

    // 1 + n
    // 2 + (n - 1) = (n + 1)
    // 3 + (n - 2) = (n + 1)
    // 4 + (n - 3) = (n + 1)
    // .....
    // (n - 1) + 2
    // n + 1
    // (n * (n + 1)) >> 1

    // 等差数列  fx(a,d) = a + (n - 1) * d 说明：a 初始值; b 等差值
    //  2，5，8，11，14  a=2 d=3
    // a  a+d   a+2d   a+3d ....   a + (n-1)*d  ... a + n*d
    // a + (a + n*d)
    // (a+d) + (a + (n-1)*d))


//    S	=	a	+	(a+d)	+	...	+	(a + (n-2)d)	+	(a + (n-1)d)
//    S	=	(a + (n-1)d)	+	(a + (n-2)d)	+	...	+	(a + d)	+	a
//    2S	=	(2a + (n-1)d)	+	(2a + (n-1)d)	+	...	+	(2a + (n-1)d)	+	(2a + (n-1)d)

 //   S = (n/2) × (2a + (n-1)d)
// (3*n+1)*n >> 1
}
