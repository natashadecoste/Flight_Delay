package resources;

import java.util.*;

public class Sort {
	
	private static ArrayList<String> aux;
	
	private void exch(ArrayList<String> a, int i, int j){
		String t = a.get(i);
		a.set(i, a.get(j));
		a.set(j, t);
	}
	
	private static boolean less(String v, String w) {
		return v.compareTo(w) < 0;
	}
	
	private static void merge(ArrayList<String> a, int lo, int mid, int hi){
		int i = lo;
		int j = mid+1;
		
		for(int k = lo; k <= hi; k++){aux.set(k, a.get(k));}
		
		for(int k = lo; k <= hi; k++){
			if(i > mid) a.set(k, aux.get(j++));
			else if(j > hi) a.set(k, aux.get(i++));
			else if (less(aux.get(j), aux.get(i))) a.set(k, aux.get(j++));
			else a.set(k, aux.get(i++));
		}
	}
	
	private static void sort(ArrayList<String> a, int lo, int hi){
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}
	
	public static void sort(ArrayList<String> a){
		aux = new ArrayList<String>();
		for(int i=0; i < a.size(); i++){
			aux.add("");
		}
		sort(a, 0, a.size()-1);
	}

/*	public static void main(String[ ] args){
		inputHandler test2 = new inputHandler("info/2XB3_AirplaneData.csv");
		ArrayList<String[]> options = test2.run();
		
		ArrayList<String> temp = new ArrayList<String>();
		for(int i=0; i<options.size();i++){
			temp.add(options.get(i)[0]);
		}
		
		sort(temp);
		
		for(String s: temp){
			System.out.println(s);
		}
	}*/

}
