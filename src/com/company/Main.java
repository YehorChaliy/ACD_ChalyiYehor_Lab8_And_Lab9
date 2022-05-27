package com.company;

import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static void main(String[] args)
    {
        System.out.println("Непосортований масив:");
        int arr[] = { 1, 6, 15, 3, 10 ,15};
        System.out.println(Arrays.toString(arr) + "\n");
        int ind = fibMonaccianSearch(arr, 15, arr.length);
        if(ind>=0)
            System.out.println("Індексів однакових елементів: "
                    +ind);
        else
            System.out.println("Не знайдено");

        System.out.println("\nМасив перед сортування: " + Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("Масив після сортування включеннями: " + Arrays.toString(arr));

        //task 1
        System.out.println("\nДано 2 масиви: ");

        int arr1[] = {3, 7, 15, 88, 10 ,15, 11, 105, 3};
        int arr2[] = {3, 10, 105, 10, 105, 99 ,15};

        System.out.println("A:" + Arrays.toString(arr1));
        System.out.println("B:" + Arrays.toString(arr2));

        //пошук однакових елеметів в двох масивах
        boolean a = true;
        int lengthOfResultArray = 0;
        for (int i = 0; i < arr1.length; i++) {
            a = true;
            for (int j = i + 1; j < arr1.length; j++) {
                if(arr1[i] == arr1[j])
                    a = false;
            }
            if(a == false)
                continue;
            int repeats = fibMonaccianSearch(arr2,  arr1[i], arr2.length);
            if(repeats >= 0){
                lengthOfResultArray++;
            }

        }

        int[] result = new int[lengthOfResultArray];

        int k = 0;
        for (int i = 0; i < arr1.length; i++) {
            a = true;
            for (int j = i + 1; j < arr1.length; j++) {
                if(arr1[i] == arr1[j])
                    a = false;
            }
            if(a == false)
                continue;
            //using fibonacci search
            int repeats = fibMonaccianSearch(arr2,  arr1[i], arr2.length);
            if(repeats >= 0){
                result[k] = arr1[i];
                k++;
            }
        }
        insertionSort(result);
        System.out.println("\nВідсортовані методом включення елементи, котрі присутні в обох масивах А і В: " + Arrays.toString(result));

        System.out.println("\n ********TaskFor9Lab********");

        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("\n1. Ручне заповнення масиву.");
            System.out.println("2. Рандомне заповнення масиву.");
            System.out.println("3. Вихід з програми.");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String str;
                    int count = -1;
                    System.out.println("Невідсортований масив елементів:");
                    int arr3[] = {5, 12, 7, 11, 60, 33 ,49};
                    System.out.println(Arrays.toString(arr3));

                    System.out.println("\nСортування масиву: ");
                    int n = arr3.length;
                    for (int i = 1; i < n; ++i) {
                        int key = arr3[i];
                        int j = i - 1;
                        while (j >= 0 && arr3[j] > key) {
                            arr3[j + 1] = arr3[j];
                            j = j - 1;
                        }
                        arr3[j + 1] = key;
                        count++;
                        str=Arrays.toString(arr3).replace(",", " ")
                                .replace("[", "")
                                .replace("]", "")
                                .trim();

                        System.out.println(str);
                    }
                    System.out.println("\nКількість ітерацій: " + count);
                    System.out.println("\nВідсортований масив:");
                    insertionSort(arr3);
                    System.out.println(Arrays.toString(arr3));
                    break;
                case 2:
                    String str1;
                    int count1 = -1;
                    Scanner in = new Scanner(System.in);
                    System.out.println("Введіть розмірність масиву:");
                    int N = in.nextInt();
                    int arr4[] = new int[N];

                    System.out.println("\nНевідсортований масив елементів:");
                    for (int i = 0; i < N; i++){
                        arr4[i] = (int)(Math.random()*30);
                        System.out.print(arr4[i] + " ");
                    }

                    System.out.println("\n\nСортування масиву: ");
                    int m = arr4.length;
                    for (int i = 0; i < m; ++i) {
                        int key = arr4[i];
                        int j = i - 1;
                        while (j >= 0 && arr4[j] > key) {
                            arr4[j + 1] = arr4[j];
                            j = j - 1;
                        }
                        arr4[j + 1] = key;
                        count1++;
                        str1=Arrays.toString(arr4).replace(",", " ")
                                .replace("[", "")
                                .replace("]", "")
                                .trim();

                        System.out.println(str1);
                    }
                    System.out.println("\nКількість ітерацій: " + count1);

                    System.out.println("\nВідсортований масив:");
                    insertionSort(arr4);
                    System.out.println(Arrays.toString(arr4));
                    break;
                case 3:
                    System.out.println("\nВихід з програми...");
                    System.exit(0);
                default:
                    System.out.println("Вкажіть правильне значення!");
            }
        }
    }

    public static int min(int x, int y)
    {
        return (x <= y) ? x : y;
    }

    //Пошук Фібоначі
    public static int fibMonaccianSearch(int arr[], int x, int n)
    {

        int fibMMm2 = 0;
        int fibMMm1 = 1;
        int fibM = fibMMm2 + fibMMm1;


        while (fibM < n) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }


        int offset = -1;

        while (fibM > 1) {
            int i = min(offset + fibMMm2, n - 1);
            if (arr[i] < x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }

            else if (arr[i] > x) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            }

            else
                return i;
        }

        if (fibMMm1 == 1 && arr[n-1] == x)
            return n-1;


        return -1;
    }

    //Сортування включеннями
    public static void insertionSort(int arr[])
    {
        int count = 0;
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

}
