package fr.litarvan.sakado.editor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import fr.litarvan.sakado.editor.SakadoEditor;
import fr.litarvan.sakado.editor.gui.api.SakadoButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SakadoToolbar extends JPanel
{
    public static final int HEIGHT = 45;

    private SakadoTextEditor editor;

    private SakadoButton bold;
    private SakadoButton italic;
    private SakadoButton underline;
    private SakadoButton strikeThrough;

    public SakadoToolbar(SakadoTextEditor editor)
    {
        this.editor = editor;

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        bold = createButton("B", Font.BOLD);
        bold.setToolTipText("Gras (CTRL+B)");
        bold.setListener(btn -> editor.setBold(btn.isToggled()));
        this.add(bold);

        italic = createButton("I", Font.ITALIC);
        italic.setToolTipText("Italique (CTRL+I)");
        italic.setListener(btn -> editor.setItalic(btn.isToggled()));
        this.add(italic);

        underline = createButton("U", Font.PLAIN);
        underline.setToolTipText("Sousligné (CTRL+U)");
        underline.setUnderline(true);
        underline.setListener(btn -> editor.setUnderline(btn.isToggled()));
        this.add(underline);

        strikeThrough = createButton("S", Font.PLAIN);
        strikeThrough.setToolTipText("Barré");
        strikeThrough.setStrikeThrough(true);
        strikeThrough.setListener(btn -> editor.setStrikeThrough(btn.isToggleButton()));
        this.add(strikeThrough);
    }

    protected SakadoButton createButton(String content, int modifier)
    {
        SakadoButton button = new SakadoButton(content) {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(SakadoEditor.enableAntialiasing(g));
            }
        };

        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setHoverColor(new Color(245, 245, 245));
        button.setToggleButton(true);
        button.setPreferredSize(new Dimension(35, 35));
        button.setFont(new Font("Liberation Serif", modifier, 24));
        button.setBorderRadius(5);

        return button;
    }

    public void updateButtons()
    {
        this.bold.setToggled(editor.isBold());
        this.italic.setToggled(editor.isItalic());
        this.underline.setToggled(editor.isUnderline());
        this.strikeThrough.setToggled(editor.isStrikeThrough());
    }
}
