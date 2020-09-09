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
	
	public JButton getButNextTurn(){return butNextTurn;} 
	public JButton getButNewPart(){return butNewPart;}
	public JLabel getLabCentral(){return labCentral;}
	public JPanel getPanCentral(){return panCentral;} 
	public JPanel getCase1(){return case1;}
	public JPanel [][] getPanCase(){return panCase;}
	public JLabel getLabSouth(){return labSouth;}
	public JLabel [][] getLabCase(){return labCase;}	
	
	private static final long serialVersionUID = 1L;
	
	public Fenetre(){
		
		this.controleur= new Controleur(this, 10);
		
		int dim = controleur.getBoard().getdim();
		
		// -------------------------------3 panneaux dans la fenetre
		cp = getContentPane();
				
		// la fenetre est structure en 3 panneaux
		JPanel panNorth = new JPanel();
				  
		panCentral = new JPanel();
		GridLayout grille = new GridLayout(dim+2,dim+2);
		panCentral.setLayout(grille);
		
		panCase = new JPanel[dim+2][dim+2];
		labCase = new JLabel[dim+2][dim+2];
		
		// On dessine le quadrillage et ses composants
		for(int i = 0; i < dim+2; i++) {
			for(int j = 0; j < dim+2; j++) {
				
				case1 = new JPanel();
				JLabel lab = new JLabel();
				case1.add(lab);
				labCase[j][i] = lab;
			
				panCase[j][i] = case1;
			
				if((j >= 1 && i == 0 && j <= dim) || (j >= 1 && i == dim+1 && j <= dim)) {
					lab.setText("" + j);
				}
				if((i >= 1 && j == 0 && i <= dim) || (i >= 1 && j == dim+1 && i <= dim)){
					lab.setText("" + i);
				}
				
				int indice = (dim+2)*i + j;
				if(controleur.getBoard().getTreasure().is_treasure(j, i) )
				{
					//lab.setText("T");
					case1.setBackground(Color.YELLOW);
				}
				else if(controleur.getBoard().getCase(indice).to_String() == "+")
				{
					case1.setBackground(Color.RED);
				}
				else 
				{
					lab.setText(controleur.getBoard().getCase(indice).to_String());
					case1.setBackground(controleur.getBoard().getCase(indice).getCoul());
				}
				if(i == 0) {
					case1.setToolTipText("1");
				}
				case1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panCentral.add(case1);
			}
		}
		
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
		
		// -------------------------------Composants sur le panneau central
		// Le label sur le panneau central  
		//labCentral = new JLabel("");
		//panCentral.add(labCentral);
		
		// -------------------------------Composants sur le panneau du bas
	    // Le label sur le panneau du bas 
		labSouth = new JLabel();
		panSouth.add(labSouth);
		
		
		//-----------------------------Les caracteristiques generales de la fenetre
		this.setTitle("Chasse au trésor");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);  
		
		controleur.update_prnt();
	}
	
	public void nouveauPlateu() {
		
		int dim = controleur.getBoard().getdim();
		
		JPanel panCentral2 = new JPanel();
		GridLayout grille = new GridLayout(dim+2,dim+2);
		panCentral2.setLayout(grille);
		panCentral = panCentral2;
		
		JPanel [][] panCase2 = new JPanel[dim+2][dim+2];
		JLabel [][] labCase2 = new JLabel[dim+2][dim+2];
		
		panCase = panCase2;
		labCase = labCase2;
		
		// On dessine le quadrillage et ses composants
		for(int i = 0; i < dim+2; i++) {
			for(int j = 0; j < dim+2; j++) {
				
				case1 = new JPanel();
				JLabel lab = new JLabel();
				case1.add(lab);
				labCase2[j][i] = lab;
			
				panCase2[j][i] = case1;
			
				if((j >= 1 && i == 0 && j <= dim) || (j >= 1 && i == dim+1 && j <= dim)) {
					lab.setText("" + j);
				}
				if((i >= 1 && j == 0 && i <= dim) || (i >= 1 && j == dim+1 && i <= dim)){
					lab.setText("" + i);
				}
				
				int indice = (dim+2)*i + j;
				
				if(controleur.getBoard().getTreasure().is_treasure(j, i) )
				{
					//lab.setText("T");
					case1.setBackground(Color.YELLOW);
				}
				else if(controleur.getBoard().getCase(indice).to_String() == "+")
				{
					case1.setBackground(Color.RED);
				}
				else 
				{
					lab.setText(controleur.getBoard().getCase(indice).to_String());
					case1.setBackground(controleur.getBoard().getCase(indice).getCoul());
				}
				if(i == 0) {
					case1.setToolTipText("1");
				}
				case1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panCentral2.add(case1);
			}
		}
		cp.add(panCentral2,BorderLayout.CENTER); //au centre
	}
	
}
