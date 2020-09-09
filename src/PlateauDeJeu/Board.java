package PlateauDeJeu;

import java.util.*;

import TreasureHunter.Hunter;
import TreasureHunter.Link;
import TreasureHunter.Treasure;
import TreasureHunter.Wall;
import TreasureHunter.Side;
import TreasureHunter.OccupiedByHunter;
import TreasureHunter.Free;

public class Board 
{
	final int LIMIT_WALL = 100;
	
	private List<Case> plateau;			// Plateau de jeu composé de cases
	private List<Hunter> joueurs;		// Liste des joueurs
	private List<Wall> murs;			// liste de murs eux même composés de pierres
	private List<Link> trouesnoirs;		// liste de Liens eux même composés de 2 troues noirs
	private Treasure tresor;			// Trésor à trouver
	private int dim;					// Dimensions du plateau dim x dim (les bordures ne sont pas comprises dans ces dimensions)

	public Board(int dim)
	{
		this.dim = dim;
		plateau = new LinkedList<Case>();
		joueurs = new LinkedList<Hunter>();
		murs = new LinkedList<Wall>();
		trouesnoirs = new LinkedList<Link>();
	}
	
	public Board()
	{
		this(10);		// Si pas de dimension donné, on initialise à 10
	}
	
	/**
	 * Initialisation des cases du plateau. On le parcoure de gauche à 
	 * droite et de haut en bas. On crée des cases vides au milieu et 
	 * des cases correspondant à des bordures
	 */
	public void init_Cases()
	{
		for(int y = 0; y < this.dim+2; y++)
		{
			for(int x = 0; x < this.dim+2; x++)
			{
				if(x == 0 || x == this.dim+1 || y == 0 || y == this.dim+1) // S'il s'agit d'une bordure
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
	
	
	/**
	 * Initialisation d'un mur. Pour ce faire un mur va être composé
	 * d'un x et d'un y initiaux. D'une taille et d'une direction
	 */
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
		
		int dir = (int) (1 + (Math.random() * (5-1)));		// On choisit la direction aléatoirement 
		
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
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		if(max_size != 1)	// on choisit la taille au hasard entre la taille max /1.5 et la taille max
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
		
		murs.add(new Wall(dir, size, rand_x, rand_y)); // Création du nouveau mur avec les variables trouvés ci-dessus
	}
	
	/**
	 * Vérifie que le mur peut commencer à cet emplacement car il ne faut 
	 * pas que deux murs soient collés
	 * 
	 * @param 		x_search	x à vérfier
	 * @param 		y_search	y à vérifier
	 * @return 		boolean 	true si le mur est possible faux sinon
	 */
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
	
	/**
	 * Synchronisation des murs avec le plateau. On place ces derniers
	 * sur le plateau en remplacant les cases "Free" pas des "Stone"
	 */
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
	
	/**
	 * On initialise le trésor à une place qui est possible car il faut 
	 * qu'il soit accessible
	 */
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
	
	/**
	 * Initialisation d'un chasseur, à des coordonnées valides et aléatoires
	 */
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
	
	/**
	 * sychronisation des chasseurs sur le plateau
	 */
	public void hunter_sync()
	{
		for(int i=0; i < joueurs.size(); i++)
		{
			plateau.remove(joueurs.get(i).getindice(this.dim+2) );
			plateau.add(joueurs.get(i).getindice(this.dim+2), new OccupiedByHunter(joueurs.get(i).getX(), joueurs.get(i).getY(), joueurs.get(i).to_String()));
		}
	}
	
	public void init_bh()
	{
		int rand_x1, rand_x2, rand_y1, rand_y2;
		do
		{
			rand_x1 = (int) (2 + (Math.random() * (this.dim-2)));
			rand_y1 = (int) (2 + (Math.random() * (this.dim-2)));
		}while(!is_okay_wall(rand_x1, rand_y1));
		do
		{
			rand_x2 = (int) (2 + (Math.random() * (this.dim-2)));
			rand_y2 = (int) (2 + (Math.random() * (this.dim-2)));
		}while(!is_okay_wall(rand_x2, rand_y2) || (rand_x1 == rand_x2 && rand_y1 == rand_y2));
		
		this.trouesnoirs.add(new Link(rand_x1, rand_y1, rand_x2, rand_y2));
	}

	public void sync_bh()
	{
		for(int i=0; i < trouesnoirs.size(); i++)
		{
			plateau.remove(this.trouesnoirs.get(i).getindice1(this.dim+2));
			plateau.add(this.trouesnoirs.get(i).getindice1(this.dim+2), this.trouesnoirs.get(i).getbh1());
			plateau.remove(this.trouesnoirs.get(i).getindice2(this.dim+2));
			plateau.add(this.trouesnoirs.get(i).getindice2(this.dim+2), this.trouesnoirs.get(i).getbh2());
		}
	}
	
	/**
	 * On joue un tour. Si ce dernier se passe bien, la fonction
	 * retourne 0 et si un joueur gagne 1
	 * 
	 * @return 		int 	0 : tour suivant // 1 : gagnant
	 */
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
	
	public Hunter gethunter(int indice)
	{
		return this.joueurs.get(indice);
	}
	
	public Treasure getTreasure()
	{
		return this.tresor;
	}
	
	public int getdim()
	{
		return this.dim;
	}
	
	public Case getCase(int indice)
	{
		return this.plateau.get(indice);
	}
	
	/**
	 * Fonctino toString qui affiche le plateau
	 * 
	 * @return 		String 		Plateau de jeu avec tous ces composants
	 */
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


