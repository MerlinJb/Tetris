package Objects;

import java.util.ArrayList;
//import java.util.List;


/**
 * Quick sort algorithm (simple)
 * based on pseudo code on Wikipedia "Quick Sort" aricle
 * 
 * @see http://en.wikipedia.org/wiki/Quicksort#Simple_version
 * @author djitz
 *
 */
public class QuickSort {

	/**
	 * Main method.
	 * @param args
	 */
	
    
    /* public static void main(String[] args) {

		QuickSort app = new QuickSort();
		
		 //Generate an integer array of length 7
	    List<Integer> input = app.generateRandomNumbers(7);
		
	    //Before sort
	    System.out.println(input);
		
	    //After sort
	    System.out.println(app.quicksort(input));
	    
	} */
	
	/**
	 * This method sort the input ArrayList using quick sort algorithm.
	 * @param players the ArrayList of integers.
	 * @return sorted ArrayList of integers.
	 */
	public static ArrayList<Player> quickSort(ArrayList<Player> players){
		
		if(players.size() <= 1){
			return players;
		}
		
		int middle = (int) Math.ceil((double)players.size() / 2);
		Player pivot = players.get(middle);

		//List<Integer> less = new ArrayList<Integer>();
		//List<Integer> greater = new ArrayList<Integer>();

        ArrayList<Player> less = new ArrayList<>();
        ArrayList<Player> greater = new ArrayList<>();
		
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).getScore() <= pivot.getScore()){
				if(i == middle){
					continue;
				}
				less.add(players.get(i));
			}
			else{
				greater.add(players.get(i));
			}
		}
		
		return concatenate(quickSort(less), pivot, quickSort(greater));
	}
	
	/**
	 * Join the less array, pivot integer, and greater array
	 * to single array.
	 * @param less integer ArrayList with values less than pivot.
	 * @param pivot the pivot integer.
	 * @param greater integer ArrayList with values greater than pivot.
	 * @return the integer ArrayList after join.
	 */
	private static ArrayList<Player> concatenate(ArrayList<Player> less, Player pivot, ArrayList<Player> greater){
		
		//List<Integer> list = new ArrayList<Integer>();
        ArrayList<Player> list = new ArrayList<>();
		
		for (int i = 0; i < less.size(); i++) {
			list.add(less.get(i));
		}
		
		list.add(pivot);
		
		for (int i = 0; i < greater.size(); i++) {
			list.add(greater.get(i));
		}
		
		return list;
	}
	
	/**
	 * This method generate a ArrayList with length n containing random integers . 
	 * @param n the length of the ArrayList to generate.
	 * @return ArrayList of random integers with length n. 
	 */
	/*private List<Integer> generateRandomNumbers(int n){
		
	    List<Integer> list = new ArrayList<Integer>(n);
	    Random random = new Random();
		
	    for (int i = 0; i < n; i++) {
		    list.add(random.nextInt(n * 10));
	    }
	
	    return list;
	}
    */

}






/*
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
 */
