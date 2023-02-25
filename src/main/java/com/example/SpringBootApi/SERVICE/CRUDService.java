package com.example.SpringBootApi.SERVICE;
import com.example.SpringBootApi.CONTROLLER.Response;
import com.example.SpringBootApi.Model.Employee;
import com.example.SpringBootApi.Model.Manager;
import com.example.SpringBootApi.REPOSITORY.EmployeeRepository;
import com.example.SpringBootApi.REPOSITORY.ManagerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CRUDService {
    private final EmployeeRepository repository;

    public Integer identifyCurrentManager(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Manager manager= (Manager) auth.getPrincipal();
        return manager.getId();
    }

    public List<Employee> getAll() {
        Integer manager_id=identifyCurrentManager();
        return repository.findAllEmployees(manager_id);
    }

    public Optional<Employee> getById(Integer id) {
        Integer manager_id=identifyCurrentManager();
        return repository.findById(id,manager_id);

    }
    public Response addEmployee(Employee employee) {
        Integer manager_id=identifyCurrentManager();
        Optional<Employee> employeeByEmail=repository.findByEmail(employee.getEmail());
        if(employeeByEmail.isPresent()){
           return new Response("Email is Already taken");
        }
        employee.setManager_id(manager_id);
        repository.save(employee);
        return new Response("Employee Saved Successfully");
    }

    public Response deleteById(Integer id) {
        Integer manager_id=identifyCurrentManager();
        Optional<Employee> employeeById=repository.findById(id,manager_id);
        if(!employeeById.isPresent()){
            return new Response("Employee doesn't Exist");
        }
        repository.deleteById(id);
        return new Response("Employee Deleted Successfully");

    }
    @Transactional
    public Response updateById(Integer id, Employee employee) {
        Integer manager_id=identifyCurrentManager();
        Optional<Employee> employeeById=repository.findById(id,manager_id);
        if(!employeeById.isPresent()){
            return new Response("Employee doesn't Exist");
        }
        List<Employee> employees=repository.findAll();
        for(Employee e :employees){
            if(e.getId()==id){
                continue;
            }
            else{
                if(e.getEmail().equals(employee.getEmail())){
                    return new Response("Email is Already taken");
                }
            }
        }
        Employee employeeToUpdate=repository.findById(id).orElseThrow(()->new IllegalStateException("No Match"));
        employeeToUpdate.setFirstname(employee.getFirstname());
        employeeToUpdate.setLastname(employee.getLastname());
        employeeToUpdate.setEmail(employee.getEmail());
        employeeToUpdate.setSalary(employee.getSalary());
        employeeToUpdate.setObservation(employee.getObservation());
        return new Response("Employee Updated Successfully");
    }
}
