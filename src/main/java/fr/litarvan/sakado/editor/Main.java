package fr.litarvan.sakado.editor;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        SakadoEditor editor = new SakadoEditor();
        editor.start();
    }
}
