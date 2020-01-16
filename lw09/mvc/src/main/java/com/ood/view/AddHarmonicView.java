package com.ood.view;

import com.ood.model.Harmonic;
import com.ood.model.HarmonicType;
import com.ood.utilites.DocumentListenerImpl;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class AddHarmonicView extends JFrame {

    private JFormattedTextField amplitudeInput = new JFormattedTextField();

    private JFormattedTextField frequencyInput = new JFormattedTextField();

    private JFormattedTextField phaseInput = new JFormattedTextField();

    private JButton btnOk = new JButton("Ok");

    private JButton btnCancel = new JButton("Cancel");

    private JRadioButton radio1 = new JRadioButton("Sin");

    private JRadioButton radio2 = new JRadioButton("Cos");

    private JLabel harmonicLabel = new JLabel();

    public AddHarmonicView() {
        super("Add new harmonic");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Container container = getContentPane();
        JPanel mainPanel = new JPanel();

        mainPanel.add(this.generateInputPanelWithLabel("Amplitude", amplitudeInput));

        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.add(radio1);
        radioButtonsPanel.add(radio2);
        mainPanel.add(radioButtonsPanel);

        mainPanel.add(this.generateInputPanelWithLabel("Frequency", frequencyInput));
        mainPanel.add(this.generateInputPanelWithLabel("Phase", phaseInput));

        JPanel buttonsPanel = new JPanel();
        btnCancel.setFocusable(false);
        buttonsPanel.add(btnOk);
        buttonsPanel.add(btnCancel);
        mainPanel.add(buttonsPanel);

        JPanel harmonicPanel = new JPanel();
        harmonicPanel.add(harmonicLabel);
        mainPanel.add(harmonicPanel);

        container.add(mainPanel);
        setPreferredSize(new Dimension(300, 300));
        pack();
        setLocationRelativeTo(null);
        setVisible(false);
    }

    private JPanel generateInputPanelWithLabel(String labelName, JFormattedTextField input) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(labelName + ": ");
        panel.add(label);
        input.setPreferredSize(new Dimension(100, 30));
        input.getDocument().addDocumentListener(new DocumentListenerImpl(input));
        panel.add(input);
        return panel;
    }

    public JFormattedTextField getAmplitudeInput() {
        return amplitudeInput;
    }

    public JFormattedTextField getFrequencyInput() {
        return frequencyInput;
    }

    public JFormattedTextField getPhaseInput() {
        return phaseInput;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnOk() {
        return btnOk;
    }

    public JRadioButton getRadio1() {
        return radio1;
    }

    public JRadioButton getRadio2() {
        return radio2;
    }
}
