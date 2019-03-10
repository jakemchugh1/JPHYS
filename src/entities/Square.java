package entities;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.LoadTexture;

public class Square implements Entity{

    private float x;
    private float y;

    private int height;
    private int width;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;

    private boolean remove;

    public Square(float startX, float startY){

        this.x = startX;
        this.y = startY;
        width = 64;
        height = 64;
        this.texture = LoadTexture("square");

        //initial position vector
        pos = new Vector2f();
        pos.x = x + width/2;
        pos.y = y + height/2;

        //initial velocity;
        vel = new Vector2f();
        vel.x = 0f;
        vel.y = 0f;

        remove = false;

    }

    public void Draw() {
        DrawQuadTex(texture, x, y, width, height);
    }

    public void setPos(){
        pos.x = x + width/2;
        pos.y = y + height/2;
    }

    public Vector2f getPos(){
        return pos;
    }

    public void ApplyForce(Vector2f force){

        //updating velocity
        vel.x = vel.x + force.x;
        vel.y = vel.y + force.y;

        //temp bounce test
        if(y > 896 && vel.y > 0) vel.y = -vel.y+0.2f;


        //applying current velocity to position

        x = x + vel.x;
        y = y + vel.y;

        if(vel.y > -0.1 && vel.y < 0.1 && pos.y > 896) remove = true;

        setPos();
    }

    public boolean isRemove() {
        return remove;
    }

}
