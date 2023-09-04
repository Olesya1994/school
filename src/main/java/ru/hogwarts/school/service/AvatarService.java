package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Value("${avatars.dir}")
    String avatarsDir;

    private final static Logger logger = LoggerFactory.getLogger(AvatarService.class);

    public void upload(Long studentId, MultipartFile file) throws IOException {
      //  logger.info("method upload Avatar was invoked");
        var student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            logger.info("no student was found");
            throw new IllegalStateException();
        }
        Path filePath = Path.of(avatarsDir, student + "." + file.getOriginalFilename());
        Files.createDirectories(filePath.getParent());
        logger.info("Creating directory"+avatarsDir);
        Files.deleteIfExists(filePath);
        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        var avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setFilePath(filePath.toString());
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student.get());
        avatarRepository.save(avatar);
    }

    public byte[] genereteDataForDB(Path filePath) throws IOException {
        logger.info("method genereteDataForDB was invoked");
        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            BufferedImage image = ImageIO.read(bis);
            int height = image.getHeight() * 100 / image.getWidth();
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D grahics2D = preview.createGraphics();
            grahics2D.drawImage(image, 0, 0, 100, height, null);
            grahics2D.dispose();
            ImageIO.write(preview, filePath.getFileName().toString(), baos);
            return baos.toByteArray();
        }

    }

    public Avatar findAvatar(Long id) {
        logger.info("method findAvatar was invoked");
        return avatarRepository.findByStudentId(id).orElse(new Avatar());
    }

    public List<Avatar> getPage(int pageNumber, int pageSize){
        logger.info("method getPage was invoked");
        return avatarRepository.findAll(PageRequest.of(pageNumber-1,pageSize)).getContent();
    }
    private String getExtension(String fileName) {
        logger.info("method getExtension was invoked");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


}
