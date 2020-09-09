import PlateauDeJeu.*;


public class main 
{
	
	public static void main(String[] args) 
	{
		final int LIMIT_WALL = 3;		// Nombre de murs maximum
		final int LIMIT_HUNTER = 2;		// Nombre de chasseurs
		final int LIMIT_BLACKHOLE = 1;
		
		Board plateau = new Board();	// Création du plateau de jeu
		plateau.init_Cases();			// Initialisation du plateau
		
		int i = 0;
		while(i < LIMIT_BLACKHOLE)
		{
			plateau.init_bh();
			plateau.sync_bh();
			i++;
		}
		
		i = 0;
		while(i < LIMIT_WALL)
		{
			plateau.init_Wall();		// on initialise un nouveau mur
			plateau.sync_walls();		// Synchronisation de ce mur, on l'intègre dans le plateau
			i++;
		}
		
		i = 0;
		plateau.init_Treasure();		// On positionne le trésor
		
		while(i < LIMIT_HUNTER)
		{
			plateau.init_Hunters();		// Création d'un nouveau chasseur
			plateau.hunter_sync();		// Synchronisation de ce chasseur avec le plateau
			i++;
		}
		
		/*
		 * DEBUG
		 * Test aucun bug / toujours un gagnant
		 
		int cmpt = 0;
		while(cmpt < LIMIT)
		{
			cmpt = 0;
			while(plateau.play_turn() == 0 && cmpt < LIMIT)
			{
				cmpt++;
				System.out.println(plateau.toString());
			}
		}*/
			
		while(plateau.play_turn() == 0)	//	tant que personne n'a gagné, on joue un nouveau tour
		{
			System.out.println(plateau.toString());		// affichage du plateau
		}
	}

}
