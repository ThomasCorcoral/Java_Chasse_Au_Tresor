package vue;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PlateauDeJeu.Board;

public class Controleur implements ActionListener{
	
	private Fenetre vue;
	private Board plateau;
	private int size;
	final int LIMIT_WALL = 0;		// Nombre de murs
	final int LIMIT_HUNTER = 1;		// Nombre de joueurs
	final int LIMIT_BLACKHOLE = 5;	// Nombre de troues noirs (/!\ à multiplier par 2)


	public Controleur(Fenetre f, int size){
		this.size = size;
		plateau = new Board(this.size);
		vue = f;
		newPart(false);
	}
	
	public Board getBoard()
	{
		return this.plateau;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		
		
		if (source == vue.getButNewPart()) {
			newPart(true);
			update_prnt();
		}
		if (source == vue.getButNextTurn()) {
			update(); 
		} 
	}
	
	private void update()
	{
		clear_place();
		if(plateau.play_turn() != 0)
		{
			// TODO : la partie est finie
			fin_partie();
		}
		else {
			
			// TODO : La partie continue, update de la fenetre
			update_prnt();
		}
		
	}
	
	public void clear_place()
	{
		for(int i = 0; i < LIMIT_HUNTER; i++)
		{
			int posx = plateau.gethunter(i).getX();
			int posy = plateau.gethunter(i).getY();
			vue.getPanCase()[posx][posy].setBackground(Color.GRAY);
			vue.getLabCase()[posx][posy].setText(".");
		}
	}
	
	public void put_place()
	{
		for(int i = 0; i < LIMIT_HUNTER; i++)
		{
			int posx = plateau.gethunter(i).getX();
			int posy = plateau.gethunter(i).getY();
			vue.getPanCase()[posx][posy].setBackground(Color.WHITE);
			vue.getLabCase()[posx][posy].setText(plateau.gethunter(i).to_String());
		}
	}
	
	public void update_prnt() {
		String text = "<html>";
		for(int i = 0; i < LIMIT_HUNTER; i++)
		{
			int posx = plateau.gethunter(i).getX();
			int posy = plateau.gethunter(i).getY();
			int dir = plateau.gethunter(i).get_dir();
			int caseXCible = plateau.gethunter(i).case_cible(this.size+2,"x");
			int caseYCible = plateau.gethunter(i).case_cible(this.size+2,"y");
			String perso = "Personnage " + plateau.gethunter(i).to_String() + " : [" + posx + " " + posy + "] dir " + dir;
			int indiceCible = (this.size+2) * caseYCible + caseXCible;
			int oldDir = plateau.gethunter(i).get_old_dir();
			if(oldDir != dir) {
				perso = "Personnage " + plateau.gethunter(i).to_String() + " : Nouvelle direction : " + dir + " [" + posx + " " + posy + "] dir " + dir;
			}
			if(plateau.getCase(indiceCible).to_String() == "#" && oldDir != dir) {
				perso = "Personnage " + plateau.gethunter(i).to_String() + " : Case ciblée est une pierre " + " : Nouvelle direction : " + dir + " [" + posx + " " + posy + "] dir " + dir;
			}
			else if(plateau.getCase(indiceCible).to_String() == "+" && oldDir != dir) {
				perso = "Personnage " + plateau.gethunter(i).to_String() + " : Case ciblée est un mur " + " : Nouvelle direction : " + dir + " [" + posx + " " + posy + "] dir " + dir;
			}
			text = text + "<br>" + perso;
		}
		text = text + "</html>";
		vue.getLabSouth().setText(text);
		put_place();
		vue.getButNewPart().setEnabled(true);
	}
	
	private void newPart(boolean res){
		
		if(res != false) {
			vue.remove(vue.getPanCentral());         
	        vue.repaint();
			vue.getButNextTurn().setEnabled(true);
			vue.getButNewPart().setEnabled(false);
			Board newPlateau = new Board(this.size);
			newPlateau.init_Cases();
			int i = 0;
			while(i < LIMIT_BLACKHOLE)
			{
				newPlateau.init_bh();
				newPlateau.sync_bh();
				i++;
			}
			i = 0;
			while(i < LIMIT_WALL)
			{
				newPlateau.init_Wall();
				newPlateau.sync_walls();
				i++;
			}
			i = 0;
			newPlateau.init_Treasure();
			while(i < LIMIT_HUNTER)
			{
				newPlateau.init_Hunters();
				newPlateau.hunter_sync();
				i++;
			}
			this.plateau = newPlateau;
	        vue.nouveauPlateu();
		}
		else {
			plateau.init_Cases();
			int i = 0;
			while(i < LIMIT_BLACKHOLE)
			{
				plateau.init_bh();
				plateau.sync_bh();
				i++;
			}
			i = 0;
			while(i < LIMIT_WALL)
			{
				plateau.init_Wall();
				plateau.sync_walls();
				i++;
			}
			i = 0;
			plateau.init_Treasure();
			while(i < LIMIT_HUNTER)
			{
				plateau.init_Hunters();
				plateau.hunter_sync();
				i++;
			}
		}
	}
	
	public void fin_partie() {
		int posxT = plateau.getTreasure().getX();
		int posyT = plateau.getTreasure().getY();
		String text = "<html>" + "Fin de la partie !";
		put_place();
		for(int i = 0; i < LIMIT_HUNTER; i++)
		{
			int posx = plateau.gethunter(i).getX();
			int posy = plateau.gethunter(i).getY();
			int dir = plateau.gethunter(i).get_dir();
			String perso = "Personnage " + plateau.gethunter(i).to_String() + " : [" + posx + " " + posy + "] dir " + dir;
			if(posx == posxT && posy == posyT) {
				vue.getPanCase()[posx][posy].setBackground(Color.YELLOW);
				vue.getLabCase()[posx][posy].setText(plateau.gethunter(i).to_String());
				perso = "Personnage " + plateau.gethunter(i).to_String() + " : ** WIN **" + " : [" + posx + " " + posy + "] dir " + plateau.gethunter(i).get_old_dir();
			}
			text = text + "<br>" + perso;	
		}
		text = text + "</html>";
		vue.getLabSouth().setText(text);
		vue.getButNextTurn().setEnabled(false);
		vue.getButNewPart().setEnabled(true);
	}

}
