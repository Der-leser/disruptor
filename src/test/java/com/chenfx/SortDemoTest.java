package com.chenfx;

/**
 * Created by Administrator on 2020/4/25 0025.
 */
public class SortDemoTest {
    private static int[] arr = {1,3,2,7,4,76,34};

    public void insertionSort() {


        for (int i = 0;i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {

            }
        }
    }

    public void mergeSort() {

    }

    private void mergeSort(int[] arr,int start,int end) {
        if (start >= end) {
            return;
        }
        int mid = start - (start - end)/2;
        mergeSort(arr,start,mid);
        mergeSort(arr,mid + 1,end);
        merge(start,mid,end);

    }

    private void merge(int start, int mid, int end) {
        int[] temp = new int[end - start];
        for (int i = start;i <= end; i ++) {
            temp[i - start] = arr[i];
        }
        int l = start;
        int m = mid + 1;
        for (int i = start; i < mid + 1;i ++) {

        }
    }
}
