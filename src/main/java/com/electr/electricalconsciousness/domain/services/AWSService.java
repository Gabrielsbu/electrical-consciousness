package com.electr.electricalconsciousness.domain.services;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public interface AWSService {

    Long saveMediaInS3(MultipartFile file) throws IOException;

    ByteArrayOutputStream findMediaByS3(Long pictureId);

    ResponseEntity<Void> deleteMediaByS3(Long pictureId);

    MediaType contentType(Long pictureId);
}
