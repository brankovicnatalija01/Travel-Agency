/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author natalija
 */
public interface AbstractDomainObject extends Serializable{
    
    public String getTableName(); 
    
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception; 
    
    public String getAttributeNames(); 
     
    public String getUnknownValues(); 
    
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject object) throws Exception;
    
    public String getUpdateQuery();
    
    public String getID(AbstractDomainObject object);
    
    public String getOrderCondition();
    
    public String getCondition(AbstractDomainObject object);
    
    public AbstractDomainObject getResult(ResultSet rs) throws Exception;
    
    public String returnID(AbstractDomainObject object) throws Exception;
    
    
}
