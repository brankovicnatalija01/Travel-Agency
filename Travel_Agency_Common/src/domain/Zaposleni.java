/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author natalija
 */
public class Zaposleni implements AbstractDomainObject{
    private int zaposleniID;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Zaposleni() {
    }

    public Zaposleni(int zaposleniID, String ime, String prezime, String username, String password) {
        this.zaposleniID = zaposleniID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public int getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.zaposleniID;
        hash = 59 * hash + Objects.hashCode(this.ime);
        hash = 59 * hash + Objects.hashCode(this.prezime);
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.password);
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
        final Zaposleni other = (Zaposleni) obj;
        if (this.zaposleniID != other.zaposleniID) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }



    @Override
    public String getTableName() {
        return "zaposleni";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new LinkedList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("zaposleniID");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            Zaposleni zaposleni = new Zaposleni(id, ime, prezime, username, password);
            list.add(zaposleni);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "ime,prezime,username,password";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject entity) throws Exception {
        Zaposleni zaposleni = (Zaposleni) entity;
        ps.setString(1, zaposleni.getIme());
        ps.setString(2, zaposleni.getPrezime());
        ps.setString(3, zaposleni.getUsername());
        ps.setString(4, zaposleni.getPassword());
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getID(AbstractDomainObject entity) {
        Zaposleni zaposleni = (Zaposleni) entity;
        return "zaposleni.zaposleniID= " + zaposleni.getZaposleniID();      
    }

    @Override
    public String getOrderCondition() {
        return "ime";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        AbstractDomainObject entity = null;
        if (resultSet.next()) {
            int id = resultSet.getInt("zaposleniID");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            entity = new Zaposleni(id, ime, prezime, username, password);
        }
        return entity;
    }

    @Override
    public String returnID(AbstractDomainObject entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCondition(AbstractDomainObject object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
