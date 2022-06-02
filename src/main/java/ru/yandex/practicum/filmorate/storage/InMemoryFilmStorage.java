package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFound;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.yandex.practicum.filmorate.service.Validator.checkFilm;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {

    private Map<Integer, Film> films = new HashMap<>();
    private int lastId = 0;

    @Override
    public Film create(Film film) {
        checkFilm(film);
        film.setId(++lastId);
        films.put(film.getId(), film);
        log.info("Added film {}", film);
        return film;
    }

    @Override
    public Film update(Film film) {
        if (!films.containsKey(film.getId())) {
            throw new FilmNotFound();
        }
        checkFilm(film);
        log.info("Update film. New data {}", film);
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public List<Film> getAll() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Film getFilm(Integer id) {
        Film film = films.get(id);
        if (film == null) {
            throw new FilmNotFound();
        }
        return film;
    }
}
