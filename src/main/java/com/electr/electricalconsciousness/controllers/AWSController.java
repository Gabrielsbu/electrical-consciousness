package com.electr.electricalconsciousness.controllers;

import com.electr.electrical.domain.services.AWSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class AWSController {

    private final AWSService awsService;

    @PostMapping
    public Long uploadPictureInS3(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return awsService.saveMediaInS3(file);
    }

    @GetMapping(value = "/{pictureId}")
    public ResponseEntity<byte[]> getPictureInS3(@PathVariable Long pictureId) {
        ByteArrayOutputStream downloadInputStream = awsService.findMediaByS3(pictureId);

        return ResponseEntity.ok()
                .contentType(awsService.contentType(pictureId))
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(downloadInputStream.toByteArray());
    }

    @DeleteMapping(value = "/{pictureId}")
    public ResponseEntity<Void> deletePictureInS3(@PathVariable Long pictureId) {
        return awsService.deleteMediaByS3(pictureId);
    }

}
