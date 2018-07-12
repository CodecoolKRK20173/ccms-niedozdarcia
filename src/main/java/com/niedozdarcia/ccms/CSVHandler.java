package com.niedozdarcia.ccms;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class CSVHandler implements DataBaseHandler {
    private List<String[]> records;
    private String filePath;
    private ArrayList<String[]> recordToSave;

    public void load(){
        records = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVReader csvReader = new CSVReader(reader);
            records = csvReader.readAll();
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    public void save(){
                recordToSave = new ArrayList<>();
                prepareDataToSave();
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get(filePath));

                    com.opencsv.CSVWriter csvWriter = new CSVWriter(writer,
                            com.opencsv.CSVWriter.DEFAULT_SEPARATOR,
                            com.opencsv.CSVWriter.NO_QUOTE_CHARACTER,
                            com.opencsv.CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                            com.opencsv.CSVWriter.DEFAULT_LINE_END);

                    for (String[] record : recordToSave) {
                        csvWriter.writeNext(record);
                    }

                } catch (IOException e) {
                    System.out.print(e);
                }

            }

    public List<String[]> getRecords() {
        return records;
    }

    public void setRecords(List<String[]> records) {
        this.records = records;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String[]> getRecordToSave() {
        return recordToSave;
    }

    public void addRecordToSave(String[] recordToSave) {
        this.recordToSave.add(recordToSave);
    }

    public abstract void prepareDataToSave();
    public abstract void prepareData();
    }


