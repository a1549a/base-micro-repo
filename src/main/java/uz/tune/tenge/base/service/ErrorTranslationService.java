package uz.tune.tenge.base.service;

import uz.tune.tenge.base.model.ErrorTranslationResponse;

import java.util.List;
import java.util.UUID;

public interface ErrorTranslationService {

    ErrorTranslationResponse getByKey(String messageKey);

    List<ErrorTranslationResponse> getByKey(List<String> messageKeys);

    ErrorTranslationResponse getByRef(UUID messageRef);

}
