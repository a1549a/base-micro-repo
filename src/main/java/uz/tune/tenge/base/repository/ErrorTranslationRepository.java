package uz.tune.tenge.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.tune.tenge.base.entity.ErrorTranslationEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ErrorTranslationRepository extends JpaRepository<ErrorTranslationEntity, UUID> {

    List<ErrorTranslationEntity> findAllByMessageKeyIn(Collection<String> messageKey);

    Optional<ErrorTranslationEntity> findByMessageKey(String messageKey);
}
