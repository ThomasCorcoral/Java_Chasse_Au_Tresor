package TreasureHunter;

import PlateauDeJeu.Case;

public class OccupiedByHunter extends Case
{

	public OccupiedByHunter(int x, int y, String nom) 
	{
		super(x,y,nom);
	}

	@Override
	public void process(Hunter h) 
	{
		int new_dir = h.get_dir();
		do
		{
			new_dir = (int) (1 + (Math.random() * (8-1)));
		}while(new_dir == h.get_dir());
		
		h.set_dir(new_dir);
	}

}
