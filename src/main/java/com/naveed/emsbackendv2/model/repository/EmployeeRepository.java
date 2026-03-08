package com.naveed.emsbackendv2.model.repository;

import com.naveed.emsbackendv2.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // UUID کے ذریعے ایمپلائی کو تلاش کرنے کے لیے (ٹیچر کے اصول کے مطابق)
    Optional<Employee> findByUuid(String uuid);
}