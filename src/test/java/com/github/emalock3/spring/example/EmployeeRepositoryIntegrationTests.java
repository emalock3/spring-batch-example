package com.github.emalock3.spring.example;

import com.github.emalock3.spring.example.domain.Department;
import com.github.emalock3.spring.example.domain.DepartmentRepository;
import com.github.emalock3.spring.example.domain.Employee;
import com.github.emalock3.spring.example.domain.EmployeeRepository;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import static org.hamcrest.Matchers.*;
import org.junit.After;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EmployeeRepositoryIntegrationTests {
    
    @Autowired
    private EmployeeRepository empRepo;
    @Autowired
    private DepartmentRepository deptRepo;
    
    @Before
    public void setUp() {
        empRepo.deleteAll();
        deptRepo.deleteAll();
    }
    
    @After
    public void tearDown() {
        empRepo.deleteAll();
        deptRepo.deleteAll();
    }
    
    @Test
    public void testFindAll() {
        if (empRepo.findAll().iterator().hasNext()) {
            fail("emp must be empty.");
        }
        Department dept = deptRepo.save(new Department(null, "foo"));
        Employee emp = new Employee(null, "Taro Suzuki");
        emp.setDeptId(dept);
        emp = empRepo.save(emp);
        Employee findEmp = empRepo.findAll().iterator().next();
        assertThat(findEmp, is(not(nullValue())));
        assertThat(findEmp.getName(), is(emp.getName()));
    }
    
}
