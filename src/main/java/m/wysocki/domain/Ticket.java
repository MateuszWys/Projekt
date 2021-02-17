package m.wysocki.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    RelationTime relationTime;

    @ManyToOne
    private Register register;

    int seatNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RelationTime getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(RelationTime relationTime) {
        this.relationTime = relationTime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
}
