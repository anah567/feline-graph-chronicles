package mision4.vista;

import app.Arista;
import app.EntradaInvalidaException;
import app.Theme;
import app.ui.RoundedButton;
import app.ui.RoundedPanel;

import mision4.algoritmo.MSTRunner;
import mision4.modelo.GraphCase;
import mision4.modelo.MSTResult;

import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import java.util.Collections;
import java.util.List;

public final class Mission4Panel extends JPanel {

    private static final int MAX_VISUAL_NODES = 100;
    private static final int MAX_VISUAL_EDGES = 300;

    private static final Color HERO_MINT =
            new Color(0xE7, 0xF8, 0xF1);

    private static final Color MINT =
            new Color(0xA9, 0xE4, 0xCC);

    private static final Color MINT_STRONG =
            new Color(0x3F, 0x9A, 0x78);

    private static final Color MINT_DARK =
            new Color(0x2B, 0x7D, 0x60);

    private static final Color MINT_LIGHT =
            new Color(0xD9, 0xF3, 0xE8);

    private static final Color NODE_COLOR =
            new Color(0xE0, 0xF4, 0xEC);

    private static final Color NODE_BORDER =
            new Color(0xA8, 0xDF, 0xCA);

    private static final Color MST_EDGE =
            new Color(0x7E, 0xD0, 0xAE);

    private static final Color DISCARDED_EDGE =
            new Color(0xE9, 0xE3, 0xF5);

    private static final Color WEIGHT_BACKGROUND =
            new Color(0xE1, 0xF5, 0xED);

    private static final String SAMPLE_INPUT =
            "1\n"
                    + "4\n"
                    + "5\n"
                    + "1 2 10\n"
                    + "2 3 20\n"
                    + "3 4 30\n"
                    + "4 1 40\n"
                    + "1 3 15\n";

    private final MSTRunner runner =
            new MSTRunner();

    private final JTextArea inputArea =
            new JTextArea();

    private final JTextArea outputArea =
            new JTextArea();

    private final JComboBox<String> caseSelector =
            new JComboBox<>();

    private final JLabel visualizationMessage =
            new JLabel(
                    "Load a sample or enter a case to begin ♡",
                    SwingConstants.CENTER
            );

    private final JLabel mstStatus =
            new JLabel(
                    "Kruskal's minimum spanning tree",
                    SwingConstants.CENTER
            );

    private final GraphCanvas graphCanvas =
            new GraphCanvas();

    private List<MSTRunner.CaseOutcome> outcomes =
            Collections.emptyList();

    public Mission4Panel() {

        setLayout(
                new BorderLayout(
                        0,
                        18
                )
        );

        setBackground(
                Theme.BACKGROUND
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        22,
                        26,
                        22,
                        26
                )
        );

        add(
                buildHero(),
                BorderLayout.NORTH
        );

        add(
                buildMainContent(),
                BorderLayout.CENTER
        );
    }

    private RoundedPanel buildHero() {

        RoundedPanel hero =
                new RoundedPanel(
                        28,
                        HERO_MINT
                );

        hero.setLayout(
                new BorderLayout()
        );

        hero.setBorder(
                BorderFactory.createEmptyBorder(
                        22,
                        30,
                        22,
                        30
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

        JLabel mission =
                new JLabel(
                        "🌿  MISSION 04"
                );

        mission.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        12
                )
        );

        mission.setForeground(
                MINT_STRONG
        );

        JLabel title =
                new JLabel(
                        "Reconnect Network"
                );

        title.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                Theme.TEXT_PRIMARY
        );

        JLabel algorithm =
                new JLabel(
                        "Kruskal's Algorithm — MST"
                );

        algorithm.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        algorithm.setForeground(
                MINT_STRONG
        );

        JLabel subtitle =
                new JLabel(
                        "Restore the cat network at minimum connection cost ♡"
                );

        subtitle.setFont(
                Theme.SUBTITLE_FONT
        );

        subtitle.setForeground(
                Theme.TEXT_SECONDARY
        );

        information.add(mission);

        information.add(
                Box.createVerticalStrut(7)
        );

        information.add(title);

        information.add(
                Box.createVerticalStrut(5)
        );

        information.add(algorithm);

        information.add(
                Box.createVerticalStrut(10)
        );

        information.add(subtitle);

        JLabel decoration =
                new JLabel();

        java.net.URL imageUrl =
                getClass().getResource(
                        "/images/mission4-cats.png"
                );

        if (imageUrl != null) {

            ImageIcon originalIcon =
                    new ImageIcon(imageUrl);

            Image originalImage =
                    originalIcon.getImage();

            int targetWidth = 180;

            int targetHeight =
                    originalIcon.getIconHeight()
                            * targetWidth
                            / originalIcon.getIconWidth();

            Image scaledImage =
                    originalImage.getScaledInstance(
                            targetWidth,
                            targetHeight,
                            Image.SCALE_SMOOTH
                    );

            decoration.setIcon(
                    new ImageIcon(scaledImage)
            );
        }

        decoration.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        decoration.setVerticalAlignment(
                SwingConstants.CENTER
        );

        hero.add(
                information,
                BorderLayout.CENTER
        );

        hero.add(
                decoration,
                BorderLayout.EAST
        );

        return hero;
    }

    private JPanel buildMainContent() {

        JPanel content =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                18,
                                0
                        )
                );

        content.setOpaque(false);

        content.add(
                buildInputCard()
        );

        content.add(
                buildVisualizationCard()
        );

        return content;
    }

    private RoundedPanel buildInputCard() {

        RoundedPanel card =
                new RoundedPanel(
                        26,
                        Theme.WHITE
                );

        card.setLayout(
                new BorderLayout(
                        0,
                        14
                )
        );

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel title =
                new JLabel(
                        "Input ♡"
                );

        title.setFont(
                Theme.CARD_TITLE_FONT
        );

        title.setForeground(
                MINT_DARK
        );

        card.add(
                title,
                BorderLayout.NORTH
        );

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        center.setOpaque(false);

        JPanel inputContent =
                new JPanel();

        inputContent.setOpaque(false);

        inputContent.setLayout(
                new BoxLayout(
                        inputContent,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel format =
                new JLabel(
                        "Format: T cases · N C · edges (a b cost)",
                        SwingConstants.CENTER
                );

        format.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        13
                )
        );

        format.setForeground(
                Theme.TEXT_SECONDARY
        );

        format.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        format.setAlignmentX(
                CENTER_ALIGNMENT
        );

        format.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        30
                )
        );

        configureInputArea();

        JScrollPane inputScroll =
                new JScrollPane(
                        inputArea
                );

        inputScroll.setBorder(
                BorderFactory.createLineBorder(
                        MINT_LIGHT,
                        2
                )
        );

        inputScroll.setAlignmentX(
                CENTER_ALIGNMENT
        );

        inputContent.add(format);

        inputContent.add(
                Box.createVerticalStrut(8)
        );

        inputContent.add(inputScroll);

        center.add(
                inputContent,
                BorderLayout.CENTER
        );

        JPanel buttons =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                10,
                                0
                        )
                );

        buttons.setOpaque(false);

        RoundedButton loadButton =
                new RoundedButton(
                        "Load sample",
                        MINT_LIGHT,
                        MINT
                );

        loadButton.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        loadButton.addActionListener(
                e -> loadSample()
        );

        RoundedButton clearButton =
                new RoundedButton(
                        "Clear",
                        new Color(
                                0xF1,
                                0xFA,
                                0xF6
                        ),
                        MINT_LIGHT
                );

        clearButton.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        clearButton.addActionListener(
                e -> clearAll()
        );

        buttons.add(loadButton);
        buttons.add(clearButton);

        center.add(
                buttons,
                BorderLayout.SOUTH
        );

        card.add(
                center,
                BorderLayout.CENTER
        );

        JPanel bottom =
                new JPanel();

        bottom.setOpaque(false);

        bottom.setLayout(
                new BoxLayout(
                        bottom,
                        BoxLayout.Y_AXIS
                )
        );

        RoundedButton solveButton =
                new RoundedButton(
                        "Solve mission  ♡",
                        MINT,
                        new Color(
                                0x8D,
                                0xD8,
                                0xBA
                        )
                );

        solveButton.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        solveButton.setAlignmentX(
                CENTER_ALIGNMENT
        );

        solveButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        46
                )
        );

        solveButton.addActionListener(
                e -> solveMission()
        );

        JLabel resultsTitle =
                new JLabel(
                        "✦ Results",
                        SwingConstants.CENTER
                );

        resultsTitle.setFont(
                Theme.CARD_TITLE_FONT
        );

        resultsTitle.setForeground(
                Theme.TEXT_PRIMARY
        );

        resultsTitle.setAlignmentX(
                CENTER_ALIGNMENT
        );

        configureOutputArea();

        JScrollPane outputScroll =
                new JScrollPane(
                        outputArea
                );

        outputScroll.setBorder(
                BorderFactory.createLineBorder(
                        MINT_LIGHT,
                        1
                )
        );

        outputScroll.setPreferredSize(
                new Dimension(
                        100,
                        100
                )
        );

        bottom.add(solveButton);

        bottom.add(
                Box.createVerticalStrut(16)
        );

        bottom.add(resultsTitle);

        bottom.add(
                Box.createVerticalStrut(8)
        );

        bottom.add(outputScroll);

        card.add(
                bottom,
                BorderLayout.SOUTH
        );

        return card;
    }

    private RoundedPanel buildVisualizationCard() {

        RoundedPanel card =
                new RoundedPanel(
                        26,
                        Theme.WHITE
                );

        card.setLayout(
                new BorderLayout(
                        0,
                        12
                )
        );

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JPanel header =
                new JPanel();

        header.setOpaque(false);

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Visualization ♡",
                        SwingConstants.CENTER
                );

        title.setFont(
                Theme.CARD_TITLE_FONT
        );

        title.setForeground(
                MINT_DARK
        );

        title.setAlignmentX(
                CENTER_ALIGNMENT
        );

        header.add(title);

        header.add(
                Box.createVerticalStrut(12)
        );

        header.add(
                buildLegend()
        );

        header.add(
                Box.createVerticalStrut(8)
        );

        mstStatus.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        mstStatus.setForeground(
                Theme.TEXT_SECONDARY
        );

        mstStatus.setAlignmentX(
                CENTER_ALIGNMENT
        );

        header.add(
                mstStatus
        );

        caseSelector.setFont(
                Theme.SMALL_FONT
        );

        caseSelector.setVisible(false);

        caseSelector.addActionListener(
                e -> updateVisualization()
        );

        JPanel casePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                0,
                                2
                        )
                );

        casePanel.setOpaque(false);

        casePanel.add(
                caseSelector
        );

        header.add(casePanel);

        card.add(
                header,
                BorderLayout.NORTH
        );

        JPanel visualizationArea =
                new JPanel(
                        new BorderLayout()
                );

        visualizationArea.setOpaque(false);

        visualizationMessage.setFont(
                Theme.SMALL_FONT
        );

        visualizationMessage.setForeground(
                Theme.TEXT_SECONDARY
        );

        visualizationArea.add(
                visualizationMessage,
                BorderLayout.NORTH
        );

        JScrollPane graphScroll =
                new JScrollPane(
                        graphCanvas
                );

        graphScroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        graphScroll.getViewport().setBackground(
                Theme.WHITE
        );

        visualizationArea.add(
                graphScroll,
                BorderLayout.CENTER
        );

        card.add(
                visualizationArea,
                BorderLayout.CENTER
        );

        return card;
    }

    private JPanel buildLegend() {

        JPanel legend =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                18,
                                4
                        )
                );

        legend.setOpaque(false);

        JLabel highlighted =
                new JLabel(
                        "✦ MST highlighted"
                );

        highlighted.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        15
                )
        );

        highlighted.setForeground(
                MINT_DARK
        );

        JLabel mstEdge =
                new JLabel(
                        "━  MST edge"
                );

        mstEdge.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        15
                )
        );

        mstEdge.setForeground(
                MINT_STRONG
        );

        JLabel discarded =
                new JLabel(
                        "─  Discarded"
                );

        discarded.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        15
                )
        );

        discarded.setForeground(
                new Color(
                        0xA8,
                        0x9B,
                        0xC7
                )
        );

        legend.add(highlighted);
        legend.add(mstEdge);
        legend.add(discarded);

        return legend;
    }

    private void configureInputArea() {

        inputArea.setFont(
                Theme.MONO_FONT
        );

        inputArea.setForeground(
                Theme.TEXT_PRIMARY
        );

        inputArea.setBackground(
                new Color(
                        0xF9,
                        0xFD,
                        0xFB
                )
        );

        inputArea.setCaretColor(
                Theme.TEXT_PRIMARY
        );

        inputArea.setLineWrap(false);

        inputArea.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );
    }

    private void configureOutputArea() {

        outputArea.setEditable(false);

        outputArea.setFont(
                Theme.MONO_FONT
        );

        outputArea.setForeground(
                Theme.TEXT_PRIMARY
        );

        outputArea.setBackground(
                HERO_MINT
        );

        outputArea.setLineWrap(true);

        outputArea.setWrapStyleWord(true);

        outputArea.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );
    }

    private void loadSample() {

        inputArea.setText(
                SAMPLE_INPUT
        );

        outputArea.setText("");

        outcomes =
                Collections.emptyList();

        caseSelector.removeAllItems();

        caseSelector.setVisible(false);

        graphCanvas.clear();

        mstStatus.setText(
                "Kruskal's minimum spanning tree"
        );

        visualizationMessage.setText(
                "Sample loaded. Press Solve mission ♡"
        );
    }

    private void clearAll() {

        inputArea.setText("");
        outputArea.setText("");

        outcomes =
                Collections.emptyList();

        caseSelector.removeAllItems();

        caseSelector.setVisible(false);

        graphCanvas.clear();

        mstStatus.setText(
                "Kruskal's minimum spanning tree"
        );

        visualizationMessage.setText(
                "Load a sample or enter a case to begin ♡"
        );
    }

    private void solveMission() {

        String rawInput =
                inputArea.getText();

        if (rawInput == null
                || rawInput.trim().isEmpty()) {

            showError(
                    "Please enter a mission case first."
            );

            return;
        }

        try {

            outcomes =
                    runner.run(rawInput);

            if (outcomes.isEmpty()) {

                showError(
                        "No cases were found in the input."
                );

                return;
            }

            StringBuilder resultText =
                    new StringBuilder();

            caseSelector.removeAllItems();

            for (int i = 0;
                 i < outcomes.size();
                 i++) {

                MSTRunner.CaseOutcome outcome =
                        outcomes.get(i);

                resultText
                        .append(outcome.line)
                        .append('\n');

                caseSelector.addItem(
                        "Case #" + (i + 1)
                );
            }

            outputArea.setText(
                    resultText.toString()
            );

            caseSelector.setVisible(
                    outcomes.size() > 1
            );

            caseSelector.setSelectedIndex(0);

            updateVisualization();

        } catch (EntradaInvalidaException ex) {

            showError(
                    ex.getMessage()
            );

        } catch (RuntimeException ex) {

            showError(
                    ex.getMessage() == null
                            ? "The mission could not be solved."
                            : ex.getMessage()
            );
        }
    }

    private void updateVisualization() {

        if (outcomes.isEmpty()) {

            graphCanvas.clear();

            return;
        }

        int selectedIndex =
                caseSelector.getSelectedIndex();

        if (selectedIndex < 0) {
            selectedIndex = 0;
        }

        MSTRunner.CaseOutcome outcome =
                outcomes.get(
                        selectedIndex
                );

        GraphCase testCase =
                outcome.testCase;

        MSTResult result =
                outcome.result;

        if (testCase.getN()
                > MAX_VISUAL_NODES
                || testCase.getEdges().size()
                > MAX_VISUAL_EDGES) {

            graphCanvas.clear();

            visualizationMessage.setText(
                    "Case solved ♡ Graph is too large to visualize."
            );

            return;
        }

        graphCanvas.showGraph(
                testCase,
                result
        );

        if (result.isConnected()) {

            mstStatus.setText(
                    "✦ MST highlighted"
            );

            mstStatus.setForeground(
                    MINT_DARK
            );

            visualizationMessage.setText(
                    "Minimum connection cost: "
                            + result.getCost()
                            + " ♡"
            );

        } else {

            mstStatus.setText(
                    "Network disconnected"
            );

            mstStatus.setForeground(
                    new Color(
                            0xD0,
                            0x77,
                            0x77
                    )
            );

            visualizationMessage.setText(
                    "Limon cut too many cables ♡"
            );
        }
    }

    private void showError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Mission 4",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private static final class GraphCanvas
            extends JPanel {

        private GraphCase testCase;
        private MSTResult result;

        private int[] xPositions;
        private int[] yPositions;

        private GraphCanvas() {

            setOpaque(true);

            setBackground(
                    Theme.WHITE
            );

            setPreferredSize(
                    new Dimension(
                            500,
                            380
                    )
            );

            setBorder(
                    BorderFactory.createEmptyBorder(
                            14,
                            14,
                            14,
                            14
                    )
            );
        }

        private void showGraph(
                GraphCase testCase,
                MSTResult result) {

            this.testCase =
                    testCase;

            this.result =
                    result;

            calculatePositions();

            repaint();
        }

        private void clear() {

            testCase = null;
            result = null;

            xPositions = null;
            yPositions = null;

            repaint();
        }

        private void calculatePositions() {

            if (testCase == null) {
                return;
            }

            int n =
                    testCase.getN();

            xPositions =
                    new int[n + 1];

            yPositions =
                    new int[n + 1];

            int width =
                    Math.max(
                            getWidth(),
                            460
                    );

            int height =
                    Math.max(
                            getHeight(),
                            340
                    );

            int centerX =
                    width / 2;

            int centerY =
                    height / 2;

            int radiusX =
                    Math.max(
                            80,
                            width / 2 - 70
                    );

            int radiusY =
                    Math.max(
                            80,
                            height / 2 - 70
                    );

            if (n == 1) {

                xPositions[1] =
                        centerX;

                yPositions[1] =
                        centerY;

                return;
            }

            for (int node = 1;
                 node <= n;
                 node++) {

                double angle =
                        -Math.PI / 2
                                + 2.0
                                * Math.PI
                                * (node - 1)
                                / n;

                xPositions[node] =
                        centerX
                                + (int) (
                                radiusX
                                        * Math.cos(angle)
                        );

                yPositions[node] =
                        centerY
                                + (int) (
                                radiusY
                                        * Math.sin(angle)
                        );
            }
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            super.paintComponent(g);

            if (testCase == null
                    || result == null) {

                return;
            }

            calculatePositions();

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            paintDiscardedEdges(g2);
            paintMSTEdges(g2);
            paintNodes(g2);

            g2.dispose();
        }

        private void paintDiscardedEdges(
                Graphics2D g2) {

            for (Arista edge
                    : testCase.getEdges()) {

                if (isSelected(edge)) {
                    continue;
                }

                paintEdge(
                        g2,
                        edge,
                        false
                );
            }
        }

        private void paintMSTEdges(
                Graphics2D g2) {

            for (Arista edge
                    : result.getSelectedEdges()) {

                paintEdge(
                        g2,
                        edge,
                        true
                );
            }
        }

        private void paintEdge(
                Graphics2D g2,
                Arista edge,
                boolean selected) {

            int a =
                    edge.getOrigen();

            int b =
                    edge.getDestino();

            int x1 =
                    xPositions[a];

            int y1 =
                    yPositions[a];

            int x2 =
                    xPositions[b];

            int y2 =
                    yPositions[b];

            if (selected) {

                g2.setColor(
                        MST_EDGE
                );

                g2.setStroke(
                        new BasicStroke(
                                4f,
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_ROUND
                        )
                );

            } else {

                g2.setColor(
                        DISCARDED_EDGE
                );

                g2.setStroke(
                        new BasicStroke(
                                1.5f,
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_ROUND
                        )
                );
            }

            g2.drawLine(
                    x1,
                    y1,
                    x2,
                    y2
            );

            paintWeight(
                    g2,
                    edge,
                    selected
            );
        }

        private void paintWeight(
                Graphics2D g2,
                Arista edge,
                boolean selected) {

            int a =
                    edge.getOrigen();

            int b =
                    edge.getDestino();

            int x =
                    (
                            xPositions[a]
                                    + xPositions[b]
                    ) / 2;

            int y =
                    (
                            yPositions[a]
                                    + yPositions[b]
                    ) / 2;

            String text =
                    String.valueOf(
                            edge.getPeso()
                    );

            g2.setFont(
                    new Font(
                            Theme.FONT_FAMILY,
                            Font.BOLD,
                            12
                    )
            );

            int width =
                    g2.getFontMetrics()
                            .stringWidth(text);

            if (selected) {

                g2.setColor(
                        WEIGHT_BACKGROUND
                );

                g2.fillRoundRect(
                        x - width / 2 - 6,
                        y - 10,
                        width + 12,
                        20,
                        10,
                        10
                );

                g2.setColor(
                        MINT_DARK
                );

            } else {

                g2.setColor(
                        new Color(
                                0xA9,
                                0x9C,
                                0xC4
                        )
                );
            }

            g2.drawString(
                    text,
                    x - width / 2,
                    y + 5
            );
        }

        private void paintNodes(
                Graphics2D g2) {

            int diameter = 54;

            for (int node = 1;
                 node <= testCase.getN();
                 node++) {

                int x =
                        xPositions[node]
                                - diameter / 2;

                int y =
                        yPositions[node]
                                - diameter / 2;

                g2.setColor(
                        NODE_COLOR
                );

                g2.fillOval(
                        x,
                        y,
                        diameter,
                        diameter
                );

                g2.setColor(
                        NODE_BORDER
                );

                g2.setStroke(
                        new BasicStroke(
                                2.5f
                        )
                );

                g2.drawOval(
                        x,
                        y,
                        diameter,
                        diameter
                );

                String text =
                        String.valueOf(node);

                g2.setFont(
                        new Font(
                                Theme.FONT_FAMILY,
                                Font.BOLD,
                                16
                        )
                );

                g2.setColor(
                        MINT_DARK
                );

                int textWidth =
                        g2.getFontMetrics()
                                .stringWidth(text);

                int ascent =
                        g2.getFontMetrics()
                                .getAscent();

                g2.drawString(
                        text,
                        xPositions[node]
                                - textWidth / 2,
                        yPositions[node]
                                + ascent / 3
                );
            }
        }

        private boolean isSelected(
                Arista edge) {

            for (Arista selected
                    : result.getSelectedEdges()) {

                boolean sameDirection =
                        edge.getOrigen()
                                == selected.getOrigen()
                                && edge.getDestino()
                                == selected.getDestino();

                boolean reverseDirection =
                        edge.getOrigen()
                                == selected.getDestino()
                                && edge.getDestino()
                                == selected.getOrigen();

                if ((sameDirection
                        || reverseDirection)
                        && edge.getPeso()
                        == selected.getPeso()) {

                    return true;
                }
            }

            return false;
        }
    }
}