package main.java.mazerunner.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static main.java.mazerunner.model.Common.pageHeight;
import static main.java.mazerunner.model.Common.pageWidth;

public class RunGame extends Application {

    private GamePage gamePage;
    private Scene gameScene;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        gamePage = new GamePage();
        Pane root = new Pane();
        root.getChildren().add(gamePage);
        gameScene = new Scene(root,pageWidth,pageHeight);
        gamePage.init();
        stage.setScene(gameScene);
        stage.setResizable(false);
        stage.setTitle("Maze Runner");
        stage.setWidth(pageWidth);
        stage.setHeight(pageHeight);
        stage.show();
    }


}
