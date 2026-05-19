package flavors;
public class Flavor {
    private String color;
    private Texture texture;
    private String name;
    public Flavor(String color,
                  Texture texture,
                  String name){
        this.color = color;
        this.texture = texture;
        this.name = name;
    }
    public Flavor(){}
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "color " + color + "\n texture " + texture + "\nname" + name;
    }
}
