package com.mengka.threaddemo.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author mengka
 * @Date 2024-07-06 19:44
 */
@Service("ss")
public class PermissionService {

    public boolean hasPermi(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
//        LoginUser loginUser = SecurityUtils.getLoginUser();
        if("动总总负责人".equals(permission)){
            return true;
        }
        return false;
    }
}
