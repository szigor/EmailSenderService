# EmailSenderService

Microservice that is responsible for sending emails to users.

# Konfiguracja aplikacji

## Konfiguracja poczty

Aby skonfigurować pocztę, należy ustawić następujące właściwości w pliku `application.properties`:

- `spring.mail.host`: adres hosta poczty
- `spring.mail.port`: numer portu serwera poczty
- `spring.mail.username`: nazwa użytkownika do logowania
- `spring.mail.password`: hasło użytkownika do logowania

## Konfiguracja bazy danych

Aby skonfigurować bazę danych H2, należy ustawić następujące właściwości w pliku `application.properties`:

- `spring.datasource.url`: adres URL bazy danych
- `spring.datasource.username`: nazwa użytkownika bazy danych
- `spring.datasource.password`: hasło użytkownika bazy danych
- `spring.h2.console.path`: sciezka bazy danych

##Dane

Dane testowe są wprowadzone w pliku `import.sql`.

## Pliki z logami

Pliki z logami znajdują się w folderze `logs`.
