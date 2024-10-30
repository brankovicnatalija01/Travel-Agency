/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.components.tableModel;

import domain.Aranzman;
import domain.Destinacija;
import domain.TipAranzmana;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author natalija
 */
public class AranzmanTableModel extends AbstractTableModel{
    List<Aranzman> lista;
    String[] kolone = {"tipAranžmana", "DatumOd", "DatumDo", "Destinacija"};

    public AranzmanTableModel(List<Aranzman> lista) {
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
        Aranzman a = lista.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return a.getTipAranzmana();
            case 1: return a.getDatumOd();
            case 2: return a.getDatumDo();
            case 3: return a.getDestinacija().toString();
            default: return "N/A";
        }
     
    }

    public List<Aranzman> getLista() {
        return lista;
    }

    public void setLista(List<Aranzman> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    
    public Aranzman getAranzmanAt(int row){
        return lista.get(row);
    }
   
   
}