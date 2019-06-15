package com.algo;

import java.util.Arrays;

/**
 * Created by hzzhaolong on 19/6/11.
 */
public class QuickSelectFindKthElement {

    public static void main(String[] args) {
        int elements[] = {3, 1, 9, 2, 4, 5, 7, 6, 8, 0, 10 };
        System.out.println(Arrays.toString(elements));

        int pivotIndex = quickSelect(elements, 4, 0, elements.length -1);
        System.out.println(elements[pivotIndex]);
    }


    private static int quickSelect(int[] elements, int k, int begin, int end) {

        System.out.println("===========================>");
        int cmpData = elements[begin];
        int left = begin, right = end;
        while (left <= right) {
            while (elements[left] >= cmpData && left <= right) {
                left++;
            }

            while (elements[right] <= cmpData && right >= left) {
                right--;
            }

            if(left <= right) {
                System.out.print("swap :" + elements[left] + "," + elements[right]);
                swap(elements, left, right);
                System.out.println( "==>" +Arrays.toString(elements));
                left++;
                right--;
            }
        }

        System.out.print("swap :" + elements[begin] + "," + elements[right]);
        swap(elements, begin, right);
        System.out.println("==>" +Arrays.toString(elements));


        if(right + 1 == k) {
            return right;
        }else if(right + 1 > k) {
            return quickSelect(elements, k, begin, right);
        } else {
           return quickSelect(elements, k, right, end);
        }
    }

    private static void swap(int[] elements, int index1, int index2) {
        int tmp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = tmp;
    }





}
