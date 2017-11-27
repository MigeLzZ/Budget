package com.budget.dao.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "places")
public class Place extends GenericEntity {

    public Place() {
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "place", cascade = CascadeType.ALL)
    private Set<Record> records;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "place", cascade = CascadeType.ALL)
    private Set<PlannedRecord> plannedRecords;

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

    public Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean deleteRecordById(long id){
        boolean complete = false;
        for (Record record : records){
            if(record.getId() == id){
                complete = records.remove(record);
            }
        }
        return complete;
    }
}
