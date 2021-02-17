package m.wysocki.service;

import m.wysocki.domain.Relation;
import m.wysocki.domain.RelationTime;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface RelationTimeService {
    List<RelationTime> getListOfRelationTimes();

    RelationTime getRelationTime(long relationId);

    List<Relation> listRelation();

    void addRelationTime(RelationTime relationTime);

    void editRelationTime(RelationTime relationTime1);

    void deleteById(long relationId);

    @Secured("ROLE_ADMIN")
    public void removeTicket (long id);
}
