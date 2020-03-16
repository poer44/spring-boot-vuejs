package com.meiya.miamodel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 项目pojo
 *
 * @author xuebw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @TableId(type= IdType.AUTO)
    private long id;
    private String name;
    @TableField("`desc`")
    private String desc;
    private Date crtm;
    private int type;
    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private boolean hasChildren;
}
