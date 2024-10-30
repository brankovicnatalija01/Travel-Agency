/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.components.tableModel;

import domain.Korisnik;
import java.awt.Font;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author natalija
 */
public class KorisnikTableModel extends AbstractTableModel{
    List<Korisnik> lista;
    String[] kolone = {"ime", "prezime", "telefon", "email"};

    public KorisnikTableModel(List<Korisnik> lista) {
        this.lista = lista;
  
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
       
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = lista.get(rowIndex);
        
        switch(columnIndex) {
            //case 0: return k.getKorisnikID();
            case 0: return k.getIme();
            case 1: return k.getPrezime();
            case 2: return k.getTelefon();
            case 3: return k.getEmail();
            default: return "N/A";
        }
     
    }

    public List<Korisnik> getLista() {
        return lista;
    }

    public void setLista(List<Korisnik> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }


    
}