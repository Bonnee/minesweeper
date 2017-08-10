/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author bonnee
 */
public class MineSweeper extends Application
{

    int size = 10;
    int bombs = 12;
    Field field;

    @Override
    public void start(Stage primaryStage)
    {
        Button newBtn = new Button("New");
        Text sizeTxt = new Text("Size:");
        NumericField sizeIn = new NumericField(size);
        Text bombsTxt = new Text("Bombs:");
        NumericField bombsIn = new NumericField(bombs);

        ToolBar tools = new ToolBar(newBtn, sizeTxt, sizeIn, bombsTxt, bombsIn);
        BorderPane root = new BorderPane();
        root.setTop(tools);
        root.setCenter(Init(primaryStage, (int) tools.getHeight()));

        Scene scene = new Scene(root);

        primaryStage.setTitle("MineSweeper");
        primaryStage.setScene(scene);
        primaryStage.show();

        newBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                size = Integer.parseInt(sizeIn.getText());
                bombs = Integer.parseInt(bombsIn.getText());
                root.setCenter(Init(primaryStage, (int) tools.getHeight()));
            }
        });
    }

    Field Init(Stage stage, int tHeight)
    {
        Field f = new Field(size, bombs);
        int w = f.getPrefSize();
        int h = w + tHeight;

        stage.setResizable(false);
        stage.setWidth(w);
        stage.setHeight(h);
        return f;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
