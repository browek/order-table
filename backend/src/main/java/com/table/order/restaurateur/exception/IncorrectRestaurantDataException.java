package com.table.order.restaurateur.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class IncorrectRestaurantDataException extends RuntimeException {
}
