package com.xsimo.blackjava;


import java.util.ArrayList;
public class Paquet {
	ArrayList<Carte> pack;
	public Paquet(){
		this.pack = null;
	}
	public void nouveau(){
		this.pack = null;
		pack = new ArrayList<Carte>();
		int k = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Carte c = new Carte(j,i);
				pack.add(k,c);
				pack.get(k).affiche();
				System.out.println("la photo est "+c.photo);
				k++;
			}
		}
	}
	public Carte pige(){
		Carte carte = null;
		do{
			carte = pack.get((int)(Math.random()*52)); 
		}while(!carte.dispo);
		carte.dispo = false;
		return carte;
	}
}
