package com.meiya.miamodel.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuebw
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class User {

    private long id;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
