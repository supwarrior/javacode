package com.github.niuke;

import java.util.Scanner;

/**
 *  单词倒排
 * I am a student
 * student a am I
 *
 * @author 康盼Java开发工程师
 */
public class WordInversion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String str = in.nextLine();
            String[] words = str.split("[^A-Za-z]");
            StringBuilder result = new StringBuilder();
            for (int i = words.length - 1; i >= 0; i--) {
                result.append(words[i]).append(" ");
            }
            System.out.println(result.toString().trim());
        }
    }
}
