package TreasureHunter;

import java.awt.*;

import PlateauDeJeu.Case;

public class BlackHole extends Case 
{
	
	private int other_x, other_y;

	public BlackHole(int x, int y, int o_x, int o_y, Color coul)
	{
		super(x,y,"O", coul);
		this.other_x = o_x;
		this.other_y = o_y;
	}
	
	@Override
	public void process(Hunter h) 
	{
		int x_plus, y_plus;
		do
		{
			x_plus = (int) (Math.random() * (3));
			y_plus = (int) (Math.random() * (3));
		}while(x_plus == 1 && y_plus == 1);
		
		h.setX(this.other_x-1+x_plus);
		h.setY(this.other_y-1+y_plus);
	}

}
