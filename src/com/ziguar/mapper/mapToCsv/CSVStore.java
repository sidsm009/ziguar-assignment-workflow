package com.ziguar.mapper.mapToCsv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVStore {

    String file;
    CSVWriter writer = null;
    CSVReader reader = null;

    public CSVStore() {

    }

    public CSVStore(String fileName, String filePath) {
        this.file = filePath+"/"+fileName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public CSVWriter getWriter() throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        writer = new CSVWriter(fileWriter);
        return writer;
    }

    public CSVReader getReader() throws IOException {
        FileReader fileReader = new FileReader(file);
        reader = new CSVReader(fileReader);
        return reader;
    }

    public CSVReader getReaderHeaderSkipped() throws IOException {
        FileReader fileReader = new FileReader(file);
        reader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        return reader;
    }

    public boolean fileExists() {
        File file = new File(this.file);
        return file.exists();
    }
}
