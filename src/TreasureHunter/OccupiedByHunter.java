package TreasureHunter;

import PlateauDeJeu.Case;
import java.awt.*;

public class OccupiedByHunter extends Case
{

	public OccupiedByHunter(int x, int y, String nom) 
	{
		super(x,y,nom, Color.WHITE);
	}

	@Override
	public void process(Hunter h) 
	{
		// nouvelle direction aléatoire différente de la direction actuelle
		int new_dir = h.get_dir();
		do
		{
			new_dir = (int) (1 + (Math.random() * (8-1)));
		}while(new_dir == h.get_dir());
		
		h.set_dir(new_dir);
	}

}
