package Lecture.Lecture_02;

import java.util.Random;
import java.util.Scanner;

public class Find {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(10);
        }
        int value = getNumber();
        printArray(arr);
        System.out.println(simpleSearch(arr, value));
    }

    public static int getNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста, введите число для поиска в массиве: ");
        int n = scanner.nextInt();
        scanner.close();
        return n;
    }

    public static String simpleSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value)
                return "Число найдено в массиве";
        }
        return "Данного числа нет в массиве";
    }

    public static int search(int[] arr, int value, int min, int max) {
        int midPoint;

        if (max < min) {
            return -1;
        } else {
            midPoint = (max - min) / 2 + min;
        }

        if (arr[midPoint] < value) {
            return search(arr, value, midPoint + 1, max);
        } else {
            if (arr[midPoint] > value) {
                return search(arr, value, min, max);
            } else {
                return midPoint;
            }
        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }
}
