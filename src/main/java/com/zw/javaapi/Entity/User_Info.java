package com.zw.javaapi.Entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class User_Info {

    @NotNull(message = "id不能为空")
    private String id;

    @NotNull(message = "id不能为空")
    private String user_id;

    private Date create_time;

    private Date edit_time;

    private Date token_end_time;

    private String token;

}

