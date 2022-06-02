package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmService {
    final private FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {

        this.filmStorage = filmStorage;
    }

    public Film addLike(Integer id, Integer userId) {
        Film film = filmStorage.getFilm(id);
        film.addLike(userId);
        return film;
    }

    public Film removeLike(Integer id, Integer userId) {
        Film film = filmStorage.getFilm(id);
        film.removeLike(userId);
        return film;
    }

    public List<Film> findPopularFilms(Integer count) {
        return filmStorage.getAll().stream()
                .sorted((o1, o2) -> (o2.getLikes().size() - o1.getLikes().size()))
                .limit(count)
                .collect(Collectors.toList());
    }
}
