package com.user.user_backend.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
    // Lay Extension file
    // vd: image.png, avatar.jpg, ...=> png, jpg
    public static String getExtensionFile(String fileName) {
        int lastIndextOf = fileName.lastIndexOf(".");
        if (lastIndextOf == -1) {
            return "";
        }
        return fileName.substring(lastIndextOf + 1);
    }

    // kiểm tra Extension file có nằm trong danh sách được upload hay không
    public static boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = Arrays.asList("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }
}
