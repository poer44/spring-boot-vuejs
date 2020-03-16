package com.meiya.miamodel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 数据集pojo
 *
 * @author xuebw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Datas {
    private long id;
    private String name;
    private String path;
    private Date crtm;
}
