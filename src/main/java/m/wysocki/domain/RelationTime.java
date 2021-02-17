package m.wysocki.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class RelationTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne(cascade = CascadeType.ALL)
    Relation relation;

    @OneToMany(cascade = CascadeType.ALL)
    Set<Ticket> tickets;

    @Basic
    @Temporal(TemporalType.TIME)
    Date timeTo;
    @Basic
    @Temporal(TemporalType.TIME)
    Date timeFrom;

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

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
