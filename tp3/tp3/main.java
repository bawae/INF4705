package tp3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class main
{
	private static int nbAretes = 0,
			nbSommets = 0;

    private static UndirectedGraph<Integer, Integer> u = new SimpleGraph<Integer, Integer>(Integer.class);
	
	public static void main(String[] args)
	{
		String path = "";
		
		// Analyse des arguments de la fonction
		for (int i = 0; i < args.length; i++)
		{
			// -f d?crit l'emplacement du fichier de valeurs
			if (args[i].equals("-f"))
			{
				path = args[++i];
			}
			else
			{
				System.out.println("Erreur dans les arguments\nLes arguments possibles sont -f [path] et -p");
				return;
			}
		}
		
		// Lecture du fichier et transfer dans un tableau de valeurs
		lireFichier(path);
		
		// Appel de l'algorithme de tri ? bulle
		ArrayList<Integer> result = optimisationRouteurs(u);
		
		for (int i = 0; i < result.size(); i++)
		{
			System.out.print(Integer.toString(result.get(i)+1) + "  ");
		}
	}
	
	
	/**
	 * Reads values from a file and stores them in an array
	 * 
	 * @param path where the file exists
	 * @return ArrayList containing all the values in the order read
	 */
	private static void lireFichier(String path)
	{
		try
		{
			Scanner s = new Scanner(new BufferedReader(new FileReader(path)));
			
			if(s.hasNext())
			{
				nbSommets = s.nextInt();
				nbAretes = s.nextInt();

			}
			else
			{
				s.close();
				return;
			}
			
			for(int i = 1; i <= nbSommets; i++)
			{
				u.addVertex(i);
			}
			
			for(int i = 0; i < nbAretes; i++)
			{
				s.nextInt();
				u.addEdge(s.nextInt(), s.nextInt());
			}
			
			s.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier n'a pas ete trouve!");
			System.exit(0);
		}
	}
	
	private static ArrayList<Integer> optimisationRouteurs(UndirectedGraph<Integer, Integer> graph)
	{
        
        
		// todo changer le return
		return null;
	}
}