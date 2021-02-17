package m.wysocki.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import m.wysocki.dao.*;
import m.wysocki.domain.Register;
import m.wysocki.domain.RegisterRole;
import m.wysocki.domain.Ticket;
import m.wysocki.domain.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegisterServiceImpl implements RegisterService{

    private RegisterRepository registerRepository;
    private RegisterRoleRepository registerRoleRepository;
    private TicketRepository ticketRepository;
    private RelationTimeRepository relationTimeRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private RelationRepository relationRepository;

    @Autowired
    public RegisterServiceImpl(RegisterRepository registerRepository, RegisterRoleRepository registerRoleRepository,
                               TicketRepository ticketRepository, RelationTimeRepository relationTimeRepository,
                               VerificationTokenRepository verificationTokenRepository, RelationRepository relationRepository) {
        this.registerRepository = registerRepository;
        this.registerRoleRepository = registerRoleRepository;
        this.ticketRepository = ticketRepository;
        this.relationTimeRepository = relationTimeRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.relationRepository = relationRepository;
    }

    @Transactional
    public void addRegister(Register register) {
        Set<RegisterRole> roles = new HashSet<>();
        RegisterRole role = registerRoleRepository.findByRole("ROLE_USER");
        roles.add(role);
        register.setRegisterRole(roles);
        register.setPassword(hashPassword(register.getPassword()));
        registerRoleRepository.save(register);;
    }

    @Transactional
    public void editRegister(Register register) {
        register.getRegisterRole().add(registerRoleRepository.findByRole("ROLE_USER"));
        register.setPassword(hashPassword(register.getPassword()));
        registerRepository.save(register);
    }

    @Transactional
    public List<Register> listRegister() {
        return registerRepository.findAll();
    }

    @Transactional
    public void removeRegister(long id) {
        registerRepository.delete(id);
    }


    @Transactional
    public Register getRegister(long id) {
        return registerRepository.findById(id);
    }

    @Override
    @Transactional
    public Register findByLogin(String login) {
        return registerRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void confirmUser(Register register) {
        registerRepository.save(register);
    }

    @Override
    @Transactional
    public void buyTicket(Long id, String username) {
        Register register = registerRepository.findByLogin(username);
        Set<Ticket> tickets;
        if(register.getTicket() != null){
            tickets = register.getTicket();
        }else
            tickets = new HashSet<>();

        Ticket ticket = new Ticket();
        for (Ticket tick:
             tickets) {
            if(tick.getRelationTime().getId() == id && tick.getSeatNumber() < 4){
                tick.setSeatNumber(tick.getSeatNumber()+1);
                return;
            } else if(tick.getRelationTime().getId() == id && tick.getSeatNumber() == 3){
                return;
            }
        }
        ticket.setRelationTime(relationTimeRepository.getOne(id));
        ticket.setSeatNumber(1);
        ticket.setRegister(register);
        ticket = ticketRepository.save(ticket);

        tickets.add(ticket);
        register.setTicket(tickets);
        registerRepository.save(register);
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}



