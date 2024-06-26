/**
 *
 */
package com.mengka.springsample.mybatis.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mengka.springsample.mybatis.model.Item;
import com.mengka.springsample.mybatis.model.ItemCondition;

import lombok.RequiredArgsConstructor;

/**
 * @author A-pZ
 *
 */
@Repository
@RequiredArgsConstructor
public class ItemRepository {
	private final SqlSession sqlSession;

	public List<Item> selectItems(ItemCondition condition) {
		return sqlSession.selectList("selectItems", condition);
	}
}
