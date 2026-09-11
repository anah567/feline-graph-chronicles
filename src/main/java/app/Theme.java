package app;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;

public final class Theme {

    private Theme() {
    }

    public static final Color BACKGROUND =
            new Color(0xF8, 0xFB, 0xFF);

    public static final Color SIDEBAR =
            new Color(0xF4, 0xF9, 0xFF);

    public static final Color HERO =
            new Color(0xE9, 0xF4, 0xFF);

    public static final Color CARD_BLUE =
            new Color(0xE8, 0xF3, 0xFF);

    public static final Color CARD_LAVENDER =
            new Color(0xF0, 0xEC, 0xFF);

    public static final Color CARD_YELLOW =
            new Color(0xFF, 0xF8, 0xDC);

    public static final Color CARD_MINT =
            new Color(0xE7, 0xF8, 0xF1);

    public static final Color BLUE =
            new Color(0xB9, 0xDA, 0xFF);

    public static final Color BLUE_STRONG =
            new Color(0x75, 0xA7, 0xE8);

    public static final Color LAVENDER =
            new Color(0xC9, 0xBE, 0xFF);

    public static final Color YELLOW =
            new Color(0xF5, 0xD9, 0x76);

    public static final Color MINT =
            new Color(0xA9, 0xE4, 0xCC);

    public static final Color PINK =
            new Color(0xFF, 0xC7, 0xD3);

    public static final Color TEXT_PRIMARY =
            new Color(0x25, 0x3B, 0x6E);

    public static final Color TEXT_SECONDARY =
            new Color(0x6E, 0x82, 0xAA);

    public static final Color BORDER =
            new Color(0xD9, 0xE8, 0xF8);

    public static final Color WHITE =
            new Color(0xFF, 0xFF, 0xFF);

    public static final Color DANGER =
            new Color(0xEF, 0x8C, 0x8C);

    public static final Color CANVAS_BACKGROUND =
            new Color(0xF4, 0xF9, 0xFF);

    public static final Color PANEL =
            WHITE;

    public static final Color ACCENT =
            BLUE_STRONG;

    public static final Color ACCENT_SECONDARY =
            BLUE;

    public static final Color CELL_EMPTY =
            new Color(0xEE, 0xF4, 0xFA);


    public static final String FONT_FAMILY = "Helvetica Neue";


    public static final Font LOGO_FONT =
            new Font(
                    FONT_FAMILY,
                    Font.BOLD,
                    22
            );

    public static final Font HERO_TITLE_FONT =
            new Font(
                    FONT_FAMILY,
                    Font.BOLD,
                    32
            );

    public static final Font TITLE_FONT =
            new Font(
                    FONT_FAMILY,
                    Font.BOLD,
                    20
            );

    public static final Font SUBTITLE_FONT =
            new Font(
                    FONT_FAMILY,
                    Font.PLAIN,
                    15
            );

    public static final Font CARD_TITLE_FONT =
            new Font(
                    FONT_FAMILY,
                    Font.BOLD,
                    17
            );

    public static final Font SMALL_FONT =
            new Font(
                    FONT_FAMILY,
                    Font.PLAIN,
                    13
            );

    public static final Font BUTTON_FONT =
            new Font(
                    FONT_FAMILY,
                    Font.BOLD,
                    14
            );

    public static final Font NORMAL_FONT =
            new Font(
                    FONT_FAMILY,
                    Font.PLAIN,
                    14
            );

    public static final Font MONO_FONT =
            new Font(
                    "Monospaced",
                    Font.PLAIN,
                    13
            );


    public static final int GAP = 16;

    public static final int RADIUS = 24;


    public static void styleTitle(
            JLabel label) {

        label.setFont(TITLE_FONT);
        label.setForeground(TEXT_PRIMARY);
    }


    public static void styleTextArea(
            JTextArea area) {

        area.setBackground(WHITE);
        area.setForeground(TEXT_PRIMARY);
        area.setCaretColor(TEXT_PRIMARY);
        area.setFont(MONO_FONT);
    }


    public static void styleButton(
            JButton button) {

        button.setBackground(BLUE);
        button.setForeground(TEXT_PRIMARY);
        button.setFocusPainted(false);
        button.setFont(BUTTON_FONT);
    }


    public static void styleRadio(
            JRadioButton radio) {

        radio.setOpaque(false);
        radio.setForeground(TEXT_PRIMARY);
        radio.setFont(NORMAL_FONT);
    }


    public static void styleCombo(
            JComboBox<?> combo) {

        combo.setBackground(WHITE);
        combo.setForeground(TEXT_PRIMARY);
        combo.setFont(NORMAL_FONT);
    }


    public static void applyGlobalDefaults() {

        UIManager.put(
                "Panel.background",
                BACKGROUND
        );

        UIManager.put(
                "Label.foreground",
                TEXT_PRIMARY
        );

        UIManager.put(
                "Label.font",
                NORMAL_FONT
        );

        UIManager.put(
                "Button.foreground",
                TEXT_PRIMARY
        );

        UIManager.put(
                "Button.font",
                BUTTON_FONT
        );

        UIManager.put(
                "RadioButton.font",
                NORMAL_FONT
        );

        UIManager.put(
                "ComboBox.font",
                NORMAL_FONT
        );

        UIManager.put(
                "OptionPane.background",
                WHITE
        );

        UIManager.put(
                "OptionPane.messageForeground",
                TEXT_PRIMARY
        );

        UIManager.put(
                "OptionPane.messageFont",
                NORMAL_FONT
        );

        UIManager.put(
                "TextArea.background",
                WHITE
        );

        UIManager.put(
                "TextArea.foreground",
                TEXT_PRIMARY
        );

        UIManager.put(
                "TextArea.font",
                MONO_FONT
        );
    }
}