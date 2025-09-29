package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA25 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
            
            if (rs != null) {

                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();

                while (rs.next()) {
                    JsonObject record = new JsonObject();
                    
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metadata.getColumnName(i);
                        
                        
                        Object columnValue = rs.getObject(i);
                        
                        if (columnValue != null) {
                            
                            columnValue = columnValue.toString(); 
                        }
                        
                        record.put(columnName, columnValue);
                    }
                    
                    records.add(record);
                }

            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}