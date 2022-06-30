
package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.exceptions.FilmValidationException;
import ru.yandex.practicum.filmorate.exceptions.UserValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.Validator;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FilmorateApplicationTests {

    @Test
    void userCorrectData() throws IOException, InterruptedException {

        User user = new User();
        user.setId(1);
        user.setEmail("mail@mail.ru");
        user.setLogin("login");
        user.setName("name");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Validator.checkUser(user);

    }

    @Test
    void userInCorrectEmail1() throws IOException, InterruptedException {

        User user = new User();
        user.setId(1);
        user.setEmail("mail1mailru");
        user.setLogin("login");
        user.setName("name");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        UserValidationException exception = assertThrows(UserValidationException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.checkUser(user);
            }
        });

        assertEquals("Email is not correct", exception.getMessage());

    }

    @Test
    void userInCorrectEmail2() throws IOException, InterruptedException {

        User user = new User();
        user.setId(1);
        user.setEmail("");
        user.setLogin("login");
        user.setName("name");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        UserValidationException exception = assertThrows(UserValidationException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.checkUser(user);
            }
        });

        assertEquals("Email is not correct", exception.getMessage());

    }

    @Test
    void userLoginIsEmpty() throws IOException, InterruptedException {

        User user = new User();
        user.setId(1);
        user.setEmail("mail@mail.ru");
        user.setLogin("");
        user.setName("name");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        UserValidationException exception = assertThrows(UserValidationException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.checkUser(user);
            }
        });

        assertEquals("Login is not correct", exception.getMessage());

    }

    @Test
    void userLoginСontainsSpaces() throws IOException, InterruptedException {

        User user = new User();
        user.setId(1);
        user.setEmail("mail@mail.ru");
        user.setLogin("log in");
        user.setName("name");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        UserValidationException exception = assertThrows(UserValidationException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.checkUser(user);
            }
        });

        assertEquals("Login is not correct", exception.getMessage());

    }

    @Test
    void userNameIsEmpty() throws IOException, InterruptedException {

        User user = new User();
        user.setId(1);
        user.setEmail("mail@mail.ru");
        user.setLogin("login");
        user.setName("");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        Validator.checkUser(user);

    }


    @Test
    void userBirthdayInFuture() throws IOException, InterruptedException {

        User user = new User();
        user.setId(1);
        user.setEmail("mail@mail.ru");
        user.setLogin("login");
        user.setName("");
        user.setBirthday(LocalDate.of(2200, 1, 1));

        UserValidationException exception = assertThrows(UserValidationException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.checkUser(user);
            }
        });

        assertEquals("Birthday is not correct", exception.getMessage());

    }

    @Test
    void filmNameIsEmpty() throws IOException, InterruptedException {

        String name = "";
        String description = "description";
        LocalDate releaseDate = LocalDate.of(2000, 1, 1);
        Integer duration = Math.toIntExact(Duration.ofHours(1).toSeconds());

        Film film = new Film();
        film.setId(1);
        film.setName(name);
        film.setDescription(description);
        film.setReleaseDate(releaseDate);
        film.setDuration(duration);

        FilmValidationException exception = assertThrows(FilmValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.checkFilm(film);
            }

            ;
        });

        assertEquals("Name of the film is not correct", exception.getMessage());

    }

    @Test
    void filmDescriptionLenght200() throws IOException, InterruptedException {

        String name = "name";
        String description = "";
        for (int i = 1; i <= 200; i++) {
            description = description + "d";
        }

        LocalDate releaseDate = LocalDate.of(2000, 1, 1);
        Integer duration = 3600;

        Film film = new Film();
        film.setId(1);
        film.setName(name);
        film.setDescription(description);
        film.setReleaseDate(releaseDate);
        film.setDuration(duration);

        Validator.checkFilm(film);

    }

    @Test
    void filmDescriptionLenght201() throws IOException, InterruptedException {

        String name = "name";
        String description = "";
        for (int i = 1; i <= 201; i++) {
            description = description + "d";
        }

        LocalDate releaseDate = LocalDate.of(2000, 1, 1);
        Integer duration = 3600;

        Film film = new Film();
        film.setId(1);
        film.setName(name);
        film.setDescription(description);
        film.setReleaseDate(releaseDate);
        film.setDuration(duration);

        FilmValidationException exception = assertThrows(FilmValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.checkFilm(film);
            }

            ;
        });

        assertEquals("Description the film is not correct", exception.getMessage());

    }

    @Test
    void filmReleaseDate28121895() throws IOException, InterruptedException {

        String name = "name";
        String description = "description";
        LocalDate releaseDate = LocalDate.of(1895, 12, 28);
        Integer duration = 3600;

        Film film = new Film();
        film.setId(1);
        film.setName(name);
        film.setDescription(description);
        film.setReleaseDate(releaseDate);
        film.setDuration(duration);

        Validator.checkFilm(film);

    }

    @Test
    void filmReleaseDate29121895() throws IOException, InterruptedException {

        String name = "name";
        String description = "description";
        LocalDate releaseDate = LocalDate.of(1895, 12, 27);
        Integer duration = 3600;

        Film film = new Film();
        film.setId(1);
        film.setName(name);
        film.setDescription(description);
        film.setReleaseDate(releaseDate);
        film.setDuration(duration);

        FilmValidationException exception = assertThrows(FilmValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.checkFilm(film);
            }

            ;
        });

        assertEquals("Release date is not correct", exception.getMessage());

    }

    @Test
    void filmDurationIsNegative() throws IOException, InterruptedException {

        String name = "name";
        String description = "description";
        LocalDate releaseDate = LocalDate.of(1895, 12, 29);
        Integer duration = -3600;

        Film film = new Film();
        film.setId(1);
        film.setName(name);
        film.setDescription(description);
        film.setReleaseDate(releaseDate);
        film.setDuration(duration);

        FilmValidationException exception = assertThrows(FilmValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Validator.checkFilm(film);
            }

            ;
        });

        assertEquals("Duration of the film cannot be negative", exception.getMessage());

    }

    @Test
    void filmDurationIsPositive() throws IOException, InterruptedException {

        String name = "name";
        String description = "description";
        LocalDate releaseDate = LocalDate.of(1895, 12, 29);
        Integer duration = 3600;

        Film film = new Film();
        film.setId(1);
        film.setName(name);
        film.setDescription(description);
        film.setReleaseDate(releaseDate);
        film.setDuration(duration);


        Validator.checkFilm(film);

    }


}

