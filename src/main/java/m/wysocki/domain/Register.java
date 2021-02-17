package m.wysocki.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="register")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private int version;

    @NotNull
    @Column(name="firstName", nullable=false)
    private String firstName;

    private String lastName;
    @NotNull
    private String email;
    @Pattern(regexp = "[+][0-9]{2}[-][0-9]{3}[-][0-9]{3}[-][0-9]{3}", message = "Prawidłowy format numeru telefonu: +XX-XXX-XXX-XXX")
    private String telephone;
    @NotNull
    @Column(unique = true)
    private String login;
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RegisterRole> registerRole = new HashSet<>(0);

    @OneToMany(mappedBy = "register", fetch = FetchType.EAGER)
    private Set<Ticket> ticket = new HashSet<>(0);

    @OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
    private VerificationToken verificationToken;

    private boolean isActive;

    public Set<Ticket> getTicket() {
        return ticket;
    }
    public void setTicket(Set<Ticket> ticket) {
        this.ticket = ticket;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<RegisterRole> getRegisterRole() {
        return registerRole;
    }
    public void setRegisterRole(Set<RegisterRole> registerRole) {
        this.registerRole = registerRole;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }
}

