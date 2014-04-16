public class Noeud
{
	public Integer numeroNoeud;
	public enum Couleur{BLANC, GRIS, NOIR};
	public Couleur couleur;
	public int compteurRouteur;
	public boolean visite;
	
	public Noeud(int numeroNoeud, Couleur couleur, int compteurRouteur, boolean visite)
	{
		this.numeroNoeud = numeroNoeud;
		this.couleur = couleur;
		this.compteurRouteur = compteurRouteur;
		this.visite = visite;
	}
	
	public Noeud()
	{
		numeroNoeud = 0;
		couleur = Couleur.BLANC;
		compteurRouteur = 0;
		visite = false;
	}
}
