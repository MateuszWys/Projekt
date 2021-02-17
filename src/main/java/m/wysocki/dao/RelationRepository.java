package m.wysocki.dao;

import m.wysocki.domain.City;
import m.wysocki.domain.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<Relation, Long> {
    Relation findById(long id);
    Relation findByCityToAndCityFrom(City to, City from);
}
