package com.user.service.dbservice.dao;

import com.user.service.dbservice.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapperTest {
    List<User> queryAll();
}
