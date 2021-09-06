package com.github.niuke;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *  记负均正
 *  @author 康盼Java开发工程师
 */
public class NegativeAndPositive {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0,x = 0, y = 0;
        while(scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n > 0) {
                sum += n;
                x++;
            } else {
                y++;
            }
        }
        System.out.println(y);
        if (x == 0) {
            System.out.print("0.0");
        } else {
            DecimalFormat decimalFormat = new DecimalFormat(".#");
            System.out.println(decimalFormat.format(sum/x));
        }
    }
}
