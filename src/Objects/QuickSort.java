package Objects;

import java.util.ArrayList;

public class QuickSort {
    public QuickSort() {}

    static void swap(ArrayList<Player> list, int i, int j) {
        Player temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    static int partition(ArrayList<Player> list, int start, int end) {
 
        int pivot = list.get(end).getScore();

        int i = (start - 1);
 
        for (int j = start; j <= end - 1; j++) {
 
            if (list.get(j).getScore() < pivot) {
 
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, end);
        return (i + 1);
    }
 
    public static void sort(ArrayList<Player> list, int start, int end) {
        if (start < end) {
 
            int pi = partition(list, start, end);
 
            sort(list, start, pi - 1);
            sort(list, pi + 1, end);
        }
    }
}
