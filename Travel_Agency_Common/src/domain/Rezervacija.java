/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author natalija
 */


public class Rezervacija implements AbstractDomainObject {
    private int rezervacijaID;
    private Zaposleni zaposleni;
    private String sifraRezervacije;
    private Date datumRezervacije;
    private String tipSobe;
    private Aranzman aranzman;
    
    private List<RasporedSoba> rasporediSoba;

    public Rezervacija() {
        rasporediSoba = new ArrayList<>();
    }

    public Rezervacija(int rezervacijaID, Zaposleni zaposleni, String sifraRezervacije, Date datumRezervacije, String tipSobe, Aranzman aranzman, List<RasporedSoba> rasporediSoba) {
        this.rezervacijaID = rezervacijaID;
        this.zaposleni = zaposleni;
        this.sifraRezervacije = sifraRezervacije;
        this.datumRezervacije = datumRezervacije;
        this.tipSobe = tipSobe;
        this.aranzman = aranzman;
        this.rasporediSoba = rasporediSoba;
    }

    public List<RasporedSoba> getRasporediSoba() {
        return rasporediSoba;
    }

    public void setRasporediSoba(List<RasporedSoba> rasporediSoba) {
        this.rasporediSoba = rasporediSoba;
    }

    

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public String getSifraRezervacije() {
        return sifraRezervacije;
    }

    public void setSifraRezervacije(String sifraRezervacije) {
        this.sifraRezervacije = sifraRezervacije;
    }

    public Date getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(Date datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public String getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(String tipSobe) {
        this.tipSobe = tipSobe;
    }

    public Aranzman getAranzman() {
        return aranzman;
    }

    public void setAranzman(Aranzman aranzman) {
        this.aranzman = aranzman;
    }

    @Override
    public String toString() {
        return sifraRezervacije;
    }

    @Override
    public String getTableName() {
        return "rezervacija";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new LinkedList<>();

        while (resultSet.next()) {
            int rezervacijaID = resultSet.getInt("rezervacijaID");
            int zaposleniID = resultSet.getInt("zaposleni");
            String sifraRezervacije = resultSet.getString("sifraRezervacije");
            Date datumRezervacije = resultSet.getDate("datumRezervacije");
            String tipSobe = resultSet.getString("tipSobe");
            int aranzmanID = resultSet.getInt("aranzman");

            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setZaposleniID(zaposleniID);

            Aranzman aranzman = new Aranzman();
            aranzman.setAranzmanID(aranzmanID);

            Rezervacija rezervacija = new Rezervacija(rezervacijaID, zaposleni, sifraRezervacije, datumRezervacije, tipSobe, aranzman, new LinkedList<>());
            list.add(rezervacija);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "rezervacijaID,zaposleni,sifraRezervacije,datumRezervacije,tipSobe,aranzman";
    }

    @Override
    public String getUnknownValues() {
        return "?, ?, ?, ?, ?, ?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject entity) throws Exception {
        Rezervacija r = (Rezervacija) entity;
        ps.setInt(1, r.getRezervacijaID());
        ps.setInt(2, r.getZaposleni().getZaposleniID());
        ps.setString(3, r.getSifraRezervacije());
        ps.setDate(4, new java.sql.Date(r.getDatumRezervacije().getTime()));
        ps.setString(5, r.getTipSobe());
        ps.setInt(6, r.getAranzman().getAranzmanID());
    }

    @Override
    public String getUpdateQuery() {
        return "zaposleni= ' " + zaposleni.getZaposleniID() + "', sifraRezervacije='" + sifraRezervacije + "', datumRezervacije='" + datumRezervacije + "', tipSobe='" + tipSobe + "', aranzman= '" + aranzman.getAranzmanID() +"'" ;
    }

    @Override
    public String getID(AbstractDomainObject entity) {
        Rezervacija rezervacija = (Rezervacija) entity;
        return "rezervacija.rezervacijaID=" + rezervacija.getRezervacijaID();
    }


    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        AbstractDomainObject entity = null;

        if (resultSet.next()) {
            int rezervacijaID = resultSet.getInt("rezervacijaID");
            int zaposleniID = resultSet.getInt("zaposleni");
            String sifraRezervacije = resultSet.getString("sifraRezervacije");
            Date datumRezervacije = resultSet.getDate("datumRezervacije");
            String tipSobe = resultSet.getString("tipSobe");
            int aranzmanID = resultSet.getInt("aranzman");

            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setZaposleniID(zaposleniID);

            Aranzman aranzman = new Aranzman();
            aranzman.setAranzmanID(aranzmanID);

            entity = new Rezervacija(rezervacijaID, zaposleni, sifraRezervacije, datumRezervacije, tipSobe, aranzman, new LinkedList<>());
        }
        return entity;
    }

    @Override
    public String returnID(AbstractDomainObject entity) throws Exception {
        Rezervacija rezervacija = (Rezervacija) entity;
        return "rezervacija.rezervacijaID";  
    }

    @Override
    public String getOrderCondition() {
        return "sifraRezervacije";
    }
    
    @Override
    public String getCondition(AbstractDomainObject object) {
        Rezervacija rezervacija = (Rezervacija) object;
        return "sifraRezervacije LIKE '%" + rezervacija.getSifraRezervacije() +"%'";
    }
}