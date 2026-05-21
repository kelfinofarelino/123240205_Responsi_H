/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 123240205
 */

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class CattyCareModel {
    private Connection conn;

    public CattyCareModel() {
        try {
            String url = "jdbc:mysql://localhost:3306/cattycare";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed: " + e.getMessage());
        }
    }

    public int hitungBiaya(int lama) {
        if (lama <= 2) {
            return lama * 40000;
        } else {
            return (2 * 40000) + ((lama - 2) * 30000); 
        }
    }

    public DefaultTableModel getPenitipanData() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Owner Name", "Cat Name", "Phone Number", "Duration (Days)", "Cost"}, 0);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM penitipan");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"), rs.getString("nama_pemilik"), 
                    rs.getString("nama_kucing"), rs.getString("nomor_telepon"), 
                    rs.getInt("lama_penitipan"), rs.getInt("biaya")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve data: " + e.getMessage());
        }
        return model;
    }

    public void insertData(String pemilik, String kucing, String telp, int lama) {
        int biaya = hitungBiaya(lama);
        try {
            String query = "INSERT INTO penitipan (nama_pemilik, nama_kucing, nomor_telepon, lama_penitipan, biaya) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, pemilik);
            pstmt.setString(2, kucing);
            pstmt.setString(3, telp);
            pstmt.setInt(4, lama);
            pstmt.setInt(5, biaya);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to add data: " + e.getMessage());
        }
    }

    public void updateData(int id, String pemilik, String kucing, String telp, int lama) {
        int biaya = hitungBiaya(lama);
        try {
            String query = "UPDATE penitipan SET nama_pemilik=?, nama_kucing=?, nomor_telepon=?, lama_penitipan=?, biaya=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, pemilik);
            pstmt.setString(2, kucing);
            pstmt.setString(3, telp);
            pstmt.setInt(4, lama);
            pstmt.setInt(5, biaya);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data updated successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to update data: " + e.getMessage());
        }
    }

    public void deleteData(int id) {
        try {
            String query = "DELETE FROM penitipan WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data deleted successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to delete data: " + e.getMessage());
        }
    }
}