package engine.shaders;

public class BasicShader extends Shader {

    private static final String VERTEX_FILE = "src/engine/shaders/basicVertexShader.vs",
                                FRAGMENT_FILE = "src/engine/shaders/basicFragmentShader.fs";

    public BasicShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);

    }

    public void bindAllAttributes() {
        super.bindAttribute(0, "vertices");
    }
}
