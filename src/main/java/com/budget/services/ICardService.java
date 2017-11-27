package com.budget.services;

import com.budget.dao.entities.Card;
import com.budget.dao.entities.User;

import java.util.List;

public interface ICardService {

    //получаем список карт пользователя
    List<Card> getCardsByUserId(long user);

    Card getCardByCardNumber(String number, long userId);

    void saveCard(Card card);
}
