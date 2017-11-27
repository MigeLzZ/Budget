package com.budget.dao.repository;

import com.budget.dao.entities.Card;
import com.budget.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT DISTINCT c FROM Card c WHERE c.cardNumber = :cardNumber and c.user.id = :userId")
    Card findByCardNumber(@Param("cardNumber") String cardNumber, @Param("userId") long userId);

    @Query("SELECT DISTINCT c FROM Card c where c.user.id = :user")
    List<Card> findCardByUserId(@Param("user") long user);

}
