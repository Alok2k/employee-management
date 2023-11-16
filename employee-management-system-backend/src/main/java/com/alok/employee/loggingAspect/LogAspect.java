package com.alok.employee.loggingAspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.alok.employee.services.EmployeeServiceImpl.createEmployee(..)) ")
    public void createEmployee() {}

    @Pointcut("execution(* com.alok.employee.services.EmployeeServiceImpl.getAllEmployees(..)) ")
    public void getAllEmployees() {}

    @Pointcut("execution(* com.alok.employee.services.EmployeeServiceImpl.deleteEmployee(..)) ")
    public void deleteEmployee() {}

    @Pointcut("execution(* com.alok.employee.services.EmployeeServiceImpl.getEmployeeById(..)) ")
    public void getEmployeeById() {}

    @Pointcut("execution(* com.alok.employee.services.EmployeeServiceImpl.updateEmployee(..)) ")
    public void updateEmployee() {}


    @AfterReturning(value = "createEmployee() || getAllEmployees() || deleteEmployee() || getEmployeeById() || updateEmployee() ",returning = "result")
    public void logSuccess(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("The method {} has completed successfully", methodName);
    }

    @AfterThrowing(value = "createEmployee() || getAllEmployees() || deleteEmployee() || getEmployeeById() || updateEmployee() ",throwing = "exception" )
    public void logFailure(Exception exception) {
        log.error(exception.getMessage());
    }

}
