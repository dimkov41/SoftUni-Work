package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String position;
    private EmployeeCard card;
    private Branch branch;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @OneToOne(cascade = CascadeType.ALL,targetEntity = EmployeeCard.class)
    public EmployeeCard getCard() {
        return card;
    }

    public void setCard(EmployeeCard card) {
        this.card = card;
    }

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Branch.class)
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
