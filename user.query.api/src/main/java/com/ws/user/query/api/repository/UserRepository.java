package com.ws.user.query.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ws.user.core.model.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {

}
