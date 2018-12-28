package hiberspring.domain.dtos.XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeImportRootDTO {

    @XmlElement(name = "employee")
    private EmployeeImportDTO[] employeeImportDTOS;

    public EmployeeImportDTO[] getEmployeeImportDTOS() {
        return employeeImportDTOS;
    }

    public void setEmployeeImportDTOS(EmployeeImportDTO[] employeeImportDTOS) {
        this.employeeImportDTOS = employeeImportDTOS;
    }
}
