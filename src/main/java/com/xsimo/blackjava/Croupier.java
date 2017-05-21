package com.xsimo.blackjava;


import java.util.ArrayList;
public class Croupier {
	ArrayList<Carte> jeu;
	final int STOP = 17;
	final int ASTOP = 17;
	int score;
	int ascore;
	public Croupier(){
		this.jeu = new ArrayList<Carte>();
	}
	public void ajouterCarte(Carte c){
		jeu.add(c);
		score+=c.valeur;
		if(c.valeur==1 && score+10 <= 21 && ascore == 0){
			ascore = score + 10;
		}
		else {if(ascore != 0 && ascore+score < 21){
			ascore += c.valeur;
		} else{
			ascore = 0;
		}}
	}
	public void init(){
		score = 0;
		ascore = 0;
		jeu = null;
		jeu = new ArrayList<Carte>();
	}
	public void affiche(){
		System.out.println("le jeu du croupier est : ");
		for(int i = 0; i<jeu.size(); i++){
			jeu.get(i).affiche();
		}
		System.out.println("Ce qui donne un score de "+score);
		if(ascore>0){
			System.out.println("Ou de "+ascore+" si l'as vaut 11");
		}
		System.out.println("-------------------------------------------------------------------");

	}
	public boolean decide() {
		if(ascore>=ASTOP)
			return false;
		if(score>=STOP)
			return false;
		return true;
	}
	public boolean busted() {
		if(score > 21){
			return true;
		}
		return false;
	}
	public int meilleurScore() {
		if(ascore != 0 && ascore < 22){
			return ascore;
		}
		return score;
	}
}
