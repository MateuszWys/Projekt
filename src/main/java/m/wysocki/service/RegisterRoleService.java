package m.wysocki.service;

import m.wysocki.domain.RegisterRole;
import java.util.List;

public interface RegisterRoleService {

    void addRegisterRole(RegisterRole registerRole);
    List<RegisterRole> listRegisterRole();
    RegisterRole getRegisterRole(long id);

}
