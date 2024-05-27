package com.mkyong.id_01;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 *
 *   使用ThreadLocalRandom获取UUID获取更优的效果 去掉"-"
 *
 *   todo:怎么更优的x
 *
 * @author mengka
 * @version 2021/4/30
 * @since
 */
public class Taa {

    public static void main(String... args){
        String idxx = IdWorker.get32UUID();
        System.out.println(idxx);
    }
}
