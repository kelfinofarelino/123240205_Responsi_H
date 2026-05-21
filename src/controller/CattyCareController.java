/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author 123240205
 */

import model.CattyCareModel;
import view.CattyCareView;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class CattyCareController {
    private CattyCareModel model;
    private CattyCareView view;
    private int selectedId = -1;

    public CattyCareController(CattyCareModel model, CattyCareView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        loadTableData();

        view.btnAdd.addActionListener(e -> {
            try {
                String owner = view.tfOwnerName.getText();
                String cat = view.tfCatName.getText();
                String phone = view.tfPhone.getText();
                int duration = Integer.parseInt(view.tfDuration.getText());
                
                model.insertData(owner, cat, phone, duration);
                clearFields();
                loadTableData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Duration must be a number!");
            }
        });

        view.btnUpdate.addActionListener(e -> {
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(null, "Please select data from the table first!");
                return;
            }
            try {
                String owner = view.tfOwnerName.getText();
                String cat = view.tfCatName.getText();
                String phone = view.tfPhone.getText();
                int duration = Integer.parseInt(view.tfDuration.getText());
                
                model.updateData(selectedId, owner, cat, phone, duration);
                clearFields();
                loadTableData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Duration must be a number!");
            }
        });

        view.btnDelete.addActionListener(e -> {
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(null, "Please select data from the table first!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this data?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.deleteData(selectedId);
                clearFields();
                loadTableData();
            }
        });
        
        view.btnClear.addActionListener(e -> clearFields());

        view.tableDaycare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tableDaycare.getSelectedRow();
                if (row != -1) {
                    selectedId = Integer.parseInt(view.tableDaycare.getValueAt(row, 0).toString());
                    view.tfOwnerName.setText(view.tableDaycare.getValueAt(row, 1).toString());
                    view.tfCatName.setText(view.tableDaycare.getValueAt(row, 2).toString());
                    view.tfPhone.setText(view.tableDaycare.getValueAt(row, 3).toString());
                    view.tfDuration.setText(view.tableDaycare.getValueAt(row, 4).toString());
                }
            }
        });
    }

    private void loadTableData() {
        view.tableDaycare.setModel(model.getPenitipanData());
    }

    private void clearFields() {
        view.tfOwnerName.setText("");
        view.tfCatName.setText("");
        view.tfPhone.setText("");
        view.tfDuration.setText("");
        selectedId = -1;
    }
}