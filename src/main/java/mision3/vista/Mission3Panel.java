package mision3.vista;

import app.Arista;
import app.EntradaInvalidaException;
import app.Theme;
import app.ui.RoundedButton;
import app.ui.RoundedPanel;

import mision3.algoritmo.ChurunRunner;
import mision3.modelo.ChurunResult;
import mision3.modelo.DirectedGraph;
import mision3.modelo.GraphCase;
import mision3.algoritmo.FloydWarshall;
import mision3.modelo.FloydWarshallResult;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.RenderingHints;

import java.util.Collections;
import java.util.List;

public final class Mission3Panel extends JPanel {

    private static final int MAX_VISUAL_NODES = 60;

    private static final Color HERO_YELLOW =
            new Color(0xFF, 0xF8, 0xDC);

    private static final Color YELLOW =
            new Color(0xF5, 0xD9, 0x76);

    private static final Color YELLOW_STRONG =
            new Color(0xB8, 0x84, 0x18);

    private static final Color YELLOW_LIGHT =
            new Color(0xFF, 0xF4, 0xC7);

    private static final Color START_COLOR =
            new Color(0xC9, 0xED, 0xDD);

    private static final Color END_COLOR =
            new Color(0xFF, 0xD5, 0xE0);

    private static final Color NODE_COLOR =
            new Color(0xFF, 0xF4, 0xC7);

    private static final Color EDGE_COLOR =
            new Color(0xE4, 0xD8, 0xB0);

    private static final Color POSITIVE_COLOR =
            new Color(0xD7, 0xEE, 0xD8);

    private static final Color NEGATIVE_COLOR =
            new Color(0xFF, 0xD9, 0xDF);

    private static final String SAMPLE_INPUT =
            "3\n"
                    + "5 7 0 4\n"
                    + "0 1 50\n"
                    + "0 2 10\n"
                    + "1 2 -30\n"
                    + "1 3 40\n"
                    + "2 1 -5\n"
                    + "2 3 60\n"
                    + "3 4 20\n"
                    + "4 4 0 3\n"
                    + "0 1 20\n"
                    + "1 2 30\n"
                    + "2 1 -10\n"
                    + "2 3 15\n"
                    + "3 3 0 2\n"
                    + "0 1 -40\n"
                    + "1 2 -25\n"
                    + "0 2 -80\n";

    private final ChurunRunner runner =
            new ChurunRunner();

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

    private final JLabel algorithmStatus =
            new JLabel(
                    "Floyd-Warshall + Bellman-Ford",
                    SwingConstants.CENTER
            );

    private final GraphCanvas graphCanvas =
            new GraphCanvas();

    private final JTable floydMatrixTable =
            new JTable();

    private List<ChurunRunner.CaseOutcome> outcomes =
            Collections.emptyList();

    public Mission3Panel() {

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
                        HERO_YELLOW
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
                        "🍰  MISSION 03"
                );

        mission.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        12
                )
        );

        mission.setForeground(
                YELLOW_STRONG
        );

        JLabel title =
                new JLabel(
                        "Churun Stash"
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
                        "Floyd-Warshall + Bellman-Ford"
                );

        algorithm.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        algorithm.setForeground(
                YELLOW_STRONG
        );

        JLabel subtitle =
                new JLabel(
                        "Trace every route to the secret stash! ♡"
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
                        "/images/mission3-cats.png"
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
                YELLOW_STRONG
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
                        "Format: T cases · N M source destination · edges (a b churun)",
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
                        YELLOW_LIGHT,
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
                        YELLOW_LIGHT,
                        YELLOW
                );

        loadButton.setFont(
                Theme.BUTTON_FONT
        );

        loadButton.addActionListener(
                e -> loadSample()
        );

        RoundedButton clearButton =
                new RoundedButton(
                        "Clear",
                        new Color(
                                0xFF,
                                0xFB,
                                0xEA
                        ),
                        YELLOW_LIGHT
                );

        clearButton.setFont(
                Theme.BUTTON_FONT
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
                        YELLOW,
                        new Color(
                                0xE8,
                                0xC4,
                                0x50
                        )
                );

        solveButton.setFont(
                Theme.BUTTON_FONT
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
                        YELLOW_LIGHT,
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
                YELLOW_STRONG
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

        algorithmStatus.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        algorithmStatus.setForeground(
                Theme.TEXT_SECONDARY
        );

        algorithmStatus.setAlignmentX(
                CENTER_ALIGNMENT
        );

        header.add(
                algorithmStatus
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
                                4
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

        graphScroll.setPreferredSize(
                new Dimension(
                        500,
                        330
                )
        );

        JPanel graphAndMatrix =
                new JPanel();

        graphAndMatrix.setOpaque(false);

        graphAndMatrix.setLayout(
                new BoxLayout(
                        graphAndMatrix,
                        BoxLayout.Y_AXIS
                )
        );

        graphAndMatrix.add(
                graphScroll
        );

        graphAndMatrix.add(
                Box.createVerticalStrut(14)
        );

        JLabel matrixTitle =
                new JLabel(
                        "Floyd-Warshall Matrix ♡",
                        SwingConstants.CENTER
                );

        matrixTitle.setFont(
                Theme.CARD_TITLE_FONT
        );

        matrixTitle.setForeground(
                YELLOW_STRONG
        );

        matrixTitle.setAlignmentX(
                CENTER_ALIGNMENT
        );

        graphAndMatrix.add(
                matrixTitle
        );

        graphAndMatrix.add(
                Box.createVerticalStrut(8)
        );

        configureFloydMatrixTable();

        JScrollPane matrixScroll =
                new JScrollPane(
                        floydMatrixTable
                );

        matrixScroll.setBorder(
                BorderFactory.createLineBorder(
                        YELLOW_LIGHT,
                        1
                )
        );

        matrixScroll.setPreferredSize(
                new Dimension(
                        500,
                        180
                )
        );

        matrixScroll.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        180
                )
        );

        matrixScroll.setAlignmentX(
                CENTER_ALIGNMENT
        );

        graphAndMatrix.add(
                matrixScroll
        );

        visualizationArea.add(
                graphAndMatrix,
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
                                14,
                                4
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
                        "Destination",
                        END_COLOR
                )
        );

        legend.add(
                createLegendItem(
                        "+ Churun",
                        POSITIVE_COLOR
                )
        );

        legend.add(
                createLegendItem(
                        "- Churun",
                        NEGATIVE_COLOR
                )
        );

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
                        15
                )
        );

        label.setForeground(
                Theme.TEXT_SECONDARY
        );

        item.add(circle);
        item.add(label);

        return item;
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
                        0xFF,
                        0xFD,
                        0xF5
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
                HERO_YELLOW
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

        algorithmStatus.setText(
                "Floyd-Warshall + Bellman-Ford"
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

        algorithmStatus.setText(
                "Floyd-Warshall + Bellman-Ford"
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

                ChurunRunner.CaseOutcome outcome =
                        outcomes.get(i);

                resultText
                        .append(outcome.line)
                        .append('\n');

                if (!outcome.algorithmsMatch) {

                    resultText.append(
                            "WARNING: algorithms do not match"
                    ).append('\n');
                }

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

        ChurunRunner.CaseOutcome outcome =
                outcomes.get(
                        selectedIndex
                );

        updateFloydMatrix(
                outcome
        );

        GraphCase testCase =
                outcome.testCase;

        ChurunResult result =
                outcome.result;

        if (testCase.getGraph().getN()
                > MAX_VISUAL_NODES) {

            graphCanvas.clear();

            visualizationMessage.setText(
                    "Case solved ♡ Graph is too large to visualize."
            );

            return;
        }

        graphCanvas.showGraph(
                testCase.getGraph(),
                testCase.getSource(),
                testCase.getDestination(),
                outcome.bellmanFordResult.getPath(),
                outcome.bellmanFordResult.getPositiveCycle()
        );

        if (outcome.algorithmsMatch) {

            algorithmStatus.setText(
                    "✓ Floyd-Warshall and Bellman-Ford agree"
            );

            algorithmStatus.setForeground(
                    new Color(
                            0x72,
                            0xA8,
                            0x7B
                    )
            );

        } else {

            algorithmStatus.setText(
                    "⚠ Algorithms do not match"
            );

            algorithmStatus.setForeground(
                    new Color(
                            0xD0,
                            0x77,
                            0x77
                    )
            );
        }

        if (result.isBlocked()) {

            visualizationMessage.setText(
                    "Limon blocked the way ♡"
            );

        } else if (result.isInfinite()) {

            visualizationMessage.setText(
                    "Infinite churun! ♡ Positive cycle affects the destination."
            );

        } else {

            visualizationMessage.setText(
                    "Maximum churun: "
                            + result.getMaxChurun()
                            + " ♡"
            );
        }
    }

    private void configureFloydMatrixTable() {

        floydMatrixTable.setFont(
                Theme.MONO_FONT
        );

        floydMatrixTable.setForeground(
                Theme.TEXT_PRIMARY
        );

        floydMatrixTable.setBackground(
                new Color(
                        0xFF,
                        0xFD,
                        0xF5
                )
        );

        floydMatrixTable.setGridColor(
                YELLOW_LIGHT
        );

        floydMatrixTable.setRowHeight(
                28
        );

        floydMatrixTable.setEnabled(
                false
        );

        floydMatrixTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        floydMatrixTable.getTableHeader()
                .setFont(
                        new Font(
                                Theme.FONT_FAMILY,
                                Font.BOLD,
                                12
                        )
                );

        floydMatrixTable.getTableHeader()
                .setBackground(
                        HERO_YELLOW
                );

        floydMatrixTable.getTableHeader()
                .setForeground(
                        Theme.TEXT_PRIMARY
                );
    }

    private void updateFloydMatrix(
            ChurunRunner.CaseOutcome outcome) {

        FloydWarshallResult floydResult =
                outcome.floydWarshallResult;

        long[][] matrix =
                floydResult.getMatrix();

        int n =
                matrix.length;

        String[] columnNames =
                new String[n + 1];

        columnNames[0] =
                "From / To";

        for (int j = 0;
             j < n;
             j++) {

            columnNames[j + 1] =
                    String.valueOf(j);
        }

        Object[][] data =
                new Object[n][n + 1];

        for (int i = 0;
             i < n;
             i++) {

            data[i][0] =
                    String.valueOf(i);

            for (int j = 0;
                 j < n;
                 j++) {

                if (floydResult.isInfinite(i, j)) {

                    data[i][j + 1] =
                            "inf";

                } else if (matrix[i][j]
                        == FloydWarshall.NEGATIVE_INFINITY) {

                    data[i][j + 1] =
                            "-";

                } else {

                    data[i][j + 1] =
                            String.valueOf(
                                    matrix[i][j]
                            );
                }
            }
        }

        DefaultTableModel model =
                new DefaultTableModel(
                        data,
                        columnNames
                );

        floydMatrixTable.setModel(
                model
        );

        for (int column = 0;
             column < floydMatrixTable
                     .getColumnCount();
             column++) {

            floydMatrixTable
                    .getColumnModel()
                    .getColumn(column)
                    .setPreferredWidth(
                            column == 0
                                    ? 75
                                    : 60
                    );
        }
    }

    private void showError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Mission 3",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private static final class GraphCanvas
            extends JPanel {

        private DirectedGraph graph;

        private int source = -1;
        private int destination = -1;

        private int[] xPositions;
        private int[] yPositions;

        /*
         * Ruta máxima cuando el resultado es finito.
         * Ejemplo: [0, 1, 2]
         */
        private List<Integer> highlightedPath =
                Collections.emptyList();

        /*
         * Ciclo positivo cuando el resultado es infinito.
         * Ejemplo: [1, 2, 1]
         */
        private List<Integer> highlightedCycle =
                Collections.emptyList();

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

        /*
         * Recibe el grafo y también la ruta/ciclo que
         * deben resaltarse en la visualización.
         */
        private void showGraph(
                DirectedGraph graph,
                int source,
                int destination,
                List<Integer> path,
                List<Integer> positiveCycle) {

            this.graph = graph;
            this.source = source;
            this.destination = destination;

            this.highlightedPath =
                    path == null
                            ? Collections.emptyList()
                            : path;

            this.highlightedCycle =
                    positiveCycle == null
                            ? Collections.emptyList()
                            : positiveCycle;

            calculatePositions();

            repaint();
        }

        private void clear() {

            graph = null;

            source = -1;
            destination = -1;

            xPositions = null;
            yPositions = null;

            highlightedPath =
                    Collections.emptyList();

            highlightedCycle =
                    Collections.emptyList();

            repaint();
        }

        private void calculatePositions() {

            if (graph == null) {
                return;
            }

            int n =
                    graph.getN();

            xPositions =
                    new int[n];

            yPositions =
                    new int[n];

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

        /*
         * Dibuja todas las aristas.
         *
         * Las que pertenecen a la ruta máxima o al
         * ciclo positivo se muestran más gruesas
         * y con un color más fuerte.
         */
        private void paintEdges(
                Graphics2D g2) {

            for (Arista edge
                    : graph.getEdges()) {

                int from =
                        edge.getOrigen();

                int to =
                        edge.getDestino();

                int x1 =
                        xPositions[from];

                int y1 =
                        yPositions[from];

                int x2 =
                        xPositions[to];

                int y2 =
                        yPositions[to];

                boolean highlighted =
                        isHighlightedEdge(
                                from,
                                to
                        );

                if (highlighted) {

                    g2.setColor(
                            YELLOW_STRONG
                    );

                    g2.setStroke(
                            new BasicStroke(
                                    5f,
                                    BasicStroke.CAP_ROUND,
                                    BasicStroke.JOIN_ROUND
                            )
                    );

                } else {

                    g2.setColor(
                            EDGE_COLOR
                    );

                    g2.setStroke(
                            new BasicStroke(
                                    2f,
                                    BasicStroke.CAP_ROUND,
                                    BasicStroke.JOIN_ROUND
                            )
                    );
                }

                drawArrow(
                        g2,
                        x1,
                        y1,
                        x2,
                        y2
                );

                paintWeight(
                        g2,
                        x1,
                        y1,
                        x2,
                        y2,
                        edge.getPeso()
                );
            }
        }

        /*
         * Determina si una arista pertenece a la
         * ruta máxima o al ciclo positivo.
         */
        private boolean isHighlightedEdge(
                int from,
                int to) {

            if (containsEdge(
                    highlightedPath,
                    from,
                    to)) {

                return true;
            }

            return containsEdge(
                    highlightedCycle,
                    from,
                    to
            );
        }

        /*
         * Comprueba si dentro de una lista de nodos
         * aparece exactamente la arista from -> to.
         */
        private boolean containsEdge(
                List<Integer> nodes,
                int from,
                int to) {

            for (int i = 0;
                 i < nodes.size() - 1;
                 i++) {

                if (nodes.get(i) == from
                        && nodes.get(i + 1) == to) {

                    return true;
                }
            }

            return false;
        }

        private void drawArrow(
                Graphics2D g2,
                int x1,
                int y1,
                int x2,
                int y2) {

            double angle =
                    Math.atan2(
                            y2 - y1,
                            x2 - x1
                    );

            int nodeRadius = 25;

            int startX =
                    x1
                            + (int) (
                            nodeRadius
                                    * Math.cos(angle)
                    );

            int startY =
                    y1
                            + (int) (
                            nodeRadius
                                    * Math.sin(angle)
                    );

            int endX =
                    x2
                            - (int) (
                            nodeRadius
                                    * Math.cos(angle)
                    );

            int endY =
                    y2
                            - (int) (
                            nodeRadius
                                    * Math.sin(angle)
                    );

            g2.drawLine(
                    startX,
                    startY,
                    endX,
                    endY
            );

            int arrowSize = 9;

            double leftAngle =
                    angle
                            + Math.PI
                            * 0.82;

            double rightAngle =
                    angle
                            - Math.PI
                            * 0.82;

            int leftX =
                    endX
                            + (int) (
                            arrowSize
                                    * Math.cos(
                                    leftAngle
                            )
                    );

            int leftY =
                    endY
                            + (int) (
                            arrowSize
                                    * Math.sin(
                                    leftAngle
                            )
                    );

            int rightX =
                    endX
                            + (int) (
                            arrowSize
                                    * Math.cos(
                                    rightAngle
                            )
                    );

            int rightY =
                    endY
                            + (int) (
                            arrowSize
                                    * Math.sin(
                                    rightAngle
                            )
                    );

            Polygon arrow =
                    new Polygon();

            arrow.addPoint(
                    endX,
                    endY
            );

            arrow.addPoint(
                    leftX,
                    leftY
            );

            arrow.addPoint(
                    rightX,
                    rightY
            );

            g2.fillPolygon(
                    arrow
            );
        }

        private void paintWeight(
                Graphics2D g2,
                int x1,
                int y1,
                int x2,
                int y2,
                long weight) {

            int x =
                    (x1 + x2) / 2;

            int y =
                    (y1 + y2) / 2;

            String text =
                    weight > 0
                            ? "+" + weight
                            : String.valueOf(weight);

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

            Color background =
                    weight >= 0
                            ? POSITIVE_COLOR
                            : NEGATIVE_COLOR;

            g2.setColor(
                    background
            );

            g2.fillRoundRect(
                    x - textWidth / 2 - 6,
                    y - 10,
                    textWidth + 12,
                    20,
                    10,
                    10
            );

            g2.setColor(
                    Theme.TEXT_PRIMARY
            );

            g2.drawString(
                    text,
                    x - textWidth / 2,
                    y + 5
            );
        }

        private void paintNodes(
                Graphics2D g2) {

            int diameter = 50;

            for (int node = 0;
                 node < graph.getN();
                 node++) {

                int x =
                        xPositions[node]
                                - diameter / 2;

                int y =
                        yPositions[node]
                                - diameter / 2;

                Color fill =
                        NODE_COLOR;

                Color border =
                        YELLOW;

                if (node == source) {

                    fill =
                            START_COLOR;

                    border =
                            new Color(
                                    0x83,
                                    0xCC,
                                    0xAA
                            );

                } else if (node
                        == destination) {

                    fill =
                            END_COLOR;

                    border =
                            new Color(
                                    0xF2,
                                    0xA6,
                                    0xB6
                            );
                }

                g2.setColor(fill);

                g2.fillOval(
                        x,
                        y,
                        diameter,
                        diameter
                );

                g2.setColor(border);

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

                paintRole(
                        g2,
                        node,
                        diameter
                );
            }
        }

        private void paintRole(
                Graphics2D g2,
                int node,
                int diameter) {

            String role = null;

            if (node == source) {
                role = "Start";
            }

            if (node == destination) {
                role = "Destination";
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
    }
}