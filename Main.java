import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Word Frequency Analyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel textLabel = new JLabel("введите текст:");
        JTextField textField = new JTextField();
        frame.add(textLabel);
        frame.add(textField);

        JLabel kLabel = new JLabel("введите минимальную длину слова:");
        JTextField kField = new JTextField();
        frame.add(kLabel);
        frame.add(kField);

        JLabel nLabel = new JLabel("введите количество слов:");
        JTextField nField = new JTextField();
        frame.add(nLabel);
        frame.add(nField);

        JButton analyzeButton = new JButton("посчитать");
        frame.add(new JLabel());
        frame.add(analyzeButton);

        JTextArea resultArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultArea);
        JLabel resultLabel = new JLabel("результат:");
        frame.add(resultLabel);
        frame.add(scrollPane);

        analyzeButton.addActionListener(e -> {
            String text = textField.getText();
            int k = Integer.parseInt(kField.getText().trim());
            int n = Integer.parseInt(nField.getText().trim());
            analyzeAndDisplay(text, k, n, resultArea);
        });

        frame.setVisible(true);
    }

    private static void analyzeAndDisplay(String text, int k, int n, JTextArea resultArea) {
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = text.toLowerCase().split("\\s+");

        for (String word : words) {
            if (word.length() >= k) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        List<Entry<String, Integer>> sortedWords = new ArrayList<>(wordCount.entrySet());
        sortedWords.sort(Entry.<String, Integer>comparingByValue().reversed());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.min(n, sortedWords.size()); i++) {
            result.append(sortedWords.get(i).getKey())
                    .append(" - ")
                    .append(sortedWords.get(i).getValue())
                    .append(" times\n");
        }

        resultArea.setText(result.toString());
    }
}
