/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.components.tableModel;

import domain.RasporedSoba;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author natalija
 */
public class RasporedSobaTableModel extends AbstractTableModel{
    List<RasporedSoba> lista;
    String[] kolone = {"Korisnik", "Osiguranje", "Ukupna cena"};

    public RasporedSobaTableModel(List<RasporedSoba> lista) {
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
        RasporedSoba r = lista.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return r.getKorisnik().toString();
            case 1: return r.getOsiguranje();
            case 2: return r.getUkupnaCena();
            default: return "N/A";
        }
     
    }

    public void dodajRaspored(RasporedSoba rs) {
        lista.add(rs);
        fireTableDataChanged();
    }
    
    public void obrisiRaspored(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }
    
    public RasporedSoba getRasporedAt(int row) {
        return lista.get(row);
    }
    
    public List<RasporedSoba> getLista() {
        return lista;
    }

    public void setLista(List<RasporedSoba> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

 
  public void osvezi() {
      fireTableDataChanged();
  }
    
}
