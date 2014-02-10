package parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class parseCSV
{
	public static void main(String[] args)
	{
		File file = new File("logs/seuil.csv");
		PrintWriter writer;
		String[] type = {"1000", "5000", "10000", "50000", "100000"};
		String[] sort = {"quickSeuil", "quickMedSeuil"};
		
		try
		{
			BufferedReader s = new BufferedReader(new FileReader(file));
			
			writer = new PrintWriter("logs/seuilSorted.csv", "UTF-8");
			writer.println("nom,algorithme,seuil,moyenne");
			writer.flush();
			s.readLine();
	
			long moyenne;
			int compteur;
			String ligne;
			
			for(String nom : type)
			{
				for(String so : sort)
				{
					for(int seuil = 5; seuil <= 100; seuil++)
					{
						moyenne = 0;
						compteur = 0;
						
						for(int i = 0; i < 30; i++)
						{
							while((ligne = s.readLine()) != null)
							{
								String[] parts = ligne.split(",");
								
								if(parts[0].equals("testset_" + nom + "_" + Integer.toString(i) + ".txt") && parts[1].equals(so) && parts[2].equals(Integer.toString(seuil)))
								{
									moyenne += Long.parseLong(parts[3]);
									compteur++;
								}
							}
							s.close();
							s = new BufferedReader(new FileReader(file));
						}

						
						if(compteur != 0)
						{
							moyenne /= compteur;
							writer.println(nom + "," + so + "," + Integer.toString(seuil) + "," + Long.toString(moyenne));
						}
					}
				}
			}
			
			writer.close();
			s.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
