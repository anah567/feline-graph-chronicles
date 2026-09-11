package app;

import app.ui.RoundedButton;
import app.vista.HomePanel;
import mision1.vista.Mission1Panel;
import mision2.vista.Mission2Panel;
import mision3.vista.Mission3Panel;
import mision4.vista.Mission4Panel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    public static final String HOME = "HOME";
    public static final String MISSION_1 = "MISSION_1";
    public static final String MISSION_2 = "MISSION_2";
    public static final String MISSION_3 = "MISSION_3";
    public static final String MISSION_4 = "MISSION_4";

    private final CardLayout cardLayout =
            new CardLayout();

    private final JPanel contentPanel =
            new JPanel(cardLayout);

    private final Map<String, RoundedButton> menuButtons =
            new LinkedHashMap<>();

    public MainFrame() {

        super("The Feline Graph Chronicles");

        Theme.applyGlobalDefaults();

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setSize(
                1250,
                800
        );

        setMinimumSize(
                new Dimension(
                        1050,
                        700
                )
        );

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                Theme.BACKGROUND
        );

        setLayout(
                new BorderLayout()
        );

        add(
                buildSidebar(),
                BorderLayout.WEST
        );

        add(
                buildContent(),
                BorderLayout.CENTER
        );

        showPage(HOME);
    }

    private JPanel buildSidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setBackground(
                Theme.SIDEBAR
        );

        sidebar.setPreferredSize(
                new Dimension(
                        220,
                        0
                )
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBorder(
                BorderFactory.createEmptyBorder(
                        28,
                        18,
                        28,
                        18
                )
        );

        JPanel brand =
                buildBrand();

        sidebar.add(brand);

        sidebar.add(
                Box.createVerticalStrut(12)
        );

        JLabel subtitle =
                new JLabel(
                        "<html>"
                                + "<div style='text-align:center;'>"
                                + "Graph algorithms for<br>"
                                + "a kinder world ♡"
                                + "</div>"
                                + "</html>",
                        SwingConstants.CENTER
                );

        subtitle.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        12
                )
        );

        subtitle.setForeground(
                Theme.TEXT_SECONDARY
        );

        subtitle.setAlignmentX(
                CENTER_ALIGNMENT
        );

        sidebar.add(subtitle);

        sidebar.add(
                Box.createVerticalStrut(18)
        );

        JSeparator separator =
                new JSeparator();

        separator.setForeground(
                Theme.BORDER
        );

        separator.setBackground(
                Theme.BORDER
        );

        separator.setMaximumSize(
                new Dimension(
                        180,
                        1
                )
        );

        separator.setAlignmentX(
                CENTER_ALIGNMENT
        );

        sidebar.add(separator);

        sidebar.add(
                Box.createVerticalStrut(18)
        );

        sidebar.add(
                createMenuButton(
                        "🏠   Home",
                        HOME
                )
        );

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(
                createMenuButton(
                        "🌸   Misión 1",
                        MISSION_1
                )
        );

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(
                createMenuButton(
                        "🌷   Misión 2",
                        MISSION_2
                )
        );

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(
                createMenuButton(
                        "🍰   Misión 3",
                        MISSION_3
                )
        );

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        sidebar.add(
                createMenuButton(
                        "🌿   Misión 4",
                        MISSION_4
                )
        );

        sidebar.add(
                Box.createVerticalGlue()
        );

        return sidebar;
    }

    private JPanel buildBrand() {

        JPanel brand =
                new JPanel();

        brand.setOpaque(false);

        brand.setLayout(
                new BoxLayout(
                        brand,
                        BoxLayout.Y_AXIS
                )
        );

        brand.setAlignmentX(
                CENTER_ALIGNMENT
        );

        brand.setMaximumSize(
                new Dimension(
                        190,
                        105
                )
        );

        brand.setPreferredSize(
                new Dimension(
                        190,
                        105
                )
        );


        JLabel cat =
                new JLabel(
                        "🐱",
                        SwingConstants.CENTER
                );

        cat.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        32
                )
        );

        cat.setAlignmentX(
                CENTER_ALIGNMENT
        );


        JLabel logo =
                new JLabel(
                        "<html>"
                                + "<div style='text-align:center;'>"
                                + "<b>The Feline</b><br>"
                                + "<b>Graph Chronicles</b>"
                                + "</div>"
                                + "</html>",
                        SwingConstants.CENTER
                );

        logo.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        15
                )
        );

        logo.setForeground(
                Theme.TEXT_PRIMARY
        );

        logo.setAlignmentX(
                CENTER_ALIGNMENT
        );


        brand.add(cat);

        brand.add(
                Box.createVerticalStrut(8)
        );

        brand.add(logo);

        return brand;
    }

    private RoundedButton createMenuButton(
            String text,
            String page) {

        RoundedButton button =
                new RoundedButton(
                        text,
                        Theme.SIDEBAR,
                        Theme.CARD_BLUE
                );

        button.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                Theme.TEXT_PRIMARY
        );

        button.setMaximumSize(
                new Dimension(
                        184,
                        42
                )
        );

        button.setPreferredSize(
                new Dimension(
                        184,
                        42
                )
        );

        button.setMinimumSize(
                new Dimension(
                        184,
                        42
                )
        );

        button.setAlignmentX(
                CENTER_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.addActionListener(
                e -> showPage(page)
        );

        menuButtons.put(
                page,
                button
        );

        return button;
    }

    private JPanel buildContent() {

        contentPanel.setBackground(
                Theme.BACKGROUND
        );

        contentPanel.add(
                new HomePanel(
                        this::showPage
                ),
                HOME
        );

        contentPanel.add(
                new Mission1Panel(),
                MISSION_1
        );

        contentPanel.add(
                new Mission2Panel(),
                MISSION_2
        );

        contentPanel.add(
                new Mission3Panel(),
                MISSION_3
        );

        contentPanel.add(
                new Mission4Panel(),
                MISSION_4
        );

        return contentPanel;
    }

    private JPanel buildMissionPlaceholder(
            String mission) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Theme.BACKGROUND
        );

        JLabel label =
                new JLabel(
                        mission,
                        SwingConstants.CENTER
                );

        label.setFont(
                Theme.HERO_TITLE_FONT
        );

        label.setForeground(
                Theme.TEXT_PRIMARY
        );

        panel.add(
                label,
                BorderLayout.CENTER
        );

        return panel;
    }

    public void showPage(
            String page) {

        cardLayout.show(
                contentPanel,
                page
        );

        updateSelectedButton(
                page
        );
    }

    private void updateSelectedButton(
            String selectedPage) {

        for (Map.Entry<String, RoundedButton> entry
                : menuButtons.entrySet()) {

            RoundedButton button =
                    entry.getValue();

            if (entry.getKey().equals(selectedPage)) {

                button.setButtonColor(
                        Theme.CARD_BLUE
                );

                button.setHoverColor(
                        Theme.BLUE
                );

            } else {

                button.setButtonColor(
                        Theme.SIDEBAR
                );

                button.setHoverColor(
                        Theme.CARD_BLUE
                );
            }
        }
    }
}