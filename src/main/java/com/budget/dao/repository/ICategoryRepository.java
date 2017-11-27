package com.budget.dao.repository;

import com.budget.dao.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT DISTINCT c FROM Category c WHERE c.type = :type")
    Category findByType(@Param("type") String type);

    @Query("SELECT DISTINCT c FROM Category c WHERE c.type = :type and c.user.id = :userId")
    Category findByTypeAndUser(@Param("type") String type, @Param("userId") long userId);

    @Query("SELECT DISTINCT c FROM Category c WHERE c.type = :type and c.user.id is null")
    Category findByTypeAndUser(@Param("type") String type);

    @Query("SELECT DISTINCT c FROM Category c WHERE c.user is null")
    List<Category> getStandartCategories();
}
