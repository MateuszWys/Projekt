package m.wysocki.domain;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Set;

@Entity
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    City cityFrom;

    @ManyToOne
    City cityTo;

    @OneToMany(mappedBy = "relation", cascade = CascadeType.ALL)
    Set<RelationTime> relationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public Set<RelationTime> getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(Set<RelationTime> relationTime) {
        this.relationTime = relationTime;
    }
}
