package br.com.delivery.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.delivery.payment.model.Payment;

public interface PaymentRepository extends JpaRepository <Payment, Long> {
    
}
