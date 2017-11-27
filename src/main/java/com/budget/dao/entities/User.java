package com.budget.dao.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "user_native")
    @GenericGenerator(name = "user_native", strategy = "native")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private long id;
    @Column(name = "Username", nullable = false, unique = true)
    private String username;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "cash", nullable = false)
    private float cash = 0;
    @Column(name = "Enabled", nullable = false)
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @Column(name = "create_time", nullable = false)
    private Date createDate;

    @Column(name = "mounthly_limit", nullable = false)
    private float mounthlyLimit;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Category> usersCategories;

    public Set<Category> getUsersCategories() {
        return usersCategories;
    }

    public void setUsersCategories(Set<Category> usersCategories) {
        this.usersCategories = usersCategories;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public float getMounthlyLimit() {
        return mounthlyLimit;
    }

    public void setMounthlyLimit(float mounthlyLimit) {
        this.mounthlyLimit = mounthlyLimit;
    }

    @Column(name = "mail", nullable = false)
    private String mail;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private Set<Record> records;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private Set<PlannedRecord> plannedRecords;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Card> cards;

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
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

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getUsername(String username) {
        return this.username;
    }

    public String getPassword(String password) {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean getEnabled(){
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addCash(float cash){
        this.cash += cash;
    }

    public void addRecord(Record record){
        this.records.add(record);
    }

    public void addPlannedRecord(PlannedRecord plannedRecord){
        this.plannedRecords.add(plannedRecord);
    }

    private static Map<Integer, Integer> mounthMap = new HashMap<Integer, Integer>(){{
        put(0,31);
        put(1,28);
        put(2,31);
        put(3,30);
        put(4,31);
        put(5,30);
        put(6,31);
        put(7,31);
        put(8,30);
        put(9,31);
        put(10,30);
        put(11,31);
    }};

    public List<Record> getRecordsByMounth(int mounth,int year1){
        List<Record> mounthRecords = new ArrayList<>();
        for(Record record : records){
                if(record.getRecordDate().getMonth() == mounth && Integer.parseInt(record.getRecordDate().toString().substring(0,4)) == year1){
                mounthRecords.add(record);
            }
        }
        Collections.sort(mounthRecords, Record.getCompByDate());
        return mounthRecords;
    }

    public List<Record> getPlannedRecordsByMounth(int mounth, int year1){
        List<Record> mounthRecords = new ArrayList<>();
        for(PlannedRecord plannedRecord : plannedRecords){
            if(plannedRecord.getDayPosition() == 0){
                Record record = new Record();
                if(plannedRecord.getCard() != null){record.setCard(plannedRecord.getCard());}
                record.setNote(plannedRecord.getNote());
                record.setSum(plannedRecord.getSum());
                record.setRecordDate(plannedRecord.getStartDate());
                record.setCategory(plannedRecord.getCategory());
                record.setUser(plannedRecord.getUser());
                record.setId(plannedRecord.getId());
                record.setPlanned(true);
                if(record.getRecordDate().getMonth() == mounth && Integer.parseInt(record.getRecordDate().toString().substring(0,4)) == year1)
                    mounthRecords.add(record);
            }
            int m = Integer.parseInt(plannedRecord.getStartDate().toString().substring(8));
            if(plannedRecord.getDayPosition() == 7){
                for(int i = 0, day = m, mounth1 = plannedRecord.getStartDate().getMonth(), year = plannedRecord.getStartDate().getYear(); i < plannedRecord.getRepeatsCount(); i++){
                    Record record = new Record();
                    String date = plannedRecord.getStartDate().toString().substring(0,8);
                    if(plannedRecord.getCard() != null){record.setCard(plannedRecord.getCard());}
                    record.setId(plannedRecord.getId());
                    record.setNote(plannedRecord.getNote());
                    record.setSum(plannedRecord.getSum());

                    record.setRecordDate(Date.valueOf(date+day));
                    record.getRecordDate().setMonth(mounth1);
                    record.getRecordDate().setYear(year);
                    record.setCategory(plannedRecord.getCategory());
                    record.setUser(plannedRecord.getUser());
                    record.setPlanned(true);
                    if(record.getRecordDate().getMonth() == mounth && Integer.parseInt(record.getRecordDate().toString().substring(0,4)) == year1)
                        mounthRecords.add(record);
                    day += 7;
                    if(day > mounthMap.get(mounth1)){
                        if(mounth1 + 1 == 12){
                            year++;
                        }
                        day %= mounthMap.get(mounth1);
                        mounth1 = (mounth1 + 1 == 12) ? 0 : mounth1 + 1;
                    }
                }
            }

            if(plannedRecord.getDayPosition() == 30){
                for(int i = 0, mounth1 = plannedRecord.getStartDate().getMonth(), year = plannedRecord.getStartDate().getYear(); i < plannedRecord.getRepeatsCount(); i++){
                    Record record = new Record();
                    if(plannedRecord.getCard() != null){record.setCard(plannedRecord.getCard());}
                    record.setId(plannedRecord.getId());
                    record.setNote(plannedRecord.getNote());
                    record.setSum(plannedRecord.getSum());
                    record.setRecordDate(Date.valueOf(plannedRecord.getStartDate().toString()));
                    record.getRecordDate().setMonth(mounth1);
                    record.getRecordDate().setYear(year);
                    record.setCategory(plannedRecord.getCategory());
                    record.setUser(plannedRecord.getUser());
                    record.setPlanned(true);
                    if(record.getRecordDate().getMonth() == mounth && Integer.parseInt(record.getRecordDate().toString().substring(0,4)) == year1)
                        mounthRecords.add(record);
                    if(mounth1 + 1 >= 12){
                        year++;
                    }
                    mounth1 = ((mounth1 + 1) == 12) ? 0 : mounth1 + 1;
                }
            }
        }
        Collections.sort(mounthRecords, Record.getCompByDate());
        return mounthRecords;
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

    public void updateCategory(Category category){
        for(Category category1 : usersCategories){
            if(category1.getId() == category.getId()){
                usersCategories.remove(category1);
                usersCategories.add(category);
                return;
            }
        }
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public Card getCardByNumber(String number){
        for(Card card : cards){
            if(card.getCardNumber().equals(number)){
                return card;
            }
        }
        return null;
    }

    public void addUserCategory(Category category){
        this.usersCategories.add(category);
    }

    public void deleteCategoryById(long id){
        for(Category category : usersCategories){
            if(category.getId() == id){
                usersCategories.remove(category);
                return;
            }
        }
    }
}
