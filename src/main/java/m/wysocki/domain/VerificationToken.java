package m.wysocki.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String verificationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Register register;

    public VerificationToken(Register register) {
        this.register = register;
        createdDate = new Date();
        verificationToken = UUID.randomUUID().toString();
    }

    public VerificationToken() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
}
