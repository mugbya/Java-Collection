package com.mugbya.repository;

import com.mugbya.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    List<User> getAllByStatue(Byte statue);

    User getById(String id);

    List<User> getAllByUsername(String username);
}
