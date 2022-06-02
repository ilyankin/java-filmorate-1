package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import ru.yandex.practicum.filmorate.exceptions.LikeNotFound;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {
    private int id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;
    private Set<Integer> likes = new HashSet<>();

    public void addLike(Integer id) {
        likes.add(id);
    }

    public void removeLike(Integer id) {
        if (likes.contains(id)) {
            likes.remove(id);
        } else {
            throw new LikeNotFound();
        }
    }

    public Set<Integer> getLikes() {
        return likes;
    }
}
