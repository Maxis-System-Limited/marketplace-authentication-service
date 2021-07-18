package com.maxis.user.adapter.out.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.maxis.user.adapter.out.model.mongo.UserDataModel;

@Repository
public interface SpringDataUserMongoRepository extends MongoRepository<UserDataModel, String> {
	@Query("{ 'userId' : ?0 }")
	UserDataModel findUserByUserId(String userId);
}
