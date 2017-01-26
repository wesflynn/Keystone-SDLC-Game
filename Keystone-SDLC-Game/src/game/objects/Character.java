package game.objects;

import game.platform.Drawable;

public abstract class Character implements Drawable
{
    private int posX, posY;
    
    public int getPosX()
    {
        return this.posX;
    }
    public int getPosY()
    {
        return this.posY;
    }
    
    public void setPosX(int x)
    {
        this.posX = x;
    }
    public void setPosY(int y)
    {
        this.posY = y;
    }
}
