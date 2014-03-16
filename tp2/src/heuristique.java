import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class heuristique {

	private static int capacite = 0,
			somme = 0;
	
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
		ArrayList<Integer> result = algoHeuristique(values);
		
		long timeElapsed = Math.abs(timeStart - System.nanoTime());
		
		if (printResult)
		{
			System.out.println("Voici la solution optimale selon l'algorithme damelioration locale:");
			
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
			int nombreRestos = 0;
			somme = 0;
			
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
				somme += values[i][0]/values[i][1];
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
	public static ArrayList<Integer> algoHeuristique(int[][] donnees)
	{
		// On part de la solution trouvee avec l'algorithme vorace
		vorace.somme = somme;
		vorace.capacite = capacite;
		
		ArrayList<Integer> solutionVorace = vorace.algoVorace(donnees);
		
		boolean modification = false;
		do
		{
			modification = false;
			for(int i = 0; i < solutionVorace.size(); i++)
			{
				for(int j = 0; j < donnees.length; j++)
				{
					if(!solutionVorace.contains(j))
					{
						ArrayList<Integer> solutionTemp = new ArrayList<>(solutionVorace);
						solutionTemp.remove(solutionVorace.get(i));
						solutionTemp.add(i,j);
						int capaciteTemp = calculerCapacite(solutionTemp, donnees);
						
						if(capaciteTemp <= capacite && calculerRevenu(solutionTemp,donnees) > calculerRevenu(solutionVorace, donnees))
						{
							solutionVorace = solutionTemp;
							modification = true;
						}
					}
				}
			}
		}
		while(modification);
		
		
		return solutionVorace;		
	}
	
	private static int calculerRevenu(ArrayList<Integer> solution, int[][] donnees)
	{
		int revenu = 0;
		for(int i = 0; i < solution.size(); i++)
		{
			revenu += donnees[solution.get(i)][0];
		}
		return revenu;
	}
	
	private static int calculerCapacite(ArrayList<Integer> solution, int[][] donnees)
	{
		int cap = 0;
		for(int i = 0; i < solution.size(); i++)
		{
			cap += donnees[solution.get(i)][1];
		}
		return cap;
	}
	
}
