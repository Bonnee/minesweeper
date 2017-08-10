/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

/**
 *
 * @author bonnee
 */
enum TileStatus
{
    UNCLICKED, CLICKED, FLAGGED
}

public class Tile extends Button
{

    private static final int MINSIZE = 30;
    private static final int PREFSIZE = 50;

    boolean hasBomb;
    Integer neighboringBombs;
    TileStatus status;

    public void setBomb(boolean bomb)
    {
        hasBomb = bomb;
    }

    public boolean getBomb()
    {
        return hasBomb;
    }

    public static Integer getMinSize()
    {
        return MINSIZE;
    }

    public static Integer getPrefSize()
    {
        return PREFSIZE;
    }

    Tile()
    {
        status = TileStatus.UNCLICKED;
        neighboringBombs = 0;
        setMinSize(MINSIZE, MINSIZE);
        setPrefSize(PREFSIZE, PREFSIZE);
        setText(" ");
    }

    public boolean click(MouseButton b)
    {
        if (b == MouseButton.PRIMARY)
        {
            if (status == TileStatus.UNCLICKED)
            {
                status = TileStatus.CLICKED;
                setDisable(true);

                if (hasBomb)
                {
                    setText("X");
                    setStyle("-fx-background-color: red; -fx-text-fill: white;");
                    return true;
                }

                setText(neighboringBombs.toString());

                if (neighboringBombs == 1)
                {
                    setStyle("-fx-background-color: green; -fx-text-fill: white;");
                } else if (neighboringBombs > 1 && neighboringBombs <= 2)
                {
                    setStyle("-fx-background-color: yellow;");
                } else if (neighboringBombs > 2)
                {
                    setStyle("-fx-background-color: orange; -fx-text-fill: white;");
                }
            }
        } else if (b == MouseButton.SECONDARY)
        {
            if (status == TileStatus.UNCLICKED)
            {
                status = TileStatus.FLAGGED;
                setText("\u2691");
            } else if (status == TileStatus.FLAGGED)
            {
                status = TileStatus.UNCLICKED;
                setText(" ");
            }
        }
        return false;

    }
}
