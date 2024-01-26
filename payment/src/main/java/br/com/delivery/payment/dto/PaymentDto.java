package br.com.delivery.payment.dto;
import br.com.delivery.payment.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDto {
     
    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String cod;
    private Status status;
    private Long id_order;
    private Long id_payment_type;
}




    
   