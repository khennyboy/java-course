package studentracker2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentTrackerGUI extends JFrame {

    private StudentManager manager = new StudentManager();
    private DefaultTableModel tableModel;
    private JTable table;

    public StudentTrackerGUI() {
        setTitle("Student Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        // --- Table setup ---
        String[] columns = {"ID", "Name", "Department", "Level", "CGPA"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(24);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        // --- Buttons panel ---
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JButton addBtn = new JButton("Add Student");
        JButton addCourseBtn = new JButton("Add Course/Score");
        JButton viewBtn = new JButton("View Details / CGPA");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton refreshBtn = new JButton("Refresh List");

        buttonPanel.add(addBtn);
        buttonPanel.add(addCourseBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(refreshBtn);

        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidePanel.add(buttonPanel, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);

        // --- Button actions ---
        addBtn.addActionListener(e -> addStudentDialog());
        addCourseBtn.addActionListener(e -> addCourseDialog());
        viewBtn.addActionListener(e -> viewDetailsDialog());
        updateBtn.addActionListener(e -> updateStudentDialog());
        deleteBtn.addActionListener(e -> deleteSelectedStudent());
        refreshBtn.addActionListener(e -> refreshTable());
    }

    private Short getSelectedStudentId() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student in the table first.",
                    "No selection", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return (Short) tableModel.getValueAt(row, 0);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Student> students = manager.getAllStudents();
        for (Student s : students) {
            // Recalculate CGPA (silently updates s's cgpa field) if they have courses.
            if (!s.getCourses().isEmpty()) {
                manager.calculateCGPA(s.getId());
            }
            tableModel.addRow(new Object[]{
                    s.getId(), s.getStudentName(), s.getDepartment(), s.getLevel(),
                    String.format("%.2f", s.getCgpa())
            });
        }
    }

    private void addStudentDialog() {
        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField levelField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Student ID (number):")); panel.add(idField);
        panel.add(new JLabel("Department:")); panel.add(deptField);
        panel.add(new JLabel("Level:")); panel.add(levelField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                short id = Short.parseShort(idField.getText().trim());
                String dept = deptField.getText().trim();
                short level = Short.parseShort(levelField.getText().trim());

                if (name.isEmpty() || dept.isEmpty()) {
                    showError("Name and Department cannot be empty.");
                    return;
                }
                if (manager.findStudentById(id) != null) {
                    showError("A student with that ID already exists.");
                    return;
                }

                manager.addStudent(new Student(name, id, dept, level));
                refreshTable();
            } catch (NumberFormatException ex) {
                showError("ID and Level must be valid numbers.");
            }
        }
    }

    private void addCourseDialog() {
        Short id = getSelectedStudentId();
        if (id == null) return;

        JTextField codeField = new JTextField();
        JTextField unitField = new JTextField();
        JTextField scoreField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Course Code:")); panel.add(codeField);
        panel.add(new JLabel("Unit:")); panel.add(unitField);
        panel.add(new JLabel("Score (0-100):")); panel.add(scoreField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Course/Score",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String code = codeField.getText().trim();
                int unit = Integer.parseInt(unitField.getText().trim());
                double score = Double.parseDouble(scoreField.getText().trim());

                if (code.isEmpty()) {
                    showError("Course code cannot be empty.");
                    return;
                }

                manager.addCourse(id, new Course(code, unit, score));
                refreshTable();
            } catch (NumberFormatException ex) {
                showError("Unit must be a whole number and Score must be a number.");
            }
        }
    }

    private void viewDetailsDialog() {
        Short id = getSelectedStudentId();
        if (id == null) return;

        Student s = manager.findStudentById(id);
        if (s == null) return;

        manager.calculateCGPA(id); // refresh cgpa field before showing

        StringBuilder sb = new StringBuilder();
        sb.append(s).append("\n\nCourses:\n");
        if (s.getCourses().isEmpty()) {
            sb.append("  (no courses added yet)\n");
        } else {
            for (Course c : s.getCourses()) {
                sb.append(String.format("  %s (Unit: %d, Score: %.1f)%n",
                        c.getCourseCode(), c.getUnit(), c.getScore()));
            }
        }
        sb.append(String.format("\nCGPA: %.2f", s.getCgpa()));

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scroll, "Student Details", JOptionPane.PLAIN_MESSAGE);
    }

    private void updateStudentDialog() {
        Short id = getSelectedStudentId();
        if (id == null) return;

        Student s = manager.findStudentById(id);
        if (s == null) return;

        String[] options = {"Name", "Department", "Level", "Course Score"};
        String choice = (String) JOptionPane.showInputDialog(this, "What do you want to update?",
                "Update Student", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (choice == null) return;

        switch (choice) {
            case "Name": {
                String newName = JOptionPane.showInputDialog(this, "Enter new name:", s.getStudentName());
                if (newName != null && !newName.trim().isEmpty()) {
                    manager.updateStudentName(id, newName.trim());
                }
                break;
            }
            case "Department": {
                String newDept = JOptionPane.showInputDialog(this, "Enter new department:", s.getDepartment());
                if (newDept != null && !newDept.trim().isEmpty()) {
                    manager.updateStudentDepartment(id, newDept.trim());
                }
                break;
            }
            case "Level": {
                String newLevel = JOptionPane.showInputDialog(this, "Enter new level:", s.getLevel());
                if (newLevel != null) {
                    try {
                        manager.updateStudentLevel(id, Short.parseShort(newLevel.trim()));
                    } catch (NumberFormatException ex) {
                        showError("Level must be a valid number.");
                    }
                }
                break;
            }
            case "Course Score": {
                if (s.getCourses().isEmpty()) {
                    showError("This student has no courses yet.");
                    return;
                }
                String[] codes = s.getCourses().stream().map(Course::getCourseCode).toArray(String[]::new);
                String courseCode = (String) JOptionPane.showInputDialog(this, "Select course:",
                        "Update Course Score", JOptionPane.PLAIN_MESSAGE, null, codes, codes[0]);
                if (courseCode == null) return;
                String newScoreStr = JOptionPane.showInputDialog(this, "Enter new score:");
                if (newScoreStr != null) {
                    try {
                        manager.updateCourseScore(id, courseCode, Double.parseDouble(newScoreStr.trim()));
                    } catch (NumberFormatException ex) {
                        showError("Score must be a valid number.");
                    }
                }
                break;
            }
        }
        refreshTable();
    }

    private void deleteSelectedStudent() {
        Short id = getSelectedStudentId();
        if (id == null) return;

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete student ID " + id + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            manager.deleteStudent(id);
            refreshTable();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new StudentTrackerGUI().setVisible(true);
        });
    }
}
