package m.wysocki.domain;

import javax.persistence.*;

@Entity
@Table(name="registerrole")
public class RegisterRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
