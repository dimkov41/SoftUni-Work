package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.JSON.EmployeeCardsDTOImports;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;

@Service
@Transactional
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private final static String EMPLOYEE_CARDS_JSON_FILE_PATH = Constants.PATH_TO_FILES + "employee-cards.json";

    private final EmployeeCardRepository employeeCardRepository;
    private final Gson gson;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, Gson gson, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.employeeCardRepository = employeeCardRepository;
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEE_CARDS_JSON_FILE_PATH);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) {
        StringBuilder builder = new StringBuilder();
        EmployeeCardsDTOImports[] employeeCardsDTOS = this.gson.fromJson(employeeCardsFileContent, EmployeeCardsDTOImports[].class);

        Arrays.stream(employeeCardsDTOS).forEach(employeeCardsDTO -> {

            EmployeeCard employeeCard = this.employeeCardRepository.findByNumber(employeeCardsDTO.getNumber()).orElse(null);
            if (employeeCard != null || !this.validationUtil.isValid(employeeCardsDTO)) {
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            employeeCard = this.modelMapper.map(employeeCardsDTO, EmployeeCard.class);
            this.employeeCardRepository.saveAndFlush(employeeCard);
            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Employee Card", employeeCard.getNumber()))
                    .append(System.lineSeparator());
        });

        return builder.toString().trim();
    }
}
