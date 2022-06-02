package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import ru.yandex.practicum.filmorate.exceptions.FriendNotFound;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private int id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;
    private Set<Integer> friends = new HashSet<>();

    public void addFriend(Integer id) {
        friends.add(id);
    }

    public Set<Integer> getFriends() {
        return friends;
    }

    public void removeFriend(Integer id) {
        if (friends.contains(id)) {
            friends.remove(id);
        } else {
            throw new FriendNotFound();
        }

    }
}
