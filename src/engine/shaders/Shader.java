package engine.shaders;

public abstract class Shader {
    private int vertexShaderID, fragmentShaderID, programID;
    private String fragmentFile, vertexFile;

    public Shader(String vertexFile, String fragmentFile) {
        this.vertexFile = vertexFile;
        this.fragmentFile = fragmentFile;
    }

    public void create() {
        
    }
}
