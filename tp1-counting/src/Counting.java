import java.util.ArrayList;


public class Counting {

	// Selon le pseudo code trouvé sur http://fr.wikipedia.org/wiki/Tri_comptage
	public ArrayList<Integer> sort(ArrayList<Integer> values, int lowerBound, int upperBound)
	{
		
//		/* Initialisation des variables */
//		 
//		   tab_comptage[borne_superieure + 1]
//		   taille_tab ⇐ taille(tab) - 1
//		 
//		   /* Initialisation du tableau de comptage à 0 */
//		 
//		   Pour i ⇐ 0 Jusqu'à borne_superieure
//		   Faire
//		      tab_comptage[ i ] ⇐ 0
//		   FinPour
//		 
//		   /* Création du tableau de comptage */
//		 
//		   Pour i ⇐ 0 Jusqu'à taille_tab
//		   Faire
//		      tab_comptage[ tab[ i ] ] ⇐ tab_comptage[ tab[ i ] ] + 1
//		   FinPour
//		 
//		   /* Création du tableau trié */
//		 
//		   l ⇐ 0
//		   Pour i ⇐ 0 Jusqu'à borne_superieure
//		   Faire
//		      Pour j ⇐ 1 Jusqu'à tab_comptage[i]
//		      Faire
//		         tab[l] = i
//		         l ⇐ l + 1
//		      FinPour
//		   FinPour
//		 
//		 Retourne tab
		
		// Initialisation des variables
		int sizeCountingTab = (upperBound - lowerBound) + 1;
		int[] countingTab = new int[sizeCountingTab];
		int tabSize = values.size() -1;
		
		// Initialisation du tableau de comptage
		for(int i = lowerBound; i <= upperBound; i++)
		{
			countingTab[values.get(i)] = countingTab[values.get(i)]+1;
		}
		
		// Création du tableau trié
		
		
		
		return values;
	}
}
