package br.com.delivery.payment.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.delivery.payment.dto.PaymentDto;
import br.com.delivery.payment.model.Payment;
import br.com.delivery.payment.model.Status;
import br.com.delivery.payment.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository repository;

    @Autowired
   private ModelMapper modelMapper;

    public Page <PaymentDto> getAll(Pageable pagination) {
        return repository.findAll(pagination)
        .map(p-> modelMapper.map(p, PaymentDto.class));
        
    }

    public PaymentDto getID(Long id) {
        Payment payment = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException());
        
        return modelMapper.map(payment, PaymentDto.class);
        
    }

    public PaymentDto save (PaymentDto dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setStatus(Status.Created);
        repository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto update (Long id, PaymentDto dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setId(id);
        payment = repository.save(payment);
        
        return modelMapper.map(payment, PaymentDto.class);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }
}
