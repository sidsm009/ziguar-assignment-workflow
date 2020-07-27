package com.ziguar.mapper.mapToJson;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ziguar.Context;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class JsonWriter {
    private ObjectMapper objectMapper = null;
    private String resourceFolderPath = "C:\\Users\\Sid\\IdeaProjects\\WorkFlow\\resources";
    private String contextFolderName;
    private String contextFileName;
    private String fileName;

    public JsonWriter(String contextFolderName, String contextFileName) {
        this.objectMapper = new ObjectMapper();
        this.contextFolderName = contextFolderName;
        this.contextFileName = contextFileName;
        if(null != contextFolderName && contextFolderName != "" && null != contextFileName && contextFileName != "") {
            fileName = resourceFolderPath+"\\"+contextFolderName+"\\"+contextFileName;
        }
    }

    public void createJson(Context context) throws JsonGenerationException, JsonMappingException, IOException {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter workflow = new StringWriter();
        objectMapper.writeValue(workflow, context);
        if(null != fileName) {
            File file = new File(fileName);
            objectMapper.writeValue(file, context);
        }
    }
}
