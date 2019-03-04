package engine.io;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import utils.Vector3f;

import java.nio.DoubleBuffer;

public class Window {

    private int width, height;
    private String title;
    private double fps_cap, time, processedTime = 0;
    private long window;
    private Vector3f backgroundColor;
    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    public Window(int width, int height, int fps, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        fps_cap = fps;
        backgroundColor = new Vector3f(0.0f, 0.0f, 0.0f);
    }

    public void create() {
        if(!GLFW.glfwInit()) {
            System.err.println("Error: Could not initialise glfw");
            System.exit(-1);
        }

        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if(window == 0) {
            System.err.println("Error: Window could not be created");
            System.exit(-1);
        }

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.width() - width)/2, (videoMode.height() - height)/2);

        GLFW.glfwShowWindow(window);

        time = getTime();

    }

    public boolean closed() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void update() {
        for(int i = 0; i < GLFW.GLFW_KEY_LAST; i++) {
            keys[i] = isKeyDown(i);
        }
        for(int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) {
            mouseButtons[i] = isMouseDown(i);
        }
        GL11.glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();
    }

    public void stop() {
        GLFW.glfwTerminate();
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public double getTime() {
        return (double) System.nanoTime() / (double) 1000000000;
    }

    public boolean isKeyDown(int keyCode) {
        return GLFW.glfwGetKey(window, keyCode) == 1;
    }

    public boolean isMouseDown(int mouseButton) {
        return GLFW.glfwGetMouseButton(window, mouseButton) == 1;
    }

    public boolean isKeyPressed(int keyCode) {
        return isKeyDown(keyCode) && !keys[keyCode];    //compares key that is pressed to key last pressed
    }

    public boolean isKeyReleased(int keyCode) {
        return !isKeyDown(keyCode) && keys[keyCode];    //same as above
    }

    public boolean isMousePressed(int mouseButton) {
        return isMouseDown(mouseButton) && !mouseButtons[mouseButton];    //compares mouse button that is pressed to key last pressed
    }

    public boolean isMouseReleased(int mouseButton) {
        return !isMouseDown(mouseButton) && mouseButtons[mouseButton];    //same as above
    }

    public double getMouseX() {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, buffer, null);
        return buffer.get(0);
    }

    public double getMouseY() {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, null, buffer);
        return buffer.get(0);
    }

    public boolean isUpdating(){
        double nextTime = getTime();
        double passedTime = nextTime - time;
        processedTime += passedTime;
        time = nextTime;

        while(processedTime > 1.0/fps_cap) {
            processedTime -= 1.0/fps_cap;
            return true;
        }

        return false;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getFPS() {
        return fps_cap;
    }

    public void setFPS(double fps_cap) {
        this.fps_cap = fps_cap;
    }

    public long getWindow() {
        return window;
    }

    public void setWindow(long window) {
        this.window = window;
    }

    public void setBackgroundColor(float r, float g, float b) {
        backgroundColor = new Vector3f(r, g, b);
    }
}
