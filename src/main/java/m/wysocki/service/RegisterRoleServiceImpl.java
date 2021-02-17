package m.wysocki.service;

import m.wysocki.dao.RegisterRoleRepository;
import m.wysocki.domain.RegisterRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RegisterRoleServiceImpl implements RegisterRoleService {

    private RegisterRoleRepository registerRoleRepository;

    @Autowired
    public RegisterRoleServiceImpl(RegisterRoleRepository registerRoleRepository) {
         this.registerRoleRepository = registerRoleRepository;
    }

    @Transactional
    public void addRegisterRole(RegisterRole registerRole) {
        registerRoleRepository.save(registerRole);
    }

    @Override
    public List<RegisterRole> listRegisterRole() {
        return registerRoleRepository.findAll();
    }

    @Override
    public RegisterRole getRegisterRole(long id) {
        return registerRoleRepository.getOne(id);
    }
}