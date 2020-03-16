package com.meiya.miamodel.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meiya.miamodel.domain.Pb;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xuebw
 */
@Mapper
@Repository
public interface PbRepository extends BaseMapper<Pb> {

}
