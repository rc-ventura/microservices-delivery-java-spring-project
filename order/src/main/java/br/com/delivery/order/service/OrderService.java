package br.com.delivery.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.delivery.order.dto.OrderDto;
import br.com.delivery.order.dto.StatusDto;
import br.com.delivery.order.model.Order;
import br.com.delivery.order.model.Status;
import br.com.delivery.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    @Autowired
    private OrderRepository repository;

    @Autowired
    private  ModelMapper modelMapper;


    public List <OrderDto> getAll() {
        return repository.findAll().stream()
                .map(o -> modelMapper.map(o, OrderDto.class))
                .collect(Collectors.toList());

    }

    public OrderDto findById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(order, OrderDto.class);
    }


    public OrderDto createOrder(OrderDto dto) {
        Order order = modelMapper.map(dto, Order.class);

        order.setDateHour(LocalDateTime.now());
        order.setStatus(Status.placed);
        order.getItens().forEach(item -> item.setOrder(order));

        return modelMapper.map(repository.save(order), OrderDto.class);
    }

    public OrderDto updateStatus(Long id, StatusDto dto) {

        Order order = repository.byOrderIdWithItens(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(dto.getStatus());
        repository.changeStatus(dto.getStatus(), order);
        return modelMapper.map(order, OrderDto.class);
    }

    public void aproveOrderPayment(Long id) {

        Order order = repository.byOrderIdWithItens(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(Status.paid);
        repository.changeStatus(Status.paid, order);
    }
}


