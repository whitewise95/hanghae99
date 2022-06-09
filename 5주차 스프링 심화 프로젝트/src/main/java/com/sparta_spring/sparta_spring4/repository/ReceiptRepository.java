package com.sparta_spring.sparta_spring4.repository;

import com.sparta_spring.sparta_spring4.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
