package tsu.pod.dynamodb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tsu.pod.dynamodb.controller.dto.ItemDto;
import tsu.pod.dynamodb.controller.dto.OrderDto;
import tsu.pod.dynamodb.repository.dao.ItemDao;
import tsu.pod.dynamodb.repository.dao.OrderDao;

import java.util.List;

public final class OrderMapper {

	private static final Logger logger = LoggerFactory.getLogger(OrderMapper.class);

	private OrderMapper() {
	}

	public static OrderDao toOrderDao(OrderDto orderDto) {
		return OrderDao.builder()
			.primaryKey(OrderDao.buidKey(orderDto.getId(), orderDto.getCustomerId()))
			.date(orderDto.getDate())
			.build();
	}

	public static List<ItemDao> toListOfItemDaos(OrderDto orderDto) {
		return orderDto.getItems().stream().map(itemDto -> toItemDao(orderDto.getId(), itemDto)).toList();
	}

	public static ItemDao toItemDao(String orderId, ItemDto itemDto) {
		return ItemDao.builder()
			.primaryKey(ItemDao.buidKey(orderId, itemDto.getId()))
			.quantity(itemDto.getQuantity())
			.price(itemDto.getPrice())
			.build();
	}

	public static OrderDto toOrderDto(OrderDao orderDao, List<ItemDao> itemDaos) {
		return OrderDto.builder()
			.id(orderDao.getOrderId())
			.customerId(orderDao.getCustomerId())
			.date(orderDao.getDate())
			.items(itemDaos.stream().map(OrderMapper::toItemDto).toList())
			.build();
	}

	public static ItemDto toItemDto(ItemDao itemDao) {
		return ItemDto.builder()
			.id(itemDao.getItemId())
			.quantity(itemDao.getQuantity())
			.price(itemDao.getPrice())
			.build();
	}

}
