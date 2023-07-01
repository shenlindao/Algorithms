// Реализовать алгоритм пирамидальной сортировки (сортировка кучей)

package Homework.Homework_02;

public class Ex_01 {
    public static void main(String[] args) {
        int[] arr = new int[] { 2, 4, 23, -33, 567, 8, 17, 25, 11, 14, 200 };
        heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    public static void heap(int[] arr, int i, int n) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (i != largest) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            
            heap(arr, largest, n);
        }
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heap(arr, i, n);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            heap(arr, 0, i);
        }
    }
}
