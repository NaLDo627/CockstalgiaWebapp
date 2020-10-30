package net.hkpark.cockstalgia.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.hkpark.cockstalgia.core.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class FileUtil { // TODO File을 유틸리티 클래스로서 다루는게 옳은 일일까?
    /* 인스턴스화 방지 */
    private FileUtil() { }

    /**
     * Multipart 요청 파일을 저장하고 저장된 경로를 반환한다.
     *
     * @param mFile Controller 에서 Http Multipart 요청으로 들어온 File
     * @param filePath 파일이 저장될 경로
     * @return 실제로 파일이 저장되었을 때, 리턴되어지는 경로
     *
     * @throws IllegalStateException 파일 저장 실패시 원인과 함께 throw
     */
    public static String saveMultiPartFile(MultipartFile mFile, String filePath) {
        try {
            makeAbsoluteDirectory(filePath);
            String completePath = filePath + mFile.getOriginalFilename(); // TODO 파일명 변경처리
            File saveFile = new File(completePath);
            mFile.transferTo(saveFile);
            return saveFile.getAbsolutePath();
        } catch (IllegalStateException | IOException e) {
            String msg = "파일 저장 중 에러 발생";
            throw new IllegalStateException(msg, e);
        }
    }

    /**
     * dirPath 경로의 디렉토리를 생성한다.
     *
     * @param dirPath 생성할 디렉토리의 경로 (상대 경로, 절대 경로 모두 가능)
     * @throws IllegalStateException 디렉토리 생성 실패시, 경로에 파일이 이미 존재시 throw
     */
    public static void makeAbsoluteDirectory(String dirPath) {
        File directory = new File(dirPath);
        if (directory.exists() && directory.isDirectory()) {
            return;
        }

        if (directory.exists() && ! directory.isDirectory()) {
            String msg = "경로가 이미 존재하며, 파일입니다.";
            throw new IllegalStateException(msg);
        }

        boolean result = directory.mkdirs();
        if (! result) {
            String msg = "디렉토리 생성 중 에러 발생";
            throw new IllegalStateException(msg);
        }
    }
}
