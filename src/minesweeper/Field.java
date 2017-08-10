/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author bonnee
 */
public class Field extends GridPane
{

    int size;
    int bombs;

    int getMinSize()
    {
        return size * Tile.getMinSize();
    }

    int getPrefSize()
    {
        return size * Tile.getPrefSize();
    }

    Field(int size, int bombs)
    {
        this.size = size;
        this.bombs = bombs;

        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                Tile t = new Tile();
                t.setOnMouseClicked(handler);
                add(t, x, y);
            }
        }

        for (int i = 0; i < bombs;)
        {
            Random rnd = new Random();
            Tile t = ((Tile) getChildren().get(rnd.nextInt(size) * size + rnd.nextInt(size)));
            if (!t.getBomb())
            {
                i++;
                t.setBomb(true);

                int col = GridPane.getColumnIndex(t);
                int row = GridPane.getRowIndex(t);

                for (int x = ((row == 0) ? 0 : -1); x <= ((row == size - 1) ? 0 : 1); x++)
                {
                    for (int y = ((col == 0) ? 0 : -1); y <= ((col == size - 1) ? 0 : 1); y++)
                    {
                        ((Tile) getChildren().get((col + y) * size + (row + x))).neighboringBombs++;
                    }
                }
            }
        }
    }

    EventHandler handler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent e)
        {
            if (((Tile) e.getSource()).click(e.getButton()))
            {
                loose();
            }
        }
    };

    private void loose()
    {
        for (int i = 0; i < size * size; i++)
        {
            Tile t = ((Tile) getChildren().get(i));
            if (t.getBomb())
            {
                t.click(MouseButton.PRIMARY);
            }
            ((Tile) t).setDisable(true);
        }
    }
}
