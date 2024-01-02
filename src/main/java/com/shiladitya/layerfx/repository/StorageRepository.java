package com.shiladitya.layerfx.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.shiladitya.layerfx.entity.ImageData;

public interface StorageRepository extends JpaRepository<ImageData,Long>
{
//	Optional<ImageData> findByName(String ImageName);
//	@Query("SELECT i.imageconv from ImageData i where i.id = ?1")
	Optional<ImageData> findById(int id);
}
