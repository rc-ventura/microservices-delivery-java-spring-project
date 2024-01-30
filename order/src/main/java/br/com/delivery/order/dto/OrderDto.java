package br.com.delivery.order.dto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.delivery.order.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime dateHour;
    private Status status;
    private List<ItemOrderDto> itens = new ArrayList<>();



}
