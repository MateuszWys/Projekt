package m.wysocki.dao;

import m.wysocki.domain.Register;
import m.wysocki.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
    void deleteById(long id);
    VerificationToken findByVerificationToken(String token);
    VerificationToken findByRegister(Register register);
}
