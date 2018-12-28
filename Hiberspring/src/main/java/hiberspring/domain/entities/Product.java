package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
    //•	Id – an integer.
    //•	Name – a string.
    //•	Clients – an integer.
    //•	Branch – a Branch, could be any Branch.
    private Integer id;
    private String name;
    private Integer clients;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public Integer getClients() {
        return clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Branch.class)
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
