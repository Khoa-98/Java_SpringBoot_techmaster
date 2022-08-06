package vn.techmaster.exam_jpa.utils;

import vn.techmaster.exam_jpa.dto.CourseDto;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static String generateTopicString(List<CourseDto.TopicDto> topics){
        List<String> topicsName = topics.stream().map(CourseDto.TopicDto::getName).toList();

        String[] itemArray = new String[topicsName.size()];
        itemArray = topicsName.toArray(itemArray);

        return String.join(", ", itemArray);
    }

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
