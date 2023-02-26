package me.igor.EmailSenderService;

import me.igor.EmailSenderService.exception.EmailNotFoundException;
import me.igor.EmailSenderService.model.Email;
import me.igor.EmailSenderService.repository.EmailRepository;
import me.igor.EmailSenderService.service.impl.EmailServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    public void testGetAllEmails() {
        // given
        List<Email> emails = Arrays.asList(new Email(1L, "email1@test.com"), new Email(2L, "email2@test.com"));
        given(emailRepository.findAll(any(Sort.class))).willReturn(emails);

        // when
        List<Email> result = emailService.getAllEmails();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(0).getEmail()).isEqualTo("email1@test.com");
        assertThat(result.get(1).getId()).isEqualTo(2L);
        assertThat(result.get(1).getEmail()).isEqualTo("email2@test.com");
    }

    @Test
    public void testGetEmailById() {
        // given
        given(emailRepository.findById(1L)).willReturn(Optional.of(new Email(1L, "email1@test.com")));

        // when
        Email result = emailService.getEmailById(1L);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo("email1@test.com");
    }

    @Test(expected = EmailNotFoundException.class)
    public void testGetEmailByIdNotFound() {
        // given
        given(emailRepository.findById(1L)).willReturn(Optional.empty());

        // when
        emailService.getEmailById(1L);

        // then
        // EmailNotFoundException should be thrown
    }

    @Test
    public void testAddEmail() {
        // given
        Email email = new Email(null, "new-email@test.com");
        given(emailRepository.save(email)).willReturn(new Email(1L, "new-email@test.com"));

        // when
        emailService.addEmail(email);

        // then
        verify(emailRepository).save(email);
    }

    @Test
    public void testUpdateEmail() {
        // given
        given(emailRepository.existsById(1L)).willReturn(true);
        given(emailRepository.findById(1L)).willReturn(Optional.of(new Email(1L, "email1@test.com")));
        Email updatedEmail = new Email(1L, "updated-email@test.com");

        // when
        emailService.updateEmail(1L, updatedEmail);

        // then
        verify(emailRepository).findById(1L);
        verify(emailRepository).save(updatedEmail);
    }

    @Test(expected = EmailNotFoundException.class)
    public void testUpdateEmailNotFound() {
        // given
        given(emailRepository.existsById(1L)).willReturn(false);

        // when
        emailService.updateEmail(1L, new Email(1L, "updated-email@test.com"));

        // then
        // EmailNotFoundException should be thrown
    }

    @Test
    public void testDeleteEmail() {
        // given
        given(emailRepository.existsById(1L)).willReturn(true);

        // when
        emailService.deleteEmail(1L);

        // then
        verify(emailRepository).deleteById(1L);
    }

    @Test(expected = EmailNotFoundException.class)
    public void testDeleteEmailNotFound() {
        // given
        given(emailRepository.existsById(1L)).willReturn(false);

        // when
        emailService.deleteEmail(1L);

        // then
        // EmailNotFoundException should be thrown
    }

}
