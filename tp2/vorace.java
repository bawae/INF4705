package tp2;

import java.util.ArrayList;
import java.util.Random;

public class vorace {
	public ArrayList<Integer> AlgoVorace(int[][] donnees, int somme, int capacite)
	{
		double[] proba = new double[donnees.length];
		double[] revenus = new double[donnees.length];
		
		for(int i =0; i < donnees.length; i++)
		{
			revenus[i] = donnees[i][0] / donnees[i][1]; 
			proba[i] = revenus[i]/somme;
		}
		
		Random rand = new Random(System.currentTimeMillis());
		ArrayList<Integer> solution = new ArrayList<>();

		ArrayList<Integer> nouvelleSolution = new ArrayList<>();
		int revenusSolution = 0;
		int revenusNouvelleSolution = 0;
		
		for(int i =0; i < 10; i++)
		{
			int capaciteSolution = capacite;
			int check = 0;
		
			while(check < 10)
			{
				boolean solutionAjoutee = false;
				double fractionRand = (rand.nextInt()%100)/100;
				double sommeProb = 0;
			
				for(int j = 0; j < proba.length; j++)
				{
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
			
			if(revenusNouvelleSolution > revenusSolution)
			{
				revenusSolution = revenusNouvelleSolution;
				solution = nouvelleSolution;
			}
			
			nouvelleSolution = new ArrayList<>();
			revenusNouvelleSolution = 0;
		}
		
		return null;
	}
}
