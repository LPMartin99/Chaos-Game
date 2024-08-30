import javafx.scene.image.Image;

public class Dot {
    private int xCoord;
    private int yCoord;
    private Image dotImage = new Image("dot.png");

    public Dot(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public Image getDotImage() {
        return dotImage;
    }
}
