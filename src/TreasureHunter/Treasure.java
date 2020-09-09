package TreasureHunter;

public class Treasure extends Entity
{

	public Treasure(int x, int y) 
	{
		super(x,y,"T");
	}
	
	/**
	 * Renvoie vrai si les coordonnées correspondent à celles du trésor et faux sinon
	 * 
	 * @param 		x		x à tester
	 * @param 		y		y à tester
	 * @return		boolean
	 */
	public boolean is_treasure(int x, int y)
	{
		return this.getX() == x && this.getY() == y;
	}
}
