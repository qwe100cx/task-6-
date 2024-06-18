import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class main_v2 extends JFrame implements ActionListener {
    private JTextField textField;
    private JTextField nField;
    private JTextField kField;
    private JTextArea outputArea;

    public main_v2() {
        this.setTitle("Word Counter");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);


        JLabel textLabel = new JLabel("Введите текст:");
        textLabel.setBounds(20, 20, 100, 20);
        this.add(textLabel);

        textField = new JTextField();
        textField.setBounds(130, 20, 200, 20);
        this.add(textField);


        JLabel nLabel = new JLabel("Введите N:");
        nLabel.setBounds(20, 50, 100, 20);
        this.add(nLabel);

        nField = new JTextField();
        nField.setBounds(130, 50, 50, 20);
        this.add(nField);


        JLabel kLabel = new JLabel("Введите K:");
        kLabel.setBounds(20, 80, 100, 20);
        this.add(kLabel);

        kField = new JTextField();
        kField.setBounds(130, 80, 50, 20);
        this.add(kField);


        JButton processButton = new JButton("Подсчитать");
        processButton.setBounds(20, 110, 100, 30);
        processButton.addActionListener(this);
        this.add(processButton);


        outputArea = new JTextArea();
        outputArea.setBounds(20, 150, 350, 100);
        outputArea.setEditable(false);
        this.add(outputArea);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textField.getText().toLowerCase();
        int N = Integer.parseInt(nField.getText());
        int K = Integer.parseInt(kField.getText());

        String[] wordsList = text.split("\\s+");
        List<String> filteredWords = new ArrayList<>();

        for (String word : wordsList) {
            if (word.length() >= K) {
                filteredWords.add(word);
            }
        }

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : filteredWords) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<String> topWords = new ArrayList<>(wordCount.keySet());
        topWords.sort((w1, w2) -> wordCount.get(w2) - wordCount.get(w1));

        int count = Math.min(N, topWords.size());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(topWords.get(i)).append(" - ").append(wordCount.get(topWords.get(i))).append(" раз\n");
        }

        outputArea.setText(result.toString());
    }

    public static void main(String[] args) {
        new main_v2();
    }
}

//nlogn
