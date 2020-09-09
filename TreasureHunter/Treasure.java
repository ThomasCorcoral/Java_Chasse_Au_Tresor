package TreasureHunter;

public class Treasure extends Entity
{

	public Treasure(int x, int y) 
	{
		super(x,y,"T");
	}
	
	public boolean is_treasure(int x, int y)
	{
		return this.getX() == x && this.getY() == y;
	}

}
