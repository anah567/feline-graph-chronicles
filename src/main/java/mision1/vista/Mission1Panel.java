package mision1.vista;

import app.EntradaInvalidaException;
import app.Theme;
import app.ui.RoundedButton;
import app.ui.RoundedPanel;

import mision1.algoritmo.SearchRunner;
import mision1.modelo.GridCase;
import mision1.modelo.Point;
import mision1.modelo.SearchResult;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

public class Mission1Panel extends JPanel {

    private static final int MAX_VISUAL_SIZE = 50;

    private static final Color COLOR_START =
            new Color(0xB9, 0xE4, 0xFF);

    private static final Color COLOR_NINA =
            new Color(0xD7, 0xEB, 0xFF);

    private static final Color COLOR_BOMB =
            new Color(0xA8, 0xC8, 0xF0);

    private static final Color COLOR_BFS =
            new Color(0xD9, 0xEC, 0xFF);

    private static final Color COLOR_DFS =
            new Color(0xC9, 0xDC, 0xF8);

    private static final Color COLOR_BOTH =
            new Color(0xA9, 0xC8, 0xEE);

    private static final String SAMPLE_INPUT =
            "10 10\n"
                    + "9\n"
                    + "0 1 2\n"
                    + "1 1 2\n"
                    + "2 2 2 9\n"
                    + "3 2 1 7\n"
                    + "5 3 3 6 9\n"
                    + "6 4 0 1 2 7\n"
                    + "7 3 0 3 8\n"
                    + "8 2 7 9\n"
                    + "9 3 2 3 4\n"
                    + "0 0\n"
                    + "9 9\n"
                    + "0 0\n";

    private final SearchRunner runner =
            new SearchRunner();

    private final JTextArea inputArea =
            new JTextArea();

    private final JTextArea outputArea =
            new JTextArea();

    private final JComboBox<String> caseSelector =
            new JComboBox<>();

    private final JRadioButton bothButton =
            new JRadioButton("🩵 Both");

    private final JRadioButton bfsButton =
            new JRadioButton("🌸 BFS only");

    private final JRadioButton dfsButton =
            new JRadioButton("🌷 DFS only");

    private final JLabel visualizationMessage =
            new JLabel(
                    "Load a sample or enter a case to begin ♡",
                    SwingConstants.CENTER
            );

    private final GridCanvas gridCanvas =
            new GridCanvas();

    private List<SearchRunner.CaseOutcome> outcomes =
            Collections.emptyList();

    public Mission1Panel() {

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
                        Theme.CARD_BLUE
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
                        "🌸  MISSION 01"
                );

        mission.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        12
                )
        );

        mission.setForeground(
                Theme.BLUE_STRONG
        );

        mission.setAlignmentX(
                LEFT_ALIGNMENT
        );

        JLabel title =
                new JLabel(
                        "Rescue Nina"
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

        title.setAlignmentX(
                LEFT_ALIGNMENT
        );

        JLabel algorithm =
                new JLabel(
                        "BFS + DFS"
                );

        algorithm.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        14
                )
        );

        algorithm.setForeground(
                Theme.BLUE_STRONG
        );

        algorithm.setAlignmentX(
                LEFT_ALIGNMENT
        );

        JLabel subtitle =
                new JLabel(
                        "Let's help Pola and Minerva find Nina safely ♡"
                );

        subtitle.setFont(
                Theme.SUBTITLE_FONT
        );

        subtitle.setForeground(
                Theme.TEXT_SECONDARY
        );

        subtitle.setAlignmentX(
                LEFT_ALIGNMENT
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
                new JLabel(
                        "🐾",
                        SwingConstants.CENTER
                );

        decoration.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        32
                )
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
                Theme.BLUE_STRONG
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

        configureInputArea();

        JScrollPane inputScroll =
                new JScrollPane(
                        inputArea
                );

        inputScroll.setBorder(
                BorderFactory.createLineBorder(
                        Theme.BORDER,
                        1
                )
        );

        center.add(
                inputScroll,
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
                        Theme.CARD_LAVENDER
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
                        Theme.CARD_BLUE
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
                        Theme.BLUE,
                        Theme.BLUE_STRONG
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
                        Theme.BORDER,
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
                Theme.BLUE_STRONG
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

        header.add(
                buildVisualizationControls()
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

        JScrollPane gridScroll =
                new JScrollPane(
                        gridCanvas
                );

        gridScroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        gridScroll.getViewport().setBackground(
                Theme.WHITE
        );

        visualizationArea.add(
                gridScroll,
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
                        new Color(0xC9, 0xED, 0xDD)
                )
        );

        legend.add(
                createLegendItem(
                        "Nina",
                        new Color(0xFF, 0xD5, 0xE0)
                )
        );

        legend.add(
                createLegendItem(
                        "Bomb",
                        new Color(0xFF, 0xE3, 0xB5)
                )
        );

        legend.add(
                createLegendItem(
                        "BFS",
                        new Color(0xD6, 0xEA, 0xFF)
                )
        );

        legend.add(
                createLegendItem(
                        "DFS",
                        new Color(0xE5, 0xDC, 0xFF)
                )
        );

        legend.add(
                createLegendItem(
                        "Both",
                        new Color(0xC9, 0xD2, 0xF4)
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

        JLabel square =
                new JLabel("■");

        square.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.PLAIN,
                        18
                )
        );

        square.setForeground(color);

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

        item.add(square);
        item.add(label);

        return item;
    }

    private JPanel buildVisualizationControls() {

        JPanel controls =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                18,
                                4
                        )
                );

        controls.setOpaque(false);

        configureRadioButton(
                bothButton
        );

        configureRadioButton(
                bfsButton
        );

        configureRadioButton(
                dfsButton
        );

        ButtonGroup group =
                new ButtonGroup();

        group.add(bothButton);
        group.add(bfsButton);
        group.add(dfsButton);

        bothButton.setSelected(true);

        bothButton.addActionListener(
                e -> updateGrid()
        );

        bfsButton.addActionListener(
                e -> updateGrid()
        );

        dfsButton.addActionListener(
                e -> updateGrid()
        );

        controls.add(bothButton);
        controls.add(bfsButton);
        controls.add(dfsButton);

        caseSelector.setFont(
                Theme.SMALL_FONT
        );

        caseSelector.setEnabled(false);
        caseSelector.setVisible(false);

        caseSelector.addActionListener(
                e -> updateGrid()
        );

        controls.add(caseSelector);

        return controls;
    }

    private void configureRadioButton(
            JRadioButton button) {

        button.setOpaque(false);

        button.setFont(
                new Font(
                        Theme.FONT_FAMILY,
                        Font.BOLD,
                        15
                )
        );

        button.setForeground(
                Theme.TEXT_PRIMARY
        );
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
                        0xFC,
                        0xFA,
                        0xFF
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
                Theme.CARD_BLUE
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

        caseSelector.setEnabled(false);

        gridCanvas.clear();

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

        caseSelector.setEnabled(false);

        gridCanvas.clear();

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

                SearchRunner.CaseOutcome outcome =
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

            caseSelector.setEnabled(
                    outcomes.size() > 1
            );

            caseSelector.setVisible(
                    outcomes.size() > 1
            );

            caseSelector.setSelectedIndex(0);

            updateGrid();

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

    private void updateGrid() {

        int selectedIndex =
                caseSelector.getSelectedIndex();

        if (selectedIndex < 0
                || selectedIndex >= outcomes.size()) {

            gridCanvas.clear();

            return;
        }

        SearchRunner.CaseOutcome outcome =
                outcomes.get(
                        selectedIndex
                );

        GridCase testCase =
                outcome.testCase;

        SearchResult result =
                outcome.result;

        if (testCase.getRows()
                > MAX_VISUAL_SIZE
                || testCase.getCols()
                > MAX_VISUAL_SIZE) {

            gridCanvas.clear();

            visualizationMessage.setText(
                    "Case solved ♡ Visualization is available up to 50 × 50."
            );

            return;
        }

        visualizationMessage.setText(
                buildCaseMessage(
                        result
                )
        );

        GridMode mode;

        if (bfsButton.isSelected()) {
            mode = GridMode.BFS;
        } else if (dfsButton.isSelected()) {
            mode = GridMode.DFS;
        } else {
            mode = GridMode.BOTH;
        }

        gridCanvas.showCase(
                testCase,
                result,
                mode
        );
    }

    private String buildCaseMessage(
            SearchResult result) {

        if (!result.isReachable()) {

            return "Nina is unreachable ♡";
        }

        return "BFS: "
                + result.getBfsMoves()
                + " moves    ·    DFS: "
                + result.getDfsMoves()
                + " moves";
    }

    private void showError(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Mission 1",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private enum GridMode {
        BOTH,
        BFS,
        DFS
    }

    private static final class GridCanvas extends JPanel {

        private static final Color EMPTY =
                new Color(
                        0xFC,
                        0xFB,
                        0xFF
                );

        private static final Color START =
                new Color(
                        0xC9,
                        0xED,
                        0xDD
                );

        private static final Color DESTINATION =
                new Color(
                        0xFF,
                        0xD5,
                        0xE0
                );

        private static final Color BOMB =
                new Color(
                        0xFF,
                        0xE3,
                        0xB5
                );

        private static final Color BFS =
                new Color(
                        0xD6,
                        0xEA,
                        0xFF
                );

        private static final Color DFS =
                new Color(
                        0xE5,
                        0xDC,
                        0xFF
                );

        private static final Color BOTH =
                new Color(
                        0xC9,
                        0xD2,
                        0xF4
                );

        private GridCase testCase;
        private SearchResult result;
        private GridMode mode =
                GridMode.BOTH;

        private GridCanvas() {

            setOpaque(true);

            setBackground(
                    Theme.WHITE
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

        private void showCase(
                GridCase testCase,
                SearchResult result,
                GridMode mode) {

            this.testCase =
                    testCase;

            this.result =
                    result;

            this.mode =
                    mode;

            updatePreferredSize();

            revalidate();
            repaint();
        }

        private void clear() {

            testCase = null;
            result = null;

            setPreferredSize(
                    new Dimension(
                            300,
                            300
                    )
            );

            revalidate();
            repaint();
        }

        private void updatePreferredSize() {

            if (testCase == null) {
                return;
            }

            int cellSize =
                    calculateCellSize();

            int width =
                    testCase.getCols()
                            * cellSize
                            + 28;

            int height =
                    testCase.getRows()
                            * cellSize
                            + 28;

            setPreferredSize(
                    new Dimension(
                            Math.max(
                                    width,
                                    300
                            ),
                            Math.max(
                                    height,
                                    300
                            )
                    )
            );
        }

        private int calculateCellSize() {

            if (testCase == null) {
                return 42;
            }

            int largest =
                    Math.max(
                            testCase.getRows(),
                            testCase.getCols()
                    );

            if (largest <= 10) {
                return 48;
            }

            if (largest <= 20) {
                return 32;
            }

            if (largest <= 35) {
                return 24;
            }

            return 18;
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            super.paintComponent(g);

            if (testCase == null
                    || result == null) {

                return;
            }

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int cellSize =
                    calculateCellSize();

            int gridWidth =
                    testCase.getCols()
                            * cellSize;

            int gridHeight =
                    testCase.getRows()
                            * cellSize;

            int startX =
                    Math.max(
                            14,
                            (getWidth() - gridWidth) / 2
                    );

            int startY =
                    Math.max(
                            14,
                            (getHeight() - gridHeight) / 2
                    );

            for (int row = 0;
                 row < testCase.getRows();
                 row++) {

                for (int col = 0;
                     col < testCase.getCols();
                     col++) {

                    paintCell(
                            g2,
                            row,
                            col,
                            startX,
                            startY,
                            cellSize
                    );
                }
            }

            g2.dispose();
        }

        private void paintCell(
                Graphics2D g2,
                int row,
                int col,
                int startX,
                int startY,
                int cellSize) {

            int gap =
                    cellSize >= 30
                            ? 3
                            : 1;

            int x =
                    startX
                            + col * cellSize;

            int y =
                    startY
                            + row * cellSize;

            Point point =
                    new Point(
                            row,
                            col
                    );

            Color color =
                    getCellColor(
                            point
                    );

            g2.setColor(color);

            g2.fillRoundRect(
                    x + gap,
                    y + gap,
                    cellSize - gap * 2,
                    cellSize - gap * 2,
                    Math.min(
                            14,
                            cellSize / 3
                    ),
                    Math.min(
                            14,
                            cellSize / 3
                    )
            );

            g2.setColor(
                    Theme.BORDER
            );

            g2.setStroke(
                    new BasicStroke(
                            1f
                    )
            );

            g2.drawRoundRect(
                    x + gap,
                    y + gap,
                    cellSize - gap * 2,
                    cellSize - gap * 2,
                    Math.min(
                            14,
                            cellSize / 3
                    ),
                    Math.min(
                            14,
                            cellSize / 3
                    )
            );

            paintCellContent(
                    g2,
                    point,
                    x,
                    y,
                    cellSize
            );
        }

        private Color getCellColor(
                Point point) {

            if (point.equals(
                    testCase.getStart()
            )) {
                return START;
            }

            if (point.equals(
                    testCase.getDestination()
            )) {
                return DESTINATION;
            }

            if (testCase.hasBomb(
                    point.getRow(),
                    point.getCol()
            )) {
                return BOMB;
            }

            if (!result.isReachable()) {
                return EMPTY;
            }

            boolean inBfs =
                    result
                            .getBfsPath()
                            .contains(point);

            boolean inDfs =
                    result
                            .getDfsPath()
                            .contains(point);

            if (mode == GridMode.BFS) {

                return inBfs
                        ? BFS
                        : EMPTY;
            }

            if (mode == GridMode.DFS) {

                return inDfs
                        ? DFS
                        : EMPTY;
            }

            if (inBfs && inDfs) {
                return BOTH;
            }

            if (inBfs) {
                return BFS;
            }

            if (inDfs) {
                return DFS;
            }

            return EMPTY;
        }

        private void paintCellContent(
                Graphics2D g2,
                Point point,
                int x,
                int y,
                int cellSize) {

            String text = null;

            if (point.equals(
                    testCase.getStart()
            )) {

                text = "▶";

            } else if (point.equals(
                    testCase.getDestination()
            )) {

                text = "♡";

            } else if (testCase.hasBomb(
                    point.getRow(),
                    point.getCol()
            )) {

                text = "×";

            } else if (cellSize >= 30) {

                text =
                        point.getRow()
                                + ","
                                + point.getCol();
            }

            if (text == null) {
                return;
            }

            g2.setFont(
                    new Font(
                            Theme.FONT_FAMILY,
                            Font.BOLD,
                            Math.max(
                                    9,
                                    cellSize / 4
                            )
                    )
            );

            g2.setColor(
                    Theme.TEXT_SECONDARY
            );

            int textWidth =
                    g2.getFontMetrics()
                            .stringWidth(text);

            int textHeight =
                    g2.getFontMetrics()
                            .getAscent();

            int textX =
                    x
                            + (cellSize - textWidth) / 2;

            int textY =
                    y
                            + (cellSize + textHeight) / 2
                            - 3;

            g2.drawString(
                    text,
                    textX,
                    textY
            );
        }
    }
}