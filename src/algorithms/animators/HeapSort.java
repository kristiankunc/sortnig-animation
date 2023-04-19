package algorithms.animators;

import cz.gyarab.util.Utils;
import cz.gyarab.util.light.Matrix;
import lib.ArrayTools;

public class HeapSort {
    public static void animate(Matrix matrix) {
        int[] array = ArrayTools.matrixToArray(matrix);

        heapSort(matrix, array);
    }

    private static void heapSort(Matrix matrix, int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            Utils.sleep(300 / array.length);
            heapify(matrix, array, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            Utils.sleep(300 / array.length);
            ArrayTools.swap(array, 0, i);
            ArrayTools.arrayToMatrix(matrix, array);
            heapify(matrix, array, i, 0);
        }
    }

    private static void heapify(Matrix matrix, int[] array, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        Utils.sleep(300 / array.length);
        if (l < n && array[l] > array[largest]) {
            largest = l;
        }
        if (r < n && array[r] > array[largest]) {
            largest = r;
        }
        if (largest != i) {
            ArrayTools.swap(array, i, largest);
            ArrayTools.arrayToMatrix(matrix, array);
            heapify(matrix, array, n, largest);
        }
    }
}