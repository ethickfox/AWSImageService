package com.epam.service.image;

import com.epam.exceptions.ImageNotFoundException;
import com.epam.model.Image;
import com.epam.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class ImageService {
    @Value("${service.s3.bucket-name}")
    private String bucketName;
    private S3Client s3Client;
    private ImageRepository imageRepository;

    public ImageService(S3Client s3Client, ImageRepository imageRepository) {
        this.s3Client = s3Client;
        this.imageRepository = imageRepository;
    }

    public Image saveImage(MultipartFile file) throws IOException {
//        final String source = bucketName + file.getOriginalFilename();

        Image imageToSave = new Image();
        imageToSave.setFileExtension(file.getContentType());
        imageToSave.setName(file.getName());
        imageToSave.setSize(file.getSize());
        imageToSave = imageRepository.save(imageToSave);

        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(imageToSave.getId().toString())
                .contentType(file.getContentType()).build();

        s3Client.putObject(putRequest,
                RequestBody.fromBytes(file.getBytes()));
        return imageToSave;
    }

    public @ResponseBody byte[] getImageFile(Long imageId) {
        if (imageId == null) {
            throw new ImageNotFoundException();
        }
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(imageId.toString())
                .build();

        return s3Client.getObjectAsBytes(request).asByteArray();
    }

    public byte[] getRandomImageFile() {
        List<Long> allIds = imageRepository.findAllIds();
        long imageId = allIds.get(new Random().nextInt(allIds.size() - 1));
        return getImageFile(imageId);
    }

    public void delete(Long imageId) {
        if (imageId == null) {
            throw new ImageNotFoundException();
        }
        imageRepository.deleteById(imageId);
        DeleteObjectRequest putRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(imageId.toString())
                .build();

        s3Client.deleteObject(putRequest);
    }

    public Image getImageMetadata(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
    }
}
