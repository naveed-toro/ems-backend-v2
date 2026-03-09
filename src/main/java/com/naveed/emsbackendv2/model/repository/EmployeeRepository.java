package com.naveed.emsbackendv2.model.repository;

import com.naveed.emsbackendv2.model.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // یہ آپ کا پہلے سے موجود شاندار کوڈ ہے
    Optional<Employee> findByUuid(String uuid);

    // نیا فیچر: نام سے سرچ کرنے اور Pagination کرنے کا جادوئی کوڈ
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
}