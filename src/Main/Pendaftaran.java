/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import java.sql.*;
import models.User;
/**
 *
 * @author fatur
 */
public class Pendaftaran extends javax.swing.JPanel {

     public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1/uas?autoReconnect=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "";

    public static Connection conn = null;
    public static Statement stmt;
    public static ResultSet rs;
    
    public Pendaftaran() {
        initComponents();
        addActionListeners();
        loadData();
    }
    
    private void addActionListeners() {
        txtProgramStudi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLabels();
            }
        });
    }
    
    private void loadData() {
    User user = Session.getInstance().getUser();
    if (user == null) {
        System.out.println("User is null in loadData");
        return;
    }
    String id = user.getId();
    System.out.println("Loading data for ID: " + id);
    
    try {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String sql = "SELECT * FROM pendaftaran WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            System.out.println("Data found for ID: " + id);
            txtProgramStudi.setSelectedItem(rs.getString("programStudi"));
            txtKode.setText(rs.getString("kode"));
            txtJenjang.setSelectedItem(rs.getString("jenjang"));
            txtBiaya.setText(rs.getString("biaya"));
            
            // Disable fields and enable the edit button
            txtProgramStudi.setEnabled(false);
            txtKode.setEnabled(false);
            txtJenjang.setEnabled(false);
            txtBiaya.setEnabled(false);
            btnDaftar.setEnabled(false);
            btnEdit.setEnabled(true);
        } else {
            System.out.println("No data found for ID: " + id);
            
            // Enable fields and disable the edit button
            txtProgramStudi.setEnabled(true);
            txtKode.setEnabled(true);
            txtJenjang.setEnabled(true);
            txtBiaya.setEnabled(true);
            btnDaftar.setEnabled(true);
            btnEdit.setEnabled(false);
        }
        
        rs.close();
        ps.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data!");
    } finally {
        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}

    private void updateLabels() {
        String selectedProgram = (String) txtProgramStudi.getSelectedItem();

        String programKode = "";
        String programBiaya = "";

        switch (selectedProgram) {
            case "Teknik Informatika":
                programKode = "A11";
                programBiaya = "30.000.000";
                break;
            case "Sistem Informatika":
                programKode = "A12";
                programBiaya = "30.000.000";
                break;
            case "Desain Komunikasi Visual":
                programKode = "A14";
                programBiaya = "30.000.000";
                break;
            case "Ilmu Komunikasi":
                programKode = "A15";
                programBiaya = "25.000.000";
                break;
            case "Film dan Televisi":
                programKode = "FTV01";
                programBiaya = "17.500.000";
                break;
            case "Teknik Elektro":
                programKode = "E11";
                programBiaya = "15.500.000";
                break;
            case "Teknik Nuklir":
                programKode = "E13";
                programBiaya = "32.000.000";
                break;
            case "Teknik Industri":
                programKode = "E12";
                programBiaya = "15.000.000";
                break;
        }

        txtKode.setText(programKode);
        txtBiaya.setText(programBiaya);
        System.out.println("Selected Program: " + selectedProgram);
        System.out.println("Program Kode: " + programKode);
        System.out.println("Program Biaya: " + programBiaya);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtProgramStudi = new javax.swing.JComboBox<>();
        txtKode = new javax.swing.JLabel();
        txtJenjang = new javax.swing.JComboBox<>();
        txtBiaya = new javax.swing.JLabel();
        btnDaftar = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Pendaftaran");

        jLabel3.setText("Program Studi");

        jLabel4.setText("Kode");

        jLabel1.setText("Biaya Registrasi");

        jLabel5.setText("Jenjang");

        txtProgramStudi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teknik Informatika", "Sistem Informatika", "Desain Komunikasi Visual", "Ilmu Komunikasi", "Film dan Televisi", "Teknik Elektro", "Teknik Nuklir", "Teknik Industri" }));

        txtKode.setText("Kode");

        txtJenjang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "D3", "D4", "S1", "S2", "S3" }));

        txtBiaya.setText("Biaya");

        btnDaftar.setText("DAFTAR");
        btnDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(66, 66, 66)
                                    .addComponent(txtProgramStudi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel1))
                                    .addGap(61, 61, 61)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtBiaya)
                                        .addComponent(txtJenjang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtKode)))))))
                .addContainerGap(318, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtProgramStudi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtKode))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtJenjang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBiaya))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(275, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, "card2");

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btnDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarActionPerformed
        User user = Session.getInstance().getUser();
    if (user == null) {
        System.out.println("User is null in btnKirimActionPerformed");
        return;
    }
    String id = user.getId();
    String programStudi = (String) txtProgramStudi.getSelectedItem();
    String kode = txtKode.getText();
    String jenjang = (String) txtJenjang.getSelectedItem();
    String biaya = txtBiaya.getText();

    if (id.isEmpty() || programStudi.isEmpty() || kode.isEmpty() || jenjang.isEmpty() || biaya.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Semua harus di isi");
    } else {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Check if the data already exists
            String checkSql = "SELECT COUNT(*) FROM pendaftaran WHERE id = ?";
            int count = 0;
            try (PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
                checkPs.setString(1, id);
                ResultSet rs = checkPs.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }

            String sql;
            if (count > 0) {
                // Update existing data
                sql = "UPDATE pendaftaran SET programStudi = ?, kode = ?, jenjang = ?, biaya = ? WHERE id = ?";
                System.out.println("Updating existing record");
            } else {
                // Insert new data
                sql = "INSERT INTO pendaftaran (programStudi, kode, jenjang, biaya, id) VALUES (?, ?, ?, ?, ?)";
                System.out.println("Inserting new record");
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, programStudi);
            ps.setString(2, kode);
            ps.setString(3, jenjang);
            ps.setString(4, biaya);
            ps.setString(5, id);
            System.out.println("Executing query: " + ps.toString());
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            System.out.println("Data saved for ID: " + id);

            // Disable combo boxes and button
            txtProgramStudi.setEnabled(false);
            txtKode.setEnabled(false);
            txtJenjang.setEnabled(false);
            txtBiaya.setEnabled(false);
            btnDaftar.setEnabled(false);
            btnEdit.setEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data!");
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    }//GEN-LAST:event_btnDaftarActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        txtProgramStudi.setEnabled(true);
        txtKode.setEnabled(true);
        txtJenjang.setEnabled(true);
        txtBiaya.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDaftar.setEnabled(true);
    }//GEN-LAST:event_btnEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDaftar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txtBiaya;
    private javax.swing.JComboBox<String> txtJenjang;
    private javax.swing.JLabel txtKode;
    private javax.swing.JComboBox<String> txtProgramStudi;
    // End of variables declaration//GEN-END:variables
}
