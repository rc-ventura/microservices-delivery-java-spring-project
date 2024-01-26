package br.com.delivery.payment.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.delivery.payment.dto.PaymentDto;
import br.com.delivery.payment.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RequestMapping("/payments")
@RestController
public class PaymentController {
    
    @Autowired
    private PaymentService service;


    @GetMapping
    public Page <PaymentDto> toListAll(@PageableDefault(size = 10) Pageable pagination) {
        return service.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity <PaymentDto> toListById(@PathVariable @NotNull Long id) {
        PaymentDto dto = service.getID(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity <PaymentDto> toRegister (@RequestBody @Valid PaymentDto dto, 
    UriComponentsBuilder uriBuilder ) {
        PaymentDto paymentDto = service.save(dto);
        URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(paymentDto.getId()).toUri();

        return ResponseEntity.created(uri).body(paymentDto);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity <PaymentDto> toUpdate(@PathVariable @NotNull Long id, 
    @RequestBody @Valid PaymentDto dto) {
        PaymentDto updated = service.update(id, dto);

        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity <PaymentDto> remove(@PathVariable @NotNull Long id) {
        service.delete(id);
        
        return ResponseEntity.noContent().build();
    }

}
