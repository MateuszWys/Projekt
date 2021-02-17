package m.wysocki.service;

import m.wysocki.domain.Register;
import m.wysocki.domain.RegisterRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("myRegisterDetailsService")
public class MyRegisterDetailsService implements UserDetailsService {
    private RegisterService registerService;

    @Autowired
    public MyRegisterDetailsService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {

        Register register = registerService.findByLogin(login);
        List<GrantedAuthority> authorities = buildUserAuthority(register.getRegisterRole());
        return buildUserForAuthentication(register, authorities);
    }

    private User buildUserForAuthentication(Register register, List<GrantedAuthority> authorities) {

        return new User(register.getLogin(), register.getPassword(),authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<RegisterRole> registerRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        // Build user's authorities
        for (RegisterRole registerRole : registerRoles) {
            setAuths.add(new SimpleGrantedAuthority(registerRole.getRole()));
        }
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return result;
    }
}

