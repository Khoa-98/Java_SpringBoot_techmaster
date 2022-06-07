package com.my_jobhunt.myproject;

import org.junit.jupiter.api.Test;

import com.my_jobhunt.job_hunt.service.StorageService;
import static org.assertj.core.api.Assertions.*;

public class StorageServiceTest {
    @Test
    public void test_getFileExtension() {
        StorageService s = new StorageService();
        String extension = s.getFileExtension("fpt.png");
        assertThat(extension).isEqualTo("png");

        extension = s.getFileExtension("cmc.jpg");
        assertThat(extension).isEqualTo("jpg");
    }
}
