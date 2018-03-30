package com.expertsoft.core.model;

import com.expertsoft.core.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select a from Account a " +
            "join fetch a.roles " +
            "where a.email = :email")
    Optional<Account> findByEmailWithRoles(@Param("email") String email);
}
