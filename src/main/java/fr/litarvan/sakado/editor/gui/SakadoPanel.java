package fr.litarvan.sakado.editor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import fr.litarvan.sakado.editor.SakadoEditor;
import javax.swing.JPanel;

public class SakadoPanel extends JPanel
{
    private SakadoEditor editor;

    private SakadoTabPanel tabPanel;
    private SakadoExplorer explorer;
    private SakadoTextEditor textEditor;

    public SakadoPanel(SakadoEditor editor)
    {
        this.editor = editor;

        this.setLayout(new BorderLayout());

        this.tabPanel = new SakadoTabPanel();
        this.explorer = new SakadoExplorer();
        this.textEditor = new SakadoTextEditor();

        this.tabPanel.setPreferredSize(new Dimension(1, SakadoTabPanel.HEIGHT));
        this.explorer.setPreferredSize(new Dimension(SakadoExplorer.WIDTH, 1));

        this.add(this.tabPanel, BorderLayout.PAGE_START);
        this.add(this.explorer, BorderLayout.WEST);
        this.add(this.textEditor, BorderLayout.CENTER);
    }
}
