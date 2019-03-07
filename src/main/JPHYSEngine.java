package main;

import entities.Ball;
import entities.Entity;
import helpers.Artist.*;
import org.lwjgl.opengl.Display;
import physics.Gravity;

import static helpers.Artist.BeginSession;
import static helpers.Artist.getFrameTimeSeconds;
import static helpers.Artist.updateDisplay;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;


public class JPHYSEngine {

    public JPHYSEngine(){
        BeginSession();

        Ball ball = new Ball(640, 100);
        Gravity gravity = new Gravity(1f);

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            ball.Draw();
            ball.ApplyForce(gravity.Apply(getFrameTimeSeconds()));
            updateDisplay();

        }


    }

    public static void main(String[] Args){
        new JPHYSEngine();
    }
}
