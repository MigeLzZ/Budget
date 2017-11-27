package com.budget.services;

import com.budget.dao.entities.Category;
import com.budget.dao.entities.Record;

import java.util.List;

public interface IRecordService {
    void addRecord(Record record);

    List<Record> getRecordsByUserId(long id);

    Record getRecordById(long id);

    void deleteRecordById(long id);

    void updateRecordCategoryId(long recordId, long category);
}
