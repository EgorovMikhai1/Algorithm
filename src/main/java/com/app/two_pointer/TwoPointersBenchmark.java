package com.app.two_pointer;

import java.util.Arrays;
import java.util.Random;

public class TwoPointersBenchmark {
    private static final Random RANDOM = new Random();

    // Генерирует случайный массив размера size с числами от 1 до maxValue
    private static int[] generateRandomArray(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt(maxValue) + 1; // От 1 до maxValue
        }
        Arrays.sort(arr); // Сортируем массив, если это требуется для задачи
        return arr;
    }

    // Генерирует случайную строку длиной size (из букв a-z)
    private static String generateRandomString() {
        StringBuilder sb = new StringBuilder(5000);
        for (int i = 0; i < 5000; i++) {
            sb.append((char) ('a' + RANDOM.nextInt(26))); // a-z
        }
        return sb.toString();
    }

    // Измеряет время выполнения метода
    private static long measureTime(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // Перевод в миллисекунды
    }

    public static void main(String[] args) {
        int size = 100_000; // Размер массива для тестирования
        int maxValue = 1_000_000; // Максимальное значение элементов
        int target = 50000; // Целевое значение для поиска пар
        String testString = generateRandomString(); // Строка для тестирования палиндрома

        // 1 - Поиск пары с заданной суммой
        int[] sortedArray = generateRandomArray(size, maxValue);
        System.out.println("1️⃣ Найти пару чисел, сумма которых = target:");
        System.out.println("   В ЛОБ O(n^2): " + measureTime(() -> TwoPointersExamples.findPairNaive(sortedArray, target)) + " ms");
        System.out.println("   Два указателя O(n): " + measureTime(() -> TwoPointersExamples.findPairTwoPointers(sortedArray, target)) + " ms");

        // 2 - Минимальная разница между двумя числами
        System.out.println("\n2️⃣ Найти два числа с минимальной разницей:");
        System.out.println("   В ЛОБ O(n^2): " + measureTime(() -> TwoPointersExamples.findMinDiffPairNaive(sortedArray)) + " ms");
        System.out.println("   Два указателя O(n log n): " + measureTime(() -> TwoPointersExamples.findMinDiffPairTwoPointers(sortedArray)) + " ms");

        // 3 - Максимальная длина подмассива с суммой = target
        int[] randomArray = generateRandomArray(size, maxValue);
        System.out.println("\n3️⃣ Найти длину самого длинного подмассива с суммой = target:");
        System.out.println("   В ЛОБ O(n^2): " + measureTime(() -> TwoPointersExamples.maxSubArrayLenNaive(randomArray, target)) + " ms");
        System.out.println("   Два указателя O(n): " + measureTime(() -> TwoPointersExamples.maxSubArrayLenTwoPointers(randomArray, target)) + " ms");

        // 4 - Удаление дубликатов из отсортированного массива
        System.out.println("\n4️⃣ Удаление дубликатов:");
        System.out.println("   В ЛОБ O(n): " + measureTime(() -> TwoPointersExamples.removeDuplicatesNaive(sortedArray)) + " ms");
        System.out.println("   Два указателя O(n): " + measureTime(() -> TwoPointersExamples.removeDuplicatesTwoPointers(sortedArray)) + " ms");

        // 5 - Проверка палиндрома
        System.out.println("\n5️⃣ Проверка, является ли строка палиндромом:");
        System.out.println("   В ЛОБ O(n): " + measureTime(() -> TwoPointersExamples.isPalindromeNaive(testString)) + " ms");
        System.out.println("   Два указателя O(n): " + measureTime(() -> TwoPointersExamples.isPalindromeTwoPointers(testString)) + " ms");

        // 6 - Перемещение всех нулей в конец
        int[] arrayWithZeros = generateRandomArray(size, maxValue);
        for (int i = 0; i < size / 10; i++) {
            arrayWithZeros[i] = 0; // Вставляем нули в начало массива
        }
        System.out.println("\n6️⃣ Перемещение всех нулей в конец:");
        System.out.println("   В ЛОБ O(n): " + measureTime(() -> TwoPointersExamples.moveZeroesNaive(arrayWithZeros)) + " ms");
        System.out.println("   Два указателя O(n): " + measureTime(() -> TwoPointersExamples.moveZeroesTwoPointers(arrayWithZeros)) + " ms");
    }
}