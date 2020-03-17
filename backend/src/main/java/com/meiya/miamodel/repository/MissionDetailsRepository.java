package com.meiya.miamodel.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meiya.miamodel.domain.MissionDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xuebw
 */
@Mapper
@Repository
public interface MissionDetailsRepository extends BaseMapper<MissionDetails> {

}
