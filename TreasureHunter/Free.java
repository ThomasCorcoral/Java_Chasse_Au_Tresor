package TreasureHunter;

import java.util.LinkedList;
import java.util.List;

import PlateauDeJeu.Case;

public class Free extends Case 
{

	public Free(int x, int y) 
	{
		super(x,y,".");
	}

	@Override
	public void process(Hunter h) 
	{
		h.setX(this.getX());
		h.setY(this.getY());
		
		int x_tresor = h.get_treasure_x();
		int y_tresor = h.get_treasure_y();
		
		
		int save = (int) (Math.pow( Math.abs(h.getX() - x_tresor) , 2) + Math.pow( Math.abs(h.getY() - y_tresor) , 2));
		
		//System.out.println("Cible : FREE"); /* DEBUG */
		
		if(save != 0)
		{
			int local_count = 0;
			int indice = 0;
			
			List<Integer> direction = new LinkedList<Integer>();
			init_liste(direction);
			
			for(int y = this.getY()-1; y < this.getY()+2; y++)
			{
				for(int x = this.getX()-1; x < this.getX()+2; x++)
				{
					if(((int) (Math.pow( Math.abs(x - x_tresor) , 2) + Math.pow( Math.abs(y - y_tresor) , 2))) < save)
					{
						save = (int) (Math.pow( Math.abs(x - x_tresor) , 2) + Math.pow( Math.abs(y - y_tresor) , 2));
						indice = local_count;
					}
					local_count++;
				}
			}
			
			h.set_dir(direction.get(indice));
		}
		else
		{
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
