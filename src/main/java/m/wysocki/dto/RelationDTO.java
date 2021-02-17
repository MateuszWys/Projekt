package m.wysocki.dto;

import m.wysocki.domain.Relation;

public class RelationDTO {
    long id;
    Relation relation;
    String timeFrom;
    String timeTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }
}
