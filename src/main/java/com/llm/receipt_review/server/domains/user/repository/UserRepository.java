package com.llm.receipt_review.server.domains.user.repository;

import com.llm.receipt_review.server.domains.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
