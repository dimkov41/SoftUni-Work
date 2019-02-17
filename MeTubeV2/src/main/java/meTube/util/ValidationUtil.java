package meTube.util;

import javax.servlet.http.HttpSession;
import javax.validation.Validation;

public class ValidationUtil {
    private javax.validation.Validator validator;

    public ValidationUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <M> boolean isValid(M model){
        return this.validator.validate(model).size() == 0;
    }

    public boolean isAuthenticated(HttpSession httpSession){
        return httpSession != null && httpSession.getAttribute(Constants.USERNAME_FIELD_NAME) != null;
    }
}

