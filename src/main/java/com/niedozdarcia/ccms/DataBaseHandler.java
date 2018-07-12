package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.List;

public interface DataBaseHandler {
    void load();
    void save();
    List<String[]> getRecords();

    void setRecords(List<String[]> records);

    String getFilePath();

    void setFilePath(String filePath);

    ArrayList<String[]> getRecordToSave();

    void addRecordToSave(String[] recordToSave);

}
