package com.app.two_pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointersExamples {

    /*
     * 1) Найти пару чисел в отсортированном массиве, сумма которых равна target
     *
     * Сложность:
     * - Наивный метод: O(n^2) - два вложенных цикла, перебираем все пары.
     * - Метод двух указателей: O(n) - проходим массив всего один раз.
     */

    // Наивное решение O(n^2)
    public static int[] findPairNaive(int[] arr, int target) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{arr[i], arr[j]};
                }
            }
        }
        return new int[0];
    }

    // Метод двух указателей O(n)
    public static int[] findPairTwoPointers(int[] arr, int target) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[]{arr[left], arr[right]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

    /*
     * 2) Найти два числа с минимальной разницей
     *
     * Сложность:
     * - Наивное решение: O(n^2) - перебираем все пары.
     * - Два указателя (предварительная сортировка O(n log n) + O(n) проход) → O(n log n).
     */

    // Наивное решение O(n^2)
    public static int[] findMinDiffPairNaive(int[] arr) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int diff = Math.abs(arr[i] - arr[j]);
                if (diff < minDiff) {
                    minDiff = diff;
                    result[0] = arr[i];
                    result[1] = arr[j];
                }
            }
        }
        return result;
    }

    // Метод двух указателей O(n log n)
    public static int[] findMinDiffPairTwoPointers(int[] arr) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }
        Arrays.sort(arr); // O(n log n)
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];
        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i - 1];
            if (diff < minDiff) {
                minDiff = diff;
                result[0] = arr[i - 1];
                result[1] = arr[i];
            }
        }
        return result;
    }

    /*
     * 3) Найти длину самого длинного подмассива с суммой target
     *
     * Сложность:
     * - Наивное решение: O(n^2) - вложенные циклы.
     * - Метод скользящего окна (двух указателей): O(n).
     */

    // Наивное решение O(n^2)
    public static int maxSubArrayLenNaive(int[] arr, int target) {
        int maxLen = 0;
        for (int start = 0; start < arr.length; start++) {
            int sum = 0;
            for (int end = start; end < arr.length; end++) {
                sum += arr[end];
                if (sum == target) {
                    maxLen = Math.max(maxLen, end - start + 1);
                }
            }
        }
        return maxLen;
    }

    // Метод двух указателей (скользящее окно) O(n)
    public static int maxSubArrayLenTwoPointers(int[] arr, int target) {
        int left = 0;
        int sum = 0;
        int maxLen = 0;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            while (sum > target && left <= right) {
                sum -= arr[left++];
            }
            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }

    /*
     * 4) Удаление дубликатов из отсортированного массива
     *
     * Сложность:
     * - Наивное решение: O(n), но использует доп. память.
     * - Метод двух указателей: O(n), но in-place.
     */

    // Наивное решение O(n)
    public static int removeDuplicatesNaive(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) list.add(arr[i]);
        }
        for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
        return list.size();
    }

    // Метод двух указателей O(n)
    public static int removeDuplicatesTwoPointers(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int slow = 0;
        for (int fast = 1; fast < arr.length; fast++) {
            if (arr[fast] != arr[slow]) {
                arr[++slow] = arr[fast];
            }
        }
        return slow + 1;
    }

    /*
     * 5) Проверка, является ли строка палиндромом
     *
     * Сложность:
     * - Наивное решение: O(n) (реверс строки + сравнение).
     * - Метод двух указателей: O(n) (один проход).
     */

    // Наивное решение O(n)
    public static boolean isPalindromeNaive(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    // Метод двух указателей O(n)
    public static boolean isPalindromeTwoPointers(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }

    /*
     * 6) Перемещение всех нулей в конец массива
     *
     * Сложность:
     * - Наивное решение: O(n), но использует вспомогательный массив.
     * - Метод двух указателей: O(n) in-place.
     */

    // Наивное решение O(n)
    public static void moveZeroesNaive(int[] arr) {
        int[] temp = Arrays.stream(arr).filter(num -> num != 0).toArray();
        System.arraycopy(temp, 0, arr, 0, temp.length);
        Arrays.fill(arr, temp.length, arr.length, 0);
    }

    // Метод двух указателей O(n)
    public static void moveZeroesTwoPointers(int[] arr) {
        int slow = 0;
        for (int fast = 0; fast < arr.length; fast++) {
            if (arr[fast] != 0) {
                int temp = arr[slow];
                arr[slow++] = arr[fast];
                arr[fast] = temp;
            }
        }
    }
}