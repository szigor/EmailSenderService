# EmailSenderService

Microservice that is responsible for sending emails to users.

# Application config

## Email config

To configure email sending, you need to provide the following parameters in the `application.properties` file:

- `spring.mail.host`: the mail host address
- `spring.mail.port`: the port number of the mail server
- `spring.mail.username`: the username for logging in
- `spring.mail.password`: the password for logging in

## Database config

To configure the H2 database, the following properties should be set in the `application.properties` file:

- `spring.datasource.url`: the database URL address
- `spring.datasource.username`: the database username
- `spring.datasource.password`: the database user password
- `spring.h2.console.path`: the database driver class name

## Database data

The data for the database is entered in the `import.sql` file.

## Log files

Log files are located in the `logs` folder.
