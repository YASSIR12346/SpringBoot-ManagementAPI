package com.example.SpringBootApi.CONTROLLER;



import com.example.SpringBootApi.Model.Employee;
import com.example.SpringBootApi.SERVICE.CRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/management/CRUD")
@RestController
@RequiredArgsConstructor
public class CRUDController {
    private final CRUDService CRUDService;

    @GetMapping("/getAll")
    public List<Employee> getAll(){
        return CRUDService.getAll();

    }
    @GetMapping("/getById/{id}")
    public Optional<Employee> getById(@PathVariable("id") Integer id){
        return CRUDService.getById(id);

    }

    @PostMapping("/addEmployee")
    public Response addEmployee(@RequestBody Employee employee){
        return CRUDService.addEmployee(employee);
    }

    @DeleteMapping("/deleteById/{id}")
    public Response deleteById(@PathVariable("id") Integer id){
        return CRUDService.deleteById(id);
    }
    @PutMapping("/updateById/{id}")
    public Response updateById( @PathVariable("id") Integer id,@RequestBody Employee employee){
        return CRUDService.updateById(id,employee);
    }



}
