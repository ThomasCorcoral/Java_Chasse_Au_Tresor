package TreasureHunter;

import PlateauDeJeu.Case;
import java.awt.*;

public class Side extends Case
{

	public Side(int x, int y) 
	{
		super(x,y,"+", Color.RED);
	}

	@Override
	public void process(Hunter h) 
	{
		// Ici, le joueur va taper dans un mur et de ce fait, on va le renvoyer dans la direction opposée
		int local_dir = h.get_dir();
		if(local_dir == 4)
		{
			h.set_dir(8);
		}
		else
		{
			h.set_dir((local_dir + 4) % 8);
		}
	}

}
