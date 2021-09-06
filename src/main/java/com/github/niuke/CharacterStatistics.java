package com.github.niuke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 字符统计
 * @author 康盼Java开发工程师
 */
public class CharacterStatistics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String str = scanner.nextLine();
            char[] chars = str.toCharArray();
            Map<Character,Integer> map = new TreeMap<>();
            for (int i = 0; i < chars.length; i++) {
                Character key = chars[i];
                if(map.containsKey(key)) {
                    Integer count = map.get(key);
                    count++;
                    map.put(key,count);
                } else {
                    map.put(key,0);
                }
            }

            // map (a,3) (b,2) (c,1)
            List<Map.Entry<Character,Integer>> list = new ArrayList<>();
            for(Map.Entry<Character,Integer> entry : map.entrySet()) {
                list.add(entry);
            }
            Collections.sort(list,(e1,e2) -> e2.getValue().compareTo(e1.getValue()));
//            Collections.sort(list, (e1,e2) -> {
//                if(e1.getValue() > e2.getValue()) {
//                    return  -1;
//                } else if(e1.getValue() < e2.getValue()) {
//                    return 1;
//                }
//                return 0;
//            });
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i).getKey());
            }
            System.out.println();
        }
    }
}
