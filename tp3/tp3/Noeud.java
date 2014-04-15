package tp3;

public class Noeud
{
	public int numeroNoeud;
	public enum Couleur{BLANC, GRIS, NOIR};
	public Couleur couleur;
	public int compteurRouteur;
	
	public Noeud(int numeroNoeud, Couleur couleur, int compteurRouteur)
	{
		this.numeroNoeud = numeroNoeud;
		this.couleur = couleur;
		this.compteurRouteur = compteurRouteur;
	}
	
	public Noeud()
	{
		numeroNoeud = 0;
		couleur = Couleur.BLANC;
		compteurRouteur = 0;
	}
}
