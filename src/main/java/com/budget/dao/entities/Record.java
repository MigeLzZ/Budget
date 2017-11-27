package com.budget.dao.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Comparator;

@Entity
@Table(name = "records")
public class Record extends GenericEntity{
    @Column(name = "order_date", nullable = false)
    private Date recordDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "places_id")
    private Place place;

    @Column(name = "sum", nullable = false)
    private float sum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categories_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_orders_cards1")
    private Card card;

    @Column(name = "note")
    private String note;

    //переменная для костыля, вывода отсортированного списка расходов
    private Boolean isPlanned = false;

    public Boolean getPlanned() {
        return isPlanned;
    }

    public void setPlanned(Boolean planned) {
        isPlanned = planned;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
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

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
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

    @Override
    public String toString() {
        String record = "";
        record += "дата расхода: " + recordDate;
        if(place != null){record += " место расхода: " + place.getName() + place.getAddress();}
        if(card != null){record += " номер карточки: " + card.getCardNumber();}
        record += " потрачено: " + sum;
        if(note != null){record += " заметки: " + note;}
        record += " категория: " + this.category.getType();
        return record;
    }

    public static Comparator<Record> getCompByDate()
    {
        Comparator comp = new Comparator<Record>(){
            @Override
            public int compare(Record r1, Record r2)
            {
                return r1.getRecordDate().compareTo(r2.getRecordDate());
            }
        };
        return comp;
    }
}
