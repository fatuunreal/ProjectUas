/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Main;
import static Main.Prestasi.conn;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.User;
/**
 *
 * @author fatur
 */
public final class Beranda extends javax.swing.JPanel {
   
    
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1/uas?autoReconnect=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "";

    public static Connection conn = null;
    public static Statement stmt;
    public static ResultSet rs;
        
    
    
    public Beranda() {
        initComponents();
        displayUserInfo();
        loadData2();
        showtable();
        loadData();
        showtable2();
    }
    private void displayUserInfo() {
        User user = Session.getInstance().getUser();
        if (user != null) {
            lblHalo.setText("Halo, Selamat Datang " + user.getUsername());
        }
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uas", "root", "");
            String sql = "SELECT * FROM pendaftaran WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Data found for ID: " + id);
                infoProgStudi.setText(rs.getString("programStudi"));
         

                // Update lblProgStudi label
                String programStudi = rs.getString("programStudi");
                String jenjang = rs.getString("jenjang");
                infoProgStudi.setText("Program Studi: "+ jenjang + " - " + programStudi);

            
            } else {
                System.out.println("No data found for ID: " + id);

           

                // Clear lblProgStudi label
                infoProgStudi.setText("");
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

    private void loadData2() {
        User user = Session.getInstance().getUser();
        if (user == null) {
            System.out.println("User is null in loadData");
            return;
        }
        String id = user.getId();
        System.out.println("Loading data for ID: " + id);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uas", "root", "");
            String sql = "SELECT * FROM biodata WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Data found for ID: " + id);
                
         

                // Update lblProgStudi label
                String nama = rs.getString("namaLengkap");
                String tmptLahir = rs.getString("tempatLahir");
                String tanggalLahir = rs.getString("tanggalLahir");
                String nik = rs.getString("nik"); 
                lbl1.setText(nama);
                lbl2.setText(tmptLahir + ", " + tanggalLahir);
                lbl3.setText(nik);

            
            } else {
                System.out.println("No data found for ID: " + id);

           

                // Clear lblProgStudi label
                lbl1.setText("");
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
    public void showtable2(){
        User user = Session.getInstance().getUser();
        if (user != null) {
            String userId = user.getId();
            try{
                Class.forName(JDBC_DRIVER);	   
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Kategori");
                model.addColumn("Tingkat");
                model.addColumn("Penyelenggara");
                model.addColumn("Nama Kejuaraaan");
                model.addColumn("juara");
                model.addColumn("Tahun");

                 String sql = "SELECT * FROM prestasi WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, userId);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    model.addRow(new Object[] {
                            rs.getString("kategori"),
                            rs.getString("tingkat"),
                            rs.getString("penyelenggara"),
                            rs.getString("namaKejuaraan"),
                            rs.getInt("juara"),
                            rs.getString("tahun")
                    });
                }
                rs.close();
                stmt.close();
                conn.close();

                tabelPrestasi.setModel(model);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void showtable(){
        User user = Session.getInstance().getUser();
        if (user != null) {
            String userId = user.getId();
            try{
                Class.forName(JDBC_DRIVER);	   
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Agama");
                model.addColumn("PPKN");
                model.addColumn("Bahasa Indonesia");
                model.addColumn("Matematika");
                model.addColumn("Sejarah");
                model.addColumn("Bhs Inggris");
                model.addColumn("Seni Budaya");
                model.addColumn("Bhs Jawa");
                model.addColumn("Biologi");
                model.addColumn("Fisika");
                model.addColumn("Kimia");

                String sql = "SELECT * FROM nilai_ijazah WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, userId);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("pendAgama"),
                        rs.getString("ppkn"),
                        rs.getString("bhsIndo"),
                        rs.getString("matematika"),
                        rs.getString("sejarah"),
                        rs.getString("bhsInggris"),
                        rs.getString("seniBudaya"),
                        rs.getString("bhsJawa"),
                        rs.getString("biologi"),
                        rs.getString("fisika"),
                        rs.getString("kimia")
                    });
                }
                rs.close();
                stmt.close();
                conn.close();

                tableData.setModel(model);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblHalo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblTTL = new javax.swing.JLabel();
        lblNoTelp = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        infoProgStudi = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPrestasi = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblHalo.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        lblHalo.setText("Halo");

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableData);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel3.setText("Informasi Akun");

        lblNama.setText("Nama Lengkap              : ");

        lblTTL.setText("Tempat, Tanggal Lahir  :");

        lblNoTelp.setText("NIK                                 :");

        lbl1.setText("......");

        lbl2.setText("......");

        lbl3.setText("......");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNama)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTTL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNoTelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel3)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(lbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTTL)
                    .addComponent(lbl2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoTelp)
                    .addComponent(lbl3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));

        jLabel2.setFont(new java.awt.Font("Myanmar Text", 0, 12)); // NOI18N
        jLabel2.setText("Informasi Pendaftaran");

        infoProgStudi.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        infoProgStudi.setText("Program Studi : Anda belum memilih");

        jLabel5.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        jLabel5.setText("Status Pembayaran : ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(99, 99, 99))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(infoProgStudi))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(infoProgStudi)
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel1.setText("Nilai Ijazah");

        jLabel4.setText("Prestasi");

        tabelPrestasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tabelPrestasi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblHalo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(367, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblHalo)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel infoProgStudi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lblHalo;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNoTelp;
    private javax.swing.JLabel lblTTL;
    private javax.swing.JTable tabelPrestasi;
    private javax.swing.JTable tableData;
    // End of variables declaration//GEN-END:variables
}
