package com.lyh.refereeManagement.mapper;

import com.lyh.refereeManagement.entity.ToMsg;
import com.lyh.refereeManagement.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User login(User user);

    int addUser(User user);

    int findByUser(User user);

    User findById(User user);

    String findByRealName(ToMsg toMsg);

    int updateById(User user);
}
