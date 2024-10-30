/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author natalija
 */
public class Aranzman implements AbstractDomainObject{
    private int aranzmanID;
    private TipAranzmana tipAranzmana;
    private Date datumOd;
    private Date datumDo;
    private Destinacija destinacija;
    
    private String filter;

    public Aranzman() {
    }

    public Aranzman(int aranzmanID, TipAranzmana tipAranzmana, Date datumOd, Date datumDo, Destinacija destinacija) {
        this.aranzmanID = aranzmanID;
        this.tipAranzmana = tipAranzmana;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.destinacija = destinacija;
    }

    public int getAranzmanID() {
        return aranzmanID;
    }

    public void setAranzmanID(int aranzmanID) {
        this.aranzmanID = aranzmanID;
    }

    public TipAranzmana getTipAranzmana() {
        return tipAranzmana;
    }

    public void setTipAranzmana(TipAranzmana tipAranzmana) {
        this.tipAranzmana = tipAranzmana;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }


    public Destinacija getDestinacija() {
        return destinacija;
    }

    public void setDestinacija(Destinacija destinacija) {
        this.destinacija = destinacija;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.aranzmanID;
        hash = 79 * hash + Objects.hashCode(this.tipAranzmana);
        hash = 79 * hash + Objects.hashCode(this.datumOd);
        hash = 79 * hash + Objects.hashCode(this.datumDo);
        hash = 79 * hash + Objects.hashCode(this.destinacija);
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
        final Aranzman other = (Aranzman) obj;
        if (this.aranzmanID != other.aranzmanID) {
            return false;
        }
        if (this.tipAranzmana != other.tipAranzmana) {
            return false;
        }
        if (!Objects.equals(this.datumOd, other.datumOd)) {
            return false;
        }
        if (!Objects.equals(this.datumDo, other.datumDo)) {
            return false;
        }
        return Objects.equals(this.destinacija, other.destinacija);
    }

    
    @Override
    public String toString() {
        return tipAranzmana + ", " + destinacija;
    }
   
   public String getTableName() {
        return "aranzman";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet resultSet) throws Exception {
        List<AbstractDomainObject> list = new LinkedList<>();
        
        while (resultSet.next()) {
            int aranzmanID = resultSet.getInt("aranzmanID");
            TipAranzmana tipAranzmana = TipAranzmana.valueOf(resultSet.getString("tipAranzmana")); 
            Date datumOd = resultSet.getDate("datumOd");
            Date datumDo = resultSet.getDate("datumDo");
            int destinacijaID = resultSet.getInt("destinacija");

            Destinacija destinacija = new Destinacija();
            destinacija.setDestinacijaID(destinacijaID);
         
            Aranzman aranzman = new Aranzman(aranzmanID, tipAranzmana, datumOd, datumDo, destinacija);
            list.add(aranzman);
        }
        return list;
    }

    @Override
    public String getAttributeNames() {
        return "tipAranzmana,datumOd,datumDo,destinacija";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject entity) throws Exception {
        Aranzman a = (Aranzman) entity;
      //  ps.setInt(1, a.getAranzmanID());
        ps.setString(1, String.valueOf(a.getTipAranzmana()));
        ps.setDate(2, a.getDatumOd());
        ps.setDate(3, a.getDatumDo());
        ps.setInt(4, a.getDestinacija().getDestinacijaID());
    }

    @Override
    public String getUpdateQuery() {
        return "tipAranzmana='" + String.valueOf(tipAranzmana)+"' , datumOd= '" + datumOd + "' , datumDo= '" + datumDo + "' , destinacija = '" + destinacija.getDestinacijaID() + "'";
    }

    @Override
    public String getID(AbstractDomainObject entity) {
        Aranzman aranzman = (Aranzman) entity;
        return "aranzman.aranzmanID=" +  aranzman.getAranzmanID();
    }

    @Override
    public AbstractDomainObject getResult(ResultSet resultSet) throws Exception {
        AbstractDomainObject entity = null;
        
        if (resultSet.next()) {
            int aranzmanID = resultSet.getInt("aranzmanID");
            TipAranzmana tipAranzmana = TipAranzmana.valueOf(resultSet.getString("tipAranzmana")); 
            Date datumOd = resultSet.getDate("datumOd");
            Date datumDo = resultSet.getDate("datumDo");
            int destinacijaID = resultSet.getInt("destinacija");

            Destinacija destinacija = new Destinacija();
            destinacija.setDestinacijaID(destinacijaID);
         
            entity = new Aranzman(aranzmanID, tipAranzmana, datumOd, datumDo, destinacija);
        }
        return entity;
    }

    @Override
    public String returnID(AbstractDomainObject entity) throws Exception {
        return "aranzman.aranzmanID";
    }

    @Override
    public String getOrderCondition() {
        return "tipAranzmana";
    }
    
    @Override
    public String getCondition(AbstractDomainObject object) {
       Aranzman aranzman = (Aranzman) object;
       return "tipAranzmana LIKE '%" + aranzman.getFilter() +"%'";
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    
}
