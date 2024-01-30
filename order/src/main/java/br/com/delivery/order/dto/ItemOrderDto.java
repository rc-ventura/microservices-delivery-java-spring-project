package br.com.delivery.order.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderDto {

    private Long id;
    private Integer quantity;
    private String description;
}
