package m.wysocki.service;

import m.wysocki.domain.Register;
import m.wysocki.domain.VerificationToken;

public interface VerificationTokenService {
        void addToken(VerificationToken token);
        void removeToken(long id);
        VerificationToken findByVerificationToken(String token);
        VerificationToken findByAppUser(Register register);
}
