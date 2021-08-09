package com.example.demobase.model;

import com.example.demobase.entity.ErrorTranslationEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ErrorTranslationResponse {

    private UUID messageRef;

    private String messageKey;

    private String textRu;

    private String textUz;

    private String textEn;

    private ErrorTranslationResponse(UUID messageRef, String messageKey, String textRu, String textUz, String textEn) {
        this.messageRef = messageRef;
        this.messageKey = messageKey;
        this.textRu = textRu;
        this.textUz = textUz;
        this.textEn = textEn;
    }

    public static ErrorTranslationResponse of(ErrorTranslationEntity errorTranslationEntity) {

        return new ErrorTranslationResponse(
                errorTranslationEntity.getMessageRef(),
                errorTranslationEntity.getMessageKey(),
                errorTranslationEntity.getTextRu(),
                errorTranslationEntity.getTextUz(),
                errorTranslationEntity.getTextEn()
        );
    }

    public static List<ErrorTranslationResponse> of(List<ErrorTranslationEntity> errorTranslationEntities) {

        return errorTranslationEntities.stream().map(ErrorTranslationResponse::of)
                .collect(Collectors.toList());
    }

    public UUID getMessageRef() {
        return messageRef;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getTextRu() {
        return textRu;
    }

    public String getTextUz() {
        return textUz;
    }

    public String getTextEn() {
        return textEn;
    }
}
