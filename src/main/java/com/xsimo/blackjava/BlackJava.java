package com.xsimo.blackjava;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;



public class BlackJava extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Buffer image
	java.net.URL u = null;
	javax.swing.JLabel lab;
	JButton carte;
	JButton reste;
	JButton miser;
	JButton newGame;
	JTextField mise;
	JTextArea message;
	JTextArea credit;
	JTextArea score;
	JLabel labelMise;
	JLabel labelCredit;
	JLabel logo;
	ArrayList<JLabel> croupier;
	ArrayList<JLabel> joueur;
	Paquet p;
	private javax.swing.JLayeredPane mainPanel;
	Controle_BlackJava controle;
	Modele_BlackJava modele;
	
    // --------------------------------------------------
	// INITIALISATION DE L'APPLET
	// --------------------------------------------------
	public void init() {
		
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
        	e.printStackTrace();
            System.err.println("createGUI didn't successfully complete");
        }
		
	}
	//Cr�ation du GUI
	protected void createGUI() {
		this.modele = new Modele_BlackJava(this);
		this.controle = new Controle_BlackJava(modele,this);
		
		mainPanel = new javax.swing.JLayeredPane();
		mainPanel.setSize(400,300);
		getContentPane().setBackground(new Color(15,180,172));

		
		
		croupier = new ArrayList<JLabel>();
		for(int i = 0;i<12;i++){
			croupier.add(i,new JLabel("",null,javax.swing.JLabel.CENTER));
			croupier.get(i).setName("croupier1Name");
	        croupier.get(i).setBounds((172+(i*12)), 12, 72, 96);
	        mainPanel.add(croupier.get(i),new Integer(i));
		}
		
		joueur = new ArrayList<JLabel>();
		for(int i = 0;i<12;i++){
			joueur.add(i,new JLabel("",null,javax.swing.JLabel.CENTER));
	        joueur.get(i).setName("joueur1Name");
	        joueur.get(i).setBounds((172+(i*12)), 120, 72, 96);
	        mainPanel.add(joueur.get(i),new Integer(i));
		}
		
		
        mainPanel.setName("mainPanel"); // NOI18N
        //logo
        ImageIcon image = createImageIcon("java.jpg","logo description");
        logo = new JLabel("",image,javax.swing.JLabel.CENTER);
        logo.setBounds(50,12,75,75);
        mainPanel.add(logo,new Integer(15));

       /**
         * COMMANDES
         */
        //carte
        carte = new JButton("carte");
		carte.addActionListener(controle.new action());
		carte.setText("carte");
		carte.setSize(75,30);
		carte.setBounds(50, 100, carte.getWidth(), carte.getHeight());
		carte.setEnabled(false);
		mainPanel.add(carte,new Integer(12));
        
		//reste
		reste = new JButton("carte");
		reste.addActionListener(controle.new action());
		reste.setText("reste");
		reste.setSize(75,30);
		reste.setBounds(50, 135, reste.getWidth(), reste.getHeight());
		reste.setEnabled(false);
		mainPanel.add(reste,new Integer(12));
		
		
		
		//message
		message = new JTextArea("Bienvenue sur BlackJava\nMise maximale : "+Integer.toString(Joueur.CREDIT_INITIAL+Joueur.DETTE_MAXI));
		message.setSize(150,55);
		message.setBackground(new Color(235,235,235));
		message.setLineWrap(true);
		BevelBorder bordure = new BevelBorder(BevelBorder.LOWERED);
		message.setBorder(bordure);
		message.setBounds(172,224,message.getWidth(),message.getHeight());
		message.setWrapStyleWord(true);
		//plus petite font
		Font font = message.getFont();//new Font(Font.SERIF,Font.PLAIN,12);
		int style = font.getStyle();
		int size = 10;
		String name = font.getName();
		Font myFont = new Font(name,style,size);
		message.setFont(myFont);
		mainPanel.add(message);
		
		//mise
		mise = new JTextField();
		mise.setSize(75,20);
		mise.setBounds(50,205,mise.getWidth(),mise.getHeight());
		mise.setText("1");
		mainPanel.add(mise);
		
		//label Mise
		labelMise = new JLabel();
		labelMise.setSize(30,20);
		labelMise.setBounds(10,205,labelMise.getWidth(),labelMise.getHeight());
		labelMise.setText("mise");
		
		labelMise.setFont(myFont);
		mainPanel.add(labelMise);
		
		//credit
		credit = new JTextArea();
		credit.setSize(75,20);
		credit.setBackground(new Color(235,235,235));
		credit.setBorder(bordure);
		credit.setBounds(50,230,credit.getWidth(),credit.getHeight());
		credit.setEditable(false);
		credit.setText(Integer.toString(modele.j.credit));
		mainPanel.add(credit);
		
		//label Cr�dit
		labelCredit = new JLabel();
		labelCredit.setSize(30,20);
		labelCredit.setBounds(10,230,labelCredit.getWidth(),labelCredit.getHeight());
		labelCredit.setText("credit");
		labelCredit.setFont(myFont);
		mainPanel.add(labelCredit);
		
		//score
		score = new JTextArea();
		score.setSize(20,20);
		score.setBackground(new Color(235,235,235));
		score.setBorder(bordure);
		score.setBounds(140,160,20,20);
		score.setText("");
		mainPanel.add(score);
		
		//miser
		miser = new JButton("miser");
		miser.setSize(75,30);
		miser.setBounds(50,170,miser.getWidth(),miser.getHeight());
		miser.addActionListener(controle.new action());
		mainPanel.add(miser);
		
		//new game
		newGame = new JButton("reset");
		newGame.setSize(75,20);
		newGame.setBounds(50,255,newGame.getWidth(),newGame.getHeight());
		newGame.addActionListener(controle.new action());
		mainPanel.add(newGame);
		/**
		 * Main
		 */
		getContentPane().add(mainPanel);
		this.setSize(400,300);
		this.setVisible(true);
		
	}
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String name,String description) {
		java.net.URL imgURL = null;
		try {
			imgURL = new java.net.URL(
					getCodeBase()+"/images/"+name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + name);
	        return null;
	    }
	}


}
