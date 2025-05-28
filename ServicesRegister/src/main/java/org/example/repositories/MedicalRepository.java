package org.example.repositories;

import org.example.documents.Medical;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalRepository extends MongoRepository<Medical, String> {
}
