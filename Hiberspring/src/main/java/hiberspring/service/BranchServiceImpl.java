package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.JSON.BranchesImportDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
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
public class BranchServiceImpl implements BranchService {
    private final static String BRANCHES_JSON_FILE_PATH = Constants.PATH_TO_FILES + "branches.json";

    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, Gson gson, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(BRANCHES_JSON_FILE_PATH);
    }

    @Override
    public String importBranches(String branchesFileContent) {
        StringBuilder builder = new StringBuilder();

        BranchesImportDTO[] branchesImportDTOS = this.gson.fromJson(branchesFileContent,BranchesImportDTO[].class);
        Arrays.stream(branchesImportDTOS).forEach(branchesImportDTO -> {

            Town town = this.townRepository.findByName(branchesImportDTO.getTown()).orElse(null);
            if(town == null || !this.validationUtil.isValid(branchesImportDTO)){
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            Branch branch = this.modelMapper.map(branchesImportDTO,Branch.class);
            branch.setTown(town);
            this.branchRepository.saveAndFlush(branch);
            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,branch.getClass().getSimpleName(),branch.getName()))
            .append(System.lineSeparator());
        });

        return builder.toString().trim();
    }
}
