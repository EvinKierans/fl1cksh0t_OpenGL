package engine.shaders;

public class BasicShader extends Shader {

    private static final String VERTEX_FILE = "src/engine/shaders/basicVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/engine/shaders/basicFragmentShader.glsl";

    private int scaleLocation;

    public BasicShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);

    }

    public void bindAllAttributes() {
        super.bindAttribute(0, "positions");
        super.bindAttribute(1, "textCoords");
    }

    @Override
    protected void getAllUniforms() {
        scaleLocation = super.getUniform("scale");
    }

    public void loadScale(float value) {
        super.loadFloatUniform(scaleLocation, value);
    }

}
