package uz.tune.tenge.base.service;

import uz.tune.tenge.base.entity.ErrorTranslationEntity;
import uz.tune.tenge.base.model.ErrorTranslationResponse;
import uz.tune.tenge.base.repository.ErrorTranslationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ErrorTranslationServiceImpl implements ErrorTranslationService {

    private final ErrorTranslationRepository errorTranslationRepository;

    public ErrorTranslationServiceImpl(ErrorTranslationRepository errorTranslationRepository) {
        this.errorTranslationRepository = errorTranslationRepository;
    }

    @Override
    public ErrorTranslationResponse getByKey(String messageKey) {

        return ErrorTranslationResponse.of(
                errorTranslationRepository.findByMessageKey(
                        messageKey
                ).orElseGet(() -> new ErrorTranslationEntity(
                        null,
                        messageKey,
                        messageKey,
                        messageKey,
                        messageKey
                ))
        );
    }

    @Override
    public List<ErrorTranslationResponse> getByKey(List<String> messageKeys) {

        var errorTranslationResponses = ErrorTranslationResponse.of(
                errorTranslationRepository.findAllByMessageKeyIn(
                        messageKeys
                )
        );

        sortAndAddNonExists(messageKeys, errorTranslationResponses);

        return errorTranslationResponses;
    }

    @Override
    public ErrorTranslationResponse getByRef(UUID messageRef) {

        return ErrorTranslationResponse.of(
                errorTranslationRepository.findById(
                        messageRef
                ).orElseGet(() -> new ErrorTranslationEntity(
                        messageRef,
                        null,
                        null,
                        null,
                        null
                ))
        );
    }

    private void sortAndAddNonExists(List<String> messageKeys, List<ErrorTranslationResponse> errorTranslationResponses) {

        errorTranslationResponses.sort(Comparator.comparingInt(o -> messageKeys.indexOf(o.getMessageKey())));

        int len = messageKeys.size();

        for (int i = 0; i < len; i++) {

            String messageKey = messageKeys.get(i);

            if (errorTranslationResponses.size() <= i || messageKey.equals(errorTranslationResponses.get(i).getMessageKey())) {
                errorTranslationResponses.add(
                        i,
                        ErrorTranslationResponse.of(
                                new ErrorTranslationEntity(
                                        null,
                                        messageKey,
                                        messageKey,
                                        messageKey,
                                        messageKey
                                )
                        )
                );
            }

        }
    }

}
