package m.wysocki.dao;

import m.wysocki.domain.Register;
import m.wysocki.domain.RegisterRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RegisterRoleRepository extends JpaRepository<RegisterRole, Long> {
    RegisterRole findByRole(String role);

    void save(Register register);
}
