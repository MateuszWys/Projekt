package m.wysocki.dao;

import m.wysocki.domain.City;
import m.wysocki.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
}
