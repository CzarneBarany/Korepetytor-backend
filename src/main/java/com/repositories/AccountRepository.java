package com.repositories;

import com.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    AccountEntity getAccountEntityByAccountId(int accountId);
    AccountEntity getAccountEntityByEmail(String email);
    boolean existsByEmail(String email);
}
