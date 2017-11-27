package com.budget.dao.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Comparator;

@Entity
@Table(name = "planned_records")
public class PlannedRecord extends GenericEntity{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "places_id")
    private Place place;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categories_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_planned_records_cards1")
    private Card card;

    @Column(name = "sum", nullable = false)
    private float sum;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    //Позиция дня в месяце
    @Column(name = "paymentDayPos", nullable = false)
    private int dayPosition;

    @Column(name = "note")
    private String note;

    @Column(name = "repeats", nullable = false)
    private long repeatsCount;

    public long getRepeatsCount() {
        return repeatsCount;
    }

    public void setRepeatsCount(long repeatsCount) {
        this.repeatsCount = repeatsCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDayPosition() {
        return dayPosition;
    }

    public void setDayPosition(int dayPosition) {
        this.dayPosition = dayPosition;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {

        String record = "";
        String period = "";
        switch (dayPosition){
            case 0: period = " без повторений"; break;
            case 7: period = " неделя"; break;
            case 30: period = " месяц"; break;
        }

        record += "сумма: " + sum;
        record += " категория: " + category.getType();
        record += " дата начала: " + startDate.toString();
        record += " период повторений: " + period;
        record += " заметка: " + note;
        return record;
    }
}
