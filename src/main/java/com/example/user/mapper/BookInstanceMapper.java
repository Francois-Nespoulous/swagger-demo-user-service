package com.example.user.mapper;

import com.example.user.domain.enums.BorrowStatus;
import com.example.user.domain.model.BookDefinition;
import com.example.user.domain.model.BookInstance;
import com.example.user.dto.out.BookDefinitionDto;
import com.example.user.dto.out.BookInstanceDto;
import com.example.user.persistence.repository.entity.BookDefinitionEntity;
import com.example.user.persistence.repository.entity.BookInstanceEntity;
import com.example.user.repository.BorrowRepository;

public class BookInstanceMapper {
    public static BookInstance toDomainWithoutDefinition(BookInstanceEntity bookInstanceEntity, BorrowRepository borrowRepository) {
        boolean isBorrowed = borrowRepository.existsByBookInstanceId_AndStatusEquals(bookInstanceEntity.getId(), BorrowStatus.ONGOING);

        return new BookInstance(
                bookInstanceEntity.getId(),
                bookInstanceEntity.getBookState(),
                null,
                isBorrowed,
                bookInstanceEntity.getVersion(), bookInstanceEntity.getLastBorrowAttempt()
        );
    }

    public static BookInstance toDomain(BookInstanceEntity bookInstanceEntity, BorrowRepository borrowRepository) {
        boolean isBorrowed = borrowRepository.existsByBookInstanceId_AndStatusEquals(bookInstanceEntity.getId(), BorrowStatus.ONGOING);
        BookDefinition bookDefinition = BookDefinitionMapper.toDomainWithoutInstance(bookInstanceEntity.getBookDefinition(), borrowRepository);

        return new BookInstance(
                bookInstanceEntity.getId(),
                bookInstanceEntity.getBookState(),
                bookDefinition,
                isBorrowed,
                bookInstanceEntity.getVersion(),
                bookInstanceEntity.getLastBorrowAttempt()
        );
    }

    public static BookInstanceDto toDtoWithoutDefinition(BookInstance bookInstance) {
        return new BookInstanceDto(
                bookInstance.getId(),
                bookInstance.getBookState(),
                null,
                bookInstance.isBorrowed()
        );
    }

    public static BookInstanceDto toDto(BookInstance bookInstance) {
        BookDefinitionDto bookDefinitionDto = BookDefinitionMapper.toDtoWithoutInstance(bookInstance.getBookDefinition());

        return new BookInstanceDto(
                bookInstance.getId(),
                bookInstance.getBookState(),
                bookDefinitionDto,
                bookInstance.isBorrowed()
        );
    }

    public static BookInstanceEntity toEntity(BookInstance bookInstance) {
        BookDefinitionEntity bookDefinitionEntity = BookDefinitionMapper.toEntityOnlyId(bookInstance.getBookDefinition());

        return new BookInstanceEntity(
                bookInstance.getId(),
                bookInstance.getBookState(),
                bookDefinitionEntity,
                bookInstance.getVersion()
        );
    }
}
