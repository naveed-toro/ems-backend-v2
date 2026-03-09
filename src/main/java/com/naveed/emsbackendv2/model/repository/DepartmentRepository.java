package com.naveed.emsbackendv2.model.repository;

import com.naveed.emsbackendv2.model.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // یہ آپ کا پرانا کوڈ ہے
    Optional<Department> findByUuid(String uuid);

    // یہ وہ جادوئی لائن ہے جو رول بیک میں غائب ہو گئی تھی
    Page<Department> findByNameContainingIgnoreCase(String name, Pageable pageable);
}