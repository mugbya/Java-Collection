package com.mugbya.service;

import com.mugbya.entities.User;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.List;

public interface UserService {

    /**
     * 获取所以用户
     * @return
     */
    List<User> getAllUser();

    /**
     * 根据ID获取用户
     * @return
     */
    User getUserById(String id);

    /**
     * 根据用户名完全匹配
     * @param username
     * @return
     */
    List<User> getUserByUsername(String username);


    List<SearchHit<User>>  getUserByFuzzy(String username);

    /**
     * 保存用户
     * @param user
     * @return
     */
    boolean save(User user);

    boolean batchSave(List<User> userList);


}
