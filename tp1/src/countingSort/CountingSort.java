package countingSort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CountingSort
{
	public static void main(String[] args)
	{
		String path = "";
		boolean printResult = false;
		
		// Analyse des arguments de la fonction
		for(int i = 0; i < args.length; i++) 
		{
			// -f décrit l'emplacement du fichier
			if(args[i].equals("-f")) 
			{
				 path = args[++i];
			} 
			// -p détermine si on affiche le résultat à la fin de l'exécution du programme
			else if (args[i].equals("-p")) 
			{
				printResult = true;
			}
			else
			{
				System.out.println("erreur dans les arguments");
				return;
			}
		}
		
		ArrayList<Integer> values = lireFichier(path);
		
		int min = values.get(0),
				max = values.get(0),
				current;
		
		// On détermine la valeur minimale et la valeur maximale
		for(int i = 0; i < values.size(); i++)
		{
			current = values.get(i);
			if(current < min)
				min = current;
			else if(current > max)
				max = current;
		}

		long timeStart = System.nanoTime();
		
		// Appel de l'algorithme de tri de comptage
		ArrayList<Integer> result = sort(values, min, max);
		
		long timeElapsed = Math.abs(timeStart - System.nanoTime());
		
		if(printResult)
		{
			for(int i = 0; i < result.size(); i++)
			{
				System.out.println(result.get(i));
			}
		}
		
		System.out.println("\nTemps d'execution total de l'algorithme: " + timeElapsed + " ns");
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
	 * Selon le pseudo code trouvé sur http://fr.wikipedia.org/wiki/Tri_comptage
	 * 
	 * @param values contains all the values to sort
	 * @param min contains the minimum value contained in the array
	 * @param max contains the maximum value contained in the array
	 * @return sorted ArrayList
	 */
	public static ArrayList<Integer> sort(ArrayList<Integer> values, int min, int max)
	{
		// Initialisation des variables
		int countingTabSize = (max - min) + 1;
		int[] countingTab = new int[countingTabSize];
		
		for(int i = 0; i < countingTabSize; i++)
		{
			countingTab[i] = 0;
		}
		
		// Initialisation du tableau de comptage
		for(int i = 0; i < values.size(); i++)
		{
			countingTab[values.get(i) - min]++;
		}
		
		int j = 0;
		
		// Création du tableau trié
		for(int i = 0; i < countingTabSize; i++)
		{
			while(countingTab[i]-- > 0)
			{
				values.set(j++, i + min);
			}
		}
		
		return values;
	}
}
