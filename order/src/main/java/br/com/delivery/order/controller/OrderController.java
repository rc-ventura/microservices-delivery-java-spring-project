package br.com.delivery.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.delivery.order.dto.OrderDto;
import br.com.delivery.order.dto.StatusDto;
import br.com.delivery.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

        @Autowired
        private OrderService service;

        @GetMapping()
        public List<OrderDto> getAll() {
            return service.getAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDto> findById(@PathVariable @NotNull Long id) {
            OrderDto dto = service.findById(id);

            return  ResponseEntity.ok(dto);
        }

        @GetMapping("/port")
        public String returnPort(@Value("${local.server.port}") String port) {
            return String.format("Request answered by instance executed on the port %s", port);
        }


        @PostMapping()
        public ResponseEntity<OrderDto> toRealizeOrder(@RequestBody @Valid OrderDto dto, UriComponentsBuilder uriBuilder) {
            OrderDto realizedOrderDto = service.createOrder(dto);

            URI url = uriBuilder.path("/orders/{id}")
                        .buildAndExpand(realizedOrderDto.getId()).toUri();

            return ResponseEntity.created(url).body(realizedOrderDto);

        }

        @PutMapping("/{id}/status")
        public ResponseEntity<OrderDto> updateStatus(@PathVariable Long id, @RequestBody StatusDto status){
           OrderDto dto = service.updateStatus(id, status);

            return ResponseEntity.ok(dto);
        }


        @PutMapping("/{id}/paid")
        public ResponseEntity<Void> toAprovePaymentOrder(@PathVariable @NotNull Long id) {
            service.aproveOrderPayment(id);

            return ResponseEntity.ok().build();

        }
}
