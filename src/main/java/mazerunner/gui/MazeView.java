package main.java.mazerunner.gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import main.java.mazerunner.engine.GameEngine;
import main.java.mazerunner.engine.GameGrid;
import main.java.mazerunner.model.Common;
import main.java.mazerunner.model.Position;

import java.util.ArrayList;

import static main.java.mazerunner.model.Common.*;
/**
 * The class for maze grid
 */
public class MazeView extends BorderPane {

    private GridPane mazePane ;
    private ArrayList<Pane> cells;
    private VBox statusPane;

    //remaining life status pane
    private HBox lifePane;
    private Label remainLifeLabel; //show remain life
    private Image lifeIcon;
    private ImageView lifeIconView;


    //key pane
    private HBox goldPane;
    private Label goldNumberLabel;
    private Image goldIconImg;
    private ImageView goldIconImgView;

    //btn
    private Button helpBtn;
    private Button restartBtn ;
    private Button exitBtn ;
    private VBox buttonPane;


    private  HBox toolbarPane;
    private HBox resultPane;

    private  ImageView leftArrowImage;
    private  ImageView rightArrowImage;
    private  ImageView upArrowImage;
    private  ImageView downArrowImage;
    //game engine
    private GameEngine gameEngine;
    /**
     * constructor
     */
    public MazeView( ) {
        //init members
        initMembers();
        //init maze grid
        initMazeGrid();
    }

    private void initMazeGrid() {

        this.mazePane.getChildren().clear();

        GameGrid gameGrid = this.gameEngine.getGameGrid();

        for (int rowNum = 0; rowNum < gameGrid.getGrids().length; rowNum++) {
            char[] row = gameGrid.getGrids()[rowNum];
            for(int columnNum = 0; columnNum < row.length; columnNum++){
                Pane cell = convertString2Image(row[columnNum]);
                setMazeGridCell(rowNum, columnNum, cell);
            }
        }
        remainLifeLabel.setText(Integer.toString(gameEngine.getPlayer().getHealthLevel()));
        goldNumberLabel.setText(Integer.toString(gameEngine.getGolds().size()));
    }

    private void setMazeGridCell(int rowNum, int columnNum, Pane cell) {
        this.mazePane.add(cell, columnNum, rowNum);
        this.cells.add(cell);
    }

    private Pane convertString2Image(char c) {
        StackPane pane = new StackPane();
        Image image = null;
        if(c == '+' || c == '-' ){
            image = new Image(baseUrl + "wall.png", cellSize, cellSize, false, false);
        }else if(c == 'G'){
            image = new Image(baseUrl + "gold.png", cellSize, cellSize, false, false);
        }else if(c == 'P'){
            image = new Image(baseUrl + "player.png", cellSize, cellSize, false, false);
        }else if(c == 'E'){
            image = new Image(baseUrl + "door.png", cellSize, cellSize, false, false);
        }else if(c == ' '){
            image = new Image(baseUrl + "free.png", cellSize, cellSize, false, false);
        }else{
            image = new Image(baseUrl + "wall.png", cellSize, cellSize, false, false);
        }
        ImageView paneImage = new ImageView(image);
        pane.getChildren().add(paneImage);
        return pane;
    }

    private void initMembers(){
        cells = new ArrayList<>();
        //init game engine.
        gameEngine = new GameEngine();
        gameEngine.init();

        //init maze view
        mazePane = new GridPane();
        statusPane = new VBox();
        buttonPane = new VBox();
        toolbarPane = new HBox();
        goldPane = new HBox();
        resultPane = new HBox();

        this.setFocusTraversable(true);

        this.setCenter(mazePane);
        this.setRight(statusPane);
        statusPane.setPadding(new Insets(20,5,30,5));
        BackgroundImage myBI= new BackgroundImage(new Image(baseUrl + "status.png",statusBarWidth, pageHeight,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        statusPane.setBackground(new Background(myBI));
        //set toolbar
        toolbarPane.setPrefSize(statusBarWidth,gameResultSize);
        initToolbar(toolbarPane);
        //set result image
        Image resultImage = null;
        resultPane.setPrefSize(statusBarWidth, gameResultSize);
        setGameResultPane(resultPane, resultImage);
        //remaining life status pane
        lifePane = new HBox();
        remainLifeLabel = new Label(Integer.toString(250)); //show remain life
        initLifePane(lifePane);
        //gold pane
        goldNumberLabel = new Label("3");
        initGoldPane(goldPane);
        //status pane
        statusPane.getChildren().addAll(goldPane, lifePane, buttonPane, toolbarPane, resultPane);
        statusPane.setSpacing(3);
        //btnPane
        initButtonPane(buttonPane);
    }

    private void initToolbar(Pane toolbarPane) {

        StackPane leftArrowPane = new StackPane();
        Image leftArrow = new Image(baseUrl + "arrowleft.png", arrowSize, arrowSize, false, false);
        leftArrowImage = new ImageView(leftArrow);
        leftArrowImage.setFitWidth(40);
        leftArrowImage.setFitHeight(40);
        leftArrowPane.getChildren().addAll(leftArrowImage);

        StackPane rightArrowPane = new StackPane();
        Image axe1 = new Image(baseUrl + "arrowright.png", arrowSize, arrowSize, false, false);
        rightArrowImage = new ImageView(axe1);
        rightArrowImage.setFitWidth(40);
        rightArrowImage.setFitHeight(40);
        rightArrowPane.getChildren().addAll(rightArrowImage);

        StackPane upArrowPane = new StackPane();
        Rectangle rec2 = new Rectangle(arrowSize, arrowSize);
        rec2.setFill(Color.TRANSPARENT);
        Image axe2 = new Image(baseUrl + "arrowup.png", arrowSize, arrowSize, false, false);
        upArrowImage = new ImageView(axe2);
        upArrowImage.setFitWidth(40);
        upArrowImage.setFitHeight(40);
        upArrowPane.getChildren().addAll(upArrowImage, rec2);

        Rectangle rec4 = new Rectangle(arrowSize,arrowSize);
        rec4.setFill(Color.TRANSPARENT);

        StackPane downArrowPane = new StackPane();
        Image pen = new Image(baseUrl + "arrowdown.png", arrowSize, arrowSize, false, false);
        downArrowImage = new ImageView(pen);
        downArrowImage.setFitWidth(40);
        downArrowImage.setFitHeight(40);
        downArrowPane.getChildren().addAll(rec4, downArrowImage);

        toolbarPane.setPadding(new Insets(10,10,10,10));

        VBox btnbox = new VBox();
        btnbox.getChildren().addAll(upArrowPane,  downArrowPane);
        toolbarPane.getChildren().addAll(leftArrowPane, btnbox, rightArrowPane);
        //add mouse click event listener
        addMoveEventListener(leftArrowPane, "a");
        addMoveEventListener(rightArrowPane, "d");
        addMoveEventListener(upArrowPane, "w");
        addMoveEventListener(downArrowPane, "s");
        addArrowKeyEventListener();
    }

    private void initGoldPane(HBox goldPane){
        String url = baseUrl + "gold.png";
        goldNumberLabel.setFont(new Font("Arial", 25));
        goldNumberLabel.setLayoutX(40);
        goldNumberLabel.setLayoutY(30);
        goldNumberLabel.setTextFill(Color.WHITE);
        goldIconImg = new Image(url);
        goldIconImgView = new ImageView(goldIconImg);
        //gold pane animation
        goldIconImgView.setFitHeight(cellSize + 10);
        goldIconImgView.setFitWidth(cellSize + 10);
        goldIconImgView.setX(10);
        goldIconImgView.setY(10);
        Rectangle recGold = new Rectangle(arrowSize,arrowSize);
        recGold.setFill(Color.TRANSPARENT);
        goldPane.getChildren().addAll( goldIconImgView, recGold, goldNumberLabel);
        goldPane.setAlignment(Pos.CENTER);
    }

    private void initLifePane(HBox lifePane){
        String url = Common.baseUrl + "health.png";
        lifeIcon = new Image(url);
        lifeIconView = new ImageView(lifeIcon);
        //life Pane text
        remainLifeLabel.setFont(new Font("Arial", 25));
        remainLifeLabel.setLayoutX(40);
        remainLifeLabel.setLayoutY(30);
        remainLifeLabel.setTextFill(Color.WHITE);
        //life Pane animation
        lifeIconView.setFitHeight(cellSize + 10);
        lifeIconView.setFitWidth(cellSize + 10);
        lifeIconView.setX(10);
        lifeIconView.setY(10);
        Rectangle rec1 = new Rectangle(arrowSize,arrowSize);
        rec1.setFill(Color.TRANSPARENT);
        lifePane.getChildren().addAll(lifeIconView, rec1, remainLifeLabel);
        lifePane.setAlignment(Pos.CENTER);
    }

    private void initButtonPane(VBox buttonPane){
        //btn
        helpBtn = new Button("HELP");
        restartBtn = new Button("RESTART");
        exitBtn = new Button("EXIT");
        buttonPane.getChildren().addAll(helpBtn,restartBtn,exitBtn);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(10);
        buttonPane.setPadding(new Insets(40,0,0,0));
        //help button
        helpBtn.setPrefSize(100,35);
        helpBtn.setStyle("-fx-background-color:#FFE08A");
        helpBtn.setTextFill(Color.web("#0A1D10"));
        //restart button
        restartBtn.setPrefSize(100,35);
        restartBtn.setStyle("-fx-background-color:#FFE08A");
        restartBtn.setTextFill(Color.web("#0A1D10"));
        //exit button
        exitBtn.setPrefSize(100,35);
        exitBtn.setStyle("-fx-background-color:#F5EAE4");
        exitBtn.setTextFill(Color.web("#0A1D10"));
        //add event handler
        restartBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            restart();
        });
        exitBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            gameExit();
        });
        helpBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showHelp();
        });
    }

    private void setGameResultPane(HBox resultPane, Image resultImage){
        resultPane.getChildren().clear();
        if(resultImage != null){
            ImageView resultImageView = new ImageView(resultImage);
            resultPane.getChildren().add(resultImageView);
        }
        resultPane.setAlignment(Pos.CENTER);
    }

    private void addArrowKeyEventListener(){
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case A:
                        move("a");
                        break;
                    case D:
                        move("d");
                        break;
                    case W:
                        move("w");
                        break;
                    case S:
                        move("s");
                        break;
                    case UP:
                        move("w");
                        break;
                    case DOWN:
                        move("s");
                        break;
                    case LEFT:
                        move("a");
                        break;
                    case RIGHT:
                        move("d");
                        break;
                }
            }
        });
    }

    private void addMoveEventListener(Pane pane, String direction){
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                move(direction);
            }
        });
    }

    private void move(String direction){
        Position oldPosition = gameEngine.getPlayer().getPosition();
        Position newPosition = gameEngine.move(direction);
        if(oldPosition.equals(newPosition)){
            return;
        }else{
            int oldIndex = oldPosition.getY() * (MAZE_HEIGHT * 4 + 1) + oldPosition.getX();
            Pane cell = convertString2Image(' ');
            this.mazePane.getChildren().remove(this.cells.get(oldIndex));
            this.cells.remove(oldIndex);
            this.mazePane.add(cell, oldPosition.getX(), oldPosition.getY());
            this.cells.add(oldIndex, cell);

            Pane playerCell = convertString2Image('P');
            int newIndex = newPosition.getY() * (MAZE_HEIGHT * 4 + 1) + newPosition.getX();
            this.mazePane.getChildren().remove(this.cells.get(newIndex));
            this.cells.remove(newIndex);
            this.mazePane.add(playerCell,  newPosition.getX(), newPosition.getY());
            this.cells.add(newIndex, playerCell);
        }
        remainLifeLabel.setText(Integer.toString(gameEngine.getPlayer().getHealthLevel()));
        goldNumberLabel.setText(Integer.toString(gameEngine.getGolds().size()));

        if (gameEngine.getPlayer().isDead()) {
            gameFailed();
        }
        // if all golds are collected
        if (gameEngine.getGolds().size() == 0) {
            foundAllGolds();
            //add exit
            Pane exit = convertString2Image('E');
            Position exitPosition = gameEngine.getExit().getPosition();
            int index = exitPosition.getY() * (MAZE_HEIGHT * 4 + 1) + exitPosition.getX();
            this.mazePane.getChildren().remove(cells.get(index));
            this.mazePane.add(exit, exitPosition.getX(), exitPosition.getY());
            if(gameEngine.isHasFoundExit()){
                gameSuccess();
            }
        }
    }

    private void gameFailed(){
        gameEngine.gameFailed();
        Image failedImage = new Image(baseUrl + "game_over.png", gameResultSize, gameResultSize, false, false);
        setGameResultPane(resultPane, failedImage);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Restart");
        alert.setTitle("GAME OVER");
        alert.setHeaderText("GAME OVER");
        alert.setContentText("Game Over.\n Do you want to restart now ?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                restart();
            }
        });
    }

    private void restart() {
        //init members
        initMembers();
        //init maze grid
        initMazeGrid();
    }

    private void gameSuccess(){
        gameEngine.gameExit();
        Image successImage = new Image(baseUrl + "win.png", gameResultSize, gameResultSize, false, false);
        setGameResultPane(resultPane, successImage);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Restart");
        alert.setTitle("You Won!");
        alert.setHeaderText("You Won.");
        alert.setContentText("You Won!!!.\n Do you want to restart now ?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                restart();
            }
        });
    }

    private void gameExit(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Exit");
        alert.setTitle("Exit");
        alert.setHeaderText("Please confirm.");
        alert.setContentText("Do you want to exit now ?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    private void showHelp(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Please read carefully.");
        alert.setContentText("Player is trapped in a maze.\n " +
                        "To escape from the maze, he must find all the golds, that are scattered in the maze.\n" +
                "After collecting all the golds, the exit will appear at the corner of the maze.\n" +
                "And of course, Player is just a normal girl, she couldn't see or walk through the maze walls.\n" +
                "Every movement will cost Player a drop of blood. So make your move wisely!.\n");
        alert.show();
    }

    private void foundAllGolds(){
        gameEngine.foundAllGolds();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("I got it.");
        alert.setTitle("Found All Golds");
        alert.setHeaderText("Game is not completed.");
        alert.setContentText("ALL GOLDS WAS FOUND!\n" +
                "BUT...WE'RE NOT DONE YET...\n" +
                "FIND THE EXIT BEFORE YOUR HP BECOMES ZERO!\n");
        alert.show();
    }
}


