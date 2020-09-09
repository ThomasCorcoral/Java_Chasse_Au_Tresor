package PlateauDeJeu;
import TreasureHunter.Questionnable;
import java.awt.*;

public abstract class Case implements Questionnable
{
	
	private int x;
	private int y;
	private Color coul;
	private String symbole;

	public Case(int x, int y, String sym, Color coul) 
	{
		this.coul = coul;
		this.x = x;
		this.y = y;
		this.symbole = sym;
	}
	
	/**
	 *  getters
	 */
	public int getY() {return this.y;}
	public int getX() {return this.x;}
	public Color getCoul(){return this.coul;}
	
	/**
	 * 	setters
	 */
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	/**
	 * @return		String 		Symbole de la case
	 */
	public String to_String()
	{
		return this.symbole;
	}

}
