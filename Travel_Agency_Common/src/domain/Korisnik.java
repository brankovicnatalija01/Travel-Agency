/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

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
public class Korisnik implements AbstractDomainObject{
    private int korisnikID;
    private String ime;
    private String prezime;
    private String telefon;
    private String email;

    public Korisnik() {
    }

    public Korisnik(int korisnikID, String ime, String prezime, String telefon, String email) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  ime + " " + prezime ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.korisnikID;
        hash = 97 * hash + Objects.hashCode(this.ime);
        hash = 97 * hash + Objects.hashCode(this.prezime);
        hash = 97 * hash + Objects.hashCode(this.telefon);
        hash = 97 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) obj;
        if (this.korisnikID != other.korisnikID) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.telefon, other.telefon)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String getTableName() {
        return "korisnik";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new LinkedList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("korisnikID");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String telefon = resultSet.getString("telefon");
            String email = resultSet.getString("email");

            Korisnik korisnik = new Korisnik(id, ime, prezime, telefon, email);
            list.add(korisnik);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "ime,prezime,telefon,email";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject object) throws Exception {
        Korisnik korisnik = (Korisnik) object;
        ps.setString(1, korisnik.getIme());
        ps.setString(2, korisnik.getPrezime());
        ps.setString(3, korisnik.getTelefon());
        ps.setString(4, korisnik.getEmail());
    }

    @Override
    public String getUpdateQuery() {
        return "ime='" + ime +"' , prezime= '" + prezime + "' , telefon= '" + telefon + "' , email = '" + email + "'";
    }

    @Override
    public String getID(AbstractDomainObject object) {
        Korisnik korisnik = (Korisnik) object;
        return "korisnik.korisnikID=" +  korisnik.getKorisnikID();
    }

    @Override
    public String getOrderCondition() {
        return "ime";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        AbstractDomainObject entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt("korisnikID");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String telefon = resultSet.getString("telefon");
            String email = resultSet.getString("email");

            entity = new Korisnik(id, ime, prezime, telefon, email);
        }
        return entity;
    }

    @Override
    public String returnID(AbstractDomainObject object) throws Exception {
        return "korisnik.korisnikID";
    }

    @Override
    public String getCondition(AbstractDomainObject object) {
        Korisnik korisnik = (Korisnik) object;
        return "ime LIKE '%" + korisnik.getIme() +"%'";
    }

    
}
