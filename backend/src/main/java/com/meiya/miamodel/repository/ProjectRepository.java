package com.meiya.miamodel.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meiya.miamodel.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author xuebw
 */
@Mapper
@Repository
public interface ProjectRepository extends BaseMapper<Project> {

    @Select("SELECT * FROM project p left join project_type t on p.type = t.id order by p.crtm desc")
    IPage<Project> selectPageVo(Page page);

}
