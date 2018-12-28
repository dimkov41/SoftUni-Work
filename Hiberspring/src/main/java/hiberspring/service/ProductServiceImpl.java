package hiberspring.service;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.XML.ProductImportRootDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
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

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final static String PRODUCTS_XML_FILE_PATH = Constants.PATH_TO_FILES + "products.xml";

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return this.fileUtil.readFile(PRODUCTS_XML_FILE_PATH);
    }

    @Override
    public String importProducts() throws JAXBException {
        StringBuilder builder = new StringBuilder();

        ProductImportRootDTO productImportRootDTO = this.xmlParser.parseXml(ProductImportRootDTO.class, PRODUCTS_XML_FILE_PATH);

        Arrays.stream(productImportRootDTO.getProductImportDTO()).forEach(productImportDTO -> {

            Branch branch = this.branchRepository.findByName(productImportDTO.getBranchName()).orElse(null);
            if(branch == null || !this.validationUtil.isValid(productImportDTO)){
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());

                return;
            }

            Product product = this.modelMapper.map(productImportDTO,Product.class);
            product.setBranch(branch);
            this.productRepository.saveAndFlush(product);
            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,product.getClass().getSimpleName(),product.getName()))
                    .append(System.lineSeparator());
        });

        return builder.toString().trim();
    }
}
