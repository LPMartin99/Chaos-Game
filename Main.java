import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends Application {

    final static int HEIGHT = 1400;
    final static int WIDTH = 1617;
    private int dotCount = 4;
    boolean isValid = false;
    private Canvas dotCanvas;
    static ArrayList<Dot> dotList = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(args);

    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sierpinski Triangle - Chaos Game");
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Dot dot1 = new Dot((int) Math.ceil(WIDTH/2), 0);
        Dot dot2 = new Dot(0, HEIGHT);
        Dot dot3 = new Dot(WIDTH, HEIGHT);
        Dot dot4 = new Dot(0, 0);

        dotList.add(dot1);
        dotList.add(dot2);
        dotList.add(dot3);

        while (!isValid) {
            int randomY = ThreadLocalRandom.current().nextInt(0, HEIGHT + 1);
            int randomX = ThreadLocalRandom.current().nextInt(0, WIDTH + 1);

            dot4.setxCoord(randomX);
            dot4.setyCoord(randomY);

            checkValid(dot4);
        }

        dotList.add(dot4);

        dotCanvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(dotCanvas);

        drawDotLayer();
        createTimeLine();

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    public boolean checkValid(Dot dot) {
        if ((dot.getyCoord()) <= (-30/13) * dot.getxCoord() + 1200) {

            if ((dot.getyCoord()) <= (30/13) * dot.getxCoord() + 1200) {
                isValid = true;
            }
        }
        return isValid;
    }

    public void drawDotLayer() {
        GraphicsContext gc = dotCanvas.getGraphicsContext2D();

        for (Dot n : dotList){
            int xCoord = n.getxCoord();
            int yCoord = n.getyCoord();
            gc.drawImage(n.getDotImage(), xCoord, yCoord);
        }
    }

    private void createTimeLine(){
        final int TICK_RATE = 1; //Ticks every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(TICK_RATE),
                event -> tickClock()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void tickClock() {
        int p1Index = ThreadLocalRandom.current().nextInt(0, 3);
        Dot p1 = dotList.get(p1Index);
        Dot p2 = dotList.get(3);

        int newXCoord = (p1.getxCoord() + p2.getxCoord())/2;
        int newYCoord = (p1.getyCoord() + p2.getyCoord())/2;

        p2.setxCoord(newXCoord);
        p2.setyCoord(newYCoord);

        if(checkValid(p2)) {
            drawDotLayer();
            dotCount +=1;
            if ((dotCount%10000) == 0) {
                System.out.println("Number of Dots: " + dotCount);
            }
        }
    }
}
