package com.example.user.repository;

import com.example.user.persistence.repository.entity.BookDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDefinitionRepository extends JpaRepository<BookDefinitionEntity, Long> {
    List<BookDefinitionEntity> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
}
