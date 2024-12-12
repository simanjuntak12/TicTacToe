/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package tictactoeapplication;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author MyBook Hype
 */
public class TicTacToeApplication extends Application {
    
    private Pane gameboard = new Pane();
    private Button btnPosition[] = new Button[9];
    private double width = 100;
    private double height = 100;
    private boolean hasWinner = false;
    private boolean isPlayerA = true;
    private Label lblDisplay = new Label();
    private Label title = new Label();
    private Label settingstitle = new Label();
    private Button btnNewGame = new Button("New Game");
    private Button btnQuit = new Button("Quit Game");
    private BorderPane border;
    
    
    @Override
    public void start(Stage stage) {
       for (int i =0; i<9; i++){
           btnPosition[i] = new Button();
           btnPosition[i].setPrefSize(width, height);
           btnPosition[i].setLayoutX(i%3 * width);
           btnPosition[i].setLayoutY(i/3 * height);
           final int pos = i;
           btnPosition[i].setOnAction (e-> selected(pos));
           gameboard.getChildren().add(btnPosition[i]);
           
       }
       
       btnNewGame.setPrefSize(100, 30);
       btnNewGame.setLayoutX(200);
       btnNewGame.setLayoutY(310);
       
       btnQuit.setPrefSize(100, 30);
       btnQuit.setLayoutX(200);
       btnQuit.setLayoutY(310);
       
       GridPane settings = new GridPane();
       
       lblDisplay.setPrefSize(100, 50);
       lblDisplay.setLayoutX(0);
       lblDisplay.setLayoutY(100);
       lblDisplay.setText("Player A, Please Start the Game!");
       
       settingstitle.setText("Settings: ");
       title.setText(" Tic Tac Toe Game");
       title.setFont(Font.font("ALGERIAN", FontWeight.BOLD, 50));
       
       border = new BorderPane();
       settings.add(settingstitle, 0, 0);
       settings.add(btnNewGame, 0, 3);
       settings.add(btnQuit, 0, 6);
       
       border.setStyle("-fx-bacground-color :white ; -fx-border-width: 10px");
       border.setTop(title);
       border.setLeft(settings);
       
       border.setCenter(gameboard);
       border.setBottom(lblDisplay);
       
       Scene scene = new Scene(border, 500, 450);
       
       stage.setScene(scene);
       stage.show();
       
       btnNewGame.setOnAction(e -> {
       System.out.println("Restaring app!");
       stage.close();
       Platform.runLater(()-> new TicTacToeApplication().start(new Stage()));
       });
       
       btnQuit.setOnAction(e -> {
       System.out.println("Quiting App!");
       stage.close();
       });
       
    }
    
    public void selected(int i) {
        if (isPlayerA){
            btnPosition[i].setText("X");
            isPlayerA = false;
            lblDisplay.setText("Player B"); 
        }else {
            btnPosition[i].setText("0");
            isPlayerA = true;
            lblDisplay.setText("Player A");
        }
        
        btnPosition[i].setFont(Font.font(40));
        btnPosition[i].setDisable(true);
        
        findWinner();
        int j = 0;
        if (hasWinner){
            for (int m=0; m<9; m++){
                btnPosition[m].setDisable(true);
            }
        if (isPlayerA){
            lblDisplay.setText("Player B won!!");
            Alert alert = new Alert (Alert.AlertType.INFORMATION,
            "Player B won",
            ButtonType.OK);
            alert.showAndWait();
        }else {
            lblDisplay.setText("Player A won!");
            Alert alert = new Alert (Alert.AlertType.INFORMATION,
            "Player A won",
            ButtonType.OK);
            alert.showAndWait();
        }
            
       }else{
        checkTie();
        }
    }

    
    public void checkDiagonals() {
        
        if (btnPosition[0].getText().equals(btnPosition[4].getText()) &&
            btnPosition[4].getText().equals(btnPosition[8].getText()) &&
            !btnPosition[8].getText().isEmpty()){
            hasWinner = true;
        }
         if (btnPosition[2].getText().equals(btnPosition[4].getText()) &&
            btnPosition[4].getText().equals(btnPosition[6].getText()) &&
            !btnPosition[6].getText().isEmpty()){
            hasWinner = true;
         }
    }
    
    public void checkRows() {
        if (btnPosition[0].getText().equals(btnPosition[1].getText()) &&
            btnPosition[1].getText().equals(btnPosition[2].getText()) &&  
            !btnPosition[2].getText().isEmpty()){
            hasWinner = true;
        }
        if (btnPosition[3].getText().equals(btnPosition[4].getText()) &&
            btnPosition[4].getText().equals(btnPosition[5].getText()) &&  
            !btnPosition[5].getText().isEmpty()){
            hasWinner = true;
        }
        if (btnPosition[6].getText().equals(btnPosition[7].getText()) &&
            btnPosition[7].getText().equals(btnPosition[8].getText()) &&  
            !btnPosition[8].getText().isEmpty()){
            hasWinner = true;
        }
    }
    
    public void checkColumns() {
        if (btnPosition[0].getText().equals(btnPosition[3].getText()) &&
            btnPosition[3].getText().equals(btnPosition[6].getText()) &&  
            !btnPosition[6].getText().isEmpty()){
            hasWinner = true;
        }
        if (btnPosition[1].getText().equals(btnPosition[4].getText()) &&
            btnPosition[4].getText().equals(btnPosition[7].getText()) &&  
            !btnPosition[7].getText().isEmpty()){
            hasWinner = true;
        }
        if (btnPosition[2].getText().equals(btnPosition[5].getText()) &&
            btnPosition[5].getText().equals(btnPosition[8].getText()) &&  
            !btnPosition[8].getText().isEmpty()){
            hasWinner = true;
        }
    }
    
    public void checkTie() {
        
        for (int x = 0; x<btnPosition.length; x++){
            if(!btnPosition[x].isDisable()){
                break; //a button is still eneabled so cancel loop
                        
            }
        
        if (x== btnPosition.length - 1 && btnPosition[x].isDisable() && !hasWinner){
            lblDisplay.setText("ohh its a TIE!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
            "TIE",
            ButtonType.OK);
        }
        }
    }

     public void findWinner() {
    checkDiagonals();
    checkRows();
    checkColumns();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
