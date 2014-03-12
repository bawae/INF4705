import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class dynamique {

	private static int capacite = 0,
			nombreRestos = 0;
	
	public static void main(String[] args)
	{
		String path = "";
		boolean printResult = false;
		
		// Analyse des arguments de la fonction
		for (int i = 0; i < args.length; i++)
		{
			// -f d?crit l'emplacement du fichier de valeurs
			if (args[i].equals("-f"))
			{
				path = args[++i];
			}
			// -p d?termine si on affiche le r?sultat ? la fin de l'ex?cution du programme
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
		int[][] values;
		
		values = lireFichier(path);
		
		long timeStart = System.nanoTime();
		
		// Appel de l'algorithme de tri ? bulle
		ArrayList<Integer> result = algoDynamique(values);
		
		long timeElapsed = Math.abs(timeStart - System.nanoTime());
		
		if (printResult)
		{
			System.out.println("Voici la solution optimale selon l'algorithme vorace al�atoire:");
			
			for (int i = 0; i < result.size(); i++)
			{
				System.out.print(Integer.toString(result.get(i)+1) + "  ");
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
	private static int[][] lireFichier(String path)
	{
		int[][] values = null;
		
		try
		{
			Scanner s = new Scanner(new BufferedReader(new FileReader(path)));
			
			if(s.hasNext())
			{
				nombreRestos = s.nextInt();

			}
			else
			{
				s.close();
				return null;
			}
			
			values = new int[nombreRestos][2];
			
			for(int i = 0; i < nombreRestos; i++)
			{
				s.nextInt();
				values[i][0] = s.nextInt();
				values[i][1] = s.nextInt();
			}
			
			if(s.hasNext())
			{
				capacite = s.nextInt();
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
	
	// fonction qui execute l'algorithme vorace
	// On a une tableau 2D avec les donnees receuillies dans les fichiers textes
	// On demande la somme des revenus
	public static ArrayList<Integer> algoDynamique(int[][] donnees)
	{
		int[][] solutionArray;
		try
		{
			solutionArray = new int[nombreRestos][capacite + 1];
		
		}
		catch(OutOfMemoryError e)
		{
			return null;
		}
		
		for(int i = 0; i < nombreRestos; i++)
		{
			for(int j = 0; j < capacite + 1; j++)
			{
				solutionArray[i][j] = 0;
				
				if(i - 1 < 0)
				{
					// Lorsqu'on rempli la premiere ligne, on ne peut pas comparer aux lignes precedentes donc on 
					// fait juste verifier si le poid le l'item i est plus petit que la capacite j.
					if(donnees[i][1] <= j)
					{
						solutionArray[i][j] = donnees[i][0];
					}
				}
				else if(j - donnees[i][1] < 0)
				{
					solutionArray[i][j] = solutionArray[i-1][j];
				}
				else
				{
					solutionArray[i][j] = Math.max(donnees[i][0] + solutionArray[i-1][j-donnees[i][1]],solutionArray[i-1][j]);
				}
			}
		}
		
		// Retracer les emplacements choisis
		for(int i = 0; i < nombreRestos; ++i)
		{
			for(int j = 0; j < capacite + 1; ++j)
			{
				System.out.print(solutionArray[i][j] + "\t");
			}
			System.out.println("");
		}
		
		return null;
		
		
	}
}
