package com.mengka.springboot.controller;

import com.mengka.springboot.component.ResourceNotFoundException;
import com.mengka.springboot.domain.Note;
import com.mengka.springboot.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  https://www.javaguides.net/2020/01/spring-boot-mariadb-crud-example-tutorial.html
 */
@RestController
@RequestMapping("/api/v1")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/employees")
    public List<Note> getAllEmployees() {
        return noteRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Note> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Note employee = noteRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    /**
     *  POST Body >> raw >> JSON
     *
     *  {"title":"test node 999..",
     * "content":"a test999111",
     * "createTime":"2023-06-02T14:31:36.000+0000",
     * "updateTime":"2023-06-02T14:31:36.000+0000"
     * }
     *
     * @param employee
     * @return
     */
    @PostMapping("/employees")
    public Note createEmployee(@Valid @RequestBody Note employee) {
        return noteRepository.save(employee);
    }

    /**
     *  http://localhost:8071/api/v1/employees/8
     *
     * @param employeeId
     * @param employeeDetails
     * @return
     * @throws ResourceNotFoundException
     */
    @PutMapping("/employees/{id}")
    public ResponseEntity<Note> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                               @Valid @RequestBody Note employeeDetails) throws ResourceNotFoundException {
        Note employee = noteRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setTitle(employeeDetails.getTitle());
        employee.setContent(employeeDetails.getContent());
        employee.setUpdateTime(new Date());
        final Note updatedEmployee = noteRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Note employee = noteRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        noteRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
