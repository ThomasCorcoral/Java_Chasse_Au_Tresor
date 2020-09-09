package TreasureHunter;

import PlateauDeJeu.Case;

public class Side extends Case
{

	public Side(int x, int y) 
	{
		super(x,y,"+");
	}

	@Override
	public void process(Hunter h) 
	{
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
