package fr.litarvan.sakado.editor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import fr.litarvan.sakado.editor.SakadoEditor;
import javax.swing.JPanel;

public class SakadoPanel extends JPanel
{
    private SakadoEditor editor;

    private SakadoExplorer explorer;
    private SakadoTextEditor textEditor;

    public SakadoPanel(SakadoEditor editor)
    {
        this.editor = editor;

        this.setLayout(new BorderLayout());

        this.explorer = new SakadoExplorer();
        this.textEditor = new SakadoTextEditor();

        this.explorer.setPreferredSize(new Dimension(SakadoExplorer.WIDTH, 1));

        this.add(this.explorer, BorderLayout.WEST);
        this.add(this.textEditor, BorderLayout.CENTER);
    }
}
