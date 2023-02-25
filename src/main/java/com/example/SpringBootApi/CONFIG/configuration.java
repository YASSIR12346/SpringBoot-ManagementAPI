package com.example.SpringBootApi.CONFIG;

import com.example.SpringBootApi.Model.Employee;
import com.example.SpringBootApi.Model.Manager;
import com.example.SpringBootApi.Model.Role;
import com.example.SpringBootApi.REPOSITORY.EmployeeRepository;
import com.example.SpringBootApi.REPOSITORY.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class configuration {
    private final PasswordEncoder passwordEncoder;
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;

    @Bean
    CommandLineRunner commandLineRunner(
            ManagerRepository repository ){
        return args->{
            Manager manager1=new Manager(1,"YASSIR","NASRI","nasriyasser007@gmail.com",passwordEncoder.encode("123"), Role.ADMIN);
            managerRepository.save(manager1);
            Manager manager2=new Manager(2,"YASSIR","NASRI","nasriyasser006@gmail.com",passwordEncoder.encode("123"), Role.ADMIN);
            managerRepository.save(manager2);
            Employee employee1=new Employee(1,"JACK","REACHER","jack@gmail.com",2000.0,"Good",1);
            employeeRepository.save(employee1);
            Employee employee2=new Employee(2,"MARK","SPECTER","mark@gmail.com",2000.0,"Good",2);
            employeeRepository.save(employee2);
        };
    }

}
