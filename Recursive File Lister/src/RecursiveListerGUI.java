import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveListerGUI extends JFrame {

    private JTextArea textArea;
    private JScrollPane scrollPane;

    public RecursiveListerGUI() {
        setTitle("Recursive File Lister");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Recursive File Lister");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, "North");

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileChooser();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, "South");

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        add(scrollPane, "Center");

        setLocationRelativeTo(null);
    }

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            listFiles(selectedDirectory);
        }
    }

    private void listFiles(File directory) {
        textArea.setText(""); // Clear previous content
        listFilesRecursive(directory);
    }

    private void listFilesRecursive(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesRecursive(file); // Recursive call for subdirectories
                } else {
                    textArea.append(file.getAbsolutePath() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RecursiveListerGUI().setVisible(true);
            }
        });
    }
}
