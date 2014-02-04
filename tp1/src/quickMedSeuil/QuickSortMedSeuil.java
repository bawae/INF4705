package quickMedSeuil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import bubbleSort.BubbleSort;


public class QuickSortMedSeuil
{
	private static int SEUIL = 5;
	
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
			System.out.println(timeElapsed);
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
		if(first < last && last - first > SEUIL)
		{
			int med = (last - first) / 2;

			int pivot;
			
			if ( (values.get(med) - values.get(first)) * (values.get(last) - values.get(med)) >= 0 ) // a >= b and a <= c OR a <= b and a >= c
		        pivot = med;
		    else if ( (values.get(first) - values.get(med)) * (values.get(last) - values.get(first)) >= 0 ) // b >= a and b <= c OR b <= a and b >= c
		        pivot = first;
		    else
		        pivot = last;
			
			//System.out.println("First value: " + values.get(first) + " Med value: " + values.get(med) + " Last value: " + values.get(last) + " Pivot : " + values.get(pivot));
			
			pivot = partition(values, first, last, pivot);
			sort(values, first, pivot-1);
			sort(values, pivot+1, last);
		}
		else
		{
			BubbleSort.sort(values);
		}
		return values;
	}
	
	private static int partition(ArrayList<Integer> values, int first, int last, int pivot)
	{
		Integer temp = values.get(last);
		values.set(last, values.get(pivot));
		values.set(pivot, temp);
		
		int j = first;
		for(int i = first; i < last; i++)
		{
			if(values.get(i) <= values.get(last))
			{
				temp = values.get(i);
				values.set(i, values.get(j));
				values.set(j++, temp);
			}
		}
		temp = values.get(j);
		values.set(j, values.get(last));
		values.set(last, temp);
		return j;
	}
}
