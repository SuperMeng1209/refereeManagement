package com.lyh.refereeManagement.api;

import com.lyh.refereeManagement.entity.User;
import com.lyh.refereeManagement.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userApi")
public class UserApi {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user){
        return userMapper.login(user);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public int addUser(@RequestBody User user){
        int i = userMapper.addUser(user);
        if(i == 1){
            i= userMapper.findByUser(user);
        }else{
            i = -1;
        }
        return i;
    }

    @PostMapping("/findById")
    @ResponseBody
    public User findById(@RequestBody User user){
        return userMapper.findById(user);
    }

    @PostMapping("/updateById")
    @ResponseBody
    public int updateById(@RequestBody User user){
        return userMapper.updateById(user);
    }
}
