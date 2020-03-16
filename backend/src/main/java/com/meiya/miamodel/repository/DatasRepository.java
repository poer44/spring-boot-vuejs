package com.meiya.miamodel.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meiya.miamodel.domain.Datas;
import com.meiya.miamodel.domain.Mission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xuebw
 */
@Mapper
@Repository
public interface DatasRepository extends BaseMapper<Datas> {

}
