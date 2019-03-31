package main;

import engine.io.Window;
import engine.render.models.Model;
import engine.render.Renderer;
import engine.render.models.TexturedModel;
import engine.render.models.UntexturedModel;
import engine.shaders.BasicShader;

import java.awt.*;

//main class
public class Main {

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //public static final int WIDTH = (int) screenSize.getWidth(), HEIGHT = (int) screenSize.getHeight(), FPS = 60; //fullscreen
    public static final int WIDTH = 800, HEIGHT = 600, FPS = 60;
    public static Window window = new Window(WIDTH, HEIGHT, FPS, "fl1cksh0t OpenGL");
    public static Renderer renderer = new Renderer();
    public static BasicShader shader = new BasicShader();

    public static void main(String args[]){
        window.create();
        window.setBackgroundColor(1.0f, 0.0f, 0.0f);
        shader.create();

        TexturedModel model = new TexturedModel(new float[] {
                -0.5f, 0.5f, 0.0f,  //top left      V0
                0.5f, 0.5f, 0.0f,   //top right     V1
                0.5f, -0.5f, 0.0f   //bottom right  V2
                -0.5f, -0.5f, 0.0f, //bottom left   V3
        }, new float[]{
                0, 0,       //V0
                0, 1,       //V1
                1, 1,       //V2
                1, 0        //V3

        }, new int[] {
                0, 1, 2,    //Triangle 1
                2, 3, 0     //Triangle 2
        }, "beautiful.png");

        while(!window.closed()) {
            if(window.isUpdating()) {
                window.update();
                shader.bind();
                shader.loadScale(0.5f);
                renderer.renderTexturedModel(model);
                window.swapBuffers();

            }
        }

        shader.remove();
        model.remove();
        window.stop();
    }
}