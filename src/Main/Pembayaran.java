/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Main;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.*;
import javax.swing.JOptionPane;
import models.User;

/**
 *
 * @author fatur
 */
public class Pembayaran extends javax.swing.JPanel {
    
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1/uas?autoReconnect=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "";

    public static Connection conn = null;
    public static Statement stmt;
    public static ResultSet rs;
    
    private String totalTagihanValue; // Asumsi tipe data integer untuk totalTagihan
    private final String keteranganValue = "belum lunas";
    private String noVaBcaValue;
    private String noVaJtgValue;
    private String noVaBriValue;
    public Pembayaran() {
        initComponents();
        initPaymentDetails();
    }
    
    
     private void initPaymentDetails() {
        User user = Session.getInstance().getUser();
        if (user == null) {
            System.out.println("User is null in Pembayaran constructor");
            return;
        }
        String id = user.getId();

        // Mengambil nilai totalTagihan dari database
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                String query = "SELECT biaya FROM pendaftaran WHERE id = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        totalTagihanValue = rs.getString("biaya");
                    } else {
                        // Handle jika tidak ada data ditemukan
                        totalTagihanValue = "0"; // atau sesuaikan dengan kebutuhan
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exception sesuai kebutuhan aplikasi
        }

        // Mengatur nilai totalTagihan dan keterangan pada tampilan
        totalTagihan.setText(String.valueOf(totalTagihanValue));
        keterangan.setText(keteranganValue);

        // Mengatur nilai noVaBca, noVaJtg, dan noVaBri
        if (id != null && !id.isEmpty()) {
            // Mengatur noVaBca
            noVaBcaValue = "014-" + id.substring(2) + "-12345";
            noVaBca.setText(noVaBcaValue);

            // Mengatur noVaJtg
            noVaJtgValue = "013-" + id.substring(2) + "-12345";
            noVaJtg.setText(noVaJtgValue);

            // Mengatur noVaBri
            noVaBriValue = "002-" + id.substring(2) + "-12345";
            noVaBri.setText(noVaBriValue);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        noVaBca = new javax.swing.JLabel();
        salin1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        noVaJtg = new javax.swing.JLabel();
        salin2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        noVaBri = new javax.swing.JLabel();
        salin3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        totalTagihan = new javax.swing.JLabel();
        keterangan = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("PEMBAYARAN");

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel2.setText("BANK BCA");

        noVaBca.setText("noVaBca");

        salin1.setText("Salin");
        salin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salin1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(53, 53, 53)
                .addComponent(noVaBca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salin1)
                .addGap(37, 37, 37))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(noVaBca)
                        .addComponent(jLabel2))
                    .addComponent(salin1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(34, 34, 34))
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(451, 85));

        jLabel8.setText("BANK Jateng");

        noVaJtg.setText("noVaJtg");

        salin2.setText("Salin");
        salin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salin2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addGap(44, 44, 44)
                .addComponent(noVaJtg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salin2)
                .addGap(37, 37, 37))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noVaJtg)
                    .addComponent(jLabel8)
                    .addComponent(salin2))
                .addGap(30, 30, 30))
        );

        jPanel6.setBackground(new java.awt.Color(0, 153, 255));

        jLabel9.setText("BANK BRI");

        noVaBri.setText("noVaBri");

        salin3.setText("Salin");
        salin3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salin3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel9)
                .addGap(50, 50, 50)
                .addComponent(noVaBri)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salin3)
                .addGap(36, 36, 36))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noVaBri)
                    .addComponent(jLabel9)
                    .addComponent(salin3))
                .addGap(30, 30, 30))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jLabel4.setText("Total Tagihan : ");

        totalTagihan.setText("totalTagihan");

        keterangan.setText("keterangan");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalTagihan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                .addComponent(keterangan)
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(totalTagihan)
                    .addComponent(keterangan))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel3.setText("1. Tidak wajib konfirmasi karena akan otomatis terupdate 1x24 jam.");

        jLabel7.setText("2. Pembayaran kuliah reguler hanya dapat dijalankan melalui metode di atas.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(315, 315, 315))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7))
                        .addGap(160, 160, 160))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(63, 63, 63)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void salin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salin1ActionPerformed
        String noVaBcaText = noVaBca.getText();
        copyToClipboard(noVaBcaText);
        JOptionPane.showMessageDialog(null, "Nomor VA Bank BCA telah disalin ke clipboard.");
    }//GEN-LAST:event_salin1ActionPerformed

    private void salin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salin2ActionPerformed
        String noVaJtgText = noVaJtg.getText();
        copyToClipboard(noVaJtgText);
        JOptionPane.showMessageDialog(null, "Nomor VA Bank Jateng telah disalin ke clipboard.");
    }//GEN-LAST:event_salin2ActionPerformed

    private void salin3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salin3ActionPerformed
        String noVaBriText = noVaBri.getText();
        copyToClipboard(noVaBriText);
        JOptionPane.showMessageDialog(null, "Nomor VA Bank BRI telah disalin ke clipboard.");
                                         
    }//GEN-LAST:event_salin3ActionPerformed
    
    private void copyToClipboard(String text) {
    StringSelection stringSelection = new StringSelection(text);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel keterangan;
    private javax.swing.JLabel noVaBca;
    private javax.swing.JLabel noVaBri;
    private javax.swing.JLabel noVaJtg;
    private javax.swing.JButton salin1;
    private javax.swing.JButton salin2;
    private javax.swing.JButton salin3;
    private javax.swing.JLabel totalTagihan;
    // End of variables declaration//GEN-END:variables

    
}
