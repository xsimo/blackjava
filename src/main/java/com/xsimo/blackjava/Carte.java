package com.xsimo.blackjava;



public class Carte {
	int hauteur;
	int valeur;
	int sorte;
	boolean dispo;
	String photo;
	public Carte(int hauteur, int sorte){
		this.hauteur = hauteur;
		this.sorte = sorte;
		this.dispo = true;
		this.valeur = hauteur>=9? 10 : hauteur+1;
		int ph = hauteur >0 ? 56-(((hauteur)*4)+sorte) : (hauteur+1)+(3-sorte);
		this.photo = Integer.toString(ph)+".png";
	}
	public void affiche(){
		String s ="";
		if(this.hauteur == 0){
			s = "As";
		}
		else{if(this.hauteur<10){
			s = Integer.toString(this.hauteur+1);
		}}
		if(this.hauteur==10){
			s = "Valet";
		}
		if(this.hauteur == 11){
			s = "Dame";
		}
		if(this.hauteur == 12){
			s = "Roi";
		}
		String c = "";
		if(this.sorte == 2){
			c = "pique";
		}
		if(this.sorte == 1){
			c = "coeur";
		}
		if(this.sorte == 0){
			c = "carreau";
		}
		if(this.sorte == 3){
			c = "tr�fle";
		}
		System.out.println(s + " de " + c ) ;
	}
}
