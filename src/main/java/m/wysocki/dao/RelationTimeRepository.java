package m.wysocki.dao;

import m.wysocki.domain.Register;
import m.wysocki.domain.Relation;
import m.wysocki.domain.RelationTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationTimeRepository extends JpaRepository<RelationTime, Long> {
    RelationTime findById(long relationId);
}
