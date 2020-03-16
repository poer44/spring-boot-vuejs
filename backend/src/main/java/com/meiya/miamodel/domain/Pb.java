package com.meiya.miamodel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 模型pojo
 *
 * @author xuebw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pb {
    @TableId(type= IdType.AUTO)
    private long id;
    private String pbName;
    private String pbPath;
    private String pbDesc;
    private String pbType;
}
