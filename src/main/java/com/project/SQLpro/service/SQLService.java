package com.project.SQLpro.service;

import com.cohere.api.Cohere;
import com.cohere.api.resources.v2.requests.V2ChatRequest;
import com.cohere.api.types.ChatMessageV2;
import com.cohere.api.types.ChatResponse;
import com.cohere.api.types.UserMessage;
import com.cohere.api.types.UserMessageContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.SQLpro.Repo.SQLRepo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class SQLService {
    private final Cohere cohere;
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON Parser
    @Autowired
    SQLRepo repo;
 
    public SQLService(@Value("${cohere.api.key}") String cohereApiKey) {
        this.cohere = Cohere.builder()
                .token(cohereApiKey)
                .clientName("SpringBoot")
                .build();
    }

    
    public List<Map<String, Object>> getQuery(String query,String table,List<String> columns) throws JsonMappingException, JsonProcessingException {
        
    	ChatMessageV2 userMessage = ChatMessageV2.user(
                UserMessage.builder()
                        .content(UserMessageContent.of("Generate an SQL query based on this request: table name "+table+" '" + query + "'. " +
                                "The database table is `"+ table +"` "+repo.getColumnNamesForTable(table)+"`Show only "+columns+". Pls give easy query" +
                                "Return only the SQL query as plain text, without explanations, formatting, or markdown.dont use \n and all just give query like query"))
                        .build()
        );

        
        V2ChatRequest chatRequest = V2ChatRequest.builder()
                .model("command-r-plus")
                .messages(List.of(userMessage))
                .build();
        ChatResponse response = cohere.v2().chat(chatRequest);
        String jsonResponse = response.toString();
        // Parse JSON response
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode messageNode = rootNode.path("message").path("content");
        String sqlQuery = "";
        if (messageNode.isArray() && messageNode.size() > 0) {
            sqlQuery = messageNode.get(0).path("text").asText(); // Extract query
        }
        return repo.executeCustomQuery(sqlQuery);
    }
}
