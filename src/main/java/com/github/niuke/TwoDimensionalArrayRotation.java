package com.github.niuke;

import java.util.ArrayList;
import java.util.List;

/**
 * 二维数组螺旋
 * @author 康盼Java开发工程师
 */
public class TwoDimensionalArrayRotation {

    /**
     *  (0,0) (0,1) (0,2) (0,3) (0,4)
     *  (1,0) (1,1) (1,2) (1,3) (1,4)
     *  (2,0) (2,1) (2,2) (2,3) (2,4)
     *  (3,0) (3,1) (3,2) (3,3) (3,4)
     *  (4,0) (4,1) (4,2) (4,3) (4,4)
     *
     * @param matrix
     * @return
     */

    public static List<Integer> spiralOrder2(int[][] matrix) {

        List<Integer> arr = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length-1;
        int top = 0;
        int down = matrix.length-1;
        // left -> right (top 固定) 第二个参数变
        while (true) {
            for (int i = left; i <= right; ++i) {
                arr.add(matrix[top][i]);
            }
            // top 指针 + 1
            top++;
            if (top > down) {
                break;
            }
            // top -> down (right 固定) 第一个参数变
            for (int i = top; i <= down; ++i) {
                arr.add(matrix[i][right]);
            }
            // right 指针 -1
            right--;
            if (left > right) {
                break;
            }
            // right -> left (down 固定)
            for (int i = right; i >= left; --i) {
                arr.add(matrix[down][i]);
            }
            // down 指针 -1
            down--;
            if (top > down) {
                break;
            }
            // down -> top 的过程 (left 固定)
            for (int i = down; i >= top; --i) {
                arr.add(matrix[i][left]);
            }
            left++;
            if (left > right) {
                break;
            }

        }
        return arr;
    }



    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList();
        //特殊情况1：处理一维数组
        if(matrix.length == 1){
            for(int num : matrix[0]){
                list.add(num);
            }
            return list;
        }
        //特殊情况2：处理一维数组中只有一个的二维数组
        if(matrix[0].length == 1){
            for(int i = 0; i < matrix.length; i++){
                list.add(matrix[i][0]);
            }
            return list;
        }
        //处理正常情况
        //定义一个二维数组记录每个节点是否被访问
        boolean[][] isVisited = new boolean[matrix.length][matrix[0].length];
        int row = 0;
        int col = 0;
        //0代表往右,1代表往下，2代表往左，3代表往上
        int flag = 0;
        while(true){
            //0一直往右走到头（col < matrix[0].length）,或者走到下一个元素被访问（isVisited[row][col] == false）
            while(flag == 0  && col < matrix[0].length && isVisited[row][col] == false){
                isVisited[row][col] = true;
                list.add(matrix[row][col]);
                col++;
            }
            //改变方向变为向下走
            flag = 1;
            //在退出循环时col已经越界，所有col要倒退一格。
            col--;
            row++;
            //如果下一个方向的第一个节点被访问，说明整个数组已经被全部访问，则退出循环
            if(isVisited[row][col]){
                break;
            }
            while(flag == 1  && row < matrix.length && isVisited[row][col] == false){
                isVisited[row][col] = true;
                list.add(matrix[row][col]);
                row++;
            }
            flag = 2;
            row--;
            col--;
            if(isVisited[row][col]){
                break;
            }
            while(flag == 2  && col >= 0 && isVisited[row][col] == false){
                isVisited[row][col] = true;
                list.add(matrix[row][col]);
                col--;
            }
            flag = 3;
            row--;
            col++;
            if(isVisited[row][col]){
                break;
            }
            while(flag == 3  && row >= 0 && isVisited[row][col] == false){
                isVisited[row][col] = true;
                list.add(matrix[row][col]);
                row--;
            }
            flag = 0;
            row++;
            col++;
            if(isVisited[row][col]){
                break;
            }
        }
        return list;
    }
}
