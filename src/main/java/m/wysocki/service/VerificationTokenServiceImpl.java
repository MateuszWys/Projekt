package m.wysocki.service;

import m.wysocki.dao.VerificationTokenRepository;
import m.wysocki.domain.Register;
import m.wysocki.domain.VerificationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository registrationTokenRepository) {
        this.verificationTokenRepository = registrationTokenRepository;
    }

    @Transactional
    public void addToken(VerificationToken token) {
        verificationTokenRepository.save(token);
    }

    @Transactional
    public void removeToken(long id) {
        verificationTokenRepository.deleteById(id);
    }

    @Override
    public VerificationToken findByVerificationToken(String token) {
        return verificationTokenRepository.findByVerificationToken(token);
    }

    @Transactional
    public VerificationToken findByAppUser(Register register) {
        return verificationTokenRepository.findByRegister(register);
    }
}
