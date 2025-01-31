/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.form.rezervacija;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author natalija
 */
public class RezervacijaFrm extends javax.swing.JDialog {

    /**
     * Creates new form RezervacijaFrm
     */
    public RezervacijaFrm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         getContentPane().setBackground(new Color(222, 239, 245));
         jPanel1.setBackground(new Color(173, 216, 230));
         jComboBoxKorisnik.setBackground(Color.WHITE);
         jComboBoxOsiguranje.setBackground(Color.WHITE);
         jComboBoxTipSobe.setBackground(Color.WHITE);
         
        JTableHeader header = jTableAranzman.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setFont(new Font("Century Schoolbook L", Font.BOLD, 16));
                label.setBackground(new Color(173, 216, 230));
                label.setForeground(Color.BLACK);
                label.setHorizontalAlignment(SwingConstants.CENTER);  // Centriranje teksta
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));  // Crtice između kolona
                return label;
            }
        });
        JTableHeader header2 = jTableRasporedSoba.getTableHeader();
        header2.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setFont(new Font("Century Schoolbook L", Font.BOLD, 16));
                label.setBackground(new Color(173, 216, 230));
                label.setForeground(Color.BLACK);
                label.setHorizontalAlignment(SwingConstants.CENTER);  // Centriranje teksta
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));  // Crtice između kolona
                return label;
            }
        });
        
         UIManager.put("OptionPane.messageFont", new Font("Century Schoolbook L", Font.BOLD, 14));
        UIManager.put("OptionPane.background", new Color(222, 239, 245));
        UIManager.put("Panel.background", new Color(222, 239, 245));
        UIManager.put("Button.font", new Font("Century Schoolbook L", Font.BOLD, 14));
        UIManager.put("Button.background", new Color(173, 216, 230));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldZaposleni = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldSifraRezervacije = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDatumRezervacije = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxTipSobe = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAranzman = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxKorisnik = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldUkupnaCena = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxOsiguranje = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableRasporedSoba = new javax.swing.JTable();
        jButtonDodajRaspored = new javax.swing.JButton();
        jButtonObrisiRaspored = new javax.swing.JButton();
        jButtonSacuvajRezervaciju = new javax.swing.JButton();
        jButtonOtkazi = new javax.swing.JButton();
        jLabelID = new javax.swing.JLabel();
        jTextFieldIDRezervacije = new javax.swing.JTextField();
        jButtonIzmeni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Century Schoolbook L", 3, 24)); // NOI18N
        jLabel1.setText("FORMA REZERVACIJA");

        jLabel2.setFont(new java.awt.Font("Century Schoolbook L", 3, 16)); // NOI18N
        jLabel2.setText("Ulogovani zaposleni:");

        jTextFieldZaposleni.setBackground(new java.awt.Color(173, 216, 230));
        jTextFieldZaposleni.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jTextFieldZaposleni.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Century Schoolbook L", 0, 16)); // NOI18N
        jLabel3.setText("Šifra rezervacije:");

        jTextFieldSifraRezervacije.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jTextFieldSifraRezervacije.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Century Schoolbook L", 0, 16)); // NOI18N
        jLabel4.setText("Datum rezervacije (yyyy-MM-dd):");

        jTextFieldDatumRezervacije.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jTextFieldDatumRezervacije.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Century Schoolbook L", 0, 16)); // NOI18N
        jLabel5.setText("Tip sobe:");

        jComboBoxTipSobe.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jComboBoxTipSobe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1/1", "1/2", "1/3", "1/4", "1/6" }));
        jComboBoxTipSobe.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Century Schoolbook L", 0, 16)); // NOI18N
        jLabel6.setText("Aranžmani:");

        jTableAranzman.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableAranzman.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jTableAranzman.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableAranzman.setRowHeight(23);
        jTableAranzman.setSelectionBackground(new java.awt.Color(173, 216, 230));
        jScrollPane1.setViewportView(jTableAranzman);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Raspored Soba", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook L", 3, 16))); // NOI18N
        jPanel1.setToolTipText("Raspored soba");
        jPanel1.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jPanel1.setName("Raspored soba"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Century Schoolbook L", 0, 16)); // NOI18N
        jLabel7.setText("Korisnik:");

        jComboBoxKorisnik.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jComboBoxKorisnik.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setFont(new java.awt.Font("Century Schoolbook L", 0, 16)); // NOI18N
        jLabel8.setText("Ukupna cena (€):");

        jTextFieldUkupnaCena.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jTextFieldUkupnaCena.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Century Schoolbook L", 0, 16)); // NOI18N
        jLabel9.setText("Osiguranje:");

        jComboBoxOsiguranje.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jComboBoxOsiguranje.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DA", "NE" }));
        jComboBoxOsiguranje.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTableRasporedSoba.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableRasporedSoba.setFont(new java.awt.Font("Century Schoolbook L", 0, 15)); // NOI18N
        jTableRasporedSoba.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableRasporedSoba.setRowHeight(23);
        jTableRasporedSoba.setSelectionBackground(new java.awt.Color(173, 216, 230));
        jScrollPane2.setViewportView(jTableRasporedSoba);

        jButtonDodajRaspored.setBackground(new java.awt.Color(0, 204, 204));
        jButtonDodajRaspored.setFont(new java.awt.Font("Century Schoolbook L", 1, 16)); // NOI18N
        jButtonDodajRaspored.setText("Dodaj raspored");

        jButtonObrisiRaspored.setBackground(new java.awt.Color(0, 204, 204));
        jButtonObrisiRaspored.setFont(new java.awt.Font("Century Schoolbook L", 1, 16)); // NOI18N
        jButtonObrisiRaspored.setText("Obriši raspored");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel7)
                .addGap(29, 29, 29)
                .addComponent(jComboBoxKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxOsiguranje, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldUkupnaCena, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonObrisiRaspored, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDodajRaspored, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxOsiguranje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldUkupnaCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonObrisiRaspored, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDodajRaspored, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonSacuvajRezervaciju.setBackground(new java.awt.Color(0, 204, 204));
        jButtonSacuvajRezervaciju.setFont(new java.awt.Font("Century Schoolbook L", 1, 18)); // NOI18N
        jButtonSacuvajRezervaciju.setText("Sačuvaj");

        jButtonOtkazi.setBackground(new java.awt.Color(0, 204, 204));
        jButtonOtkazi.setFont(new java.awt.Font("Century Schoolbook L", 1, 18)); // NOI18N
        jButtonOtkazi.setText("Otkaži");

        jLabelID.setFont(new java.awt.Font("Century Schoolbook L", 0, 16)); // NOI18N
        jLabelID.setText("ID rezervacije:");

        jTextFieldIDRezervacije.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonIzmeni.setBackground(new java.awt.Color(0, 204, 204));
        jButtonIzmeni.setFont(new java.awt.Font("Century Schoolbook L", 1, 18)); // NOI18N
        jButtonIzmeni.setText("Izmeni podatke");
        jButtonIzmeni.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButtonOtkazi, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jButtonSacuvajRezervaciju, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelID)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldIDRezervacije)
                                    .addComponent(jTextFieldSifraRezervacije, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldDatumRezervacije, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(405, 405, 405)
                                        .addComponent(jLabel2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxTipSobe, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(403, 403, 403)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelID)
                    .addComponent(jTextFieldIDRezervacije, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSifraRezervacije, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldDatumRezervacije, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxTipSobe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonOtkazi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSacuvajRezervaciju, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    public JButton getjButtonDodajRaspored() {
        return jButtonDodajRaspored;
    }

    public void setjButtonDodajRaspored(JButton jButtonDodajRaspored) {
        this.jButtonDodajRaspored = jButtonDodajRaspored;
    }

    public JButton getjButtonObrisiRaspored() {
        return jButtonObrisiRaspored;
    }

    public void setjButtonObrisiRaspored(JButton jButtonObrisiRaspored) {
        this.jButtonObrisiRaspored = jButtonObrisiRaspored;
    }

    public JButton getjButtonOtkazi() {
        return jButtonOtkazi;
    }

    public void setjButtonOtkazi(JButton jButtonOtkazi) {
        this.jButtonOtkazi = jButtonOtkazi;
    }

    public JButton getjButtonSacuvajRezervaciju() {
        return jButtonSacuvajRezervaciju;
    }

    public void setjButtonSacuvajRezervaciju(JButton jButtonSacuvajRezervaciju) {
        this.jButtonSacuvajRezervaciju = jButtonSacuvajRezervaciju;
    }

    public JComboBox<Object> getjComboBoxKorisnik() {
        return jComboBoxKorisnik;
    }

    public void setjComboBoxKorisnik(JComboBox<Object> jComboBoxKorisnik) {
        this.jComboBoxKorisnik = jComboBoxKorisnik;
    }

    public JComboBox<String> getjComboBoxOsiguranje() {
        return jComboBoxOsiguranje;
    }

    public void setjComboBoxOsiguranje(JComboBox<String> jComboBoxOsiguranje) {
        this.jComboBoxOsiguranje = jComboBoxOsiguranje;
    }

    public JComboBox<String> getjComboBoxTipSobe() {
        return jComboBoxTipSobe;
    }

    public void setjComboBoxTipSobe(JComboBox<String> jComboBoxTipSobe) {
        this.jComboBoxTipSobe = jComboBoxTipSobe;
    }

    public JTable getjTableAranzman() {
        return jTableAranzman;
    }

    public void setjTableAranzman(JTable jTableAranzman) {
        this.jTableAranzman = jTableAranzman;
    }

    public JTable getjTableRasporedSoba() {
        return jTableRasporedSoba;
    }

    public void setjTableRasporedSoba(JTable jTableRasporedSoba) {
        this.jTableRasporedSoba = jTableRasporedSoba;
    }

    public JTextField getjTextFieldDatumRezervacije() {
        return jTextFieldDatumRezervacije;
    }

    public void setjTextFieldDatumRezervacije(JTextField jTextFieldDatumRezervacije) {
        this.jTextFieldDatumRezervacije = jTextFieldDatumRezervacije;
    }

    public JTextField getjTextFieldSifraRezervacije() {
        return jTextFieldSifraRezervacije;
    }

    public void setjTextFieldSifraRezervacije(JTextField jTextFieldSifraRezervacije) {
        this.jTextFieldSifraRezervacije = jTextFieldSifraRezervacije;
    }

    public JTextField getjTextFieldUkupnaCena() {
        return jTextFieldUkupnaCena;
    }

    public void setjTextFieldUkupnaCena(JTextField jTextFieldUkupnaCena) {
        this.jTextFieldUkupnaCena = jTextFieldUkupnaCena;
    }

    public JTextField getjTextFieldZaposleni() {
        return jTextFieldZaposleni;
    }

    public void setjTextFieldZaposleni(JTextField jTextFieldZaposleni) {
        this.jTextFieldZaposleni = jTextFieldZaposleni;
    }

    public JTextField getjTextFieldIDRezervacije() {
        return jTextFieldIDRezervacije;
    }

    public void setjTextFieldIDRezervacije(JTextField jTextFieldIDRezervacije) {
        this.jTextFieldIDRezervacije = jTextFieldIDRezervacije;
    }

    public JLabel getjLabelID() {
        return jLabelID;
    }

    public void setjLabelID(JLabel jLabelID) {
        this.jLabelID = jLabelID;
    }

    public JButton getjButtonIzmeni() {
        return jButtonIzmeni;
    }

    public void setjButtonIzmeni(JButton jButtonIzmeni) {
        this.jButtonIzmeni = jButtonIzmeni;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDodajRaspored;
    private javax.swing.JButton jButtonIzmeni;
    private javax.swing.JButton jButtonObrisiRaspored;
    private javax.swing.JButton jButtonOtkazi;
    private javax.swing.JButton jButtonSacuvajRezervaciju;
    private javax.swing.JComboBox<Object> jComboBoxKorisnik;
    private javax.swing.JComboBox<String> jComboBoxOsiguranje;
    private javax.swing.JComboBox<String> jComboBoxTipSobe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableAranzman;
    private javax.swing.JTable jTableRasporedSoba;
    private javax.swing.JTextField jTextFieldDatumRezervacije;
    private javax.swing.JTextField jTextFieldIDRezervacije;
    private javax.swing.JTextField jTextFieldSifraRezervacije;
    private javax.swing.JTextField jTextFieldUkupnaCena;
    private javax.swing.JTextField jTextFieldZaposleni;
    // End of variables declaration//GEN-END:variables

 

   

    public void dodajRasporedBtnAddActionListener(ActionListener actionListener) {
        jButtonDodajRaspored.addActionListener(actionListener);
    }

    public void obrisiRasporedBtnAddActionListener(ActionListener actionListener) {
       jButtonObrisiRaspored.addActionListener(actionListener);
    }

    public void addOtkaziBtnAddActionListener(ActionListener actionListener) {
        jButtonOtkazi.addActionListener(actionListener);
    }

    public void addSacuvajBtnAddActionListener(ActionListener actionListener) {
         jButtonSacuvajRezervaciju.addActionListener(actionListener);
    }

    public void izmeniRezervacijuBtnAddActionListener(ActionListener actionListener) {
        jButtonIzmeni.addActionListener(actionListener);
    }
}
