package com.project.SQLpro.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Repository
public class SQLRepo {
//
    private final JdbcTemplate template;
    @Autowired
    public SQLRepo(JdbcTemplate template) {
        this.template = template;
    }
    
	public List<Map<String, Object>> executeCustomQuery(String query) {
	    System.out.println("Executing Repo function");

	    // Validate if query starts with SELECT
	    if (!isValidQuery(query)) {
	        System.out.println("❌ Invalid Query: " + query);
	        return Collections.emptyList();
	    }

	    try {
	    	//select * from name;
	        return template.queryForList(query);  // Fetching data from DB
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        return Collections.emptyList();
	    }
	}
	
	//get tables names
	
	public List<String> getTableNames() {
	    String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'"; 
	    return template.queryForList(query, String.class);
	}

	//get columns names
	
	public List<String> getColumnNamesForTable(String tableName) {
	    String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
	    
	    return template.queryForList(query, new Object[]{tableName}, String.class);
	}


	
	 private boolean isValidQuery(String query) {
   String regex = "^(?i)\\s*SELECT\\s+.*";
    return Pattern.matches(regex, query);
 }

}   
