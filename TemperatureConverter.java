import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Main Panel with a Dark Theme
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(30, 30, 46));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Title Label
        JLabel titleLabel = new JLabel("Temperature Converter");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Input Area Container
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        inputPanel.setBackground(new Color(30, 30, 46));

        // Text Field for Value
        JTextField inputField = new JTextField(8);
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        inputField.setHorizontalAlignment(JTextField.CENTER);

        // Dropdown for Units
        String[] units = { "Celsius", "Fahrenheit", "Kelvin" };
        JComboBox<String> unitCombo = new JComboBox<>(units);
        unitCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        unitCombo.setBackground(Color.WHITE);

        inputPanel.add(inputField);
        inputPanel.add(unitCombo);
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Convert Button
        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        convertButton.setBackground(new Color(108, 92, 231)); // Purple Accent
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        convertButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        convertButton.setPreferredSize(new Dimension(150, 40));
        convertButton.setMaximumSize(new Dimension(150, 40));
        mainPanel.add(convertButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 35)));

        // Results Container
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(3, 1, 0, 10));
        resultsPanel.setBackground(new Color(42, 42, 64)); // Slightly lighter dark background
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(108, 92, 231), 2), // Purple Border
                new EmptyBorder(15, 20, 15, 20)));
        resultsPanel.setMaximumSize(new Dimension(350, 150));

        // Result Labels
        JLabel result1Label = new JLabel("Celsius: --", SwingConstants.CENTER);
        result1Label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        result1Label.setForeground(Color.WHITE);

        JLabel result2Label = new JLabel("Fahrenheit: --", SwingConstants.CENTER);
        result2Label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        result2Label.setForeground(Color.WHITE);

        JLabel result3Label = new JLabel("Kelvin: --", SwingConstants.CENTER);
        result3Label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        result3Label.setForeground(Color.WHITE);

        resultsPanel.add(result1Label);
        resultsPanel.add(result2Label);
        resultsPanel.add(result3Label);

        mainPanel.add(resultsPanel);

        // Wiring the conversion logic
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = inputField.getText().trim();
                    if (text.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a temperature value.", "Empty Input",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    double value = Double.parseDouble(text);
                    String unit = (String) unitCombo.getSelectedItem();
                    double c = 0, f = 0, k = 0;

                    if ("Celsius".equals(unit)) {
                        c = value;
                        f = (c * 9 / 5) + 32;
                        k = c + 273.15;
                    } else if ("Fahrenheit".equals(unit)) {
                        f = value;
                        c = (f - 32) * 5 / 9;
                        k = c + 273.15;
                    } else if ("Kelvin".equals(unit)) {
                        k = value;
                        c = k - 273.15;
                        f = (c * 9 / 5) + 32;
                    }

                    result1Label.setText(String.format("Celsius: %.2f °C", c));
                    result2Label.setText(String.format("Fahrenheit: %.2f °F", f));
                    result3Label.setText(String.format("Kelvin: %.2f K", k));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid numerical temperature.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
