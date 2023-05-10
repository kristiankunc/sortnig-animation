package algorithms.animators;

import cz.gyarab.util.Utils;
import cz.gyarab.util.light.Matrix;
import lib.ArrayTools;
import lib.SoundPlayer;

// https://www.geeksforgeeks.org/stooge-sort/

public class StoogeSort {
    public static void animate(Matrix matrix, boolean playSound) {
        matrix.setTitle("Stooge Sort");
        int[] array = ArrayTools.matrixToArray(matrix);
        SoundPlayer player = new SoundPlayer(playSound);

        stoogeSort(matrix, player, array, 0, array.length - 1);

        player.reset();

    }

    private static void stoogeSort(Matrix matrix, SoundPlayer player, int[] array, int l, int h) {
        if (l >= h) {
            return;
        }

        if (array[l] > array[h]) {
            ArrayTools.swap(array, l, h);
            player.playFromArray(array[l], array.length);
            ArrayTools.arrayToMatrix(matrix, array);
            Utils.sleep(2);
        }

        if (h - l + 1 > 2) {
            int t = (h - l + 1) / 3;
            stoogeSort(matrix, player, array, l, h - t);
            stoogeSort(matrix, player, array, l + t, h);
            stoogeSort(matrix, player, array, l, h - t);
        }
    }
}
