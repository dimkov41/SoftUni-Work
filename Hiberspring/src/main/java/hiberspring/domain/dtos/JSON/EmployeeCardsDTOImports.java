package hiberspring.domain.dtos.JSON;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class EmployeeCardsDTOImports {
    @Expose
    private String number;

    @NotNull
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
