package hiberspring.domain.dtos.XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportRootDTO {

    @XmlElement(name = "product")
    private ProductImportDTO[] productImportDTO;

    public ProductImportDTO[] getProductImportDTO() {
        return productImportDTO;
    }

    public void setProductImportDTO(ProductImportDTO[] productImportDTO) {
        this.productImportDTO = productImportDTO;
    }
}
