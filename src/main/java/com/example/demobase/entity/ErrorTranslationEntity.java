package com.example.demobase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "error_translation")
public class ErrorTranslationEntity {

    @Id
    @Column(name = "message_ref")
    private UUID messageRef;

    @Column(name = "message_key", nullable = false, updatable = false, unique = true)
    private String messageKey;

    @Column(name = "text_ru", nullable = false)
    private String textRu;

    @Column(name = "text_uz", nullable = false)
    private String textUz;

    @Column(name = "text_en", nullable = false)
    private String textEn;

    public ErrorTranslationEntity() {
    }

    public ErrorTranslationEntity(UUID messageRef, String messageKey, String textRu, String textUz, String textEn) {
        this.messageRef = messageRef;
        this.messageKey = messageKey;
        this.textRu = textRu;
        this.textUz = textUz;
        this.textEn = textEn;
    }

    public UUID getMessageRef() {
        return messageRef;
    }

    public ErrorTranslationEntity setMessageRef(UUID messageRef) {
        this.messageRef = messageRef;
        return this;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public ErrorTranslationEntity setMessageKey(String messageKey) {
        this.messageKey = messageKey;
        return this;
    }

    public String getTextRu() {
        return textRu;
    }

    public ErrorTranslationEntity setTextRu(String textRu) {
        this.textRu = textRu;
        return this;
    }

    public String getTextUz() {
        return textUz;
    }

    public ErrorTranslationEntity setTextUz(String textUz) {
        this.textUz = textUz;
        return this;
    }

    public String getTextEn() {
        return textEn;
    }

    public ErrorTranslationEntity setTextEn(String textEn) {
        this.textEn = textEn;
        return this;
    }
}


