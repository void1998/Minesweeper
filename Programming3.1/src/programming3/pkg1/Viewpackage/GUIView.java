package programming3.pkg1.Viewpackage;

import java.util.List;
import java.util.regex.Pattern;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.PINK;
import javafx.stage.Stage;
import programming3.pkg1.UtilPackage.Constants;
import programming3.pkg1.Game;
import programming3.pkg1.Game;
import programming3.pkg1.Grid;
import programming3.pkg1.Grid;
import programming3.pkg1.NormalGame;
import programming3.pkg1.NormalGame;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.ScoreGame;
import programming3.pkg1.Square;
import programming3.pkg1.Timerhelperspackage.MoveTimer;

/**
 *
 * @author void
 */
public class GUIView {

    public static Stage window;
    static MoveTimer Time = new MoveTimer();

    public static void initWindow() {
        window = new Stage();
        window.setTitle("Minesweeper");
        //window.setMinWidth(600);
        //window.setMinHeight(600);
        window.setFullScreen(true);
        //window.setMaxWidth(800);
        //window.setMaxHeight(800);
    }

    public static void initGraphics(GridPane layout) {
        // create a background fill 
        BackgroundFill background_fill = new BackgroundFill(PINK, CornerRadii.EMPTY, Insets.EMPTY);
        // create Background 
        Background background = new Background(background_fill);
        layout.setBackground(background);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
    }

    public static GridPane initLayout(Button button[][], int height, int width, Label nameLabel, Label scoreLabel) {
        GridPane Grid = new GridPane();
        Grid.setPadding(new Insets(0, 0, 0, 0));
        Grid.setVgap(0);
        Grid.setHgap(0);
        Grid.setAlignment(Pos.CENTER);
        //
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Grid.getChildren().add(button[i][j]);
            }
        }
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(scoreLabel, 4, 0);
        Grid.getChildren().addAll(scoreLabel);

        //
        return Grid;

    }

    public static Button[][] initGrid(int height, int width) {
        Button button[][] = new Button[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                button[i][j] = new Button();
                GridPane.setConstraints(button[i][j], i + 1, j + 1);
                button[i][j].setStyle("-fx-background-color: gray;-fx-border-color:black;");
                button[i][j].setMinWidth(30);
                button[i][j].setMinHeight(30);
            }
        }
        return button;
    }

    public static void interact(Button buttons[][], Square squares[][], int height, int width, int playerNumber) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                //setting the border depending on a player
                String borderColor = "black";
                if (playerNumber == 0) {
                    borderColor = "Chartreuse";
                } else if (playerNumber == 1) {
                    borderColor = "blue";
                } else {
                    borderColor = "yellow";
                }
                if (buttons[i][j].getStyle().contains("-fx-border-color:black;")) {
                    if (squares[i + 1][j + 1].getSquareStatus().getStatus().equals(Constants.CLOSED)) {
                        buttons[i][j].setStyle("-fx-background-color: gray;-fx-border-color:black;");
                    } else if (squares[i + 1][j + 1].getSquareStatus().getStatus().equals(Constants.MARKED)) {
                        buttons[i][j].setStyle("-fx-background-image: url('programming3/pkg1/flag.png');-fx-background-size:30px 30px;-fx-border-color:" + borderColor + ";");
                    } else if (squares[i + 1][j + 1].getSquareStatus().getStatus().equals(Constants.OPENED_EMPTY)) {
                        buttons[i][j].setStyle("-fx-background-color: white;-fx-border-color:" + borderColor + ";");
                    } else if (squares[i + 1][j + 1].getSquareStatus().getStatus().equals(Constants.OPENED_MINE)) {
                        buttons[i][j].setStyle("-fx-background-image: url('programming3/pkg1/mine.jpg');-fx-background-size:30px 30px;-fx-border-color:" + borderColor + ";");
                    } else if (squares[i + 1][j + 1].getSquareStatus().getStatus().equals(Constants.OPENED_NUMBER)) {
                        buttons[i][j].setStyle("-fx-background-color: red;-fx-border-color:" + borderColor + ";");
                        Integer temp = squares[i + 1][j + 1].getSquareStatus().getValue();
                        String value = temp.toString();
                        buttons[i][j].setText(value);
                    }
                    //checking if there is an open shield
                    if (squares[i + 1][j + 1].getSquareStatus().getShield() != null) {
                        if (squares[i + 1][j + 1].getSquareStatus().getShield().getType() == 50 && (squares[i + 1][j + 1].getSquareStatus().getStatus() == Constants.OPENED_EMPTY || squares[i + 1][j + 1].getSquareStatus().getStatus() == Constants.OPENED_NUMBER)) {
                            buttons[i][j].setStyle("-fx-background-image: url('programming3/pkg1/shield50.png');-fx-background-size:30px 30px;");
                        } else if (squares[i + 1][j + 1].getSquareStatus().getShield().getType() == 100 && (squares[i + 1][j + 1].getSquareStatus().getStatus() == Constants.OPENED_EMPTY || squares[i + 1][j + 1].getSquareStatus().getStatus() == Constants.OPENED_NUMBER)) {
                            buttons[i][j].setStyle("-fx-background-image: url('programming3/pkg1/shield100.jpg');-fx-background-size:30px 30px;");
                        } else if (squares[i + 1][j + 1].getSquareStatus().getShield().getType() == 200 && (squares[i + 1][j + 1].getSquareStatus().getStatus() == Constants.OPENED_EMPTY || squares[i + 1][j + 1].getSquareStatus().getStatus() == Constants.OPENED_NUMBER)) {
                            buttons[i][j].setStyle("-fx-background-image: url('programming3/pkg1/shield200.png');-fx-background-size:30px 30px;");
                        }
                    }

                }
            }
        }

    }

    static GridPane finishGame(String message) {
        GridPane Grid = new GridPane();
        Grid.setPadding(new Insets(0, 0, 0, 0));
        Grid.setVgap(0);
        Grid.setHgap(0);
        Grid.setAlignment(Pos.CENTER);
        Label statment = new Label(message);
        //

        Grid.getChildren().add(statment);

        //
        return Grid;
    }

    public static GridPane initMenu() {
        GridPane Grid = new GridPane();
        Grid.setPadding(new Insets(10, 10, 10, 10));
        Grid.setVgap(10);
        Grid.setHgap(10);
        Grid.setAlignment(Pos.CENTER);
        //init buttons
        Button start = new Button("Start Game");
        Button options = new Button("Options");
        Button exit = new Button("Exit");

        //
        Button buttons[][] = GUIView.initGrid(19, 19);
        mainMenuButtonsActions(start, options, exit, window, buttons);
        Grid.getChildren().addAll(start, options, exit);
        initButton(start, 0, 1);
        initButton(options, 0, 2);
        initButton(exit, 0, 3);

        //
        return Grid;

    }

    public static void initButton(Button button, int x, int y) {
        GridPane.setConstraints(button, x, y);
        button.setStyle("-fx-background-color: blue;-fx-border-color:black;-fx-color: red;");
        button.setMinWidth(100);
        button.setMinHeight(40);

    }

    public static void mainMenuButtonsActions(Button start, Button options, Button exit, Stage window, Button buttons[][]) {
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                initGame(new ScoreGame(), 2, 16, 16, 0, 2, 20);
            }

        });
        //
        options.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                initGraphics(initOptions());
            }

        });
        //
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                window.close();
            }

        });
    }

    public static GridPane initOptions() {
        GridPane Grid = new GridPane();
        Grid.setPadding(new Insets(50, 50, 50, 50));
        Grid.setVgap(20);
        Grid.setHgap(20);
        Grid.setAlignment(Pos.CENTER);
        //init buttons
        Button submit = new Button("Apply changes and start game:");
        GridPane.setConstraints(submit, 0, 14);
        Button back = new Button("Back to main menu");
        GridPane.setConstraints(back, 1, 14);
        Label playersNumLabel = new Label("Number of players:");
        GridPane.setConstraints(playersNumLabel, 0, 0);
        TextField playersNumTextArea = new TextField();
        GridPane.setConstraints(playersNumTextArea, 0, 1);
        Label heightLabel = new Label("MineSweeper Width:");
        GridPane.setConstraints(heightLabel, 0, 2);
        TextField heightTextArea = new TextField();
        GridPane.setConstraints(heightTextArea, 0, 3);
        Label widthLabel = new Label("MineSweeper Height :");
        GridPane.setConstraints(widthLabel, 0, 4);
        TextField widthTextArea = new TextField();
        GridPane.setConstraints(widthTextArea, 0, 5);
        Label typeLabel = new Label("Game Type:(enter 1 for normal game & 2 for Score game)");
        GridPane.setConstraints(typeLabel, 0, 6);
        TextField typeTextArea = new TextField();
        GridPane.setConstraints(typeTextArea, 0, 7);
        Label AutoLabel = new Label("VS PC:(enter 0 for  game without PC & 1 for game with pc)");
        GridPane.setConstraints(AutoLabel, 0, 8);
        TextField AutoTextArea = new TextField();
        GridPane.setConstraints(AutoTextArea, 0, 9);

        Label playerShieldsNumLabel = new Label("initial player shields num(between 0&4)");
        GridPane.setConstraints(playerShieldsNumLabel, 0, 10);
        TextField playerShieldsNumTextArea = new TextField();
        GridPane.setConstraints(playerShieldsNumTextArea, 0, 11);
        Label gridShieldsNumLabel = new Label("grid shields num(between 0&15)");
        GridPane.setConstraints(gridShieldsNumLabel, 0, 12);
        TextField gridShieldsNumTextArea = new TextField();
        GridPane.setConstraints(gridShieldsNumTextArea, 0, 13);
        Grid.getChildren().addAll(playerShieldsNumLabel, playerShieldsNumTextArea, gridShieldsNumLabel, gridShieldsNumTextArea, submit, back, playersNumLabel, playersNumTextArea, heightLabel, heightTextArea, widthLabel, widthTextArea, typeLabel, typeTextArea,
                AutoLabel, AutoTextArea);
        optionsButtonActions(submit, playersNumTextArea, heightTextArea, widthTextArea, typeTextArea, AutoTextArea, playerShieldsNumTextArea, gridShieldsNumTextArea);
        backEvent(back);
        return Grid;
    }

    public static void backEvent(Button back) {
        back.setOnMouseClicked((MouseEvent event) -> {
            initGraphics(GUIView.initMenu());
        });
    }

    private static void optionsButtonActions(Button submit, TextField playersNumTextArea, TextField heightTextArea,
            TextField widthTextArea, TextField typeTextArea, TextField AutoTextArea,
            TextField playerShieldsNumTextArea, TextField gridShieldsNumTextArea) {

        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String pNum = playersNumTextArea.getText();
                    String hNum = heightTextArea.getText();
                    String wNum = widthTextArea.getText();
                    String tNum = typeTextArea.getText();
                    String ANum = AutoTextArea.getText();
                    String playerShieldsNum = playerShieldsNumTextArea.getText();
                    String gridShieldsNum = gridShieldsNumTextArea.getText();

                    if (Pattern.compile("[1-2]").matcher(pNum).matches()
                            && Pattern.compile("[5-9]|1[0-9]|2[0]").matcher(hNum).matches()
                            && Pattern.compile("[5-9]|1[0-9]|2[0]").matcher(wNum).matches()
                            && Pattern.compile("[1-2]").matcher(tNum).matches()
                            && Pattern.compile("[0-1]").matcher(ANum).matches()
                            && Pattern.compile("[0-4]").matcher(playerShieldsNum).matches()
                            && Pattern.compile("[0-9]||1[0-5]").matcher(gridShieldsNum).matches()) {
                        if (Integer.parseInt(tNum) == 1) {
                            initGame(new NormalGame(), Integer.parseInt(pNum), Integer.parseInt(hNum), Integer.parseInt(wNum), Integer.parseInt(ANum), Integer.parseInt(playerShieldsNum), Integer.parseInt(gridShieldsNum));
                        } else {
                            initGame(new ScoreGame(), Integer.parseInt(pNum), Integer.parseInt(hNum), Integer.parseInt(wNum), Integer.parseInt(ANum), Integer.parseInt(playerShieldsNum), Integer.parseInt(gridShieldsNum));
                        }
                    }

                    throw new Exception("INVALID INPUT");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }

        });

    }

    public static void applyGUI(Button buttons[][], Game myGame, Grid grid, Label nameLabel, Label scoreLabel, int Auto) {
        for (int i = 0; i < grid.getHieght(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                final int x;
                x = i + 1;
                final int y;
                y = j + 1;

                buttons[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        //
                        for (int i = 0; i < grid.getShieldsPlaces().size(); i++) {
                            System.out.printf("x = %d  y = %d", grid.getShieldsPlaces().get(i).getX(), grid.getShieldsPlaces().get(i).getY());
                            System.out.println("");
                        }
                        System.out.println("");
                        //
                        PlayerMove temp2 = new PlayerMove();
                        if (event.getButton() == MouseButton.PRIMARY) {

                            temp2.getMove().setType(Constants.REVEAL);
                            temp2.getSquare().setX(x);
                            temp2.getSquare().setY(y);
                            temp2.setPlayer(myGame.getCurrentPlayer());
                            grid.AcceptMove(temp2);

                            grid.getCurrentGame().getCurrentRules().DecideNextPlayer(myGame.getCurrentPlayer());
                            Time.stop();

                            Time = new MoveTimer() {

                                @Override
                                public void check() {
                                    grid.ss();
                                    System.out.println(""+grid.getCurrentGame().getCurrentPlayer().getName());
                                    System.out.println("SCORE:"+grid.getCurrentGame().getCurrentPlayer().getCurrentScore().getRealScore());
                                    //System.out.println("Shields number:"+grid.getCurrentGame().getCurrentPlayer().getShields().size());
                                }
                            };
                            Time.start();

                            if (Auto == 1) {
                                grid.AcceptMove(myGame.getCurrentPlayer().GetPlayerMove());
                            }

                            if (myGame.checkGame(grid) == Constants.WINNER) {

                                Scene fscene = new Scene(GUIView.finishGame("Grid finished correctly"));
                                window.setScene(fscene);
                                window.show();
                            } else if (myGame.checkGame(grid) == Constants.LOSER) {
                                Scene fscene = new Scene(GUIView.finishGame("game finished " + grid.getCurrentGame().getCurrentPlayer().getName() + " lost"));
                                window.setScene(fscene);
                                window.show();
                            } else {
                                interact(buttons, grid.getSquares(), grid.getHieght(), grid.getWidth(), temp2.getPlayer().getPlayerNumber());
                            }

                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            temp2.getMove().setType(Constants.MARK);
                            temp2.getSquare().setX(x);
                            temp2.getSquare().setY(y);
                            temp2.setPlayer(myGame.getCurrentPlayer());
                            grid.AcceptMove(temp2);
                            if (Auto == 1) {
                                grid.AcceptMove(myGame.getCurrentPlayer().GetPlayerMove());
                            }
                            interact(buttons, grid.getSquares(), grid.getHieght(), grid.getWidth(), temp2.getPlayer().getPlayerNumber());

                        }
                        nameLabel.setText(grid.getCurrentGame().getCurrentPlayer().getName());
                        Integer temp = grid.getCurrentGame().getCurrentPlayer().getCurrentScore().getRealScore();
                        scoreLabel.setText(temp.toString());

                    }
                });

            }
        }
    }

    public static void initGame(Game game, int playersNum, int height, int width, int Auto, int playerShieldsNum, int gridShieldsNum) {
        //init game
        Game myGame = game;
        myGame.initGame(playersNum, Auto, playerShieldsNum);
        Grid grid = new Grid(height, width, myGame, gridShieldsNum);
        System.out.println(myGame.getCurrentPlayer().getShields().size());

        PlayerMove temp = new PlayerMove();
        grid.initGrid(temp.getSquare());
        //
        Button buttons[][] = GUIView.initGrid(height, width);
        Label scoreLabel = new Label();
        Label nameLabel = new Label();
        initGraphics(initLayout(buttons, height, width, nameLabel, scoreLabel));
        applyGUI(buttons, myGame, grid, nameLabel, scoreLabel, Auto);
    }

}
