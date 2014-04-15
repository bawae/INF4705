package tp3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import tp3.Noeud.Couleur;

public class main
{
	private static int nbAretes = 0,
			nbSommets = 0;

    private static UndirectedGraph<Noeud, Integer> u = new SimpleGraph<Noeud, Integer>(Integer.class);
	
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
		ArrayList<Noeud> result = optimisationRouteurs(u);
		
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

			Noeud[] tabNoeuds = new Noeud[nbSommets];
			
			for(int i = 1; i <= nbSommets; i++)
			{
				tabNoeuds[i-1] = new Noeud(i, Couleur.BLANC, 0); 
				u.addVertex(tabNoeuds[i-1]);
			}
			
			for(int i = 0; i < nbAretes; i++)
			{
				s.nextInt();
				u.addEdge(tabNoeuds[s.nextInt()-1], tabNoeuds[s.nextInt()-1]);
			}
			
			s.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier n'a pas ete trouve!");
			System.exit(0);
		}
	}
	
	private static ArrayList<Noeud> optimisationRouteurs(UndirectedGraph<Noeud, Integer> graph)
	{
		Queue<Noeud> fileFEL = new LinkedList<Noeud>();
		Noeud noeudDepart = new Noeud();
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
			boolean besoinRouteur = false;
			
			for(Integer i : graph.edgesOf(noeudCourant))
			{
				Noeud voisin = graph.getEdgeSource(i); 
				if(voisin == noeudCourant)
				{
					voisin = graph.getEdgeTarget(i);
				}
				
				if(voisin.couleur == Couleur.BLANC)
				{
					besoinRouteur = true;
					break;
				}
			}
			
			if(besoinRouteur)
			{
				noeudCourant.couleur = Couleur.NOIR;
				noeudCourant.compteurRouteur++;
				
				pileColore.push(noeudCourant);
				
				for(Integer i : graph.edgesOf(noeudCourant))
				{
					Noeud voisin = graph.getEdgeSource(i); 
					if(voisin == noeudCourant)
					{
						voisin = graph.getEdgeTarget(i);
					}
					
					if(voisin.couleur == Couleur.BLANC)
					{
						voisin.couleur = Couleur.GRIS;
					}
					
					voisin.compteurRouteur++;
					pileColore.push(voisin);
					fileFEL.offer(voisin);
				}
			}
			else
			{
				for(Integer i : graph.edgesOf(noeudCourant))
				{
					Noeud voisin = graph.getEdgeSource(i); 
					if(voisin == noeudCourant)
					{
						voisin = graph.getEdgeTarget(i);
					}
					
					fileFEL.offer(voisin);
				}
			}
		}
		
		while(!pileColore.isEmpty())
		{
			noeudCourant = pileColore.pop();
			boolean peutEnleverRouteur = true;
			
			if(noeudCourant.couleur == Couleur.NOIR)
			{
				for(Integer i : graph.edgesOf(noeudCourant))
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
				noeudCourant.couleur = Couleur.GRIS;
				noeudCourant.compteurRouteur--;
				
				for(Integer i : graph.edgesOf(noeudCourant))
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