package com.mycompany.gui;

import javax.swing.*;
import java.awt.*;

public class LetterBlock extends JLabel
{
    //wordle letter block background colors
    public final static Color
            COLOR_UNSET = new Color(0xC4CFA1),
            COLOR_OFF_TARGETED = new Color(0x8B956D),
            COLOR_DISPLACED = new Color(0x4D533C),
            COLOR_HIT = new Color(0x1F1F1F);
    int number;
    public boolean numberSet = false;

    public LetterBlock()
    {
        //setText("x");
        setFont(new Font("Silkscreen", 0, 25));
        setNumber(-1);
        setHorizontalAlignment(JLabel.CENTER);
        setBackground(COLOR_UNSET);
        setForeground(Color.WHITE);
        setOpaque(true);
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public void setText(String text)
    {
        super.setText(text.toUpperCase());
    }

    public void setNumber(int number)
    {
        this.number = number;
        switch (number)
        {
            case -1:
            case 0:
            {
                setBackground(COLOR_OFF_TARGETED);
                break;
            }
            case 1:
            {
                setBackground(COLOR_HIT);
                break;
            }
            case 2:
            {
                setBackground(COLOR_DISPLACED);
                break;
            }
            default:
            {
                throw new IllegalArgumentException("Invalid number: " + number);
            }
        }
    }
}
