package vn.techmaster.login.repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.login.model.State;
import vn.techmaster.login.model.User;

@Repository
public class UserRepo {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    // tao user ở trạng thái PENDING
    public User addPendingUser(String fullName, String email, String hashPassword) {
        return addUser(fullName, email, hashPassword, State.PENDING);
    }

    public User addUser(String fullName, String email, String hashPassword, State state) {
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                .id(id)
                .fullName(fullName)
                .email(email)
                .hashPassword(hashPassword)
                .state(state)
                .build();
        users.put(id, user);
        return user;
    }

    public boolean isEmailExist(String email) {
        return users.values().stream().anyMatch(o1 -> o1.getEmail().equalsIgnoreCase(email));
    }

    public Optional<User> findEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
}
