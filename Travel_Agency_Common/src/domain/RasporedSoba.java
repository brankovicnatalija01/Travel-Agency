/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.LinkedList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author natalija
 */
public class RasporedSoba implements AbstractDomainObject {
    private int rasporedSobaID;
    private Rezervacija rezervacija;
    private Korisnik korisnik;
    private String osiguranje;
    private double ukupnaCena;

    public RasporedSoba() {
    }

    public RasporedSoba(int rasporedSobaID, Rezervacija rezervacija, Korisnik korisnik, String osiguranje, double ukupnaCena) {
        this.rasporedSobaID = rasporedSobaID;
        this.rezervacija = rezervacija;
        this.korisnik = korisnik;
        this.osiguranje = osiguranje;
        this.ukupnaCena = ukupnaCena;
    }

    public int getRasporedSobaID() {
        return rasporedSobaID;
    }

    public void setRasporedSobaID(int rasporedSobaID) {
        this.rasporedSobaID = rasporedSobaID;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getOsiguranje() {
        return osiguranje;
    }

    public void setOsiguranje(String osiguranje) {
        this.osiguranje = osiguranje;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    @Override
    public String toString() {
        return "RasporedSoba{" +
                "rasporedSobaID=" + rasporedSobaID +
                ", rezervacija=" + rezervacija +
                ", korisnik=" + korisnik +
                ", osiguranje='" + osiguranje + '\'' +
                ", ukupnaCena=" + ukupnaCena +
                '}';
    }

    @Override
    public String getTableName() {
        return "rasporedSoba";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new LinkedList<>();

        while (resultSet.next()) {
            int rasporedSobaID = resultSet.getInt("rasporedSobaID");
            int rezervacijaID = resultSet.getInt("rezervacija");
            int korisnikID = resultSet.getInt("korisnik");
            String osiguranje = resultSet.getString("osiguranje");
            double ukupnaCena = resultSet.getDouble("ukupnaCena");

            Rezervacija rezervacija = new Rezervacija();
            rezervacija.setRezervacijaID(rezervacijaID);

            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnikID(korisnikID);

            RasporedSoba rasporedSoba = new RasporedSoba(rasporedSobaID, rezervacija, korisnik, osiguranje, ukupnaCena);
            list.add(rasporedSoba);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "rasporedSobaID, rezervacija, korisnik, osiguranje, ukupnaCena";
    }

    @Override
    public String getUnknownValues() {
        return "?, ?, ?, ?, ?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject entity) throws Exception {
        RasporedSoba rs = (RasporedSoba) entity;
        ps.setInt(1, rs.getRasporedSobaID());
        ps.setInt(2, rs.getRezervacija().getRezervacijaID());
        ps.setInt(3, rs.getKorisnik().getKorisnikID());
        ps.setString(4, rs.getOsiguranje());
        ps.setDouble(5, rs.getUkupnaCena());
    }

    @Override
    public String getUpdateQuery() {
        return "rasporedSobaID=" + rasporedSobaID + ", rezervacija=" + rezervacija.getRezervacijaID() + ", korisnik=" + korisnik.getKorisnikID() + ", osiguranje='" + osiguranje + "', ukupnaCena=" + ukupnaCena;
    }

    @Override
    public String getID(AbstractDomainObject entity) {
        RasporedSoba rs = (RasporedSoba) entity;
        return "rasporedSoba.rasporedSobaID=" + rs.getRasporedSobaID();
    }
     
    public String returnID(AbstractDomainObject entity) {
        RasporedSoba rs = (RasporedSoba) entity;
        return "rasporedSoba.rasporedSobaID";
    }
    
    @Override
    public String getOrderCondition() {
        return "rasporedSobaID";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        AbstractDomainObject entity = null;

        if (resultSet.next()) {
            int rasporedSobaID = resultSet.getInt("rasporedSobaID");
            int rezervacijaID = resultSet.getInt("rezervacija");
            int korisnikID = resultSet.getInt("korisnikID");
            String osiguranje = resultSet.getString("osiguranje");
            double ukupnaCena = resultSet.getDouble("ukupnaCena");

            Rezervacija rezervacija = new Rezervacija();
            rezervacija.setRezervacijaID(rezervacijaID);

            Korisnik korisnik = new Korisnik();
            korisnik.setKorisnikID(korisnikID);

            entity = new RasporedSoba(rasporedSobaID, rezervacija, korisnik, osiguranje, ukupnaCena);
        }
        return entity;
    }

    @Override
    public String getCondition(AbstractDomainObject object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
