package com.electr.electricalconsciousness.domain.repositories;

import com.electr.electricalconsciousness.domain.models.MediaPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaPictureRepository extends JpaRepository<MediaPicture, Long> {
}