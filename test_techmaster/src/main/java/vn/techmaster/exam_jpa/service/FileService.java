package vn.techmaster.exam_jpa.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.exam_jpa.exception.StorageException;


@Service
public class FileService {
    @Value("${upload.path}")
    private String path;

    // id của course id
    public String saveFile(MultipartFile file, Long id) throws IOException {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }
        // logo.pgn
        String extension = getFileExtension(file.getOriginalFilename()); // png
        String newFileName = path + id + "." + extension; // path=/abc/25445553455.png
        // Lấy Extension
        try {
            var is = file.getInputStream();
            Files.copy(is, Paths.get(newFileName), StandardCopyOption.REPLACE_EXISTING);
            return id + "." + extension;
        } catch (IOException e) {
            var msg = String.format("Failed to store file %s", newFileName);
            throw new StorageException(msg, e);
        }

    }

    public void deleteFile(String logopath) {
        String filePathToDelete = path + logopath;
        try {
            Files.deleteIfExists(Paths.get(filePathToDelete));
        } catch (IOException e) {
            var msg = String.format("Failed to delete file %s", filePathToDelete);
            throw new StorageException(msg, e);
        }
    }

    // mission: NẾu cta tải lên 1 file có đuôi: png, jpg, .. thì nó sẽ trả về cái
    // đuôi đó

    /*
     * Bóc tách file extension từ file name. ví dụ từ input: pic1.png
     * output: png
     */
    public String getFileExtension(String fileName) {
        int postOfDot = fileName.lastIndexOf(".");
        if (postOfDot >= 0) {
            return fileName.substring(postOfDot + 1);
        } else {
            return null;
        }
    }
}
