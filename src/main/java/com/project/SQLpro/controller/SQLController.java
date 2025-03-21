package com.project.SQLpro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.project.SQLpro.Repo.SQLRepo;
import com.project.SQLpro.service.SQLService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class SQLController {
    private final SQLService sqlService;
    private final SQLRepo repo;
    private List<Map<String, Object>> result;
    private String table;
    
    
    public SQLController(SQLService sqlService,SQLRepo repo) {
    	this.repo = repo;
        this.sqlService = sqlService;
        this.result = List.of();
    }
    
    
    @GetMapping("/")
    public String showHomePage(Model model) {
    	List<String> tables = repo.getTableNames();
    	model.addAttribute("tables", tables);
    	return "home";

    }
    
    @PostMapping("/userQuery")
    public String execute(@RequestParam("selectedTable") String tableName,Model model) {
    	this.table = tableName;
    	List<String> columnNames = repo.getColumnNamesForTable(table);
    	model.addAttribute("tableName", tableName);
    	model.addAttribute("columns",columnNames);
        return "index";
    }
    
 

    
    
    @PostMapping("/register")
    public String show(@RequestParam("query") String input,@RequestParam(value = "columns", required = false) List<String> selectedColumns,Model model) throws JsonMappingException, JsonProcessingException {
        System.out.println(selectedColumns);       
    	this.result = sqlService.getQuery(input,table,selectedColumns); 
        model.addAttribute("records",result);
    	return "result";
    }
    
   

    
}
