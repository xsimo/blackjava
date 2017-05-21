//
//
//public class Jeu {
//
//	public static void main(String[] args) {
//		Joueur j = new Joueur();
//		Croupier c = new Croupier();
//		do{
//			j.miser();
//			Paquet p = new Paquet();
//			p.nouveau();
//			j.ajouterCarte(p.pige());
//			j.ajouterCarte(p.pige());
//			c.ajouterCarte(p.pige());
//			c.ajouterCarte(p.pige());
//			j.affiche();
//			System.out.println("la carte visible du croupier est :");
//			c.affiche();
//			if (j.blackjack()) {
//				j.gagne();
//			} else {
//				while (!j.busted() && j.decide()) {
//					j.ajouterCarte(p.pige());
//					j.affiche();
//				}
//				if (j.busted()) {
//					System.out.println("Vous avez dépassé 21");
//					j.perd();
//				} else {
//					while (!c.busted() && c.decide()) {
//						c.ajouterCarte(p.pige());
//					}
//					if (c.busted()) {
//						c.affiche();
//						j.gagne();
//					} else {
//						if (j.gagnant(c.meilleurScore())) {
//							c.affiche();
//							j.gagne();
//						} else {
//							c.affiche();
//							j.perd();
//						}
//					}
//				}
//			}
//			c.init();
//		} while (continuer());
//	}
//	public static boolean continuer(){
//		char c = 'a';
//		do{
//			System.out.println("(c)ontinuer ou (q)uitter ? ");
//			c = Keyboard.readChar();
//		}while(c!= 'c' && c!= 'q');
//		return c=='c';
//	}
//}
