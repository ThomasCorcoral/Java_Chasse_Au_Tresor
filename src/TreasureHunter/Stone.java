package TreasureHunter;

import PlateauDeJeu.Case;
import java.awt.*;

public class Stone extends Case
{
	
	private int x_start, y_start, x_end, y_end;

	public Stone(int x, int y, int xst, int yst, int xend, int yend) 
	{
		super(x,y,"#", Color.BLUE);
		this.x_start = xst;
		this.y_start = yst;
		this.x_end = xend;
		this.y_end = yend;
	}

	@Override
	public void process(Hunter h) 
	{
		// Le joueur va rencontrer une pierre et donc un mur qu'il va falloir contourner 
		if(h.getX() == this.x_end)	// Si le Joueur se situe à la position représenté par la limite en x du mur
		{
			if((this.x_start - this.x_end) > 0) // le joueur se trouve à gauche
			{
				if(h.getY() < this.getY()) // au dessus
				{
					h.set_dir(6);
				}
				else
				{
					h.set_dir(4);
				}
			}
			else // le joueur se trouve à droite
			{
				if(h.getY() < this.getY()) // au dessus
				{
					h.set_dir(8);
				}
				else
				{
					h.set_dir(2);
				}
			}
		}
		else if(h.getX() == this.x_start) // Si le Joueur se situe à la position représenté par le début en x du mur
		{
			if((this.x_start - this.x_end) < 0) // le joueur se trouve à gauche
			{
				if(h.getY() < this.getY()) // au dessus
				{
					h.set_dir(6);
				}
				else
				{
					h.set_dir(4);
				}
			}
			else // le joueur se trouve à droite
			{
				if(h.getY() < this.getY()) // au dessus
				{
					h.set_dir(8);
				}
				else
				{
					h.set_dir(2);
				}
			}
		}
		else if(h.getY() == this.y_end) // Si le Joueur se situe à la position représenté par la limite en y du mur
		{
			if((this.y_start - this.y_end) > 0) // le joueur se trouve en haut
			{
				if(h.getX() < this.getX())	// à gauche 
				{
					h.set_dir(2);
				}
				else // à droite
				{
					h.set_dir(4);
				}
			}
			else // le joueur se trouve en bas
			{
				if(h.getX() < this.getX())	// à gauche 
				{
					h.set_dir(8);
				}
				else // à droite
				{
					h.set_dir(6);
				}
			}
		}
		else if(h.getY() == this.y_start) // Si le Joueur se situe à la position représenté par le début en y du mur
		{
			if((this.y_start - this.y_end) < 0) // le joueur se trouve en haut
			{
				if(h.getX() < this.getX())	// à gauche 
				{
					h.set_dir(2);
				}
				else // à droite
				{
					h.set_dir(4);
				}
			}
			else // le joueur se trouve en bas
			{
				if(h.getX() < this.getX())	// à gauche 
				{
					h.set_dir(8);
				}
				else // à droite
				{
					h.set_dir(6);
				}
			}
		}
		else if(this.x_start == this.x_end) 	// Mur vertical
		{
			// distance en passant par y_start
			int sol_1 = (int) ( Math.pow (Math.abs(this.y_start - h.getY()), 2) + 
						( Math.pow (Math.abs(this.y_start - h.get_treasure_y() ) , 2) + Math.pow( Math.abs(h.getX() - h.get_treasure_x()) , 2)));
		
			// distance en passant par y_end
			int sol_2 = (int) ( Math.pow (Math.abs(this.y_end - h.getY()), 2) +
						( Math.pow (Math.abs(this.y_end - h.get_treasure_y() ) , 2) + Math.pow( Math.abs(h.getX() - h.get_treasure_x()) , 2)));
			
			if(sol_1 < sol_2) // on passe par y_start
			{
				if(y_start < h.getY()) // si la destination (y_start) est au dessus 
				{
					h.set_dir(3);
				}
				else
				{
					h.set_dir(7);
				}
			}
			else // on passe par y_end
			{
				if(y_end < h.getY()) // si la destination (y_end) est au dessus 
				{
					h.set_dir(3);
				}
				else
				{
					h.set_dir(7);
				}
			}
		}
		else		// Mur horizontal
		{
			// distance en passant par x_start
			int sol_1 = (int) ( Math.pow (Math.abs(this.x_start - h.getX()), 2) + 
						( Math.pow (Math.abs(this.x_start - h.get_treasure_x() ) , 2) + Math.pow( Math.abs(h.getY() - h.get_treasure_y()) , 2)));
			
			// distance en passant par y_start
			int sol_2 = (int) ( Math.pow (Math.abs(this.x_end - h.getX()), 2) +
						( Math.pow (Math.abs(this.x_end - h.get_treasure_x() ) , 2) + Math.pow( Math.abs(h.getY() - h.get_treasure_y()) , 2)));
			
			if(sol_1 < sol_2) // on passe par x_start
			{
				if(x_start < h.getX()) // si la destination (x_start) est à gauche 
				{
					h.set_dir(5);
				}
				else // si la destination (x_start) est à droite 
				{
					h.set_dir(1);
				}
			}
			else
			{
				if(x_end < h.getX()) // si la destination (x_end) est à gauche
				{
					h.set_dir(5);
				}
				else // si la destination (x_end) est à droite 
				{
					h.set_dir(1);
				}
			}
		}
	}
}
