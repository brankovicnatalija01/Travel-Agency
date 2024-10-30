/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author natalija
 */
public class Destinacija implements AbstractDomainObject{
    private int destinacijaID;
    private String grad;
    private String drzava;

    public Destinacija() {
    }

    public Destinacija(int destinacijaID, String grad, String drzava) {
        this.destinacijaID = destinacijaID;
        this.grad = grad;
        this.drzava = drzava;
    }

    public int getDestinacijaID() {
        return destinacijaID;
    }

    public void setDestinacijaID(int destinacijaID) {
        this.destinacijaID = destinacijaID;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.destinacijaID;
        hash = 61 * hash + Objects.hashCode(this.grad);
        hash = 61 * hash + Objects.hashCode(this.drzava);
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
        final Destinacija other = (Destinacija) obj;
        if (this.destinacijaID != other.destinacijaID) {
            return false;
        }
        if (!Objects.equals(this.grad, other.grad)) {
            return false;
        }
        return Objects.equals(this.drzava, other.drzava);
    }

    

    @Override
    public String toString() {
        return grad + ", " + drzava;
    }
    
    @Override
    public String getTableName() {
        return "destinacija";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new LinkedList<>();
        
        while (resultSet.next()) {
            int destinacijaID = resultSet.getInt("destinacijaID");
            String grad = resultSet.getString("grad");
            String drzava = resultSet.getString("drzava");

            Destinacija destinacija = new Destinacija(destinacijaID, grad, drzava);
            list.add(destinacija);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "grad,drzava";
    }

    @Override
    public String getUnknownValues() {
        return "?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject entity) throws Exception {
        Destinacija destinacija  = (Destinacija) entity;
        ps.setString(1, destinacija.getGrad());
        ps.setString(2, destinacija.getDrzava());
    }

    @Override
    public String getUpdateQuery() {
        return "grad='" + grad +"' , drzava= '" + drzava + "'";
    }

    @Override
    public String getID(AbstractDomainObject entity) {
        Destinacija destinacija  = (Destinacija) entity;
        return "destinacija.destinacijaID=" +  destinacija.getDestinacijaID();
    }

    @Override
    public String getOrderCondition() {
        return "grad";
    }

    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        AbstractDomainObject entity = null;
        if (resultSet.next()) {
            int destinacijaID = resultSet.getInt("destinacijaID");
            String grad = resultSet.getString("grad");
            String drzava = resultSet.getString("drzava");

            entity = new Destinacija(destinacijaID, grad, drzava);
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
