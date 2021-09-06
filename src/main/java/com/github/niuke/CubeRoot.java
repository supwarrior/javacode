package com.github.niuke;

import java.util.Scanner;

/**
 * 牛顿迭代法
 * @author 康盼Java开发工程师
 */
public class CubeRoot {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        double num = in.nextDouble();
        double x = 1.0;
        double x1 = x + (num - x*x*x)/(3*x*x);
        while((x-x1 > 0.000001) || (x-x1 < -0.000001)){
            x = x1;
            x1 = x + (num - x*x*x)/(3*x*x);
        }
        System.out.println(String.format("%.1f",x1));
    }
}
