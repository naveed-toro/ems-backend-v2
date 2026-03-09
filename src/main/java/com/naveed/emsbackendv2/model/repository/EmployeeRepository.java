package com.naveed.emsbackendv2.model.repository;

import com.naveed.emsbackendv2.model.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // یہ آپ کا پہلے سے موجود شاندار کوڈ ہے
    Optional<Employee> findByUuid(String uuid);

    // نیا فیچر: نام سے سرچ کرنے اور Pagination کرنے کا جادوئی کوڈ
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE " +
            "(:departmentUuid IS NULL OR e.department.uuid = :departmentUuid) AND " +
            "(:positionUuid IS NULL OR e.position.uuid = :positionUuid) AND " +
            "(:status IS NULL OR " +
            "(:status = 'ACTIVE' AND e.isDeleted = false) OR " +
            "(:status = 'INACTIVE' AND e.isDeleted = true))")
    List<Employee> findByFilters(@Param("departmentUuid") String departmentUuid,
                                 @Param("positionUuid") String positionUuid,
                                 @Param("status") String status);
}