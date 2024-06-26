/**
 *
 */
package com.mengka.springsample.mybatis.service;

import java.util.List;

import com.mengka.springsample.mybatis.repository.ItemRepository;
import org.springframework.stereotype.Service;

import com.mengka.springsample.mybatis.model.Item;
import com.mengka.springsample.mybatis.model.ItemCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * 商品検索Service。
 * @author A-pZ
 *
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class ItemService {
	private final ItemRepository repository;

	public List<Item> selectItems(ItemCondition condition) {

		log.debug("condition -> {}", condition);
		return repository.selectItems(condition);
	}
}
