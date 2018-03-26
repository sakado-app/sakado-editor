package fr.litarvan.sakado.editor.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SakadoExplorer extends JPanel
{
    public static final int WIDTH = 300;

    public SakadoExplorer()
    {
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
        this.setBackground(Color.WHITE);
    }
}
