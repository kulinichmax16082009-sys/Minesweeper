package panels;

import enums.Difficulty;
import utils.saveUtils.GameData;
import utils.simpleUI.SimpleButton;
import windows.BoardWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;

public class StatsPanel extends JPanel {
    public final static float SIZE_FACTOR = 1.5f;
    public final static int HEIGHT = (int) (IntroducingPanel.HEIGHT / SIZE_FACTOR);
    public final static int WIDTH = (int) (IntroducingPanel.WIDTH / SIZE_FACTOR);

    private final int BUTTON_WIDTH = (int) (IntroducingPanel.BUTTON_WIDTH / SIZE_FACTOR);
    private final int BUTTON_HEIGHT = (int) (IntroducingPanel.BUTTON_HEIGHT / SIZE_FACTOR);
    public static final int BUTTON_FONT_SIZE = (int) (IntroducingPanel.BUTTON_FONT_SIZE / SIZE_FACTOR);

    private final String[] columns = {"Title", "Win/Loose" , "Difficulty" ," Playtime", "Flags", "Date","Time"};

    private IntroducingPanel introducingPanel;
    private DefaultTableModel model;
    private JTable table;

    public StatsPanel(IntroducingPanel introducingPanel) {
        this.introducingPanel = introducingPanel;

        setLayout(null);

        initTable();
        initBackButton();

        setBounds((IntroducingPanel.WIDTH - WIDTH) / 2, (IntroducingPanel.HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(false);
        setBackground(new Color(191, 191, 191));
    }

    private void initBackButton() {
        SimpleButton back = SimpleButton.createButton("Back", BUTTON_WIDTH, BUTTON_HEIGHT, e -> introducingPanel.setPaused(this, false));
        back.setDesign(new Color(200, 0, 0), new Color(0, 0, 0), BUTTON_FONT_SIZE, "Arial");
        back.setLocation(10, HEIGHT - 10 - BUTTON_HEIGHT);

        add(back);
    }

    private void initTable() {
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setEnabled(false);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 10, WIDTH - 20, HEIGHT - BUTTON_HEIGHT - 30);
        pane.setEnabled(false);
        add(pane);

        GameData gameData = (GameData) new GameData().loadData();
        addRows(gameData);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) model.removeRow(row);
                    gameData.removeAllAtIndex(row);
                    gameData.saveData();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        BoardWindow boardWindow = new BoardWindow(gameData.getBoards().get(row));
                    }
                }
            }
        });
    }

    private void addRows(GameData gameData) {
        for (int i = 0; i < (gameData != null ? gameData.getTitles().size() : 0); i++) {
            String title = gameData.getTitles().get(i);

            boolean isWin = gameData.getBoards().get(i).isWin();
            String win = "Win";
            if (!isWin) win = "Loose";

            Difficulty difficulty = gameData.getBoards().get(i).getDifficulty();
            long playerTime = gameData.getPlayerTimes().get(i);
            int flagsLeft = gameData.getFlagsLeft().get(i);
            LocalDate date = gameData.getDates().get(i);
            LocalTime time = gameData.getTimes().get(i);

            model.addRow(new Object[]{title, win, difficulty, playerTime + " s", flagsLeft, date, time});
        }
    }

    public void setIntroducingPanel(IntroducingPanel introducingPanel) {
        this.introducingPanel = introducingPanel;
    }
}
