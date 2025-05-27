package org.example.repositories;

import org.example.documents.Domestic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomesticRepository extends MongoRepository<Domestic, String> {}
