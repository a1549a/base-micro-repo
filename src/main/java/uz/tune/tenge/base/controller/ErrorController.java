package uz.tune.tenge.base.controller;

import uz.tune.tenge.base.exception.ExceptionWithStatusCode;
import uz.tune.tenge.base.model.BaseResponse;
import uz.tune.tenge.base.model.ErrorTranslationResponse;
import uz.tune.tenge.base.service.ErrorTranslationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = "uz.tune.tenge")
public class ErrorController {

    @Value("${service.name}")
    private String serviceName;

    private final ErrorTranslationService errorTranslationService;

    public ErrorController(ErrorTranslationService errorTranslationService) {
        this.errorTranslationService = errorTranslationService;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<BaseResponse<?>> validException(MethodArgumentNotValidException e, ServletWebRequest webRequest) {

        List<String> errors = new ArrayList<>();
        var fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String key =
                    serviceName +
                            "." +
                            webRequest.getRequest().getRequestURI() +
                            "." +
                            webRequest.getHttpMethod() +
                            "." +
                            fieldError.getObjectName() +
                            "." +
                            fieldError.getField() +
                            "." +
                            fieldError.getCode();
            errors.add(key);
        }

        var translations = errorTranslationService.getByKey(errors);

        return ResponseEntity.badRequest().body(
                new BaseResponse<>(
                        null,
                        translations.stream().map(ErrorTranslationResponse::getMessageRef).collect(Collectors.toList()),
                        translations.stream().map(ErrorTranslationResponse::getTextUz).collect(Collectors.toList())
                )
        );
    }

    @ExceptionHandler({ExceptionWithStatusCode.class})
    public ResponseEntity<BaseResponse<?>> validException(ExceptionWithStatusCode e, ServletWebRequest webRequest) {

        var errorTranslationResponse = errorTranslationService.getByKey(e.getErrorMessageKey());

        var response = new BaseResponse<>(
                null,
                List.of(errorTranslationResponse.getMessageRef()),
                List.of(errorTranslationResponse.getTextUz())
        );

        return ResponseEntity
                .status(e.getHttpStatusCode())
                .body(response);
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<BaseResponse<?>> validException(Throwable e, ServletWebRequest webRequest) {

        var errorTranslationResponse = errorTranslationService.getByKey("internal.error");

        var response = new BaseResponse<>(
                null,
                List.of(errorTranslationResponse.getMessageRef()),
                List.of(errorTranslationResponse.getTextUz())
        );

        return ResponseEntity
                .status(500)
                .body(response);
    }

}