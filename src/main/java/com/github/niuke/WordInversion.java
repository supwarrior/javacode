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
                StringBuilder stringBuilders = new StringBuilder(words[i]);
                result.append(stringBuilders.reverse()).append(" ");
            }
            System.out.println(result.toString().trim());
        }
    }
}
