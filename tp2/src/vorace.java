package tp2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class vorace {
	

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
		ArrayList<Integer> result = algoVorace(values, somme, values.size() - 1);
		
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
	private static int[][] lireFichier(String path)
	{
		ArrayList<Integer> values = new ArrayList<>();
		
		try
		{
			Scanner s = new Scanner(new BufferedReader(new FileReader(path)));
			
			while (s.hasNext())
			{
				
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
	public ArrayList<Integer> algoVorace(int[][] donnees, int somme, int capacite)
	{
		// contient la probabilite pour chaque restaurant
		double[] proba = new double[donnees.length];
		// contient les revenus de chaque restaurant possible
		double[] revenus = new double[donnees.length];
		
		for(int i =0; i < donnees.length; i++)
		{
			revenus[i] = donnees[i][0] / donnees[i][1]; 
			proba[i] = revenus[i]/somme;
		}
		
		// On va generer nombre aleatoire pour determiner le restaurant choisit
		Random rand = new Random(System.currentTimeMillis());
		
		// On va garder les differents restaurants dans un tableau qui contiendra la solution de cette iteration
		ArrayList<Integer> solution = new ArrayList<>();

		// On a besoin d'un deuxieme tableau de solution pour comparer la solution de la prochaine iteration avec la solution de l'iteration precedente
		ArrayList<Integer> nouvelleSolution = new ArrayList<>();
		
		// On garde aussi les revenus de chaque solution pour les comparer a la fin (ce qui determinera quelle solution on garde)
		int revenusSolution = 0;
		int revenusNouvelleSolution = 0;
		
		// On fait l'algorithme vorace 10 fois et on garde la meilleure solution (d'ou l'utilisation de deux tableaux de solution qu'on compare)
		for(int i =0; i < 10; i++)
		{
			// on debute avec la capacite de la ville (cette capacite va diminuer au fur et a mesure qu'on va rajouter un restaurant)
			int capaciteSolution = capacite;
			
			// cette variable est presente pour determiner quand nous devons arreter d'essayer de rajouter une ville a la solution
			int check = 0;
		
			// on determine de facon arbitraire que lorsqu'on echoue la recherche d'une ville a ajouter 10 fois, on assume qu'il
			// n'y a pas d'autre ville a rajouter dans la solution.
			while(check < 10)
			{
				// le flag solitionAjoutee permet de determiner si nous allons incrementer la variable 'check' ou non
				boolean solutionAjoutee = false;
				
				// en generant le nombre aleatoire on va determiner une probabilite entre 0 et 1 pour trouver quel restaurant ajouter a la solution
				double fractionRand = (rand.nextInt()%100)/100;
				
				// la somme des probabilite nous permet de trouver le ''nombre pige'' dans notre tableau de probabilites
				double sommeProb = 0;
			
				// on parcours le tableau de probabilites jusqu'a trouver le nombre qu'on a pige
				for(int j = 0; j < proba.length; j++)
				{
					// Si la ville actuelle est celle qui a ete pigee on peut l'ajouter au tableau et sortir de la boucle
					if(fractionRand < proba[j] + sommeProb && (capaciteSolution - donnees[j][1] >= 0) && (!nouvelleSolution.contains(j)))
					{
						nouvelleSolution.add(j);
						capaciteSolution -= donnees[j][1];
						solutionAjoutee = true;
						break;
					}
					else
					{
						sommeProb += proba[j];
					}
				}
				if(!solutionAjoutee)
				{
					check++;
				}				
			}
			
			// On determine quelle solution on garde
			if(revenusNouvelleSolution > revenusSolution)
			{
				revenusSolution = revenusNouvelleSolution;
				solution = nouvelleSolution;
			}
			
			// Reinitiliser la nouvelle solution pour la prochaine iteration
			nouvelleSolution = new ArrayList<>();
			revenusNouvelleSolution = 0;
		}
		
		return solution;
	}
}
