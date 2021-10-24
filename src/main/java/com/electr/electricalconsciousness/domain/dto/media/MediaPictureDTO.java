package com.electr.electricalconsciousness.domain.dto.media;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaPictureDTO {

    private Long mediaPictureId;

    private String mediaUrl;

    private String pictureName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
