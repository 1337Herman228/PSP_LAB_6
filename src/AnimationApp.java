import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationApp extends JFrame implements ActionListener {
    private JButton startButton, stopButton;
    private AnimationPanel animationPanel;
    private Thread carThread, personThread, treeThread, backThread;

    public AnimationApp() {
        setTitle("lab6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        startButton = new JButton("Старт");
        stopButton = new JButton("Стоп");

        // Добавление слушателя событий для кнопок
        startButton.addActionListener(this);
        stopButton.addActionListener(this);

        // Создание панели с кнопками
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        // Создание панели для анимации
        animationPanel = new AnimationPanel();

        // Добавление панелей на форму
        Container container = getContentPane();
        container.add(animationPanel, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Обработчик событий кнопок
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Запуск потоков анимации
            carThread = new Thread(animationPanel.getCar());
            personThread = new Thread(animationPanel.getPerson());
            treeThread = new Thread(animationPanel.getTree());
            carThread.start();
            personThread.start();
            treeThread.start();
        } else if (e.getSource() == stopButton) {
            // Остановка потоков анимации
            animationPanel.stopAnimation();
            carThread.interrupt();
            personThread.interrupt();
            treeThread.interrupt();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AnimationApp app = new AnimationApp();
                app.setVisible(true);
            }
        });
    }

}