package com.meiya.miamodel.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meiya.miamodel.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xuebw
 */
@Mapper
public interface UserRepository extends BaseMapper<User> {

}
