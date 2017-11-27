package com.budget.services;

import com.budget.dao.entities.Place;
import com.budget.dao.repository.IPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlaceService implements IPlaceService {

    private final IPlaceRepository placeRepository;

    @Autowired
    public PlaceService(IPlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    @Override
    @Transactional
    public void addPlace(Place place) {
        placeRepository.saveAndFlush(place);
    }
}
