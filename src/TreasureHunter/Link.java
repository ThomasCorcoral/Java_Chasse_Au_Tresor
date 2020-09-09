package TreasureHunter;

import java.awt.*;

public class Link {
	
	private BlackHole bh1, bh2;

	public Link(int x1,int y1,int x2,int y2) 
	{
		int fonce = 80;		// Plus il est petit plus la couleur sera foncée. De base fixé à 80
		int r = (int) (Math.random() * fonce);
		int g = (int) (Math.random() * fonce);
		int b = (int) (Math.random() * fonce);
		Color c = new Color(r,g,b);
		this.bh1 = new BlackHole(x1,y1,x2,y2, c);
		this.bh2 = new BlackHole(x2,y2,x1,y1, c);
	}
	
	public int getindice1(int dim)
	{
		return dim * bh1.getY() + bh1.getX();
	}
	
	public int getindice2(int dim)
	{
		return dim * bh2.getY() + bh2.getX();
	}
	
	public BlackHole getbh1()
	{
		return bh1;
	}
	public BlackHole getbh2()
	{
		return bh2;
	}

}
