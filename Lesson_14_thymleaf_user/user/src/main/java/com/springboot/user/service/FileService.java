package com.springboot.user.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.user.exception.BadRequestException;
import com.springboot.user.util.Utils;


@Service
public class FileService {
    // Path folder để upload file
    private final Path rootPath = Paths.get("uploads");

    public FileService() {
        createFolder(rootPath.toString());
    }

    // taoj folder

    public void createFolder(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // upload file

    public String uploadFile(int id, MultipartFile file) {
        // B1: tạo folder tương ứng với user:
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        createFolder(userDir.toString());

        // B2: Validate file:
        validate(file);

        // B3: tạo path tương ứng với file update
        String generateFileId = String.valueOf(Instant.now().getEpochSecond());
        File fileServer = new File(userDir + "/" + generateFileId);

        try {
            // Sử dụng Buffer để lưu dữ liệu từ file:
            BufferedOutputStream stream = new BufferedOutputStream((new FileOutputStream(fileServer)));

            stream.write(file.getBytes());
            stream.close();

            return "/api/v1/users/" + id + "/files/" + generateFileId;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi update");
        }
    }

    public void validate(MultipartFile file) {
        // Kiem tra ten file
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.equals("")) {
            throw new BadRequestException("Ten file khong hop le");
        }

        // kiem tra duoi file
        String fileExtension = Utils.getExtensionFile(fileName);
        if (!Utils.checkFileExtension(fileExtension)) {
            throw new BadRequestException("File khong hop le");
        }
        // Kiem tra sixe (upload duoi 2MB)
        if ((double) file.getSize() / 1_000_000L > 2) {
            throw new BadRequestException("File khong được vượt quá 2MB");
        }
    }

    // Xem file
    public byte[] readFile(int id, String fileId) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootPath.resolve((String.valueOf(id)));

        // kiểm tra userPath có tồn tại hay không
        if (!Files.exists(userPath)) {
            throw new RuntimeException("Lỗi khi đọc file " + fileId);
        }

        try {
            // Nối đường dẫn đến file
            Path file = userPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            } else {
                throw new RuntimeException("Lỗi khi đọc file " + fileId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đọc file " + fileId);
        }
    }

    // xoa file
    public void deleteFile(int id, String fileId) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootPath.resolve((String.valueOf(id)));

        // kiểm tra userPath có tồn tại hay không
        if (!Files.exists(userPath)) {
            throw new RuntimeException("Lỗi khi đọc file " + fileId);
        }

        Path file = userPath.resolve(fileId);
        if (!file.toFile().exists()) {
            throw new RuntimeException("file " + fileId + "  khong ton tai");
        }

        file.toFile().delete();
    }

    // Lay danh sach file
    public List<String> getFiles(int id) {
        // Lấy đường dẫn file tương ứng với user_id
        Path userPath = rootPath.resolve((String.valueOf(id)));

        // kiểm tra userPath có tồn tại hay không
        if (!Files.exists(userPath)) {
            return new ArrayList<>();
        }

        // lấy danh sách file của user
        File[] files = userPath.toFile().listFiles();

        return Arrays.stream(files)
                .map(file -> file.getName())
                .sorted(Comparator.reverseOrder())
                .map(file -> "api/v1/users/" + id + "/files/" + file)
                .collect(Collectors.toList());

    }
}
