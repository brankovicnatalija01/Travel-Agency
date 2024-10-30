/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.AbstractDomainObject;
import domain.Korisnik;
import domain.RasporedSoba;
import domain.Rezervacija;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author natalija
 */
public class RepositoryDBGeneric implements DbRepository<AbstractDomainObject>{

    @Override
    public List<AbstractDomainObject> getAll(AbstractDomainObject param) throws Exception {
       List<AbstractDomainObject> list = new ArrayList<>();
    
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + param.getTableName(); 
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = param.getList(resultSet);
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return list;
    }

    @Override
    public void add(AbstractDomainObject param) throws Exception {
         try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "INSERT INTO " + param.getTableName() + " (" + param.getAttributeNames() + ") VALUES(" + param.getUnknownValues() + ")";
            PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(query);
            param.prepareStatement(ps, param);
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void edit(AbstractDomainObject param) throws Exception {
         try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "UPDATE " + param.getTableName() + " SET " + param.getUpdateQuery()+ " WHERE " + param.getID(param);   
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            
        } catch (Exception ex) {
            if (DbConnectionFactory.getInstance().getConnection() != null) {
                DbConnectionFactory.getInstance().getConnection().rollback();
            }
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void delete(AbstractDomainObject param) throws Exception {
         try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "DELETE  FROM " + param.getTableName() + " WHERE " + param.getID(param);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception ex) {
            if (DbConnectionFactory.getInstance().getConnection() != null) {
                DbConnectionFactory.getInstance().getConnection().rollback();
            }
            ex.printStackTrace();
            throw ex;
        }
    }


    @Override
    public AbstractDomainObject getByID(AbstractDomainObject param) throws Exception {
        AbstractDomainObject object = null;
        
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + param.getTableName() + " WHERE " + param.getID(param);
     
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            object = param.getResult(resultSet);
            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return object;
    }

    @Override
    public int returnMaxIndex(AbstractDomainObject param) throws Exception {
        int maxIndex= 0;
        
        try {
            String query = "SELECT MAX( "+ param.returnID(param) + ") AS max FROM " + param.getTableName();
            Statement statement = DbConnectionFactory.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                maxIndex = resultSet.getInt("max");
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return maxIndex + 1;
    }

    @Override
    public List<AbstractDomainObject> filter(AbstractDomainObject object) throws Exception {
       List<AbstractDomainObject> list = null;
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + object.getTableName() + " WHERE " + object.getCondition(object) + " ORDER BY " + object.getOrderCondition();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = object.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public AbstractDomainObject login(AbstractDomainObject object, String username, String password) throws Exception {
        AbstractDomainObject adObject = null;
        
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + object.getTableName() + " WHERE username='" + username + "' AND password='" + password + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            adObject = object.getResult(resultSet);
            resultSet.close();
            statement.close();
            return adObject;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<AbstractDomainObject> getByRezervacijaID(AbstractDomainObject object, int rezervacijaID) throws Exception {
       List<AbstractDomainObject> list = null;
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + object.getTableName() + " WHERE rezervacija=" + rezervacijaID;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = object.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    //@Override
//    public List<AbstractDomainObject> getByKorisnikID(AbstractDomainObject param) {
//        List<AbstractDomainObject> list = new ArrayList<>();
//        Rezervacija r = new Rezervacija();
//        
//       // RasporedSoba rs = new RasporedSoba();
//        
//        Korisnik k = (Korisnik) param;
//        int id = k.getKorisnikID();
//    
//        try {
//            Connection connection = DbConnectionFactory.getInstance().getConnection();
//            String query = "SELECT rezervacijaID, zaposleni, sifraRezervacije, datumRezervacije, tipSobe, aranzman FROM rezervacija JOIN rasporedSoba WHERE korisnik=" + id; 
//            //String query = "SELECT * FROM rasporedSoba WHERE korisnik=" + id;
//         
//            
//            System.out.println("repository.db.impl.RepositoryDBGeneric.getByKorisnikID()" + query);
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            list = r.getList(resultSet);
//            resultSet.close();
//            statement.close();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            try {
//                throw ex;
//            } catch (SQLException ex1) {
//                Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }

    @Override
    public List<AbstractDomainObject> getByKorisnikID(AbstractDomainObject param, int korisnikID) {
         List<AbstractDomainObject> list = null;
         try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + param.getTableName() + " WHERE korisnik=" + korisnikID;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = param.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException ex) {
             try {
                 ex.printStackTrace();
                 throw ex;
             } catch (SQLException ex1) {
                 Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex1);
             }
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDBGeneric.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return null;
    }

 
  
    
}
