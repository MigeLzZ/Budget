package com.budget.dao.repository;

import com.budget.dao.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlaceRepository extends JpaRepository<Place, Long> {
}
