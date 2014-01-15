package bubble;

import java.util.ArrayList;

public class Bubble
{
	// Selon le pseudo code trouvé sur http://en.wikipedia.org/wiki/Bubble_sort
	public ArrayList<Integer> sort(ArrayList<Integer> values)
	{
		// Détermine si un interchangement a eu lieu dans la dernière itération
		boolean swapped = false;
		
		do
		{
			swapped = false;
			for (int i = 1; i < values.size(); i++)
			{
				// Si la paire n'est pas ordonnée
				if (values.get(i - 1) > values.get(i))
				{
					// on interchange les valeurs
					int temp = values.get(i);
					values.set(i, values.get(i - 1));
					values.set(i - 1, temp);
					swapped = true;
				}
			}
		} while (swapped);
		
		return values;
	}
	
}
