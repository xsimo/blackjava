package com.xsimo.blackjava;


import java.util.ArrayList;
import ca.umontreal.iro.Keyboard;
public class Joueur {
	final static int DETTE_MAXI = 20;
	final static int CREDIT_INITIAL = 10;
	
	int credit;
	ArrayList<Carte> jeu;
	int mise;
	int score;
	int ascore;
	
	public Joueur(){
		this.credit = CREDIT_INITIAL;
		this.jeu = new ArrayList<Carte>();
	}
	
	public int miseMax(){
		return DETTE_MAXI+credit>45000?45000:DETTE_MAXI+credit;
	}
	
	public void miser(){
		System.out.println("Veuillez miser un nombre de jetons entre 1 et "+miseMax());
		mise = Keyboard.readInt();
		while(mise>miseMax() || mise<=0){
			System.out.println("S.v.p. veuillez entrez un nombre entier entre 1 et "+miseMax());
			mise = Keyboard.readInt();
		}
		System.out.println("-------------------------------------------------------------------");
	}
	public void gagne(){
		credit+= mise;
		init();
		System.out.println("Vous remportez cette manche et votre credit est maintenant de "+ credit);
	}
	public void perd(){
		credit-= mise;
		init();
		System.out.println("Voue perdez cette manche votre credit est maintenant de "+ credit);
		if(credit==-DETTE_MAXI){
			System.out.println("Votre dette maximale est atteinte, Au revoir \n");
			//System.exit(1);
		}
	}
	public void init(){
		mise = 0;
		ascore = 0;
		score = 0;
		jeu = null;
		jeu = new ArrayList<Carte>();
	}
	public void ajouterCarte(Carte c){
		jeu.add(c);
		score+=c.valeur;
		
		//s'il s'agit d'un AS et que le boni de dix points ne fait pas d�passer 21
		if(c.valeur==1 && score+10 <= 21){
				ascore = score + 10;
		}
		//sinon
		else {
			//si un possible boni de dix points pr�c�dent ne fait pas d�passer 21
			if((ascore+c.valeur) <= 21){
				ascore += c.valeur;
			} //sinon on perd le bonus pr�c�dent 
			else{
				ascore = score;
			}
		}
	}
	public void affiche(){
		System.out.println("voici votre jeu : ");
		for(int i = 0; i<jeu.size(); i++){
			jeu.get(i).affiche();
		}
		System.out.println("Ce qui fait que votre score est de "+score);
		if(ascore>0){
			System.out.println("Ou de "+ascore+" si l'as vaut 11");
		}
		System.out.println("-------------------------------------------------------------------");
	}

	public boolean decide() {
		char d = 'a';
		do{
			System.out.println("(c)arte ou (r)este ?");
			 d = Keyboard.readChar();
			
		}while(d != 'c' && d != 'r');
		return d=='c';
	}

	public boolean busted() {
		return (score>21);
	}

	public boolean blackjack() {
		return (score == 21 || ascore ==21);
	}
	public int gagnant(int croupier){
		if(score>croupier){
			return 1;
		}
		if(ascore > croupier){
			return 1;
		}
		if(score==croupier){
			return 0;
		}
		if(ascore==croupier){
			return 0;
		}
		return -1;
	}

	public void egalite() {
		//rien !
		init();
		
	}
}