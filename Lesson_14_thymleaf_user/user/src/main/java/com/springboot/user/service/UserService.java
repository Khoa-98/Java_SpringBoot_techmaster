package com.springboot.user.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.springboot.user.dto.UserDto;
import com.springboot.user.exception.BadRequestException;
import com.springboot.user.exception.NotFoundException;
import com.springboot.user.mapper.UserMapper;
import com.springboot.user.model.User;
import com.springboot.user.request.CreateUserRequest;
import com.springboot.user.request.UpdatePasswordRequest;
import com.springboot.user.request.UpdateUserRequest;


@Service
public class UserService {
    // Tạo List User để quản lý
    private List<User> users;

    @Autowired
    private FileService fileService;

    public UserService() {
        init();
    }

    // Khởi tạo một số đối tượng User và thêm vào trong list
    public void init() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyễn Văn A", "a@gmail.com", "0123456789", "Tỉnh Thái Bình", null, "111"));
        users.add(new User(2, "Trần Văn B", "b@gmail.com", "0123456789", "Tỉnh Nam Định", null, "222"));
        users.add(new User(3, "Ngô Thị C", "c@gmail.com", "0123456789", "Tỉnh Hưng Yên", null, "333"));
    }

    // Lấy danh sách user và trả về ở dạng UserDto
    public List<UserDto> getUsers() {
        return users
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Tìm kiếm user theo tên:
    public List<UserDto> searchUser(String name) {
        return users
                .stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Xoá user theo ID:
    public void deleteUser(int id) {
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("Khong có user nào có id =" + id);
        }

        users.removeIf(user -> user.getId() == id);
    }

    // Tạo user mới:
    public UserDto createUser(CreateUserRequest requets) {
        // Kiem tra xem email này đã tồn tại hay chưa
        if (findUser(requets.getEmail()).isPresent()) {
            throw new BadRequestException("email =" + requets.getEmail() + "đã tồn tại");
        }

        // Tao user mới
        Random rd = new Random();
        User user = new User();
        user.setId(rd.nextInt((100 - 4 + 1) + 4)); // random tu 4-> 100
        user.setName(requets.getName());
        user.setEmail(requets.getEmail());
        user.setPhone(requets.getPhone());
        user.setAddress(requets.getAddress());
        user.setPassword(requets.getPassword());

        users.add(user);

        return UserMapper.toUserDto(user);
    }

    // Lấy thông tin user theo id
    public UserDto getUserById(int id) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Khong có user nào có id =" + id);
        }
        return UserMapper.toUserDto(userOptional.get());
    }

    // cập nhật thông tin user
    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Khong có user nào có id =" + id);
        }

        User user = userOptional.get();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        return UserMapper.toUserDto(user);

    }

    // Cập nhật mật khẩu
    public void updatePassword(int id, UpdatePasswordRequest request) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Khong có user nào có id =" + id);
        }

        User user = userOptional.get();

        // Kiểm tra xem oldPassword có chính xác hay không
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("Password cũ Không chính xác");
        }
        // kiểm tra xem oldpasswrd == new password không
        if (request.getOldPassword().equals(request.getNewPassword())) {

            throw new BadRequestException("Password cũ và mới không trùng nhau");
        }

        user.setPassword(request.getNewPassword());
    }

    // quen mat khau
    public String forgotPassword(int id) {
        Optional<User> userOptional = findUser(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Khong có user nào có id =" + id);
        }

        User user = userOptional.get();

        // Tao mat khau moi
        Random rd = new Random();
        String password = String.valueOf(rd.nextInt((1000 - 100) + 100));
        user.setPassword(password);

        return password;

    }

    // upload-file
    public String uploadFile(int id, MultipartFile file) {
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("Khong có user nào có id =" + id);
        }

        return fileService.uploadFile(id, file);
    }

    // Xem file
    public byte[] readFile(int id, String fileId) {

        return fileService.readFile(id, fileId);
    }

    // xoa file:
    public void deleteFile(int id, String fileId) {
        fileService.deleteFile(id, fileId);
    }

    // Lay danh sach file
    public List<String> getFiles(int id) {
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("Khong có user nào có id =" + id);
        }

        return fileService.getFiles(id);
    }

    // Helper Method
    public Optional<User> findUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> findUser(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }
}
