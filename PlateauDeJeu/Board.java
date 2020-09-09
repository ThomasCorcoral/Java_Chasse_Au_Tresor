package PlateauDeJeu;

import java.util.*;

import TreasureHunter.Hunter;
import TreasureHunter.Treasure;
import TreasureHunter.Wall;
import TreasureHunter.Side;
import TreasureHunter.OccupiedByHunter;
import TreasureHunter.Free;

public class Board 
{
	final int LIMIT_WALL = 100;
	
	private List<Case> plateau;
	private List<Hunter> joueurs;
	private List<Wall> murs;
	private Treasure tresor;
	private int dim;

	public Board(int dim) 
	{
		this.dim = dim;
		plateau = new LinkedList<Case>();
		joueurs = new LinkedList<Hunter>();
		murs = new LinkedList<Wall>();
	}
	
	public Board()
	{
		this(10);
	}
	
	public void init_Cases()
	{
		for(int y = 0; y < this.dim+2; y++)
		{
			for(int x = 0; x < this.dim+2; x++)
			{
				if(x == 0 || x == this.dim+1 || y == 0 || y == this.dim+1)
				{
					plateau.add(new Side(x,y));
				}
				else
				{
					plateau.add(new Free(x,y));
				}
			}
		}
	}
	
	public void init_Wall()			//			  4
	{								// dir :   3  #  1
									//			  2
		int limit = 0;
		int rand_x;
		int rand_y;
		
		do	//Recherche du point de départ initial du mur
		{
			rand_x = (int) (2 + (Math.random() * (this.dim-2)));
			rand_y = (int) (2 + (Math.random() * (this.dim-2)));
			limit++;
		}while(!is_okay_wall(rand_x, rand_y) && limit < LIMIT_WALL);
		
		//System.out.println(rand_x);
		//System.out.println(rand_y);
		
		if(limit == LIMIT_WALL) // trop de mur sur le plateau
		{
			return; 
		}
		
		int dir = (int) (1 + (Math.random() * (5-1)));		// On choisit la direction
		
		//System.out.println(dir);
		
		int max_size = 0;
		
		int max_x = rand_x;
		int max_y = rand_y;
		
		while(is_okay_wall(max_x, max_y))
		{
			switch(dir)
			{
				case 1 : max_x++;
						break;
				case 2 : max_y++;
						break;
				case 3 : max_x--;
						break;
				case 4 : max_y--;
						break;
			}
			max_size++;
		}
		
		int size;
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		if(max_size != 1)
		{
			size = (int) (max_size/1.5 + (Math.random() * (max_size-max_size/1.5)));
		}
		else
		{
			size = max_size;
		}
		//System.out.println(max_x);
		//System.out.println(max_y);
		
		//System.out.println("direction : " + dir + " / x : " + rand_x + " / y : " + rand_y + " / size max : " + max_size + " / size : " + size);
		
		murs.add(new Wall(dir, size, rand_x, rand_y));
		
	}
	
	public boolean is_okay_wall(int x_search, int y_search)
	{
		for(int y = y_search-1; y < y_search+2; y++)
		{
			for(int x = x_search-1; x < x_search+2; x++)
			{
				int indice = (this.dim+2)*y + x;
				if(this.plateau.get(indice).to_String() != ".")
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public void sync_walls()
	{
		for(int i=0; i < murs.size(); i++)
		{
			for(int y = 0; y < murs.get(i).getsize(); y++)
			{
				plateau.remove(murs.get(i).getindice(y, this.dim+2) );
				plateau.add(murs.get(i).getindice(y, this.dim+2), murs.get(i).getcase(y));
			}
		}
	}
	
	public void init_Treasure()
	{
		int rand_x;
		int rand_y;
		
		do{
			rand_x = (int) (1 + (Math.random() * (this.dim)));
			rand_y = (int) (1 + (Math.random() * (this.dim)));
		}while(plateau.get((this.dim+2)*rand_y + rand_x).to_String() != ".");
		
		this.tresor = new Treasure(rand_x, rand_y);
		//System.out.println(" TRESOR : x :" + rand_x + " / y : " + rand_y );
	}
	
	public void init_Hunters()
	{
		int rand_x;
		int rand_y;
		
		do{
			rand_x = (int) (1 + (Math.random() * (this.dim)));
			rand_y = (int) (1 + (Math.random() * (this.dim)));
		}while(plateau.get((this.dim+2)*rand_y + rand_x).to_String() != "." || this.tresor.is_treasure(rand_x, rand_y));
		
		String str = "";
		
		if(joueurs.size() == 0)
		{
			str = "A";
			joueurs.add(new Hunter(rand_x, rand_y, str, this.tresor.getX(), this.tresor.getY()));
		}
		else
		{
			char nom = (char) ((int) joueurs.get(joueurs.size()-1).to_String().charAt(0) + 1);
			str = "" + nom;
			joueurs.add(new Hunter(rand_x, rand_y, str, this.tresor.getX(), this.tresor.getY()));
		}
		//System.out.println(" HUNTER : x :" + rand_x + " / y : " + rand_y + " / nom : " + str + " / Position : " + ((this.dim+2)*rand_y + rand_x) + " / case déja présent : " + plateau.get((this.dim+2)*rand_y + rand_x).to_String());
		hunter_sync(); 
	}
	
	public void hunter_sync()
	{
		for(int i=0; i < joueurs.size(); i++)
		{
			plateau.remove(joueurs.get(i).getindice(this.dim+2) );
			plateau.add(joueurs.get(i).getindice(this.dim+2), new OccupiedByHunter(joueurs.get(i).getX(), joueurs.get(i).getY(), joueurs.get(i).to_String()));
		}
	}
	
	public int play_turn()
	{
		int i;
		for(i = 0; i < joueurs.size(); i++)
		{
			int old_i = joueurs.get(i).getindice(this.dim+2);
			plateau.remove(old_i);
			plateau.add(old_i, new Free( joueurs.get(i).getX() , joueurs.get(i).getY() ));
			
			System.out.println("Personnage " + joueurs.get(i).to_String());
			System.out.println("    Hunter [" + joueurs.get(i).getX() + " " + joueurs.get(i).getY() + "] dir " + joueurs.get(i).get_dir());
			
			int next = joueurs.get(i).move_hunter(dim);
			plateau.get(next).process(joueurs.get(i));
			
			if(joueurs.get(i).get_dir() == -1)
			{
				System.out.println("\nWINNER : HUNTER " + joueurs.get(i).to_String() );
				return 1;
			}
			
			System.out.println("    Best dir " + joueurs.get(i).get_dir());
			System.out.println("    -> Hunter [" + joueurs.get(i).getX() + " " + joueurs.get(i).getY() + "] dir " + joueurs.get(i).get_dir() + "\n");
			
			int new_i = joueurs.get(i).getindice(this.dim+2);
			plateau.remove(new_i);
			plateau.add(new_i, new OccupiedByHunter(joueurs.get(i).getX(), joueurs.get(i).getY(), joueurs.get(i).to_String()));
		}
		
		return 0;
		
	}
	
	public String toString()
	{
		String str="";
		for(int y = 0; y < dim+2; y++)
		{
			for(int x = 0; x < dim+2; x++)
			{
				int indice = (this.dim+2)*y + x;
				if(this.tresor.is_treasure(x,y))
				{
					str += this.tresor.to_String();
				}
				else
				{
					str += this.plateau.get(indice).to_String();
				}
			}
			str += "\n";
		}
		return str;
	}
		
}


