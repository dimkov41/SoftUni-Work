package exam.web.beans;

import exam.domain.models.view.DocumentHomeViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class DocumentHomeBean {
    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentHomeBean() {
    }

    @Inject
    public DocumentHomeBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public List<String> getAll() {
        List<DocumentHomeViewModel> list = Arrays
                .stream(this.modelMapper.map(this.documentService.getAll(), DocumentHomeViewModel[].class))
                .collect(Collectors.toList());

        int loopLenght = list.size() % 5;
        int emptyBlocks = 5 - loopLenght;

        if (loopLenght == 0) {
            loopLenght = list.size();
        } else {
            loopLenght = list.size() + emptyBlocks;
        }

        int emptyBlockFirstIndex = list.size();

        List<String> documents = new ArrayList<>();

        for (int i = 0; i < loopLenght; i++) {
            StringBuilder builder = new StringBuilder();

            if (i % 5 == 0) {
                builder.append("<div class='row mb-4 d-flex justify-content-around'>");
            }

            if (i >= emptyBlockFirstIndex) {
                builder.append("<div class=\"print-scheduled col-md-2 bg-white rounded\"></div>")
                        .append(System.lineSeparator());
            } else {
                DocumentHomeViewModel documentHomeViewModel = list.get(i);
                //print element
                builder.append("<div class=\"print-scheduled col-md-2 bg-exodia rounded\">")
                        .append(System.lineSeparator())
                        .append(String.format("<h5 class=\"text-center text-white font-weight-bold py-3 " +
                                "mt-3\">%s</h5>", documentHomeViewModel.getTitle()))
                        .append(System.lineSeparator())
                        .append("<hr class=\"bg-white\" />")
                        .append(System.lineSeparator())
                        .append("<div class=\"buttons-holder w-75 mx-auto d-flex justify-content-between\">")
                        .append(System.lineSeparator())
                        .append(String.format("<button class=\"btn bg-exodia text-white font-weight-bold mb-3\"\n" +
                                "                onclick=\"window.location.href='/print/%s'\">Print</button>",
                                documentHomeViewModel.getId()))
                        .append(System.lineSeparator())
                        .append(String.format("<button class=\"btn bg-exodia text-white font-weight-bold mb-3\"\n" +
                                        "                onclick=\"window.location" +
                                        ".href='/details/%s'\">Details</button>",
                                documentHomeViewModel.getId()))
                        .append(System.lineSeparator())
                        .append("</div>")
                        .append(System.lineSeparator())
                        .append("</div>");


            }

            if (i % 5 == 4) {
                builder.append("</div>");
            }

            documents.add(builder.toString());
        }

        return documents;
    }

}
