package Pans.Api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${upload.path}") // Pobieranie ścieżki do katalogu z application.properties
    private String uploadPath;

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Nie wybrano pliku do przesłania.");
        }

        String fileName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = Paths.get(uploadPath, fileName);

        try {
            Files.copy(file.getInputStream(), filePath);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Błąd podczas przesyłania obrazka.");
        }
    }

    public Resource downloadImage(String fileName) throws IOException {
        try {
            Path filePath = Paths.get(uploadPath, fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new IOException("Nie znaleziono pliku obrazka.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Błąd podczas pobierania obrazka.");
        }
    }
}
