package com.budget.dao.repository;

import com.budget.dao.entities.PlannedRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPlannedRecordRepository extends JpaRepository<PlannedRecord, Long> {

    @Query("SELECT DISTINCT r FROM PlannedRecord r where r.user.id = :id")
    List<PlannedRecord> getRecordsByUserId(@Param("id") long id);

    @Modifying
    @Query("update PlannedRecord r set r.category.id = :categoryId where r.id = :id")
    void updateRecordCategory(@Param("id") long id, @Param("categoryId") long category);

}
