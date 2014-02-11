package quickMedSeuil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import bubbleSort.BubbleSort;


public class QuickSortMedSeuil
{
	private static int SEUIL = 1;
	
	public static void main(String[] args)
	{
		String path = "";
		boolean printResult = false;
		
		// Analyse des arguments de la fonction
		for (int i = 0; i < args.length; i++)
		{
			// -f d�crit l'emplacement du fichier de valeurs
			if (args[i].equals("-f"))
			{
				path = args[++i];
			}
			// -p d�termine si on affiche le r�sultat � la fin de l'ex�cution du programme
			else if (args[i].equals("-p"))
			{
				printResult = true;
			}
			else if(args[i].equals("-s"))
			{
				SEUIL = Integer.parseInt(args[++i]);
			}
			else
			{
				System.out.println("Erreur dans les arguments\nLes arguments possibles sont -f [path] et -p");
				return;
			}
		}
		
		// Lecture du fichier et transfer dans un tableau de valeurs
		ArrayList<Integer> values;
		values = lireFichier(path);
		
		long timeStart = System.nanoTime();
		
		// Appel de l'algorithme de tri � bulle
		ArrayList<Integer> result = sort(values, 0, values.size() - 1);
		
		long timeElapsed = Math.abs(timeStart - System.nanoTime());
		
		if (printResult)
		{
			for (int i = 0; i < result.size(); i++)
			{
				System.out.println(result.get(i));
			}
		}
		
		if(printResult)
			System.out.println("\nTemps d'execution total de l'algorithme: " + timeElapsed + " ns");
		else
			System.out.print(timeElapsed);
	}
	
	/**
	 * Reads values from a file and stores them in an array
	 * 
	 * @param path where the file exists
	 * @return ArrayList containing all the values in the order read
	 */
	private static ArrayList<Integer> lireFichier(String path)
	{
		ArrayList<Integer> values = new ArrayList<>();
		
		try
		{
			Scanner s = new Scanner(new BufferedReader(new FileReader(path)));
			
			while (s.hasNext())
			{
				values.add(Integer.parseInt(s.next()));
			}
			
			s.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier n'a pas ete trouve!");
			System.exit(0);
		}
		
		return values;
	}
	
	/**
	 * Selon le pseudo code trouvé sur http://fr.wikipedia.org/wiki/Tri_rapide
	 * 
	 * @param values contains all the values to sort
	 * @param first contains the index of the first value contained in the array
	 * @param last contains the index of the last value contained in the array
	 * @return sorted ArrayList
	 */
	public static ArrayList<Integer> sort(ArrayList<Integer> values, int first, int last)
	{
		if(first < last)
		{
			if(first + SEUIL < last)
			{
				//System.out.println("First value: " + values.get(first) + " Med value: " + values.get(med) + " Last value: " + values.get(last) + " Pivot : " + values.get(pivot));
				
				int partition = partition(values, first, last);
				if (first < partition - 1)
					sort(values, first, partition - 1);
				if (partition < last)
					sort(values, partition + 1, last);
			}	
			else
			{
				BubbleSort.sort(values, first, last+1);
			}
		}
		return values;
	}
	
	private static int partition(ArrayList<Integer> values, int left, int right)
	{
		int i = left,
				j = right - 1;
		int pivot = medianOf3(values, left, right);

		while (i <= j)
		{
			while (values.get(i) < pivot)
				i++;
			
			while (values.get(j) > pivot)
				j--;
			
			if (i <= j)
			{
				swap(values, i, j);
				i++;
				j--;
			}
		}
		
		swap(values, i, right - 1);

		return i;
	}
	
	private static int medianOf3(ArrayList<Integer> data, int left, int right)
	{
	    int center = (left + right) / 2;
	    // order left & center
	    if (data.get(left) > data.get(center))
	      swap(data, left, center);
	    // order left & right
	    if (data.get(left) > data.get(right))
	      swap(data, left, right);
	    // order center & right
	    if (data.get(center) > data.get(right))
	      swap(data, center, right);

	    swap(data, center, right - 1); // put pivot on right
	    return data.get(right - 1); // return median value
	}
	
	private static void swap(ArrayList<Integer> data, int dex1, int dex2)
	{
		int temp = data.get(dex1);
	    data.set(dex1, data.get(dex2));
	    data.set(dex2, temp);
	}
}
