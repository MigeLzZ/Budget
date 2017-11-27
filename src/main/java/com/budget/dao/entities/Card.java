package com.budget.dao.entities;

import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card extends GenericEntity{

    public Card() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "cash")
    private float cash;

    public User getUser() {
        return user;
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

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "card")
    private Set<Record> records;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "card")
    private Set<PlannedRecord> plannedRecords;


    public void setUser(User user) {
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {

        String result = "Номер карты: " + cardNumber;


        return result;
    }
    public void addRecord(Record record){
        this.records.add(record);
    }

    public void addPlannedRecord(PlannedRecord plannedRecord){
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

    public boolean deletePlannedRecordById(long id){
        boolean complete = false;
        for (PlannedRecord plannedRecord : plannedRecords){
            if(plannedRecord.getId() == id){
                return plannedRecords.remove(plannedRecord);
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

    public void updatePlannedRecord(PlannedRecord plannedRecord){
        for(PlannedRecord record : plannedRecords){
            if(record.getId() == plannedRecord.getId()){
                plannedRecords.remove(record);
                plannedRecords.add(plannedRecord);
                return;
            }
        }
    }

    public Record getRecordById(long id){
        for (Record record : records){
            if(record.getId() == id){
                return record;
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

}
