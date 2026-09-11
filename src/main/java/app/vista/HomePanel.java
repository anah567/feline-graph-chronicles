package app.vista;

import app.Theme;
import app.ui.RoundedButton;
import app.ui.RoundedPanel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.function.Consumer;

public class HomePanel extends JPanel {

    private final Consumer<String> navigator;

    public HomePanel(Consumer<String> navigator) {

        this.navigator = navigator;

        setLayout(
                new BorderLayout(
                        0,
                        22
                )
        );

        setBackground(
                Theme.BACKGROUND
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        24,
                        28,
                        20,
                        28
                )
        );

        add(
                buildHero(),
                BorderLayout.NORTH
        );

        add(
                buildMissionSection(),
                BorderLayout.CENTER
        );

        add(
                buildFooter(),
                BorderLayout.SOUTH
        );
    }

    private RoundedPanel buildHero() {

        RoundedPanel hero =
                new RoundedPanel(
                        30,
                        Theme.HERO
                );

        hero.setLayout(
                new BorderLayout()
        );

        hero.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        42,
                        30,
                        42
                )
        );

        JPanel information =
                new JPanel();

        information.setOpaque(false);

        information.setLayout(
                new BoxLayout(
                        information,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel eyebrow =
                new JLabel(
                        "✦  A GRAPH ALGORITHM ADVENTURE  ✦"
                );

        eyebrow.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        12
                )
        );

        eyebrow.setForeground(
                Theme.BLUE_STRONG
        );

        eyebrow.setAlignmentX(
                LEFT_ALIGNMENT
        );

        JLabel title =
                new JLabel(
                        "<html>"
                                + "The Feline Graph<br>"
                                + "Chronicles ♡"
                                + "</html>"
                );

        title.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        34
                )
        );

        title.setForeground(
                Theme.TEXT_PRIMARY
        );

        title.setAlignmentX(
                LEFT_ALIGNMENT
        );

        JLabel subtitle =
                new JLabel(
                        "Pola & Minerva's little adventure"
                );

        subtitle.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        16
                )
        );

        subtitle.setForeground(
                Theme.TEXT_SECONDARY
        );

        subtitle.setAlignmentX(
                LEFT_ALIGNMENT
        );

        information.add(eyebrow);

        information.add(
                Box.createVerticalStrut(12)
        );

        information.add(title);

        information.add(
                Box.createVerticalStrut(12)
        );

        information.add(subtitle);

        information.add(
                Box.createVerticalStrut(22)
        );

        information.add(
                buildAlgorithmTags()
        );

        hero.add(
                information,
                BorderLayout.CENTER
        );

        return hero;
    }

    private JPanel buildAlgorithmTags() {

        JPanel tags =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                9,
                                7
                        )
                );

        tags.setOpaque(false);

        tags.add(createTag("🕸️  Graphs"));
        tags.add(createTag("🌸  BFS - DFS"));
        tags.add(createTag("🌷  Dijkstra"));
        tags.add(createTag("🍰  Floyd-Warshall"));
        tags.add(createTag("🩵  Bellman-Ford"));
        tags.add(createTag("🌿  Kruskal"));

        return tags;
    }

    private RoundedPanel createTag(String text) {

        RoundedPanel tag =
                new RoundedPanel(
                        22,
                        new Color(
                                0xF8,
                                0xFB,
                                0xFF
                        )
                );

        tag.setLayout(
                new BorderLayout()
        );

        tag.setBorder(
                BorderFactory.createEmptyBorder(
                        7,
                        13,
                        7,
                        13
                )
        );

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(
                Theme.TEXT_SECONDARY
        );

        tag.add(
                label,
                BorderLayout.CENTER
        );

        return tag;
    }

    private JPanel buildMissionSection() {

        JPanel section =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        section.setOpaque(false);

        section.add(
                buildMissionHeader(),
                BorderLayout.NORTH
        );

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                16,
                                0
                        )
                );

        cards.setOpaque(false);

        cards.add(
                createMissionCard(
                        "MISSION 01",
                        "🌸",
                        "Rescue Nina",
                        "BFS + DFS",
                        "Help Pola find Nina through the maze!",
                        Theme.CARD_BLUE,
                        Theme.BLUE,
                        Theme.BLUE_STRONG,
                        "MISSION_1"
                )
        );

        cards.add(
                createMissionCard(
                        "MISSION 02",
                        "🌷",
                        "Claude Accounts",
                        "Dijkstra",
                        "Minerva finds the shortest path to safety.",
                        Theme.CARD_LAVENDER,
                        Theme.LAVENDER,
                        new Color(
                                0x88,
                                0x78,
                                0xC8
                        ),
                        "MISSION_2"
                )
        );

        cards.add(
                createMissionCard(
                        "MISSION 03",
                        "🍰",
                        "Churun Stash",
                        "Floyd-Warshall + Bellman-Ford",
                        "Trace every route to the secret stash!",
                        Theme.CARD_YELLOW,
                        Theme.YELLOW,
                        new Color(
                                0xB3,
                                0x8B,
                                0x28
                        ),
                        "MISSION_3"
                )
        );

        cards.add(
                createMissionCard(
                        "MISSION 04",
                        "🌿",
                        "Reconnect Network",
                        "Kruskal",
                        "Restore the cat network at minimum cost!",
                        Theme.CARD_MINT,
                        Theme.MINT,
                        new Color(
                                0x4F,
                                0x9A,
                                0x7C
                        ),
                        "MISSION_4"
                )
        );

        section.add(
                cards,
                BorderLayout.CENTER
        );

        return section;
    }

    private JPanel buildMissionHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Choose a mission ♡"
                );

        title.setFont(
                Theme.TITLE_FONT
        );

        title.setForeground(
                Theme.TEXT_PRIMARY
        );

        JLabel count =
                new JLabel(
                        "4 adventures await"
                );

        count.setFont(
                Theme.SMALL_FONT
        );

        count.setForeground(
                Theme.TEXT_SECONDARY
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                count,
                BorderLayout.EAST
        );

        return header;
    }

    private RoundedPanel createMissionCard(
            String mission,
            String emoji,
            String title,
            String algorithm,
            String description,
            Color background,
            Color buttonColor,
            Color accentColor,
            String page) {

        RoundedPanel card =
                new RoundedPanel(
                        28,
                        background
                );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        24,
                        18,
                        20,
                        18
                )
        );

        JLabel missionLabel =
                new JLabel(
                        mission,
                        JLabel.CENTER
                );

        missionLabel.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        11
                )
        );

        missionLabel.setForeground(
                accentColor
        );

        missionLabel.setAlignmentX(
                CENTER_ALIGNMENT
        );

        JLabel emojiLabel =
                new JLabel(
                        emoji,
                        JLabel.CENTER
                );

        emojiLabel.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        34
                )
        );

        emojiLabel.setAlignmentX(
                CENTER_ALIGNMENT
        );

        JLabel titleLabel =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                                + title
                                + "</div></html>",
                        JLabel.CENTER
                );

        titleLabel.setFont(
                Theme.CARD_TITLE_FONT
        );

        titleLabel.setForeground(
                Theme.TEXT_PRIMARY
        );

        titleLabel.setAlignmentX(
                CENTER_ALIGNMENT
        );

        JLabel algorithmLabel =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                                + algorithm
                                + "</div></html>",
                        JLabel.CENTER
                );

        algorithmLabel.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        12
                )
        );

        algorithmLabel.setForeground(
                accentColor
        );

        algorithmLabel.setAlignmentX(
                CENTER_ALIGNMENT
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                                + description
                                + "</div></html>",
                        JLabel.CENTER
                );

        descriptionLabel.setFont(
                Theme.SMALL_FONT
        );

        descriptionLabel.setForeground(
                Theme.TEXT_SECONDARY
        );

        descriptionLabel.setAlignmentX(
                CENTER_ALIGNMENT
        );

        RoundedButton button =
                new RoundedButton(
                        "Open mission  ♡",
                        buttonColor
                );

        button.setFont(
                Theme.BUTTON_FONT
        );

        button.setForeground(
                Theme.TEXT_PRIMARY
        );

        button.setAlignmentX(
                CENTER_ALIGNMENT
        );

        button.setMaximumSize(
                new java.awt.Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        button.setPreferredSize(
                new java.awt.Dimension(
                        190,
                        48
                )
        );

        button.setHorizontalAlignment(
                JLabel.CENTER
        );

        button.addActionListener(
                e -> navigator.accept(page)
        );

        card.add(missionLabel);

        card.add(
                Box.createVerticalStrut(20)
        );

        card.add(emojiLabel);

        card.add(
                Box.createVerticalStrut(14)
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(8)
        );

        card.add(algorithmLabel);

        card.add(
                Box.createVerticalStrut(18)
        );

        card.add(descriptionLabel);

        card.add(
                Box.createVerticalGlue()
        );

        card.add(button);

        return card;
    }

    private JPanel buildFooter() {

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setOpaque(false);

        JLabel message =
                new JLabel(
                        "♡  Because every node matters"
                );

        message.setFont(
                Theme.SMALL_FONT
        );

        message.setForeground(
                Theme.TEXT_SECONDARY
        );

        JLabel name =
                new JLabel(
                        "The Feline Graph Chronicles"
                );

        name.setFont(
                Theme.SMALL_FONT
        );

        name.setForeground(
                Theme.TEXT_SECONDARY
        );

        footer.add(
                message,
                BorderLayout.WEST
        );

        footer.add(
                name,
                BorderLayout.EAST
        );

        return footer;
    }
}