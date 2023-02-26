package me.igor.EmailSenderService.repository;

import me.igor.EmailSenderService.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    @Override
    @Transactional
    <S extends Email> S save(S entity);
}
