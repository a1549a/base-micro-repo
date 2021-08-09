package com.example.demobase.service;

import com.example.demobase.model.ErrorTranslationResponse;

import java.util.List;
import java.util.UUID;

public interface ErrorTranslationService {

    ErrorTranslationResponse getByKey(String messageKey);

    List<ErrorTranslationResponse> getByKey(List<String> messageKeys);

    ErrorTranslationResponse getByRef(UUID messageRef);

}
