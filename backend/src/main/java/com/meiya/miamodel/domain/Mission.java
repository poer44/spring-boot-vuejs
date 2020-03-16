package com.meiya.miamodel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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

    private long id;
    private String name;
    @TableField("`desc`")
    private String desc;
    private long status;
    private Date crtm;
    private Date srtm;
    private long pid;

}
