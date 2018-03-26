package fr.litarvan.sakado.editor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class SakadoTextEditor extends JPanel implements CaretListener
{
    private SakadoToolbar toolbar;
    private JEditorPane editor;

    public SakadoTextEditor()
    {
        this.setLayout(new BorderLayout());

        this.toolbar = new SakadoToolbar(this);
        this.toolbar.setPreferredSize(new Dimension(1, SakadoToolbar.HEIGHT));
        this.add(this.toolbar, BorderLayout.PAGE_START);

        this.editor = new JEditorPane();
        this.editor.setContentType("text/html");
        this.editor.setBackground(Color.WHITE);
        this.editor.setForeground(Color.BLACK);
        this.editor.addCaretListener(this);
        this.add(this.editor, BorderLayout.CENTER);

        addCTRLShortcut(KeyEvent.VK_B, () -> setBold(!isBold()));
        addCTRLShortcut(KeyEvent.VK_I, () -> setItalic(!isItalic()));
        addCTRLShortcut(KeyEvent.VK_U, () -> setUnderline(!isUnderline()));
        addCTRLShortcut(KeyEvent.VK_R, () -> setFontSize(21));
    }

    public void addCTRLShortcut(int key, Runnable event)
    {
        String name = "key-" + key;

        editor.getInputMap().put(KeyStroke.getKeyStroke(key, InputEvent.CTRL_DOWN_MASK), name);
        editor.getActionMap().put(name, new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                event.run();
                caretUpdate(null);
            }
        });
    }

    @Override
    public void caretUpdate(CaretEvent caretEvent)
    {
        SwingUtilities.invokeLater(() -> this.toolbar.updateButtons());
    }

    public void setBold(boolean bold)
    {
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setBold(sas, bold);

        this.setSelection(sas);
    }

    public boolean isBold()
    {
        return StyleConstants.isBold(getKit().getInputAttributes());
    }

    public void setItalic(boolean italic)
    {
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setItalic(sas, italic);

        this.setSelection(sas);
    }

    public boolean isItalic()
    {
        return StyleConstants.isItalic(getKit().getInputAttributes());
    }

    public void setUnderline(boolean underline)
    {
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setUnderline(sas, underline);

        this.setSelection(sas);
    }

    public boolean isUnderline()
    {
        return StyleConstants.isUnderline(getKit().getInputAttributes());
    }

    public void setStrikeThrough(boolean strikeThrough)
    {
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setStrikeThrough(sas, strikeThrough);

        this.setSelection(sas);
    }

    public boolean isStrikeThrough()
    {
        return StyleConstants.isStrikeThrough(getKit().getInputAttributes());
    }

    public void setFontSize(int size)
    {
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setFontSize(sas, size);

        this.setSelection(sas);
    }

    public int getFontSize()
    {
        return StyleConstants.getFontSize(getKit().getInputAttributes());
    }

    public void setSelection(AttributeSet attr)
    {
        int a = editor.getSelectionStart();
        int b = editor.getSelectionEnd();

        if (a != b)
        {
            getDocument().setCharacterAttributes(a, b - a, attr, false);
        }

        getKit().getInputAttributes().addAttributes(attr);
    }

    public HTMLDocument getDocument()
    {
        return (HTMLDocument) this.editor.getDocument();
    }

    public HTMLEditorKit getKit()
    {
        return (HTMLEditorKit) this.editor.getEditorKit();
    }

    public SakadoToolbar getToolbar()
    {
        return toolbar;
    }
}
