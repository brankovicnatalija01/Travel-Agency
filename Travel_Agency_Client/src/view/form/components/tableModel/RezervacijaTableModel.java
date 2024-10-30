/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.components.tableModel;

import domain.Aranzman;
import domain.Destinacija;
import domain.Rezervacija;
import domain.TipAranzmana;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author natalija
 */
public class RezervacijaTableModel extends AbstractTableModel {
    private List<Rezervacija> lista;
    private final String[] kolone = {"Zaposleni", "Šifra rezervacije", "Datum rezervacije", "Tip sobe", "Aranžman"};

    public RezervacijaTableModel(List<Rezervacija> lista) {
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
        Rezervacija rezervacija = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rezervacija.getZaposleni().toString();
            case 1:
                return rezervacija.getSifraRezervacije();
            case 2:
                return rezervacija.getDatumRezervacije();
            case 3:
                return rezervacija.getTipSobe();
            case 4:
                return rezervacija.getAranzman().toString();
            default:
                return "N/A";
        }
    }

    public List<Rezervacija> getLista() {
        return lista;
    }

    public void setLista(List<Rezervacija> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public Rezervacija getRezervacijaAt(int row) {
        return lista.get(row);
    }
    

}