package TreasureHunter;

public class Hunter extends Entity
{
	
	private int old_dir;
	private int dir;
	private int treasure_x, treasure_y;

	public Hunter(int x, int y, String sym, int t_x, int t_y) 
	{
		super(x,y,sym);
		this.old_dir = 0;
		this.dir = init_dir();
		this.treasure_x = t_x;
		this.treasure_y = t_y;
	}
	
	private int init_dir()
	{
		return (int) (1 + (Math.random() * (8-1)));
	}
	
	/**
	 * getters
	 */
	public int get_treasure_x() {return this.treasure_x;}
	public int get_treasure_y() {return this.treasure_y;}
	public int get_dir(){return dir;}
	public int get_old_dir(){return this.old_dir;}
	public int getindice(int dim) {return dim * this.getY() + this.getX();}
	
	/**
	 * setter
	 */
	public void set_dir(int new_dir)
	{
		this.old_dir = this.dir;
		this.dir = new_dir;
	}
	
	/**
	 * Renvoie l'indice de la future case du chasseur en fonction de sa direction
	 * 
	 * @param 		int 	dim		taille du plateau
	 * @return		int 	future indice
	 */
	public int move_hunter(int dim)
	{
		int res = 0;
		
		switch(this.dir)
		{
			case 1 : res = (dim+2) * this.getY() + (this.getX()+1);
				     System.out.println("    Case cible : " + (this.getX()+1) + " " + this.getY() );
				break;
			case 2 : res = (dim+2) * (this.getY()-1) + (this.getX()+1);
					 System.out.println("    Case cible : " + (this.getX()+1) + " " + (this.getY()-1) );
				break;
			case 3 : res = (dim+2) * (this.getY()-1) + this.getX();
		     		 System.out.println("    Case cible : " + this.getX() + " " + (this.getY()-1) );
				break;
			case 4 : res = (dim+2) * (this.getY()-1) + (this.getX()-1);
					 System.out.println("    Case cible : " + (this.getX()-1) + " " + (this.getY()-1) );
				break;
			case 5 : res = (dim+2) * this.getY() + (this.getX()-1);
					 System.out.println("    Case cible : " + (this.getX()-1) + " " + this.getY() );
				break;
			case 6 : res = (dim+2) * (this.getY()+1) + (this.getX()-1);
	     			 System.out.println("    Case cible : " + (this.getX()-1) + " " + (this.getY()+1) );
				break;
			case 7 : res = (dim+2) * (this.getY()+1) + this.getX();
		     		 System.out.println("    Case cible : " + this.getX() + " " + (this.getY()+1) );
				break;
			case 8 : res = (dim+2) * (this.getY()+1) + (this.getX()+1);
		     		 System.out.println("    Case cible : " + (this.getX()+1) + " " + (this.getY()+1) );
				break;
		}
		return res;
	}
	
	public int case_cible(int dim, String valeur)
	{
		int caseCible = 0;
		
		if(valeur == "x") {
		
			switch(this.dir)
			{
				case 1 : caseCible = this.getX()+1;
					break;
				case 2 : caseCible = this.getX()+1; 
					break;
				case 3 : caseCible = this.getX();
					break;
				case 4 : caseCible = this.getX()-1;
					break;
				case 5 : caseCible = this.getX()-1;
					break;
				case 6 : caseCible = this.getX()-1;
					break;
				case 7 : caseCible = this.getX();
					break;
				case 8 : caseCible = this.getX()+1;
					break;
			}
		}
		else {
			
			switch(this.dir)
			{
				case 1 : caseCible = this.getY();
					break;
				case 2 : caseCible = this.getY()-1; 
					break;
				case 3 : caseCible = this.getY()-1;
					break;
				case 4 : caseCible = this.getY()-1;
					break;
				case 5 : caseCible = this.getY();
					break;
				case 6 : caseCible = this.getY()+1;
					break;
				case 7 : caseCible = this.getY()+1;
					break;
				case 8 : caseCible = this.getY()+1;
					break;
			}
		}
		return caseCible;
	}
}
