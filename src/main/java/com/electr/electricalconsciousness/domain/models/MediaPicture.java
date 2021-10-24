package com.electr.electricalconsciousness.domain.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "pictures")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class MediaPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaPictureId;

    private String mediaUrl;

    private String pictureName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
