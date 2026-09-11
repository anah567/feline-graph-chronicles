package mision2.vista;

import app.Arista;
import app.EntradaInvalidaException;
import app.Theme;
import app.ui.RoundedButton;
import app.ui.RoundedPanel;
import mision2.algoritmo.DijkstraRunner;
import mision2.modelo.DijkstraResult;
import mision2.modelo.GraphCase;
import mision2.modelo.UndirectedGraph;

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
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Mission2Panel extends JPanel {

    private static final int MAX_VISUAL_NODES = 60;

    private static final Color HERO_PURPLE =
            new Color(0xF1, 0xEC, 0xFF);

    private static final Color PURPLE =
            new Color(0x8F, 0x7A, 0xD6);

    private static final Color PURPLE_STRONG =
            new Color(0x6F, 0x58, 0xB5);

    private static final Color PURPLE_BUTTON =
            new Color(0xC9, 0xBB, 0xF7);

    private static final Color PURPLE_LIGHT =
            new Color(0xE9, 0xE2, 0xFF);

    private static final Color START_COLOR =
            new Color(0xC9, 0xED, 0xDD);

    private static final Color END_COLOR =
            new Color(0xFF, 0xD5, 0xE0);

    private static final Color NORMAL_NODE =
            new Color(0xED, 0xE7, 0xFF);

    private static final Color NORMAL_EDGE =
            new Color(0xDF, 0xD9, 0xF3);

    private static final Color PATH_NODE =
            new Color(0xD7, 0xCB, 0xFA);

    private static final Color PATH_EDGE =
            new Color(0xA8, 0x91, 0xE5);

    private static final String SAMPLE_INPUT =
            "3\n"
                    + "2 1 0 1\n"
                    + "0 1 100\n"
                    + "3 3 2 0\n"
                    + "0 1 100\n"
                    + "0 2 200\n"
                    + "1 2 50\n"
                    + "2 0 0 1\n";

    private final DijkstraRunner runner =
            new DijkstraRunner();

    private final JTextArea inputArea =
            new JTextArea();

    private final JTextArea outputArea =
            new JTextArea();

    private final JLabel visualizationMessage =
            new JLabel(
                    "Load a sample or enter a graph ♡",
                    SwingConstants.CENTER
            );

    private final JLabel pathInformation =
            new JLabel(
                    "Shortest path will appear here",
                    SwingConstants.CENTER
            );

    private final JComboBox<String> caseSelector =
            new JComboBox<>();

    private final GraphCanvas graphCanvas =
            new GraphCanvas();

    private List<DijkstraRunner.CaseOutcome> outcomes =
            new ArrayList<>();

    public Mission2Panel() {

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

    private JPanel buildHero() {

        RoundedPanel hero =
                new RoundedPanel(
                        28,
                        HERO_PURPLE
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

        JPanel textPanel =
                new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel mission =
                new JLabel(
                        "🌷  MISSION 02"
                );

        mission.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        12
                )
        );

        mission.setForeground(
                PURPLE
        );

        JLabel title =
                new JLabel(
                        "Claude Accounts"
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
                        "Dijkstra's Algorithm"
                );

        algorithm.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        algorithm.setForeground(
                PURPLE
        );

        JLabel description =
                new JLabel(
                        "Minerva finds the shortest and safest path ♡"
                );

        description.setFont(
                Theme.SUBTITLE_FONT
        );

        description.setForeground(
                Theme.TEXT_SECONDARY
        );

        textPanel.add(mission);
        textPanel.add(
                Box.createVerticalStrut(8)
        );
        textPanel.add(title);
        textPanel.add(
                Box.createVerticalStrut(5)
        );
        textPanel.add(algorithm);
        textPanel.add(
                Box.createVerticalStrut(10)
        );
        textPanel.add(description);

        JLabel decoration =
                new JLabel(
                        "🌷",
                        SwingConstants.CENTER
                );

        decoration.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        34
                )
        );

        hero.add(
                textPanel,
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

    private JPanel buildInputCard() {

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
                Theme.TITLE_FONT
        );

        title.setForeground(
                PURPLE
        );

        card.add(
                title,
                BorderLayout.NORTH
        );

        JPanel center =
                new JPanel();

        center.setOpaque(false);

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel format =
                new JLabel(
                        "Format: T cases · N C source destination · edges (a b weight)",
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

        format.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        center.add(format);

        center.add(
                Box.createVerticalStrut(10)
        );

        Theme.styleTextArea(
                inputArea
        );

        inputArea.setLineWrap(false);

        inputArea.setBorder(
                BorderFactory.createEmptyBorder(
                        14,
                        14,
                        14,
                        14
                )
        );

        JScrollPane inputScroll =
                new JScrollPane(
                        inputArea
                );

        inputScroll.setBorder(
                BorderFactory.createLineBorder(
                        PURPLE_LIGHT,
                        2
                )
        );

        inputScroll.setAlignmentX(
                CENTER_ALIGNMENT
        );

        inputScroll.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                )
        );

        inputScroll.setPreferredSize(
                new Dimension(
                        450,
                        300
                )
        );

        center.add(
                inputScroll
        );

        center.add(
                Box.createVerticalStrut(12)
        );

        JPanel secondaryButtons =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                10,
                                0
                        )
                );

        secondaryButtons.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        secondaryButtons.setAlignmentX(
                CENTER_ALIGNMENT
        );

        secondaryButtons.setOpaque(false);

        RoundedButton loadButton =
                new RoundedButton(
                        "Load sample",
                        PURPLE_LIGHT,
                        PURPLE_BUTTON
                );

        RoundedButton clearButton =
                new RoundedButton(
                        "Clear",
                        new Color(
                                0xF6,
                                0xF2,
                                0xFF
                        ),
                        PURPLE_LIGHT
                );

        loadButton.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        clearButton.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        loadButton.addActionListener(
                e -> loadSample()
        );

        clearButton.addActionListener(
                e -> clearAll()
        );

        secondaryButtons.add(
                loadButton
        );

        secondaryButtons.add(
                clearButton
        );

        center.add(
                secondaryButtons
        );

        center.add(
                Box.createVerticalStrut(12)
        );

        RoundedButton solveButton =
                new RoundedButton(
                        "Solve mission ♡",
                        PURPLE_BUTTON,
                        new Color(
                                0xB8,
                                0xA5,
                                0xF0
                        )
                );

        solveButton.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        15
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

        center.add(
                solveButton
        );

        card.add(
                center,
                BorderLayout.CENTER
        );

        JPanel results =
                new JPanel();

        results.setOpaque(false);

        results.setLayout(
                new BoxLayout(
                        results,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel resultsTitle =
                new JLabel(
                        "✦ Results",
                        SwingConstants.CENTER
                );

        resultsTitle.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        16
                )
        );

        resultsTitle.setForeground(
                Theme.TEXT_PRIMARY
        );

        resultsTitle.setAlignmentX(
                CENTER_ALIGNMENT
        );

        results.add(
                resultsTitle
        );

        results.add(
                Box.createVerticalStrut(8)
        );

        outputArea.setEditable(false);

        outputArea.setLineWrap(true);

        outputArea.setWrapStyleWord(true);

        outputArea.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        14
                )
        );

        outputArea.setForeground(
                Theme.TEXT_PRIMARY
        );

        outputArea.setBackground(
                HERO_PURPLE
        );

        outputArea.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        12,
                        10,
                        12
                )
        );

        JScrollPane outputScroll =
                new JScrollPane(
                        outputArea
                );

        outputScroll.setBorder(
                BorderFactory.createLineBorder(
                        PURPLE_LIGHT
                )
        );

        outputScroll.setPreferredSize(
                new Dimension(
                        100,
                        100
                )
        );

        outputScroll.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        90
                )
        );

        results.add(
                outputScroll
        );

        card.add(
                results,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel buildVisualizationCard() {

        RoundedPanel card =
                new RoundedPanel(
                        26,
                        Theme.WHITE
                );

        card.setLayout(
                new BorderLayout(
                        0,
                        10
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
                Theme.TITLE_FONT
        );

        title.setForeground(
                PURPLE
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
                Box.createVerticalStrut(10)
        );

        pathInformation.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        14
                )
        );

        pathInformation.setForeground(
                Theme.TEXT_SECONDARY
        );

        pathInformation.setAlignmentX(
                CENTER_ALIGNMENT
        );

        header.add(
                pathInformation
        );

        header.add(
                Box.createVerticalStrut(8)
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
                                0
                        )
                );

        casePanel.setOpaque(false);

        casePanel.add(
                caseSelector
        );

        header.add(
                casePanel
        );

        card.add(
                header,
                BorderLayout.NORTH
        );

        graphCanvas.setBackground(
                Theme.WHITE
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

        card.add(
                graphScroll,
                BorderLayout.CENTER
        );

        visualizationMessage.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        14
                )
        );

        visualizationMessage.setForeground(
                Theme.TEXT_SECONDARY
        );

        card.add(
                visualizationMessage,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel buildLegend() {

        JPanel legend =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                14,
                                3
                        )
                );

        legend.setOpaque(false);

        legend.add(
                createLegendItem(
                        "Start",
                        START_COLOR
                )
        );

        legend.add(
                createLegendItem(
                        "End",
                        END_COLOR
                )
        );

        legend.add(
                createLegendItem(
                        "On path",
                        PATH_NODE
                )
        );

        JPanel edgeItem =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                6,
                                0
                        )
                );

        edgeItem.setOpaque(false);

        JLabel line =
                new JLabel("━");

        line.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        20
                )
        );

        line.setForeground(
                PATH_EDGE
        );

        JLabel text =
                new JLabel(
                        "Path edge"
                );

        text.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        14
                )
        );

        text.setForeground(
                Theme.TEXT_SECONDARY
        );

        edgeItem.add(line);
        edgeItem.add(text);

        legend.add(edgeItem);

        return legend;
    }

    private JPanel createLegendItem(
            String text,
            Color color) {

        JPanel item =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                6,
                                0
                        )
                );

        item.setOpaque(false);

        JLabel circle =
                new JLabel("●");

        circle.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        18
                )
        );

        circle.setForeground(color);

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        14
                )
        );

        label.setForeground(
                Theme.TEXT_SECONDARY
        );

        item.add(circle);
        item.add(label);

        return item;
    }

    private void loadSample() {

        inputArea.setText(
                SAMPLE_INPUT
        );

        outputArea.setText("");

        outcomes.clear();

        caseSelector.removeAllItems();

        caseSelector.setVisible(false);

        graphCanvas.clearGraph();

        pathInformation.setText(
                "Shortest path will appear here"
        );

        visualizationMessage.setText(
                "Sample loaded. Press Solve mission ♡"
        );
    }

    private void clearAll() {

        inputArea.setText("");

        outputArea.setText("");

        outcomes.clear();

        caseSelector.removeAllItems();

        caseSelector.setVisible(false);

        graphCanvas.clearGraph();

        pathInformation.setText(
                "Shortest path will appear here"
        );

        visualizationMessage.setText(
                "Load a sample or enter a graph ♡"
        );
    }

    private void solveMission() {

        try {

            outcomes =
                    runner.run(
                            inputArea.getText()
                    );

            if (outcomes.isEmpty()) {

                outputArea.setText(
                        "No cases found."
                );

                graphCanvas.clearGraph();

                return;
            }

            StringBuilder resultText =
                    new StringBuilder();

            for (DijkstraRunner.CaseOutcome outcome
                    : outcomes) {

                resultText
                        .append(outcome.line)
                        .append('\n');
            }

            outputArea.setText(
                    resultText.toString()
            );

            caseSelector.removeAllItems();

            for (int i = 0;
                 i < outcomes.size();
                 i++) {

                caseSelector.addItem(
                        "Case #" + (i + 1)
                );
            }

            caseSelector.setVisible(
                    outcomes.size() > 1
            );

            caseSelector.setSelectedIndex(0);

            updateVisualization();

        } catch (EntradaInvalidaException e) {

            showError(
                    e.getMessage()
            );

        } catch (RuntimeException e) {

            showError(
                    e.getMessage() == null
                            ? "Ocurrió un error inesperado"
                            : e.getMessage()
            );
        }
    }

    private void updateVisualization() {

        if (outcomes.isEmpty()) {
            return;
        }

        int index =
                caseSelector.getSelectedIndex();

        if (index < 0) {
            index = 0;
        }

        DijkstraRunner.CaseOutcome outcome =
                outcomes.get(index);

        GraphCase testCase =
                outcome.testCase;

        DijkstraResult result =
                outcome.result;

        UndirectedGraph graph =
                testCase.getGraph();

        if (graph.getN()
                > MAX_VISUAL_NODES) {

            graphCanvas.clearGraph();

            visualizationMessage.setText(
                    "Solved correctly, but graphs with more than "
                            + MAX_VISUAL_NODES
                            + " nodes are not visualized ♡"
            );

            if (result.isReachable()) {

                pathInformation.setText(
                        "Minimum cost: "
                                + result.getCost()
                );

            } else {

                pathInformation.setText(
                        "Destination unreachable"
                );
            }

            return;
        }

        graphCanvas.setGraph(
                graph,
                testCase.getSource(),
                testCase.getDestination(),
                result
        );

        if (!result.isReachable()) {

            pathInformation.setText(
                    "Nina is very sad"
            );

            visualizationMessage.setText(
                    "No path connects the start and destination."
            );

            return;
        }

        pathInformation.setText(
                "Shortest path: "
                        + formatPath(
                        result.getPath()
                )
                        + "   ·   Cost: "
                        + result.getCost()
        );

        visualizationMessage.setText(
                "✦ Shortest path highlighted in purple"
        );
    }

    private String formatPath(
            List<Integer> path) {

        StringBuilder text =
                new StringBuilder();

        for (int i = 0;
             i < path.size();
             i++) {

            if (i > 0) {
                text.append(" → ");
            }

            text.append(
                    path.get(i)
            );
        }

        return text.toString();
    }

    private void showError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Mission 2",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private static final class GraphCanvas
            extends JPanel {

        private UndirectedGraph graph;

        private int source = -1;
        private int destination = -1;

        private DijkstraResult result;

        private int[] xPositions;
        private int[] yPositions;

        private GraphCanvas() {

            setOpaque(true);

            setPreferredSize(
                    new Dimension(
                            620,
                            500
                    )
            );
        }

        private void setGraph(
                UndirectedGraph graph,
                int source,
                int destination,
                DijkstraResult result) {

            this.graph = graph;
            this.source = source;
            this.destination = destination;
            this.result = result;

            calculatePositions();

            repaint();
        }

        private void clearGraph() {

            graph = null;
            result = null;

            source = -1;
            destination = -1;

            xPositions = null;
            yPositions = null;

            repaint();
        }

        private void calculatePositions() {

            int n =
                    graph.getN();

            xPositions =
                    new int[n];

            yPositions =
                    new int[n];

            int width =
                    Math.max(
                            getWidth(),
                            560
                    );

            int height =
                    Math.max(
                            getHeight(),
                            420
                    );

            int centerX =
                    width / 2;

            int centerY =
                    height / 2;

            int radiusX =
                    Math.max(
                            100,
                            width / 2 - 80
                    );

            int radiusY =
                    Math.max(
                            100,
                            height / 2 - 80
                    );

            if (n == 1) {

                xPositions[0] =
                        centerX;

                yPositions[0] =
                        centerY;

                return;
            }

            for (int i = 0;
                 i < n;
                 i++) {

                double angle =
                        -Math.PI / 2
                                + 2.0
                                * Math.PI
                                * i
                                / n;

                xPositions[i] =
                        centerX
                                + (int) (
                                radiusX
                                        * Math.cos(angle)
                        );

                yPositions[i] =
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

            if (graph == null) {
                return;
            }

            calculatePositions();

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            paintEdges(g2);

            paintNodes(g2);

            g2.dispose();
        }

        private void paintEdges(
                Graphics2D g2) {

            Set<String> drawn =
                    new HashSet<>();

            int n =
                    graph.getN();

            for (int node = 0;
                 node < n;
                 node++) {

                for (Arista edge
                        : graph.getNeighbors(node)) {

                    int a =
                            edge.getOrigen();

                    int b =
                            edge.getDestino();

                    int min =
                            Math.min(a, b);

                    int max =
                            Math.max(a, b);

                    String key =
                            min
                                    + "-"
                                    + max
                                    + "-"
                                    + edge.getPeso();

                    if (!drawn.add(key)) {
                        continue;
                    }

                    boolean pathEdge =
                            isPathEdge(
                                    a,
                                    b
                            );

                    Stroke oldStroke =
                            g2.getStroke();

                    if (pathEdge) {

                        g2.setColor(
                                PATH_EDGE
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
                                NORMAL_EDGE
                        );

                        g2.setStroke(
                                new BasicStroke(
                                        2f,
                                        BasicStroke.CAP_ROUND,
                                        BasicStroke.JOIN_ROUND
                                )
                        );
                    }

                    g2.drawLine(
                            xPositions[a],
                            yPositions[a],
                            xPositions[b],
                            yPositions[b]
                    );

                    g2.setStroke(
                            oldStroke
                    );

                    paintWeight(
                            g2,
                            a,
                            b,
                            edge.getPeso(),
                            pathEdge
                    );
                }
            }
        }

        private void paintWeight(
                Graphics2D g2,
                int a,
                int b,
                long weight,
                boolean pathEdge) {

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
                            weight
                    );

            g2.setFont(
                    new Font(
                            Theme.FONT_FAMILY,
                            Font.BOLD,
                            12
                    )
            );

            int textWidth =
                    g2.getFontMetrics()
                            .stringWidth(text);

            g2.setColor(
                    Theme.WHITE
            );

            g2.fillRoundRect(
                    x - textWidth / 2 - 5,
                    y - 10,
                    textWidth + 10,
                    20,
                    10,
                    10
            );

            g2.setColor(
                    pathEdge
                            ? PURPLE_STRONG
                            : Theme.TEXT_SECONDARY
            );

            g2.drawString(
                    text,
                    x - textWidth / 2,
                    y + 5
            );
        }

        private void paintNodes(
                Graphics2D g2) {

            int n =
                    graph.getN();

            int diameter = 52;

            for (int node = 0;
                 node < n;
                 node++) {

                int x =
                        xPositions[node]
                                - diameter / 2;

                int y =
                        yPositions[node]
                                - diameter / 2;

                Color fillColor =
                        getNodeColor(
                                node
                        );

                g2.setColor(
                        fillColor
                );

                g2.fillOval(
                        x,
                        y,
                        diameter,
                        diameter
                );

                g2.setColor(
                        getNodeBorderColor(
                                node
                        )
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

                String number =
                        String.valueOf(
                                node
                        );

                g2.setFont(
                        new Font(
                                Theme.FONT_FAMILY,
                                Font.BOLD,
                                15
                        )
                );

                g2.setColor(
                        Theme.TEXT_PRIMARY
                );

                int textWidth =
                        g2.getFontMetrics()
                                .stringWidth(number);

                int textHeight =
                        g2.getFontMetrics()
                                .getAscent();

                g2.drawString(
                        number,
                        xPositions[node]
                                - textWidth / 2,
                        yPositions[node]
                                + textHeight / 3
                );

                paintNodeRole(
                        g2,
                        node,
                        diameter
                );
            }
        }

        private void paintNodeRole(
                Graphics2D g2,
                int node,
                int diameter) {

            String role = null;

            if (node == source) {
                role = "Start";
            }

            if (node == destination) {
                role = "End";
            }

            if (role == null) {
                return;
            }

            g2.setFont(
                    new Font(
                            Theme.FONT_FAMILY,
                            Font.PLAIN,
                            11
                    )
            );

            g2.setColor(
                    Theme.TEXT_SECONDARY
            );

            int width =
                    g2.getFontMetrics()
                            .stringWidth(role);

            g2.drawString(
                    role,
                    xPositions[node]
                            - width / 2,
                    yPositions[node]
                            + diameter / 2
                            + 17
            );
        }

        private Color getNodeColor(
                int node) {

            if (node == source) {
                return START_COLOR;
            }

            if (node == destination) {
                return END_COLOR;
            }

            if (isNodeOnPath(node)) {
                return PATH_NODE;
            }

            return NORMAL_NODE;
        }

        private Color getNodeBorderColor(
                int node) {

            if (node == source) {
                return new Color(
                        0x7C,
                        0xCE,
                        0xAA
                );
            }

            if (node == destination) {
                return new Color(
                        0xF4,
                        0xA9,
                        0xB7
                );
            }

            if (isNodeOnPath(node)) {
                return PURPLE;
            }

            return new Color(
                    0xD1,
                    0xC4,
                    0xF5
            );
        }

        private boolean isNodeOnPath(
                int node) {

            return result != null
                    && result.isReachable()
                    && result.getPath()
                    .contains(node);
        }

        private boolean isPathEdge(
                int a,
                int b) {

            if (result == null
                    || !result.isReachable()) {

                return false;
            }

            List<Integer> path =
                    result.getPath();

            for (int i = 0;
                 i < path.size() - 1;
                 i++) {

                int first =
                        path.get(i);

                int second =
                        path.get(i + 1);

                if ((first == a
                        && second == b)
                        || (first == b
                        && second == a)) {

                    return true;
                }
            }

            return false;
        }
    }
}