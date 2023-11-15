package com.alok.employee.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmptyListException extends RuntimeException{
    private static final long serialVersionUID=1L;
    private String errorMessage;
}
