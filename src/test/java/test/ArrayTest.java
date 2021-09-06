package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * TwoDimensionalArrayRotation
 */
public class ArrayTest {

    private int[][] arr = new int[5][5];

    @Before
    public void init() {
        int n = 1;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                arr[i][j] = n++;
            }
        }
    }


    @Test
    public void testArray() {
        int top = 0,left = 0;
        int right = arr[0].length - 1;
        int bottom = arr.length - 1;
        List<Integer> list = new ArrayList<>();
        while(true) {
            for(int i = left; i <= right; ++i) {
                list.add(arr[top][i]);
            }
            top ++;
            if (top > bottom) {
                break;
            }
            for(int i = top; i <= bottom; ++i) {
                list.add(arr[i][right]);
            }
            right --;
            if (left > right) {
                break;
            }
            for (int i = right; i >= left; --i) {
                list.add(arr[bottom][i]);
            }
            bottom --;
            if (top > bottom) {
                break;
            }
            for(int i = bottom; i>= top; --i) {
                list.add(arr[i][left]);
            }
            left ++;
            if (left > right) {
                break;
            }
        }
        list.forEach(c -> System.out.print(c + " "));
    }
}
