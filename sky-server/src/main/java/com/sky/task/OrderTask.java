package com.sky.task;

import com.sky.constant.StatusConstant;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderTask {

    private final RedisTemplate<String, Object> redisTemplate;
    private final OrderMapper orderMapper;

    @Scheduled(cron = "0 * * * * ?") //
    public void processTimeoutOrders() {
        log.info("开始处理超时订单, {}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeBefore(Orders.PENDING_PAYMENT, time);

        if (ordersList == null || ordersList.isEmpty()) {
            log.info("没有超时订单需要处理");
            return;
        }

        for (Orders orders : ordersList) {
            orders.setStatus(Orders.CANCELLED);
            orders.setCancelTime(LocalDateTime.now());
            orders.setCancelReason("订单超时未支付，已自动取消");
            orderMapper.update(orders);
        }
    }

    @Scheduled(cron = "0 0 1 * * ?") //
    public void processDeliveredOrders() {
        log.info("开始处理派送中订单, {}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().plusHours(-1);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeBefore(Orders.DELIVERY_IN_PROGRESS, time);

        Integer status = (Integer) redisTemplate.opsForValue().get(StatusConstant.SHOP_STATUS_KEY);
        if (Objects.equals(status, StatusConstant.ENABLE)) {
            log.info("店铺状态为启用，跳过派送中订单处理");
            return;
        }

        if (ordersList == null || ordersList.isEmpty()) {
            log.info("没有已发货订单需要处理");
            return;
        }

        for (Orders orders : ordersList) {
            orders.setStatus(Orders.COMPLETED);
            orderMapper.update(orders);
        }
    }
}
