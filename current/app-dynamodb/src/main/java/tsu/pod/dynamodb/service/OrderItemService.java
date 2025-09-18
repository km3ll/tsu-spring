package tsu.pod.dynamodb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tsu.pod.dynamodb.model.OrderItem;
import tsu.pod.dynamodb.repository.OrderItemRepository;
import tsu.pod.dynamodb.repository.dao.OrderItemDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class OrderItemService {

    private final Logger logger = LoggerFactory.getLogger(OrderItemService.class);

    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public OrderItem save(OrderItem orderItem) {
        var orderItemDao = OrderItemDao.builder()
            .keyDao(OrderItemDao.buildKey(orderItem.getOrderId(), orderItem.getItemId()))
            .quantity(orderItem.getQuantity())
            .price(orderItem.getPrice())
            .build();
        repository.save(orderItemDao);
        return orderItem;
    }

    public Optional<OrderItem> find(String orderId, String itemId) {
        var keyDao = OrderItemDao.buildKey(orderId, itemId);
        return repository.findById(keyDao).map(this::toOrderItem);
    }

    public List<OrderItem> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
            .map(this::toOrderItem)
            .toList();
    }

    private OrderItem toOrderItem(OrderItemDao dao) {
        return OrderItem.builder()
            .orderId(dao.getPk().split("#")[1])
            .itemId(dao.getSk().split("#")[1])
            .quantity(dao.getQuantity())
            .price(dao.getPrice())
            .build();
    }

}
