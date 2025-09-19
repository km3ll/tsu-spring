package tsu.pod.dynamodb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tsu.pod.dynamodb.controller.dto.OrderDto;
import tsu.pod.dynamodb.repository.dao.OrderDao;

public final class OrderMapper {

	private static final Logger logger = LoggerFactory.getLogger(OrderMapper.class);

	private OrderMapper() {
	}

	public static OrderDao toOrderDao(OrderDto dto) {
		return OrderDao.builder().primaryKey(OrderDao.buidKey(dto.id(), dto.customerId())).date(dto.date()).build();
	}

	public static OrderDto toOrderDto(OrderDao dao) {
		return new OrderDto(dao.getOrderId(), dao.getCustomerId(), dao.getDate());
	}

}
