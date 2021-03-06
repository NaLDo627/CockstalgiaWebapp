package net.hkpark.cockstalgia.core.util;

import org.junit.After;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @Test
    public void 파일_저장_테스트() {
        // given
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        // when
        String filePath = FileUtil.saveMultiPartFile(mockMultipartFile, "./");

        // then
        File testFile = new File(filePath);
        assertTrue(testFile.exists());

        // teardown
        testFile.delete();
    }
}