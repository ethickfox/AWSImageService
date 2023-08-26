package com.epam.controllers;

import com.epam.exceptions.FileUploadException;
import com.epam.model.Image;
import com.epam.service.image.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/image")
public class ImageController {
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public Image upload(@RequestParam("files") MultipartFile file) {
        try {
            return imageService.saveImage(file);
        } catch (IOException e) {
            throw new FileUploadException();
        }
    }

    @GetMapping(value = "/file/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public byte[] getImage(@PathVariable Long id) {
        return imageService.getImageFile(id);
    }

    @GetMapping(value = "/file/",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public byte[] getImage() {
        return imageService.getRandomImageFile();
    }

    @GetMapping("/info/{id}")
    public Image getMetadata(@PathVariable Long id) {
        return imageService.getImageMetadata(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        imageService.delete(id);
    }
}
