package br.com.delivery.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.delivery.order.model.Order;
import br.com.delivery.order.model.Status;
import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository <Order, Long> { 
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order o set o.status = :status where o = :order")
    void changeStatus(Status status, Order order);

    @Query(value = "SELECT o from Order o LEFT JOIN FETCH o.itens where o.id = :id")
    Order byOrderIdWithItens (Long id);
}
