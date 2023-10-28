import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BugTrackingSystemGUI {
    private List<Bug> bugs;
    private DefaultListModel<String> bugListModel;
    private JFrame frame;
    private JList<String> bugList;
    private JTextField titleField;
    private JTextArea descriptionArea;

    public BugTrackingSystemGUI() {
        bugs = new ArrayList<>();
        bugListModel = new DefaultListModel<>();
        bugList = new JList<>(bugListModel);

        frame = new JFrame("Bug Tracking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 300);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel titleLabel = new JLabel("Bug Title:");
        titleField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionArea = new JTextArea();
        JButton createBugButton = new JButton("Create Bug");
        JButton markFixedButton = new JButton("Mark as Fixed");

        createBugButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionArea.getText();
                if (!title.isEmpty() && !description.isEmpty()) {
                    Bug bug = new Bug(title, description);
                    bugs.add(bug);
                    bugListModel.addElement(bug.toString());
                    titleField.setText("");
                    descriptionArea.setText("");
                }
            }
        });

        markFixedButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = bugList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    bugs.get(selectedIndex).markAsFixed();
                    bugListModel.set(selectedIndex, bugs.get(selectedIndex).toString());
                }
            }
        });

        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionArea);
        inputPanel.add(createBugButton);
        inputPanel.add(markFixedButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(bugList), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BugTrackingSystemGUI();
            }
        });
    }
}

class Bug {
    private String title;
    private String description;
    private boolean isFixed;

    public Bug(String title, String description) {
        this.title = title;
        this.description = description;
        this.isFixed = false;
    }

    public void markAsFixed() {
        isFixed = true;
    }

    
    public String toString() {
        return "Title: " + title + " - Description: " + description + " - Status: " + (isFixed ? "Fixed" : "Open");
    }
}
