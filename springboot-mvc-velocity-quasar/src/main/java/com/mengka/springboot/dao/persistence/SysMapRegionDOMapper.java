package com.mengka.springboot.dao.persistence;

import com.mengka.springboot.dao.domain.SysMapRegionDO;

public interface SysMapRegionDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_map_region
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_map_region
     *
     * @mbg.generated
     */
    int insert(SysMapRegionDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_map_region
     *
     * @mbg.generated
     */
    int insertSelective(SysMapRegionDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_map_region
     *
     * @mbg.generated
     */
    SysMapRegionDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_map_region
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysMapRegionDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_map_region
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysMapRegionDO record);
}