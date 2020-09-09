package TreasureHunter;

import java.util.LinkedList;
import java.util.List;
import java.awt.*;
import PlateauDeJeu.Case;

public class Free extends Case 
{

	public Free(int x, int y) 
	{
		super(x,y,".", Color.GRAY);
	}

	@Override
	public void process(Hunter h) 
	{
		h.setX(this.getX());
		h.setY(this.getY());
		
		int x_tresor = h.get_treasure_x();
		int y_tresor = h.get_treasure_y();
		
		// Distance entre le chasseur et le tresor 
		int save = (int) (Math.pow( Math.abs(h.getX() - x_tresor) , 2) + Math.pow( Math.abs(h.getY() - y_tresor) , 2));
		
		//System.out.println("Cible : FREE"); /* DEBUG */
		
		if(save != 0)
		{
			int local_count = 0;
			int indice = 0;
			
			// Tableau avec les nouvelles direction, cette dernière sera choisit en fonction de la 
			// distance qui se trouve entre le chasseur et le trésor
			List<Integer> direction = new LinkedList<Integer>();
			init_liste(direction);
			
			for(int y = this.getY()-1; y < this.getY()+2; y++)
			{
				for(int x = this.getX()-1; x < this.getX()+2; x++)
				{
					// Si la distance est plus courte alors on stocke l'indice pour la direction future
					if(((int) (Math.pow( Math.abs(x - x_tresor) , 2) + Math.pow( Math.abs(y - y_tresor) , 2))) < save)
					{
						save = (int) (Math.pow( Math.abs(x - x_tresor) , 2) + Math.pow( Math.abs(y - y_tresor) , 2));
						indice = local_count;
					}
					local_count++;
				}
			}
			// on prend la future direction dans le tableau
			h.set_dir(direction.get(indice));
		}
		else
		{
			// Si l'utilisateur a gagné, on neutralise la direction
			h.set_dir(-1);
		}
	}
	
	public void init_liste(List<Integer> liste)
	{
		liste.add(4);
		liste.add(3);
		liste.add(2);
		liste.add(5);
		liste.add(0);	// Valeur normalement jamais utilisée
		liste.add(1);
		liste.add(6);
		liste.add(7);
		liste.add(8);
	}

}
