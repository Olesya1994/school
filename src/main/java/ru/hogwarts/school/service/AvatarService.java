package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Collection;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Value("${path.to.avatar.folder}")
    String avatarsDir;


    public void upload(Long studentId, MultipartFile file) throws IOException {
        var student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new IllegalStateException();
        }
        Path filePath = Path.of(avatarsDir, student + "." + file.getOriginalFilename());
        Files.createDirectories(filePath.getParent());
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

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }


}
