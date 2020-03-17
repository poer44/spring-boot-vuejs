package com.meiya.miamodel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 任务明细pojo
 *
 * @author xuebw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissionDetails {
    @TableId(type = IdType.AUTO)
    private long id;
    private long mid;
    private long nid;
    private long etype;
    private String etypeCld;
    private double learnrate;
    private double epochs;
    private double batch;
    private long gpuid;
    private String log;
    private double accuracy;
    @TableField("`precision`")
    private double precision;
    private double recall;
    private double process;
    private String dids;

}
