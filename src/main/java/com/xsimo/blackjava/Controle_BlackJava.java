package com.xsimo.blackjava;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controle_BlackJava {
	Modele_BlackJava modele;
	BlackJava vue;

	public Controle_BlackJava(Modele_BlackJava modele, BlackJava vue) {
		this.modele = modele;
		this.vue = vue;
	}

	public class action implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == vue.miser){
				try {
					int mise = Integer.parseInt(vue.mise.getText());
					if(mise<1 || mise > modele.j.miseMax()){
						throw new Exception();
					}
					modele.j.mise = mise;
				} catch (Exception ex) {
					vue.message.setText("la mise doit �tre \nun nombre entier entre 1 et "+modele.j.miseMax());
					return;
				}
				modele.c.init();
				for(int i =0; i<12;i++){
					vue.joueur.get(i).setIcon(null);
					vue.croupier.get(i).setIcon(null);
				}
				vue.carte.setEnabled(true);
				vue.reste.setEnabled(true);
				vue.miser.setEnabled(false);

				modele.p = new Paquet();
				modele.p.nouveau();
				modele.j.ajouterCarte(modele.p.pige());
				modele.j.ajouterCarte(modele.p.pige());
				modele.c.ajouterCarte(modele.p.pige());
				modele.c.ajouterCarte(modele.p.pige());
				vue.joueur.get(0).setIcon(vue.createImageIcon(modele.j.jeu.get(0).photo,""));
				vue.joueur.get(1).setIcon(vue.createImageIcon(modele.j.jeu.get(1).photo,""));
				vue.croupier.get(0).setIcon(vue.createImageIcon(modele.c.jeu.get(0).photo,""));
				vue.croupier.get(1).setIcon(vue.createImageIcon("b2fv.png",""));
				vue.message.setText("carte ou reste ?");
				vue.score.setText(Integer.toString(modele.j.ascore));
				if(modele.j.score==21 || modele.j.ascore==21){
					gagne();
					vue.carte.setEnabled(false);
					vue.reste.setEnabled(false);
					vue.miser.setEnabled(true);
				}
			}
			else if (event.getSource() == vue.carte){
				/** 
				 * une carte est pig� pour le joueur
				 */
				modele.j.ajouterCarte(modele.p.pige());
				vue.joueur.get(modele.j.jeu.size()-1).setIcon(vue.createImageIcon(modele.j.jeu.get(modele.j.jeu.size()-1).photo,""));
				vue.score.setText(Integer.toString(modele.j.ascore));
				/**
				 * si le joueur d�passe 21, il perd sans autre forme de proc�s
				 */
				if(modele.j.busted()){
					vue.message.setText("Vous avez depassez 21");
					perd();
				}else{
					/**
					 * sinon le croupier redemande carte ou reste
					 */
					vue.message.setText("carte ou reste ?");
				}
			}else if(event.getSource()== vue.reste){
				/**
				 * le croupier joue
				 */
				while(!modele.c.busted()&&modele.c.decide()){
					modele.c.ajouterCarte(modele.p.pige());
					vue.croupier.get(modele.c.jeu.size()-1).setIcon(vue.createImageIcon(modele.c.jeu.get(modele.c.jeu.size()-1).photo,""));
				}
				if(modele.c.busted()){
					gagne();
				}else{
					int result = modele.j.gagnant(modele.c.meilleurScore());
					switch(result){
					case(1): {
							gagne();
							break;
						}
					case (-1) : {
							vue.croupier.get(1).setIcon(vue.createImageIcon(modele.c.jeu.get(1).photo,""));
							vue.message.setText("Le croupier remporte cette manche");
							perd();
							break;
						}
					case (0) :{
							egalite();
						}
					}
				}
			}//fin de "reste"
			if(event.getSource()==vue.newGame){
				vue.carte.setEnabled(false);
				vue.reste.setEnabled(false);
				vue.miser.setEnabled(true);
				modele.j.init();
				modele.j.credit = Joueur.CREDIT_INITIAL;
				vue.credit.setText(Integer.toString(modele.j.credit));
				vue.message.setText("Bienvenue sur BlackJava\nMise maximale : "+Integer.toString(Joueur.CREDIT_INITIAL+Joueur.DETTE_MAXI));
				return;
			}
		}//fin de "actionPerformed" 
		public void perd(){
			vue.carte.setEnabled(false);
			vue.reste.setEnabled(false);
			modele.j.perd();
			vue.credit.setText(Integer.toString(modele.j.credit));

			if(modele.j.credit==-Joueur.DETTE_MAXI){
				vue.message.setText(vue.message.getText()+("\nvotre credit maximal est atteint"));
				vue.miser.setEnabled(false);
			}else{
				vue.miser.setEnabled(true);
			}
		}
		public void gagne(){
			vue.carte.setEnabled(false);
			vue.reste.setEnabled(false);
			vue.miser.setEnabled(true);
			vue.croupier.get(1).setIcon(vue.createImageIcon(modele.c.jeu.get(1).photo,""));
			modele.j.gagne();
			vue.message.setText("vous remportez cette manche");
			vue.credit.setText(Integer.toString(modele.j.credit));
		}
		public void egalite(){
			vue.carte.setEnabled(false);
			vue.reste.setEnabled(false);
			vue.miser.setEnabled(true);
			vue.croupier.get(1).setIcon(vue.createImageIcon(modele.c.jeu.get(1).photo,""));
			modele.j.egalite();
			vue.message.setText("cette manche est nulle");
			vue.credit.setText(Integer.toString(modele.j.credit));
		}
	}//fin de la classe action
}//fin de la classe Controle_BlackJava
