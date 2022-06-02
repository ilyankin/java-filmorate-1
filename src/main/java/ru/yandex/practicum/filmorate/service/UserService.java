package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserService {

    final private UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User addFriend(Integer id, Integer friendId) {
        User user = userStorage.getUser(id);
        User friend = userStorage.getUser(friendId); //проверим наличие друга по id
        user.addFriend(friendId);
        return user;
    }

    public User removeFriend(Integer id, Integer friendId) {
        User user = userStorage.getUser(id);
        User friend = userStorage.getUser(friendId); //проверим наличие друга по id
        user.removeFriend(friendId);
        return user;
    }

    public List<User> getUserFriends(Integer id) {
        return userStorage.getUser(id).getFriends().stream().map(userStorage::getUser).collect(Collectors.toList());
    }

    public List<User> getCommonFriends(Integer id, Integer otherId) {

        Set<Integer> userFriends = userStorage.getUser(id).getFriends();
        Set<Integer> friendFriends = userStorage.getUser(otherId).getFriends();

        return userFriends.stream()
                .filter(friendFriends::contains)
                .map(userStorage::getUser).collect(Collectors.toList());
    }
}
