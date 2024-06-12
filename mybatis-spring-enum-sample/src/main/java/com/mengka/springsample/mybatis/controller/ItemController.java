/**
 *
 */
package com.mengka.springsample.mybatis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mengka.springsample.mybatis.model.Item;
import com.mengka.springsample.mybatis.model.ItemCondition;
import com.mengka.springsample.mybatis.model.ItemPublish;
import com.mengka.springsample.mybatis.model.TrueOrFalse;
import com.mengka.springsample.mybatis.service.ItemService;

import lombok.RequiredArgsConstructor;

/**
 * 商品検索RestController。
 * @author A-pZ
 *
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

	private final ItemService service;

	/**
	 *  http://127.0.0.1:8080/items/list?publish=2&display=0
	 *
	 * @param publish
	 * @param display
	 * @return
	 */
	@GetMapping("/list")
	public List<Item> searchItems(@RequestParam Optional<String> publish, @RequestParam Optional<String> display) {
		ItemCondition condition = ItemCondition.builder()
				.status(ItemPublish.getDisplayStatus(publish.orElse("")))
				.display(TrueOrFalse.getTrueOrFalse(display.orElse("")))
				.build();

		return service.selectItems(condition);
	}

}
