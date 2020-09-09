package TreasureHunter;

import java.util.LinkedList;
import java.util.List;

import PlateauDeJeu.Case;

public class Wall 
{
	private List<Case> mur;
	private int size;
	
	/**
	 * Création d'un mur à l'aide des paramètres déterminés dans la classe Board
	 * 
	 * @param dir		int		direction du mur
	 * @param size		int 	taille du mur
	 * @param x_init	int 	x de la première case
	 * @param y_init	int 	y de la première case
	 */
	public Wall(int dir, int size, int x_init, int y_init) 	//			  4
	{														// dir :   3  #  1
		this.size = size;									//			  2
		mur = new LinkedList<Case>();
		
		if(dir == 4 || dir == 3)
		{
			if(dir == 4) // haut
			{	
				for(int y = y_init; y > (y_init - size); y--)
				{
					mur.add(new Stone(x_init , y , x_init , y_init, x_init, y_init-size+1));
					//System.out.println(" MUR : x :" + x_init + " / y : " + y );
				}
			//	System.out.println(" MUR : x :" + x_init + " / y : " + y_init + " / x max : " + x_init + " / y max : " + (y_init-size+1));
			}
			else	// gauche
			{
				for(int x = x_init; x > (x_init - size); x--)
				{
					mur.add(new Stone(x,y_init , x_init , y_init, x_init - size+1 , y_init));
					//System.out.println(" MUR : x :" + x + " / y : " + y_init );
				}
				//System.out.println(" MUR : x :" + x_init + " / y : " + y_init + " / x max : " + (x_init-size+1) + " / y max : " + y_init);
			}
		}
		else
		{
			if(dir == 2)	// bas
			{
				for(int y = y_init; y < (y_init + size); y++)
				{
					mur.add(new Stone(x_init,y , x_init , y_init, x_init, y_init+size-1));
					//System.out.println(" MUR : x :" + x_init + " / y : " + y );
				}
				//System.out.println(" MUR : x :" + x_init + " / y : " + y_init + " / x max : " + x_init + " / y max : " + (y_init+size-1));
			}
			else		// droite
			{
				for(int x = x_init; x < (x_init + size); x++)
				{
					mur.add(new Stone(x,y_init, x_init , y_init, x_init + size - 1, y_init));
					//System.out.println(" MUR : x :" + x_init + " / y : " + y_init + " / x max : " + (x_init+size-1) + " / y max : " + y_init);
				}
				//System.out.println(" MUR : x :" + x_init + " / y : " + y_init + " / x max : " + (x_init+size-1) + " / y max : " + y_init);
			}
		}
		
	}
	
	/**
	 * getters
	 */
	public int getsize() {return this.size;}
	public Case getcase(int i) {return mur.get(i);}
	public int getindice(int i, int dim) {return dim * mur.get(i).getY() + mur.get(i).getX();}

}
