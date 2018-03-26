package fr.litarvan.sakado.editor.gui.api;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;

import fr.litarvan.sakado.editor.SakadoEditor;
import javax.swing.JComponent;

public class SakadoButton extends JComponent implements MouseListener
{
    private boolean toggleButton;
    private boolean toggled;
    private String content;
    private int borderRadius;
    private boolean hover;
    private Color hoverColor;
    private boolean underline;
    private boolean strikeThrough;

    private ButtonListener listener;

    public SakadoButton(String content)
    {
        this.content = content;
        this.borderRadius = 0;
        this.hover = false;
        this.toggled = false;
        this.toggleButton = false;
        this.hoverColor = Color.GRAY;

        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        Graphics2D g = SakadoEditor.enableAntialiasing(graphics);

        g.setColor(hover || toggled ? hoverColor : this.getBackground());
        g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), borderRadius, borderRadius);

        FontMetrics metrics = graphics.getFontMetrics();
        Rectangle2D stringBounds = metrics.getStringBounds(content, g);

        int x = (int) ((this.getWidth() - stringBounds.getWidth()) / 2);
        int y = (int) ((this.getHeight() - stringBounds.getHeight()) / 2 + metrics.getAscent());

        AttributedString as = new AttributedString(content);
        as.addAttribute(TextAttribute.FONT, graphics.getFont());

        if (underline)
        {
            as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        }

        if (strikeThrough)
        {
            as.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        }

        g.setColor(getForeground());
        g.drawString(as.getIterator(), x, y);
    }

    public void setUnderline(boolean underline)
    {
        this.underline = underline;
    }

    public void setStrikeThrough(boolean strikeThrough)
    {
        this.strikeThrough = strikeThrough;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getBorderRadius()
    {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius)
    {
        this.borderRadius = borderRadius;
    }

    public Color getHoverColor()
    {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor)
    {
        this.hoverColor = hoverColor;
    }

    public boolean isToggleButton()
    {
        return toggleButton;
    }

    public void setToggleButton(boolean toggleButton)
    {
        this.toggleButton = toggleButton;
    }

    public void setToggled(boolean toggled)
    {
        this.toggled = toggled;
        repaint();
    }

    public boolean isToggled()
    {
        return toggled;
    }

    public ButtonListener getListener()
    {
        return listener;
    }

    public void setListener(ButtonListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {
        if (toggleButton)
        {
            this.toggled = !this.toggled;
        }

        if (listener != null)
        {
            listener.onChange(this);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent)
    {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent)
    {
        this.hover = true;
        this.repaint();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent)
    {
        this.hover = false;
        this.repaint();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
