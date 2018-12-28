package hiberspring.domain.dtos.XML;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportDTO {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "clients")
    private Integer clients;

    @XmlElement(name = "branch")
    private String branchName;

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

    @NotNull
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
