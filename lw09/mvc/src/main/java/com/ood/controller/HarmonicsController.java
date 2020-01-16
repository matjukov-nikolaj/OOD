package com.ood.controller;

import com.ood.model.Harmonic;
import com.ood.model.HarmonicType;
import com.ood.view.AddHarmonicView;
import com.ood.view.ChartDrawerView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HarmonicsController {

    private List<Harmonic> harmonics;

    private AddHarmonicView addHarmonicView;

    private ChartDrawerView chartDrawerView;

    public HarmonicsController() {
        harmonics = new ArrayList<>();
        chartDrawerView = new ChartDrawerView();
        addHarmonicView = new AddHarmonicView();
    }

    public void start() {
        chartDrawerView.setVisible(true);
        handleChartDrawerView();
        handleAddHarmonicView();
    }

    private void handleAddHarmonicView() {
        addHarmonicView.getBtnCancel().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addHarmonicView.dispose();
            }
        });

        addHarmonicView.getRadio1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton radio2 = addHarmonicView.getRadio2();
                if (radio2.isSelected()) {
                    radio2.setSelected(false);
                }
            }
        });

        addHarmonicView.getRadio2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton radio1 = addHarmonicView.getRadio1();
                if (radio1.isSelected()) {
                    radio1.setSelected(false);
                }
            }
        });

        addHarmonicView.getBtnOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((addHarmonicView.getRadio1().isSelected() || addHarmonicView.getRadio2().isSelected()) &&
                        !addHarmonicView.getAmplitudeInput().getText().equals("") &&
                        !addHarmonicView.getFrequencyInput().getText().equals("") &&
                        !addHarmonicView.getPhaseInput().getText().equals("")) {
                    double amplitude = Double.parseDouble(addHarmonicView.getAmplitudeInput().getText());
                    double frequency = Double.parseDouble(addHarmonicView.getFrequencyInput().getText());
                    double phase = Double.parseDouble(addHarmonicView.getPhaseInput().getText());

                    String typeStr = addHarmonicView.getRadio1().isSelected() ? "sin" : "cos";
                    Harmonic harmonic = new Harmonic(amplitude, HarmonicType.toType(typeStr), frequency,phase);
                    harmonics.add(harmonic);
                    chartDrawerView.getListModel().addElement(harmonic);
                    addHarmonicView.dispose();
                }
            }
        });
    }

    private void handleChartDrawerView() {
        chartDrawerView.getAddButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addHarmonicView.setVisible(true);
            }
        });

        chartDrawerView.getList().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (chartDrawerView.getList().getSelectedIndex() >= 0) {
                    chartDrawerView.getRemoveButton().setEnabled(true);
                } else {
                    chartDrawerView.getRemoveButton().setEnabled(false);
                }
            }
        });

        chartDrawerView.getRemoveButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chartDrawerView.getListModel().remove(chartDrawerView.getList().getSelectedIndex());
            }
        });
    }

}
