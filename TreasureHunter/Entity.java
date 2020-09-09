package TreasureHunter;

public class Entity 
{
	
	private int x;
	private int y;
	private String symbole;

	public Entity(int x, int y, String sym) 
	{
		this.x = x;
		this.y = y;
		this.symbole = sym;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public String to_String()
	{
		return this.symbole;
	}
	
}
