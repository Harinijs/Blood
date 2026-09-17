package com.blood.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blood.Domain.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    Donor findByEmailAndPassword(String email, String password);

}