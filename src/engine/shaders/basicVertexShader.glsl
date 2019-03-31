#version 150

in vec3 position;
in vec2 textCoords;

out vec2 passTextCoords;

uniform float scale;

void main() {
    gl_Position = vec4(position * scale, 1.0);
    passTextCoords = textCoords;
}