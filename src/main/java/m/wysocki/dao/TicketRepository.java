package m.wysocki.dao;

import m.wysocki.domain.RelationTime;
import m.wysocki.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
