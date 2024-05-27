package com.mkyong.t_01;

import com.mkyong.model.BaseDO;
import lombok.Builder;
import lombok.Data;

/**
 * @author mengka
 * @version 2021/4/29
 * @since
 */
@Data
@Builder
public class BookDO extends BaseDO {

    private String id;

    private String name;

    private String email;

    private BookAddrDO bookAddr;
}
