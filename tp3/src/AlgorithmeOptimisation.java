import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class AlgorithmeOptimisation
{
	private static int nbAretes = 0,
			nbSommets = 0;

    private static UndirectedGraph<Noeud, DefaultEdge> graph = new SimpleGraph<Noeud, DefaultEdge>(DefaultEdge.class);
	
    private static Noeud[] tabNoeuds;
    
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
		long tempsDebut = System.nanoTime();
		ArrayList<Noeud> result = optimisationRouteurs();
		double tempsTotal = (double)(System.nanoTime() - tempsDebut)/1000000000.0;
		
		System.out.println(result.size() + " " + new DecimalFormat("#.##########").format(tempsTotal));
		
		Collections.sort(result, new Comparator<Noeud>() {
	        @Override
	        public int compare(Noeud noeud1, Noeud noeud2)
	        {
	            return  noeud1.numeroNoeud.compareTo(noeud2.numeroNoeud);
	        }
	    });
		
		for (int i = 0; i < result.size(); i++)
		{
			System.out.println(Integer.toString(result.get(i).numeroNoeud));
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

			tabNoeuds = new Noeud[nbSommets];
			
			for(int i = 1; i <= nbSommets; i++)
			{
				tabNoeuds[i-1] = new Noeud(i, Noeud.Couleur.BLANC, 0, false); 
				graph.addVertex(tabNoeuds[i-1]);
			}
			
			for(int i = 0; i < nbAretes; i++)
			{
				Noeud source = tabNoeuds[s.nextInt()-1];
				Noeud destination = tabNoeuds[s.nextInt()-1];
				graph.addEdge(source, destination);
			}
			
			s.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier n'a pas ete trouve!");
			System.exit(0);
		}
	}
	
	private static ArrayList<Noeud> optimisationRouteurs()
	{
		Queue<Noeud> fileFEL = new LinkedList<Noeud>();
		Noeud noeudDepart = tabNoeuds[0];
		Stack<Noeud> pileColore = new Stack<Noeud>();
		ArrayList<Noeud> listeRouteurs = new ArrayList<Noeud>();
		
		for(Noeud noeudCourant : graph.vertexSet())
		{
			if(graph.edgesOf(noeudCourant).size() > graph.edgesOf(noeudDepart).size())
			{
				noeudDepart = noeudCourant;
			}
		}
		
		fileFEL.offer(noeudDepart);
		Noeud noeudCourant;
		
		while(!fileFEL.isEmpty())
		{
			noeudCourant = fileFEL.poll();
			noeudCourant.visite = true;
			boolean besoinRouteur = false;
			
			for(DefaultEdge i : graph.edgesOf(noeudCourant))
			{
				Noeud voisin = graph.getEdgeSource(i); 
				if(voisin == noeudCourant)
				{
					voisin = graph.getEdgeTarget(i);
				}
				
				if(voisin.couleur == Noeud.Couleur.BLANC)
				{
					besoinRouteur = true;
					break;
				}
			}
			
			if(besoinRouteur)
			{
				noeudCourant.couleur = Noeud.Couleur.NOIR;
				noeudCourant.compteurRouteur++;
				
				if(!pileColore.contains(noeudCourant))
					pileColore.push(noeudCourant);
				
				for(DefaultEdge i : graph.edgesOf(noeudCourant))
				{
					Noeud voisin = graph.getEdgeSource(i); 
					if(voisin == noeudCourant)
					{
						voisin = graph.getEdgeTarget(i);
					}
					
					if(voisin.couleur == Noeud.Couleur.BLANC)
					{
						voisin.couleur = Noeud.Couleur.GRIS;
						
						if(!pileColore.contains(noeudCourant))
							pileColore.push(voisin);
					}
					
					voisin.compteurRouteur++;
					
					if(!voisin.visite)
						fileFEL.offer(voisin);
				}
			}
			else
			{
				for(DefaultEdge i : graph.edgesOf(noeudCourant))
				{
					Noeud voisin = graph.getEdgeSource(i); 
					if(voisin == noeudCourant)
					{
						voisin = graph.getEdgeTarget(i);
					}

					if(!voisin.visite)
						fileFEL.offer(voisin);
				}
			}
		}
		
		while(!pileColore.isEmpty())
		{
			noeudCourant = pileColore.pop();
			boolean peutEnleverRouteur = true;
			
			if(noeudCourant.couleur == Noeud.Couleur.NOIR)
			{
				for(DefaultEdge i : graph.edgesOf(noeudCourant))
				{
					Noeud voisin = graph.getEdgeSource(i); 
					if(voisin == noeudCourant)
					{
						voisin = graph.getEdgeTarget(i);
					}
					
					if(voisin.compteurRouteur == 1)
					{
						peutEnleverRouteur = false;
						break;
					}
				}
			}
			
			if(peutEnleverRouteur)
			{
				noeudCourant.couleur = Noeud.Couleur.GRIS;
				noeudCourant.compteurRouteur--;
				
				for(DefaultEdge i : graph.edgesOf(noeudCourant))
				{
					Noeud voisin = graph.getEdgeSource(i); 
					if(voisin == noeudCourant)
					{
						voisin = graph.getEdgeTarget(i);
					}
					
					voisin.compteurRouteur--;
				}
			}
			else
				listeRouteurs.add(noeudCourant);
		}
		
		// todo changer le return
		return listeRouteurs;
	}
}