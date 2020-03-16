package com.meiya.miamodel.domain;

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
    private long id;
    private String pbName;
    private String pbPath;
    private String pbDesc;
    private String pbType;
}
