package main.java.mazerunner.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import static main.java.mazerunner.model.Common.*;
/**
 * A class for game board
 */
public class GamePage extends BorderPane {

    public VBox statusPane = new VBox();
    public Label remainTimeLabel = new Label();
    private MazeView mazeView;
    public GamePage(){
        this.statusPane.getChildren().add(remainTimeLabel);
        this.mazeView = new MazeView();
        init();
        BackgroundImage myBI= new BackgroundImage(new Image(baseUrl + "gamepage.png",pageWidth, pageHeight,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
    }

    public void init(){
        this.setPrefSize(pageWidth,pageHeight);
        this.setCenter(mazeView);
    }
}
