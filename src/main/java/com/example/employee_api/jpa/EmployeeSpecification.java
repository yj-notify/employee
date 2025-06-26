package com.example.employee_api.jpa;

import com.example.employee_api.model.Employee;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class EmployeeSpecification {

    public static Specification<Employee> filterBy(Long id, String name, String department) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(id != null && id>0){
                predicates.add(cb.equal(root.get("id"),id));
            }

            if (name != null && !name.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (department != null && !department.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("department")), "%" + department.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
