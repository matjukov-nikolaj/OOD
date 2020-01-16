package com.ood.utilites;

import com.ood.model.Harmonic;
import com.ood.model.HarmonicType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentListenerImpl implements DocumentListener {

    private JFormattedTextField field;

    public DocumentListenerImpl(JFormattedTextField field) {
        this.field = field;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        Runnable format = new Runnable() {
            @Override
            public void run() {
                String text = field.getText();
                if(!text.matches("\\d*(\\.\\d{0,5})?")){
                    field.setText(text.substring(0,text.length()-1));
                }
            }
        };
        SwingUtilities.invokeLater(format);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

}
