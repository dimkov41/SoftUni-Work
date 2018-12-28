package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.JSON.TownImportDTO;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

@Service
@Transactional
public class TownServiceImpl implements TownService {
    private final static String TOWNS_JSON_FILE_PATH = Constants.PATH_TO_FILES + "towns.json";

    private final TownRepository townRepository;
    private final Gson gson;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, FileUtil fileUtil, ValidationUtil validationUtil,ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_JSON_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder builder = new StringBuilder();
        TownImportDTO[] townImportDTOS = this.gson.fromJson(townsFileContent,TownImportDTO[].class);

        Arrays.stream(townImportDTOS).forEach(townImportDTO -> {
            if(!this.validationUtil.isValid(townImportDTO)){
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            Town town = this.modelMapper.map(townImportDTO,Town.class);
            this.townRepository.saveAndFlush(town);

            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,town.getClass().getSimpleName(),town.getName()))
                    .append(System.lineSeparator());
        });

        return builder.toString().trim();
    }
}
