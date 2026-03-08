package com.naveed.emsbackendv2.model.repository;

import com.naveed.emsbackendv2.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByUuid(String uuid);
}