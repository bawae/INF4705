package bubble;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Bubble
{
	public static void main(String[] args)
	{
		
		String path = "";
		boolean printResult = false;
		
		// Analyse des arguments de la fonction
		for (int i = 0; i < args.length; i++)
		{
			// -f décrit l'emplacement du fichier de valeurs
			if (args[i].equals("-f"))
			{
				path = args[++i];
			}
			// -p détermine si on affiche le résultat a la fin de l'exécution du programme
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
		
		// Appel de l'algorithme de tri à bulle
		Bubble bubbleSort = new Bubble();
		ArrayList<Integer> result = bubbleSort.sort(values);
		
		if (printResult)
		{
			for (int i = 0; i < result.size(); i++)
			{
				System.out.println(result.get(i));
			}
		}
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
			String currentLine = "";
			
			while ((currentLine = s.next()) != null)
			{
				values.add(Integer.parseInt(currentLine));
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
	 * Selon le pseudo code trouvé sur http://en.wikipedia.org/wiki/Bubble_sort
	 * 
	 * @param values Contains the array to sort
	 * @return The sorted array
	 */
	private ArrayList<Integer> sort(ArrayList<Integer> values)
	{
		// Détermine si un interchangement a eu lieu dans la dernière itération
		boolean swapped = false;
		int temp;
		
		do
		{
			swapped = false;
			for (int i = 1; i < values.size(); i++)
			{
				// Si la paire n'est pas ordonnée
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
