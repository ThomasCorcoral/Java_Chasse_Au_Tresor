package TreasureHunter;

public class Treasure extends Entity
{

	public Treasure(int x, int y) 
	{
		super(x,y,"T");
	}
	
	/**
	 * Renvoie vrai si les coordonn�es correspondent � celles du tr�sor et faux sinon
	 * 
	 * @param 		x		x � tester
	 * @param 		y		y � tester
	 * @return		boolean
	 */
	public boolean is_treasure(int x, int y)
	{
		return this.getX() == x && this.getY() == y;
	}
}
