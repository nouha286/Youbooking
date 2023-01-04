package Youcode.project.Repository;

import Youcode.project.Model.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    public Optional<Hotel> findHotelById(Long id);

    @Query(value = "SELECT * FROM Hotel WHERE etat =Active ", nativeQuery = true)
    public List<Hotel> findHotels();

    @Query(value = "SELECT * FROM Hotel WHERE etat =Desactive ", nativeQuery = true)
    List<Hotel> findHotelsDeleted();


}
