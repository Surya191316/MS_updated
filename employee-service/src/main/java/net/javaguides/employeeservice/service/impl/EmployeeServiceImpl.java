package net.javaguides.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;   // This is for DB access
//    private RestTemplate restTemplate;
//    private WebClient webClient;
    private APIClient apiClient;   // This for calling Department Service

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDTO = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );
        return savedEmployeeDTO;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
//        This is REST template
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

//        This is Web Client
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/"+ employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
//      This is Feign Client
        Employee employee = employeeRepository.findById(employeeId).get();  // Queries Employee DB, Returns employee record
        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
//      When this runs: Feign internally:
//        1️⃣ Builds URL  2️⃣ Sends HTTP GET 3️⃣ Receives JSON 4️⃣ Converts JSON → DepartmentDto
//        5️⃣ Returns object -->> No manual HTTP handling.

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

//    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
//    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
//    @Override
//    public APIResponseDto getEmployeeById(Long employeeId) {
//
//        LOGGER.info("inside getEmployeeById() method");
//        Employee employee = employeeRepository.findById(employeeId).get();

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://DEPARTMENT-SERVICE/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

      //  DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

//        OrganizationDto organizationDto = webClient.get()
//                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
//                .retrieve()
//                .bodyToMono(OrganizationDto.class)
//                .block();
//
//        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
//
//        APIResponseDto apiResponseDto = new APIResponseDto();
//        apiResponseDto.setEmployee(employeeDto);
//        apiResponseDto.setDepartment(departmentDto);
//        apiResponseDto.setOrganization(organizationDto);
//        return apiResponseDto;
//    }

//    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
//
//        LOGGER.info("inside getDefaultDepartment() method");
//
//        Employee employee = employeeRepository.findById(employeeId).get();
//
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setDepartmentName("R&D Department");
//        departmentDto.setDepartmentCode("RD001");
//        departmentDto.setDepartmentDescription("Research and Development Department");
//
//        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
//
//        APIResponseDto apiResponseDto = new APIResponseDto();
//        apiResponseDto.setEmployee(employeeDto);
//        apiResponseDto.setDepartment(departmentDto);
//        return apiResponseDto;
//    }
}
