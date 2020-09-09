package vue;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	
	private Controleur controleur;
	private Container cp;
	private JButton butNextTurn;
	private JButton butNewPart;
	private JPanel [][] panCase;
	private JPanel panCentral;
	private JPanel case1;
	private JLabel [][] labCase;
	private JLabel labCentral;
	private JLabel labSouth;
	
	/**
	 *  Getters
	 */
	public JButton getButNextTurn(){return butNextTurn;} 
	public JButton getButNewPart(){return butNewPart;}
	public JLabel getLabCentral(){return labCentral;}
	public JPanel getPanCentral(){return panCentral;} 
	public JPanel getCase1(){return case1;}
	public JPanel [][] getPanCase(){return panCase;}
	public JLabel getLabSouth(){return labSouth;}
	public JLabel [][] getLabCase(){return labCase;}	
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructeur de la fenetre de type Fenetre
     *          
     */
	public Fenetre(){
		
		this.controleur= new Controleur(this, 12);
		
		int dim = controleur.getBoard().getdim();
		
		// -------------------------------3 panneaux dans la fenetre
		cp = getContentPane();
				
		// Panel Nord
		JPanel panNorth = new JPanel();
		
		// Panel Central
		panCentral = new JPanel();
		labCase = new JLabel[dim+2][dim+2];
		panCase = new JPanel[dim+2][dim+2];
		
		// On affiche le plateau du jeu avec ses composants
		dessinePlateau(dim, panCentral, panCase, labCase);
		
		// Panel Sud
		JPanel panSouth = new JPanel(); 
		panSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// placement des panneaux dans la fenetre
		cp.add(panNorth,BorderLayout.NORTH); // en haut
		cp.add(panCentral,BorderLayout.CENTER); //au centre
		cp.add(panSouth,BorderLayout.SOUTH); // en bas
		
		// -------------------------------Composants sur le panneau haut		
		butNextTurn = new JButton("Tour suivant");
		butNewPart = new JButton("Nouvelle Partie");
		
		butNewPart.addActionListener(controleur);
		butNextTurn.addActionListener(controleur);
		  
		panNorth.add(butNextTurn);
		panNorth.add(butNewPart);
		
		// -------------------------------Composants sur le panneau du bas
	    // Le label sur le panneau du bas 
		labSouth = new JLabel();
		panSouth.add(labSouth);
		
		
		//-----------------------------Les caracteristiques generales de la fenetre
		this.setTitle("Chasse au tr�sor");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);  
		
		controleur.update_prnt();
	}
	
	/**
     * Créer et dessine un nouveau plateau avec une nouveau panel central
     * qui remplace l'ancien en appelant la fonction dessinePlateau()
     *          
     */
	public void nouveauPlateu() {
		
		int dim = controleur.getBoard().getdim();
		
		// Nouveau panel central
		JPanel panCentral2 = new JPanel();
		
		JPanel [][] panCase2 = new JPanel[dim+2][dim+2];
		JLabel [][] labCase2 = new JLabel[dim+2][dim+2];
		
		dessinePlateau(dim, panCentral2, panCase2, labCase2);
	}
	
	/**
     * Dessine et affiche le plateau du jeu avec ses différents composants
     *  
     * @param	int		dim				Dimension du plateau
     * @param	JPanel	panCentral1		Panel central de la fenetre graphique
     * @param	JPanel	panCase1		Panel des cases du plateau
     * @param	JLabel	labCase1		Label des cases du plateau
     */
	public void dessinePlateau(int dim, JPanel panCentral1, JPanel [][] panCase1, JLabel [][] labCase1) {
			
			GridLayout grille = new GridLayout(dim+2,dim+2);
			panCentral1.setLayout(grille);
			panCentral = panCentral1;
	
			panCase = panCase1;
			labCase = labCase1;
			
			// On dessine le quadrillage et ses composants
			for(int i = 0; i < dim+2; i++) {
				for(int j = 0; j < dim+2; j++) {
					
					case1 = new JPanel();
					JLabel lab = new JLabel();
					case1.add(lab);
					labCase1[j][i] = lab;
				
					panCase1[j][i] = case1;
				
					if((j >= 1 && i == 0 && j <= dim) || (j >= 1 && i == dim+1 && j <= dim)) {
						lab.setText("" + j);
					}
					if((i >= 1 && j == 0 && i <= dim) || (i >= 1 && j == dim+1 && i <= dim)){
						lab.setText("" + i);
					}
					
					int indice = (dim+2)*i + j;
					
					// Si on arrive à la position de la case du trésor on lui met une couleur jaune,
					// et un tool tip text
					if(controleur.getBoard().getTreasure().is_treasure(j, i) )
					{
						case1.setToolTipText("Trésor");
						case1.setBackground(Color.YELLOW);
					}
					// Si on arrive à la position de la case d'un mur on lui met une couleur rouge,
					// et un tool tip text
					else if(controleur.getBoard().getCase(indice).to_String() == "+")
					{
						case1.setToolTipText("Mur");
						case1.setBackground(Color.RED);
					}
					// Si on arrive à la position de la case vide on lui met une couleur grise,
					// et un tool tip text
					else if(controleur.getBoard().getCase(indice).to_String() == ".")
					{
						case1.setToolTipText("Case " + "[" + j + ";" + i + "]");
						lab.setText(".");
						case1.setBackground(Color.GRAY);
					}
					// Si on arrive à la position de la case d'une pierre on lui met une couleur bleue,
					// et un tool tip text
					else if(controleur.getBoard().getCase(indice).to_String() == "#")
					{
						case1.setBackground(Color.BLUE);
						case1.setToolTipText("Pierre");
					}
					// Si on arrive à la position de la case d'un trou noir lui met sa couleur prédefinie,
					// et un tool tip text
					else if(controleur.getBoard().getCase(indice).to_String() == "O")
					{
						case1.setBackground(controleur.getBoard().getCase(indice).getCoul());
						case1.setToolTipText("Trou Noir");
					}
					// Sinon c'est les positions des personnages donc on met leur couleur
					else 
					{
						case1.setBackground(controleur.getBoard().getCase(indice).getCoul());
					}
					case1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					panCentral1.add(case1);
				}
			}
			cp.add(panCentral1,BorderLayout.CENTER); //au centre
		}
		
}
