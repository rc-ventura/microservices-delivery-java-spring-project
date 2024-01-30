package br.com.delivery.order.model;


public enum Status {
    placed,
    cancelled,
    paid,
    unauthorized_request,
    confirmed,
    ready,
    out_for_delivery,
    delivered;
}
