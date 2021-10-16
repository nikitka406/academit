package ru.academits.popov.temperture.view;

import javax.swing.*;
import java.awt.*;

import ru.academits.popov.temperture.model.Converter;
import ru.academits.popov.temperture.model.Scale;

public class View {
    private JFrame frame;
    private JPanel panel;
    private JComboBox<Scale> inputScaleComboBox;
    private JComboBox<Scale> outputScaleComboBox;
    private JTextField inputTemperatureTextField;
    private JButton converterButton;
    private JButton clearButton;
    private JTextField outputTemperatureTextField;
    private final Converter converter;

    public View(Converter converter) {
        this.converter = converter;
    }

    private void createFrame(String frameName, int width, int height) {
        frame = new JFrame(frameName);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void eventHandling() {
        converterButton.addActionListener(event -> {
            try {
                Scale inputScale = (Scale) inputScaleComboBox.getSelectedItem();
                Scale outputScale = (Scale) outputScaleComboBox.getSelectedItem();
                double inputTemperature = Double.parseDouble(inputTemperatureTextField.getText());
                if (inputScale != null && outputScale != null){
                    double outputTemperature = converter.getResultConvert(inputScale,outputScale,inputTemperature);
                    outputTemperatureTextField.setText(String.valueOf(outputTemperature));
                }else {
                    throw new IllegalArgumentException("Не выбрана шкала");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Неверный формат введенного исла", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(event -> {
            inputTemperatureTextField.setText("");
            inputTemperatureTextField.setEditable(true);
            outputTemperatureTextField.setText("");
        });
    }

    private void createView() {
        SwingUtilities.invokeLater(() -> {
            createFrame("Перевод температуры", 500, 300);

            panel = new JPanel(new GridBagLayout());
            panel.setPreferredSize(new Dimension(500, 300));
            frame.add(panel);

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            panel.add(new JLabel("Выберите в какой шкале будете вводить:"), c);

            Scale[] inputScalesArrays = converter.getScales();
            inputScaleComboBox = new JComboBox<>(inputScalesArrays);
            panel.add(inputScaleComboBox, c);
            inputScaleComboBox.setEnabled(true);
            inputScaleComboBox.setSelectedIndex(0);

            c.gridx = 0;
            c.gridy = 2;
            panel.add(new JLabel("Выберите в какую шкалу нужно перевести:"), c);

            Scale[] outputScalesArrays = converter.getScales();
            outputScaleComboBox = new JComboBox<>(outputScalesArrays);
            c.gridx = 1;
            c.gridy = 2;
            panel.add(outputScaleComboBox, c);
            outputScaleComboBox.setEnabled(true);
            outputScaleComboBox.setSelectedIndex(0);

            c.gridx = 0;
            c.gridy = 3;
            panel.add(new JLabel("Введите температуру:"), c);

            c.gridx = 1;
            c.gridy = 3;
            inputTemperatureTextField = new JTextField("", 20);
            panel.add(inputTemperatureTextField, c);

            c.gridx = 1;
            c.gridy = 4;
            converterButton = new JButton("Перевести температуру");
            panel.add(converterButton, c);

            c.gridx = 0;
            c.gridy = 5;
            panel.add(new JLabel("Результат перевода:"), c);

            c.gridx = 1;
            c.gridy = 5;
            outputTemperatureTextField = new JTextField("", 20);
            outputTemperatureTextField.setEditable(false);
            panel.add(outputTemperatureTextField, c);

            c.gridx = 1;
            c.gridy = 6;
            clearButton = new JButton("Попробовать еще раз.");
            panel.add(clearButton, c);

            eventHandling();
        });
    }

    public void run() {
        createView();
    }
}
