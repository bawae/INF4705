package bubbleSort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BubbleSort
{
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
		ArrayList<Integer> result = sort(values, 0, values.size());
		
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
	 * Selon le pseudo code trouv� sur http://en.wikipedia.org/wiki/Bubble_sort
	 * 
	 * @param values contains the array to sort
	 * @return the sorted array
	 */
	public static ArrayList<Integer> sort(ArrayList<Integer> values, int start, int size)
	{
		// D�termine si un interchangement a eu lieu dans la derni�re it�ration
		boolean swapped;
		int temp;
		
		do
		{
			swapped = false;
			for (int i = start+1; i < size; i++)
			{
				// Si la paire n'est pas ordonn�e
				if (values.get(i - 1) > values.get(i))
				{
					// On interchange les valeurs
					temp = values.get(i);
					values.set(i, values.get(i - 1));
					values.set(i - 1, temp);
					swapped = true;
				}
			}
		} while (swapped);
		
		return values;
	}
}
