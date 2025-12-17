package com.lshdainty.porest.common.util;

import com.lshdainty.porest.common.exception.ErrorCode;
import com.lshdainty.porest.common.exception.ExternalServiceException;
import com.lshdainty.porest.common.exception.ResourceNotFoundException;
import com.lshdainty.porest.common.message.MessageKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PorestFile {

    private static final Logger log = LoggerFactory.getLogger(PorestFile.class);

    // 유틸리티 클래스는 인스턴스화 방지
    private PorestFile() {}

    /**
     * 파일을 저장하는 함수</br>
     * fileName을 넘겨주면 해당 파일명으로 저장</br>
     * 없다면 multipartFile에서 가져온 파일명으로 저장
     *
     * @param multipartFile 저장할 파일
     * @param path 파일 저장 경로
     * @param fileName 다른 이름으로 저장 시 사용
     * @param messageResolver MessageResolver
     * @return boolean 저장 성공 여부
     */
    public static boolean save(MultipartFile multipartFile, String path, String fileName, MessageResolver messageResolver) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            log.warn("MultipartFile is null or empty");
            return false;
        }

        // 옵션 파일명이 없다면 객체에서 원본 파일명을 가져와서 저장
        if (!StringUtils.hasText(fileName)) {
            // 원본 파일명 가져오기 및 경로 정리 (경로 조작 방지)
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        }

        if (fileName.isEmpty()) {
            log.warn("Could not save file with empty name");
            return false;
        }

        log.debug("Saving file. path: {}, fileName: {}", path, fileName);
        try {
            // 파일 저장 경로가 없을 경우 생성
            Path directory = Paths.get(path);
            Files.createDirectories(directory);

            // 파일을 저장할 전체 경로
            Path filePath = directory.resolve(fileName);

            // 파일 저장
            multipartFile.transferTo(filePath.toFile());
            log.info("File saved successfully. path: {}, fileName: {}", path, fileName);
            return true;
        } catch (IOException e) {
            log.error("File save failed. path: {}, fileName: {}", path, fileName, e);
            throw new ExternalServiceException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    messageResolver.getMessage(MessageKey.FILE_REGISTER_HOLIDAY, fileName),
                    e
            );
        }
    }

    /**
     * 파일을 읽어오는 함수</br>
     * 파일 용량이 큰 경우 해당함수 사용금지</br>
     * OutOfMemoryError 발생 위험
     *
     * @param fullPath 읽어올 파일의 전체 경로
     * @param messageResolver MessageResolver
     * @return byte[] 파일 내용
     */
    public static byte[] read(String fullPath, MessageResolver messageResolver) {
        log.debug("Reading file. fullPath: {}", fullPath);
        try {
            // 파일을 읽어올 전체 경로
            Path filePath = Paths.get(fullPath);

            // 파일이 존재하고, 일반 파일이며, 읽기 가능한지 확인
            if (Files.exists(filePath) && Files.isRegularFile(filePath) && Files.isReadable(filePath)) {
                // 파일 내용을 byte 배열로 읽어옴
                byte[] fileBytes = Files.readAllBytes(filePath);
                log.info("File read successfully. fullPath: {}, size: {} bytes", fullPath, fileBytes.length);
                return fileBytes;
            } else {
                log.warn("File not found or not readable. fullPath: {}", fullPath);
                throw new ResourceNotFoundException(ErrorCode.FILE_NOT_FOUND);
            }
        } catch (IOException e) {
            log.error("File read failed. fullPath: {}", fullPath, e);
            throw new ExternalServiceException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    messageResolver.getMessage(MessageKey.FILE_READ, fullPath),
                    e
            );
        }
    }

    /**
     * 파일을 복사하는 함수
     *
     * @param sourcePath 원본 파일 전체 경로
     * @param targetPath 복사할 파일 전체 경로
     * @param messageResolver MessageResolver
     * @return boolean 복사 성공 여부
     */
    public static boolean copy(String sourcePath, String targetPath, MessageResolver messageResolver) {
        log.debug("Copying file. source: {}, target: {}", sourcePath, targetPath);
        Path source = Paths.get(sourcePath);
        Path target = Paths.get(targetPath);

        try {
            // 원본 파일이 존재하는지 확인
            if (!Files.exists(source)) {
                log.warn("Source file not found for copy: {}", sourcePath);
                throw new ResourceNotFoundException(ErrorCode.FILE_NOT_FOUND);
            }

            // 복사할 경로의 부모 디렉토리가 없으면 생성
            Path targetDir = target.getParent();
            if (targetDir != null && !Files.exists(targetDir)) {
                Files.createDirectories(targetDir);
            }

            // 파일 복사 (이미 파일이 존재하면 예외 발생)
            Files.copy(source, target);
            log.info("File copied successfully. source: {}, target: {}", sourcePath, targetPath);
            return true;
        } catch (IOException e) {
            log.error("File copy failed. source: {}, target: {}", sourcePath, targetPath, e);
            throw new ExternalServiceException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    messageResolver.getMessage(MessageKey.FILE_COPY, sourcePath, targetPath),
                    e
            );
        }
    }

    /**
     * 파일을 이동하는 함수
     *
     * @param sourcePath 원본 파일 전체 경로
     * @param targetPath 이동할 파일 전체 경로
     * @param messageResolver MessageResolver
     * @return boolean 이동 성공 여부
     */
    public static boolean move(String sourcePath, String targetPath, MessageResolver messageResolver) {
        log.debug("Moving file. source: {}, target: {}", sourcePath, targetPath);
        Path source = Paths.get(sourcePath);
        Path target = Paths.get(targetPath);

        try {
            // 원본 파일이 존재하는지 확인
            if (!Files.exists(source)) {
                log.warn("Source file not found for move: {}", sourcePath);
                throw new ResourceNotFoundException(ErrorCode.FILE_NOT_FOUND);
            }

            // 이동할 경로의 부모 디렉토리가 없으면 생성
            Path targetDir = target.getParent();
            if (targetDir != null && !Files.exists(targetDir)) {
                Files.createDirectories(targetDir);
            }

            // 파일 이동
            Files.move(source, target);
            log.info("File moved successfully. source: {}, target: {}", sourcePath, targetPath);
            return true;
        } catch (IOException e) {
            log.error("File move failed. source: {}, target: {}", sourcePath, targetPath, e);
            throw new ExternalServiceException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    messageResolver.getMessage(MessageKey.FILE_MOVE, sourcePath, targetPath),
                    e
            );
        }
    }

    /**
     * 원본 파일명과 UUID로 물리적 파일명 생성하는 함수 </br>
     * 형식: originalName.ext + uuid -> originalName_uuid.ext
     *
     * @param originalFilename 원본 파일명
     * @param uuid UUID 문자열
     * @return 물리적 파일명 (originalName_UUID.extension)
     */
    public static String generatePhysicalFilename(String originalFilename, String uuid) {
        if (!StringUtils.hasText(originalFilename) || !StringUtils.hasText(uuid)) {
            return originalFilename;
        }

        // 확장자 분리
        if (originalFilename.contains(".")) {
            int lastDotIndex = originalFilename.lastIndexOf(".");
            String nameWithoutExt = originalFilename.substring(0, lastDotIndex);
            String extension = originalFilename.substring(lastDotIndex);
            return nameWithoutExt + "_" + uuid + extension;
        }

        // 확장자가 없는 경우
        return originalFilename + "_" + uuid;
    }

    /**
     * 물리적 파일명에서 원본 파일명 추출하는 함수</br>
     * 형식: originalName_UUID.extension -> originalName.extension
     *
     * @param physicalFilename 물리적 파일명
     * @param uuid UUID 문자열 (null이면 패턴으로 찾아서 제거)
     * @return 원본 파일명
     */
    public static String extractOriginalFilename(String physicalFilename, String uuid) {
        if (!StringUtils.hasText(physicalFilename)) {
            return null;
        }

        // UUID가 제공된 경우: 간단하게 replace로 제거
        if (StringUtils.hasText(uuid)) {
            return physicalFilename.replace("_" + uuid, "");
        }

        // UUID가 없는 경우: 패턴으로 찾아서 제거
        // 마지막 '_' 이후부터 확장자 전까지가 UUID라고 가정
        String extractedUuid = extractUuid(physicalFilename, null);
        if (StringUtils.hasText(extractedUuid)) {
            return physicalFilename.replace("_" + extractedUuid, "");
        }

        // UUID가 없으면 원본 그대로 반환
        return physicalFilename;
    }

    /**
     * 물리적 파일명에서 UUID 추출</br>
     * 형식: originalName_UUID.extension -> UUID
     *
     * @param physicalFilename 물리적 파일명
     * @param uuid UUID 문자열 (제공되면 그대로 반환, null이면 패턴으로 찾기)
     * @return 추출된 UUID
     */
    public static String extractUuid(String physicalFilename, String uuid) {
        if (!StringUtils.hasText(physicalFilename)) {
            return null;
        }

        // UUID가 이미 제공된 경우 그대로 반환
        if (StringUtils.hasText(uuid)) {
            return uuid;
        }

        // UUID를 패턴으로 찾기
        // 확장자 제거
        String nameWithoutExt = physicalFilename;
        if (physicalFilename.contains(".")) {
            nameWithoutExt = physicalFilename.substring(0, physicalFilename.lastIndexOf("."));
        }

        // 마지막 '_' 이후가 UUID
        int lastUnderscoreIndex = nameWithoutExt.lastIndexOf("_");
        if (lastUnderscoreIndex != -1 && lastUnderscoreIndex < nameWithoutExt.length() - 1) {
            return nameWithoutExt.substring(lastUnderscoreIndex + 1);
        }

        return null;
    }
}