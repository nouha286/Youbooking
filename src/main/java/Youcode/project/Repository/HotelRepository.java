package Youcode.project.Repository;

import Youcode.project.Model.Etat;
import Youcode.project.Model.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    public Optional<Hotel> findHotelById(Long id);

   List<Hotel> findHotelsByEtat(Etat etat);

    @Query(value = "SELECT DISTINCT ville FROM Hotel  WHERE etat ='Active' ", nativeQuery = true)
    List<String> getAllVille();

    List<Hotel> findHotelsByVille(String ville);

}
