package m.wysocki.dao;

import java.util.List;
import javax.transaction.Transactional;

import m.wysocki.domain.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import m.wysocki.domain.Register;

@Transactional
@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Register> findByEmail(String email);
    List<Register> findByLastName(String lastName);
    Register findById(long id);
    Register findByLogin(String login);

}
