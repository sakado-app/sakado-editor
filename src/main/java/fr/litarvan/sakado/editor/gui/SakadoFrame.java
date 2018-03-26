package fr.litarvan.sakado.editor.gui;

import fr.litarvan.sakado.editor.SakadoEditor;
import javax.swing.JFrame;

public class SakadoFrame extends JFrame
{
    public SakadoFrame(SakadoEditor editor)
    {
        this.setTitle("Sakado");
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setContentPane(new SakadoPanel(editor));
    }

    @Override
    public SakadoPanel getContentPane()
    {
        return (SakadoPanel) super.getContentPane();
    }
}
