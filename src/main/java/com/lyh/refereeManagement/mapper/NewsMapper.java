package com.lyh.refereeManagement.mapper;

import com.lyh.refereeManagement.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    List<News> findAll();
}
