package com.lyh.refereeManagement.api;

import com.lyh.refereeManagement.entity.News;
import com.lyh.refereeManagement.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/newsApi")
public class NewsApi {

    @Autowired
    private NewsMapper newsMapper;

    @PostMapping("findAll")
    @ResponseBody
    public List<News> findAll(){
        return newsMapper.findAll();
    }
}
