package jointcase.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import jointcase.dto.request.user.UserSignupRequestDto;
import jointcase.util.validator.annotation.PasswordCheck;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordCheckValidator
        implements ConstraintValidator<PasswordCheck, UserSignupRequestDto> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(PasswordCheck constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(UserSignupRequestDto userSignupRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(userSignupRequestDto)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(userSignupRequestDto)
                .getPropertyValue(fieldMatch);
        return fieldValue != null && fieldValue.equals(fieldMatchValue);
    }
}
