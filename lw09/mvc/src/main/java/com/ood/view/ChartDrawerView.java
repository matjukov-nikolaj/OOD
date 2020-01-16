package com.ood.view;

import com.ood.model.Harmonic;
import com.ood.model.HarmonicType;
import com.ood.view.AddHarmonicView;
import org.apache.log4j.Layout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChartDrawerView extends JFrame
{
    private JButton addButton = new JButton("Add new");

    private DefaultListModel<Harmonic> listModel = new DefaultListModel<>();

    private JList list = new JList(listModel);

    private JButton removeButton = new JButton("Delete selected");

    public ChartDrawerView() {
        super("Simple Example");
        this.setBounds(100,100,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        list.setSelectedIndex(0);
        list.setFocusable(false);
        mainPanel.add(new JScrollPane(list), BorderLayout.WEST);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 0));
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        addButton.setFocusable(false);
        buttonsPanel.add(addButton);
        removeButton.setFocusable(false);
        buttonsPanel.add(removeButton);

        JPanel propertiesPanel = new JPanel();


        getContentPane().add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JList getList() {
        return list;
    }

    public DefaultListModel<Harmonic> getListModel() {
        return listModel;
    }
}