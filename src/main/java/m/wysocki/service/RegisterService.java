package m.wysocki.service;

import java.util.List;
import m.wysocki.domain.Register;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

public interface RegisterService {

    public void addRegister(Register reg);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void editRegister(Register reg);


    public List<Register> listRegister();

    @Secured("ROLE_ADMIN")
    public void removeRegister (long id);



    public Register getRegister(long id);

    Register findByLogin(String login);

    void buyTicket(Long id, String username);

    void confirmUser(Register register);
}
