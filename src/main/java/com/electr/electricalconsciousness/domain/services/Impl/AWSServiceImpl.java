package com.electr.electricalconsciousness.domain.services.Impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.electr.electricalconsciousness.domain.models.MediaPicture;
import com.electr.electricalconsciousness.domain.repositories.MediaPictureRepository;
import com.electr.electricalconsciousness.domain.services.AWSService;
import com.electr.electricalconsciousness.exceptions.AllException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AWSServiceImpl implements AWSService {

    private final AmazonS3 s3;
    private final MediaPictureRepository mediaPictureRepository;

    @Value("${aws.bucket}")
    private String bucketName;

    public Long saveMediaInS3(MultipartFile file) throws IOException {
        try {
            File fileOutput = new File(Objects.requireNonNull(file.getOriginalFilename()));
            FileOutputStream fos = new FileOutputStream(fileOutput);
            fos.write(file.getBytes());
            fos.close();

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            s3.putObject(bucketName, fileName, fileOutput);
            fileOutput.delete();

            MediaPicture mediaPicture = new MediaPicture();
            mediaPicture.setPictureName(fileName);

            mediaPictureRepository.save(mediaPicture);

            mediaPicture.setMediaUrl("/media/" + mediaPicture.getMediaPictureId());

            mediaPictureRepository.save(mediaPicture);

            return mediaPicture.getMediaPictureId();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public ByteArrayOutputStream findMediaByS3(Long pictureId) {


        Optional<MediaPicture> mediaPicture = mediaPictureRepository.findById(pictureId);

        if(mediaPicture.isEmpty()) {
            throw new AllException("Media not found", HttpStatus.NOT_FOUND);
        }

        try {
            S3Object s3object = s3.getObject(new GetObjectRequest(bucketName, mediaPicture.get().getPictureName()));

            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream;

        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("failed", e.getCause());
        }
    }

    public ResponseEntity<Void> deleteMediaByS3(Long pictureId) {

        MediaPicture picture = findPicture(pictureId);

        try {
            s3.deleteObject(bucketName, picture.getPictureName());
            mediaPictureRepository.deleteById(picture.getMediaPictureId());
            return ResponseEntity.noContent().build();
        } catch (AllException e) {
            throw new AllException("Não foi possível deletar está imagem", HttpStatus.BAD_REQUEST);
        }
    }

    public MediaType contentType(Long pictureId) {
        String mediaName;
        Optional<MediaPicture> mediaPicture = mediaPictureRepository.findById(pictureId);

        if (mediaPicture.isEmpty()) {
            MediaPicture picture = findPicture(pictureId);
            mediaName = picture.getPictureName();
        } else {
            mediaName = mediaPicture.get().getPictureName();
        }

        String[] fileArrSplit = mediaName.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        switch (fileExtension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public MediaPicture findPicture(Long pictureId) {
        return mediaPictureRepository
                .findById(pictureId)
                .orElseThrow(
                        () -> new AllException("Imagem de perfil não encontrada", HttpStatus.NOT_FOUND));

    }
}
