package com.alok.employee.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeNotFoundException extends RuntimeException {
    private static final long serialVersionUID=1L;
    private String error;
}
