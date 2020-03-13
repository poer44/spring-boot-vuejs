package com.meiya.miamodel.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meiya.miamodel.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xuebw
 */
@Mapper
@Repository
public interface UserRepository extends BaseMapper<User> {

}
