package com.meiya.miamodel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 任务pojo
 *
 * @author xuebw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mission {
    @TableId(type= IdType.AUTO)
    private long id;
    private String name;
    @TableField("`desc`")
    private String desc;
    private long status;
    private Date crtm;
    private Date srtm;
    private long pid;

}
