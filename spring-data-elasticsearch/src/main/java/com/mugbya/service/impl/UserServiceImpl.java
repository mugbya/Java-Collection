package com.mugbya.service.impl;

import com.mugbya.config.Config;
import com.mugbya.entities.User;
import com.mugbya.repository.UserRepository;
import com.mugbya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class UserServiceImpl implements UserService {

    Byte ENABLE = 1;
    Byte DISABLE = 0;


    @Autowired
    Config config;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.getAllByStatue(ENABLE);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getUserByUsername(String username) {
        return userRepository.getAllByUsername(username);
    }

    @Override
    public List<SearchHit<User>>  getUserByFuzzy(String username) {
        String keyword = username.concat("*");
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withFilter(boolQuery()
                        .must(queryStringQuery(keyword).defaultField("username"))
                        .must(matchQuery("statue", ENABLE))
                )
                .build();

        // spring data spring 3.* 下面写法, 在 4.0 已经被弃用了
        // userRepository.search(query);

        // spring data spring 4.0 开始以下写法
        SearchHits<User> res =  config.elasticsearchTemplate().search(query, User.class, IndexCoordinates.of("user"));

        List<SearchHit<User>> userList = res.getSearchHits();
        return userList;
    }

    @Override
    public boolean save(User user) {
        User res = userRepository.save(user);
        System.out.println(res);
        return true;
    }

    @Override
    public boolean batchSave(List<User> userList) {
        userRepository.saveAll(userList);
        return true;
    }
}
