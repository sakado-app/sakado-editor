package fr.litarvan.sakado.editor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import fr.litarvan.sakado.editor.gui.SakadoFrame;

public class SakadoEditor
{
    private SakadoFrame frame;

    public SakadoEditor()
    {
        this.frame = new SakadoFrame(this);
    }

    public void start()
    {
        this.frame.setVisible(true);
    }

    public static Graphics2D enableAntialiasing(Graphics graphics)
    {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        return g2d;
    }

    public SakadoFrame getFrame()
    {
        return frame;
    }
}
