package com.budget.services;

import com.budget.dao.entities.Card;
import com.budget.dao.entities.User;
import com.budget.dao.repository.ICardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CardService implements ICardService{

    private final ICardRepository cardRepository;

    @Autowired
    public CardService(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    @Transactional
    public List<Card> getCardsByUserId(long user) {
        return cardRepository.findCardByUserId(user);
    }

    @Override
    @Transactional
    public Card getCardByCardNumber(String number, long userId) {
        return cardRepository.findByCardNumber(number, userId);
    }

    @Override
    @Transactional
    public void saveCard(Card card) {
        cardRepository.saveAndFlush(card);
    }
}
