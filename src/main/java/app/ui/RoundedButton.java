package app.ui;

import app.Theme;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

public class RoundedButton extends JButton {

    private final int radius;
    private Color buttonColor;
    private Color hoverColor;

    public RoundedButton(String text) {
        this(
                text,
                Theme.BLUE,
                Theme.BLUE_STRONG
        );
    }

    public RoundedButton(
            String text,
            Color buttonColor) {

        this(
                text,
                buttonColor,
                buttonColor.brighter()
        );
    }

    public RoundedButton(
            String text,
            Color buttonColor,
            Color hoverColor) {

        super(text);

        this.radius = 22;
        this.buttonColor = buttonColor;
        this.hoverColor = hoverColor;

        setForeground(
                Theme.TEXT_PRIMARY
        );

        setFont(
                Theme.BUTTON_FONT
        );

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        setMargin(
                new Insets(
                        8,
                        12,
                        8,
                        12
                )
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );
    }

    public void setButtonColor(
            Color buttonColor) {

        this.buttonColor =
                buttonColor;

        repaint();
    }

    public void setHoverColor(
            Color hoverColor) {

        this.hoverColor =
                hoverColor;

        repaint();
    }

    @Override
    protected void paintComponent(
            Graphics g) {

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (getModel().isPressed()) {

            g2.setColor(
                    buttonColor.darker()
            );

        } else if (getModel().isRollover()) {

            g2.setColor(
                    hoverColor
            );

        } else {

            g2.setColor(
                    buttonColor
            );
        }

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                radius,
                radius
        );

        g2.dispose();

        super.paintComponent(g);
    }
}