package main;

import entities.*;
import helpers.Artist.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import physics.Gravity;

import java.util.HashSet;

import static helpers.Artist.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;


public class JPHYSEngine {

    public JPHYSEngine(){
        BeginSession();


        HashSet<Entity> entitySet = new HashSet<>();

        Gravity gravity = new Gravity(1f);

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            HashSet<Entity> remove = new HashSet<>();
            for(Entity ball : entitySet){
                ball.Draw();
                ball.ApplyForce(gravity.Apply(getFrameTimeSeconds()));
                if(ball.isRemove()) remove.add(ball);
            }
            for(Entity ball : remove){
                entitySet.remove(ball);
            }
            if(Mouse.isButtonDown(0)) entitySet.add(new Ball(Mouse.getX(), getHEIGHT() - Mouse.getY()));
            if(Mouse.isButtonDown(1)) entitySet.add(new Triangle(Mouse.getX(), getHEIGHT() - Mouse.getY()));
            if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) entitySet.add(new Square(Mouse.getX(), getHEIGHT() - Mouse.getY()));
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) entitySet.add(new Octagon(Mouse.getX(), getHEIGHT() - Mouse.getY()));

            updateDisplay();

        }


    }

    public static void main(String[] Args){
        new JPHYSEngine();
    }
}
