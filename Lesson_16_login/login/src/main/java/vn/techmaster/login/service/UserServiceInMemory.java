package vn.techmaster.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.techmaster.login.exception.UserException;
import vn.techmaster.login.hash.Hashing;
import vn.techmaster.login.model.State;
import vn.techmaster.login.model.User;
import vn.techmaster.login.repository.UserRepo;

@Service
@AllArgsConstructor
public class UserServiceInMemory implements UserService {

    private UserRepo userRepo;
    private Hashing hashing;

    @Override
    public User addUser(String fullName, String email, String password) {

        return userRepo.addPendingUser(fullName, email, hashing.hashPassword(password));
    }

    @Override
    public User addActiveUser(String fullName, String email, String password) {

        return userRepo.addUser(fullName, email, hashing.hashPassword(password), State.ACTIVE);
    }

    @Override
    public User login(String email, String password) {
        // get User from email
        Optional<User> oUser = userRepo.findEmail(email);
        // Nếu user ko tồn tại thì báo lỗi
        if (!oUser.isPresent()) {
            throw new UserException("User is not found");
        }
        User user = oUser.get();
        // user muon login phai co trang thai active
        if (user.getState() != State.ACTIVE) {
            throw new UserException("User is not Activated");
        }
        // Validate Password
        if (hashing.validatePassword(password, user.getHashPassword())) {
            return user;
        } else {
            throw new UserException("Password is not true");
        }
    }

    @Override
    public Boolean logout(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean activateUser(String activation_code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
