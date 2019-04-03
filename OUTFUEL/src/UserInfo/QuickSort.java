package UserInfo;

import java.util.Comparator;

public class QuickSort {
	
	private static void swap(GasStationADT[] a, int i, int j) { GasStationADT t = a[i]; a[i] = a[j]; a[j]= t; } //helper method 

	public static void sort(GasStationADT[] a, String rank)
	{ sort(a, 0, a.length-1, rank); }
	private static void sort(GasStationADT[] a, int lo, int hi, String rank)
	{
		if(hi <= lo) return;
		int j = partition(a,lo,hi, rank);
		sort(a, lo, j-1, rank);
		sort(a, j+1, hi, rank);
	}
	private static int partition(GasStationADT[] a, int lo, int hi, String rank)
	
	{
		int i = lo, j= hi+1; 
		GasStationADT pe = a[lo]; 
		String p = "price"; 
		String d = "distance"; 
		while(true)
		{
			if(rank.equals(p)) {
			while(a[++i].compareTo(pe,p) == -1 || a[i].compareTo(pe, p) == 0) if (i==hi) break;  //sorts lexographically if prices or distance are the same
			while(pe.compareTo(a[--j],p) == -1 || pe.compareTo(a[j],p) == 0) if (j==lo) break;
			}
			else if (rank.equals(d)) {
				while(a[++i].compareTo(pe,d) == -1 || a[i].compareTo(pe, d) == 0) if (i==hi) break; 
				while(pe.compareTo(a[--j],d) == -1 || pe.compareTo(a[j],d) == 0) if (j==lo) break;
				}
			if (i>= j) break; 
			swap(a, i ,j); 
		}
		swap(a,lo,j); 
		return j; 
	}
	private static boolean isSorted(GasStationADT[] a, String rank)
	
	{  
		if (rank.equals("price")) {
		for(int i = 1; i < a.length; i++) 
			if (a[i-1].compareTo(a[i],"price") == 1) return false;
		}
		if(rank.equals("distance")) {
			for(int i = 1; i < a.length; i++) 
				if (a[i-1].compareTo(a[i],"distance") == 1) return false;
		} 
		return true;  
	 }
/*	
	public static void main(String[] args) //used for testing -- can delete when not in use 
	{
		// TODO Auto-generated method stub
		GasStationADT hello = new GasStationADT("name", 112.5, "sub", "269 potts", 1.77); 
		GasStationADT hello1 = new GasStationADT("came", 102.5, "sub", "269 potts", 1.27); 
		GasStationADT hello2 = new GasStationADT("rame", 132.5, "sub", "269 potts", 1.66); 
		GasStationADT hello3 = new GasStationADT("lame", 112.5, "sub", "269 potts", 1.55); 
		GasStationADT[] array = {hello, hello1, hello2, hello3}; 
		QuickSort.sort(array, "distance"); 
		for(int i =0; i < array.length; i++) 
			System.out.println(array[i]);
		System.out.println(QuickSort.isSorted(array, "price"));
	}
*/
}
