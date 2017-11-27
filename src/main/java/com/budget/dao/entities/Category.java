package com.budget.dao.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends GenericEntity {

    public Category() {
    }

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    private Set<Record> records;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    private Set<PlannedRecord> plannedRecords;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    public Set<PlannedRecord> getPlannedRecords() {
        return plannedRecords;
    }

    public void setPlannedRecords(Set<PlannedRecord> plannedRecords) {
        this.plannedRecords = plannedRecords;
    }

    public void addRecord(Record record){
        this.records.add(record);
    }

    public void addPlannedRecords(PlannedRecord plannedRecord){
        this.plannedRecords.add(plannedRecord);
    }

    public boolean deleteRecordById(long id){
        boolean complete = false;
        for (Record record : records){
            if(record.getId() == id){
                return records.remove(record);
            }
        }
        return complete;
    }

    public PlannedRecord getPlannedRecordById(long id){
        for(PlannedRecord plannedRecord : plannedRecords){
            if(plannedRecord.getId() == id){
                return plannedRecord;
            }
        }
        return null;
    }

    public void updateRecord(Record record){
        for(Record record1 : records){
            if(record1.getId() == record.getId()){
                records.remove(record1);
                records.add(record);
                return;
            }
        }
    }

    public void updatePlannedRecord(PlannedRecord plannedRecord){
        for(PlannedRecord record : plannedRecords){
            if(record.getId() == plannedRecord.getId()){
                plannedRecords.remove(record);
                plannedRecords.add(plannedRecord);
                return;
            }
        }
    }

    public boolean deletePlannedRecordById(long id){
        boolean complete = false;
        for (PlannedRecord plannedRecord : plannedRecords){
            if(plannedRecord.getId() == id){
                return plannedRecords.remove(plannedRecord);
            }
        }
        return complete;
    }
}
