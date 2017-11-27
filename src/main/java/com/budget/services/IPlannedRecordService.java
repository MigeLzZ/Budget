package com.budget.services;

import com.budget.dao.entities.PlannedRecord;

import java.util.List;

public interface IPlannedRecordService {

    void savePlannedRecord(PlannedRecord plannedRecord);

    List<PlannedRecord> getPlannedRecordsByUserId(long id);

    PlannedRecord getPlannedRecordById(long id);

    void deletePlannedRecordById(long id);

    void updatePlannedRecord(long recordId, long categoryId);

}
