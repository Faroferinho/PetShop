package org.example.services;

import java.util.List;
import java.util.Optional;

public interface CRUDServices<Document, DocumentDTO> {
    Document save(DocumentDTO dto);

    List<Document> findAll();

    Optional<Document> findByID(String id);

    void deleteByID(String id);
}
