package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.List;

public class AssignmentCSVDAO extends CSVHandler implements AssignmentDAO{
    private List<String> assignments;

    public AssignmentCSVDAO() {
        setFilePath(AssignmentCSVDAO.class.getResource("/users/assignments.csv").getPath());
        load();
        prepareData();
    }

    public void prepareData() {
        assignments = new ArrayList<>();
        for (String[] record : getRecords()) {
            assignments.add(record[0]);
        }
    }

    public void prepareDataToSave() {
        for (String assignment : assignments){
            String[] preparedString = new String[1];
            preparedString[0] = assignment;
            addRecordToSave(preparedString);
        }

    }

    public List<String> getAssignments() {
        return assignments;
    }
}
