package com.niedozdarcia.ccms;

import java.util.List;

public interface AssignmentDAO {
    void prepareData();
    void prepareDataToSave();
    List<String> getAssignments();
}
