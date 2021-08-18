package uz.tune.tenge.base.exception;

public class BaseEntityNotFoundException extends ExceptionWithStatusCode{

    public BaseEntityNotFoundException() {
        super(400, "entity.not.found");
    }

    public BaseEntityNotFoundException(String errorMessageKey) {
        super(400, errorMessageKey);
    }
}
