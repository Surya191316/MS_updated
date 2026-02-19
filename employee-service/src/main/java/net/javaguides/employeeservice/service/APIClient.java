package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// So here we have 2 instance of department-service 8080, 9090 so if 8080 fails it should call 9090
// For this we can write like
// @FeignClient(url = "http://localhost:8080", "http://localhost:9099", value = "DEPARTMENT-SERVICE")
// The problem with this is if we have vast instance we can't have every link here so...
@FeignClient(name="DEPARTMENT-SERVICE")
// Creates a proxy class for me
// Whenever I call methods inside this interface, it makes an HTTP call.
// Sends HTTP requests to that base URL.
// value = "DEPARTMENT-SERVICE" This is just the name of the client bean.
public interface APIClient {
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}

// In the above code
// Feign does:
//1️⃣ Send GET request
//2️⃣ Get JSON
//3️⃣ Convert JSON → DepartmentDto
//4️⃣ Return object
//You write ZERO HTTP logic.