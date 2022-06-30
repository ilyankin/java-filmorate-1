package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {

    private Map<Integer, User> users = new HashMap<>();
    private int lastId = 0;
    private UserStorage userStorage;
    private UserService userService;

    @Autowired
    public UserController(UserStorage userStorage, UserService userService) {
        this.userStorage = userStorage;
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        return userStorage.create(user);
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) {
        return userStorage.update(user);
    }

    @GetMapping(value = "/users")
    public List<User> getAll() {
        return userStorage.getAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) {
        return userStorage.getUser(Integer.valueOf(id));
    }

    @PutMapping("/users/{id}/friends/{friendId}")
    public User addFriend(@PathVariable Map<String, String> pathVarsMap) {
        String id = pathVarsMap.get("id");
        String friendId = pathVarsMap.get("friendId");
        if ((id != null) && (friendId != null)) {
            return userService.addFriend(Integer.valueOf(id), Integer.valueOf(friendId));
        } else {
            return null;
        }
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public User removeFriend(@PathVariable Map<String, String> pathVarsMap) {
        String id = pathVarsMap.get("id");
        String friendId = pathVarsMap.get("friendId");
        if ((id != null) && (friendId != null)) {
            return userService.removeFriend(Integer.valueOf(id), Integer.valueOf(friendId));
        } else {
            return null;
        }
    }

    @GetMapping("/users/{id}/friends")
    public List<User> getUserFriends(@PathVariable String id) {
        return userService.getUserFriends(Integer.valueOf(id));
    }

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable Map<String, String> pathVarsMap) {
        String id = pathVarsMap.get("id");
        String otherId = pathVarsMap.get("otherId");
        if ((id != null) && (otherId != null)) {
            return userService.getCommonFriends(Integer.valueOf(id), Integer.valueOf(otherId));
        } else {
            return null;
        }
    }


}
