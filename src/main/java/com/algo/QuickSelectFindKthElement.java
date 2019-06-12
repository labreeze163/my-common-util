package com.algo;

/**
 * Created by hzzhaolong on 19/6/11.
 */
public class QuickSelectFindKthElement {

    public static void main(String[] args) {
        int arr[] = {7, 10, 4, 3, 20, 15 };

        int pivotIndex = quickSelect(arr,4, 0, arr.length - 1 );
        System.out.println("pivotIndex="+pivotIndex);
    }




    private static int quickSelect(int[] elements, int k, int start, int end) {

        int pivot = getPivotByPartition(elements, start, end);

        if (k == (pivot - start + 1)) {
            System.out.println("pivot value="+elements[pivot]);
            return pivot;
        } else if (k < (pivot - start + 1)) {
            return quickSelect(elements, k, start, pivot - 1);
        } else {
            return quickSelect(elements, k - (pivot - start + 1), pivot + 1, end);
        }
    }

    private static int getPivotByPartition(int[] elements, int start, int end) {
        int pivot = start;
        int lessThan = start;

        for (int i = start; i <= end; i++) {
            int currentElement = elements[i];
            if (currentElement < elements[pivot]) {
                lessThan++;
                int tmp = elements[lessThan];
                elements[lessThan] = elements[i];
                elements[i] = tmp;
            }
        }
        int tmp = elements[lessThan];
        elements[lessThan] = elements[pivot];
        elements[pivot] = tmp;
        //System.out.println(" --- array = " +Arrays.toString(elements));
        return lessThan;
    }



}
