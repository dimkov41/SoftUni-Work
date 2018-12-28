package hiberspring.service;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.XML.EmployeeImportRootDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final static String EMPLOYEES_XML_FILE_PATH = Constants.PATH_TO_FILES + "employees.xml";

    private final EmployeeRepository employeeRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_XML_FILE_PATH);
    }

    @Override
    public String importEmployees() throws JAXBException {
        StringBuilder builder = new StringBuilder();

        EmployeeImportRootDTO employeeImportRootDTO = this.xmlParser.parseXml(EmployeeImportRootDTO.class, EMPLOYEES_XML_FILE_PATH);

        Arrays.stream(employeeImportRootDTO.getEmployeeImportDTOS()).forEach(employeeImportDTO -> {
            Branch branch = this.branchRepository.findByName(employeeImportDTO.getBranchName()).orElse(null);
            EmployeeCard employeeCard = this.employeeCardRepository.findByNumber(employeeImportDTO.getCardNumber()).orElse(null);
            Employee employee = this.employeeRepository.findByCard(employeeCard).orElse(null);

            if (branch == null || employeeCard == null || employee != null || !this.validationUtil.isValid(employeeImportDTO)) {
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            employee = this.modelMapper.map(employeeImportDTO, Employee.class);
            employee.setCard(employeeCard);
            employee.setBranch(branch);
            this.employeeRepository.saveAndFlush(employee);

            builder.append(
                    String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                            employee.getClass().getSimpleName(),
                            employee.getFirstName() + " " + employee.getLastName())
            )
                    .append(System.lineSeparator());


        });

        return builder.toString().trim();
    }

    @Override
    public String exportProductiveEmployees() {
        StringBuilder builder = new StringBuilder();
        List<Employee> employeeList = this.employeeRepository.exportProductiveEmployees();

        employeeList.forEach(employee -> {

            if (employee.getBranch().getProducts().size() > 0) {
                String fullName = employee.getFirstName() + " " + employee.getLastName();

                builder.append(String.format("Name: %s", fullName))
                        .append(System.lineSeparator())
                        .append(String.format("Position: %s", employee.getPosition()))
                        .append(System.lineSeparator())
                        .append(String.format("Card Number: %s", employee.getCard().getNumber()))
                        .append(System.lineSeparator())
                        .append("-------------------------")
                        .append(System.lineSeparator());
            }
        });

        return builder.toString().trim();
    }
}
