package com.niedozdarcia.ccms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceCSVDao extends CSVHandler {
    private Map<String, ArrayList<String>> attendance;

    public AttendanceCSVDao() {
        setFilePath(HandleCsv.class.getResource("/users/attendance.csv").getPath());
        load();
        prepareData();
    }

    public void prepareData(){
        attendance = new HashMap<>();
        for (String[] record : getRecords()){
            ArrayList<String> studentsAttendance = prepareAttendanceList(record);
            attendance.put(record[0], studentsAttendance);
        }
    }

    private ArrayList<String> prepareAttendanceList(String[] record){
        ArrayList<String> studentsAttendance = new ArrayList<>();
        for (int i = 1, n = record.length - 1; i<n; i++){
            studentsAttendance.add(record[i]);
        }
        return  studentsAttendance;
    }

    public void prepareDataToSave() {
        for (String day : attendance.keySet()) {
            String[] preparedString = prepareArray(day);
            addRecordToSave(preparedString);
        }

    }

    private String[] prepareArray(String day){
        String[] preparedString = new String[attendance.get(day).size()+1];
        preparedString[0] = day;
        int i = 1;
        for (String student : attendance.get(day)){
            preparedString[i] = student;
            i++;
        }
        return  preparedString;
    }


    public Map<String, ArrayList<String>> getAttendance() {
        return attendance;
    }
}
