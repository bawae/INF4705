package tp1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		
		String path = "";
		boolean printResult = false;
		
		// Analyse des arguments de la fonction
		for(int i = 0; i < args.length; i++) 
		{
			// -f décris l'emplacement du fichier
			if(args[i].equals("-f")) 
			{
				 path = args[++i];
			} 
			// -p détermine si on affiche le résultat a la fin ou non
			else if (args[i].equals("-p")) 
			{
				printResult = true;
			}
			else
			{
				System.out.println("erreur dans les arguments");
				return;
			}
		}
		
		// Lecture du fichier et transfer dans un tableau de valeurs
		ArrayList<Integer> values = new ArrayList<>();
		try 
		{
			FileInputStream fileInp = new FileInputStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileInp));
			String currentLine = "";
			while((currentLine = reader.readLine()) != null)
			{
				values.add(Integer.parseInt(currentLine));
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Appel de l'algorithme de tri à bulle
		Bubble bubbleSort = new Bubble();
		ArrayList<Integer> result = bubbleSort.sort(values);
		
		if(printResult)
		{
			for(int i = 0; i < result.size(); i++)
			{
				System.out.println(result.get(i));
			}
		}
		
	}
}
