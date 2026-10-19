package ru.xorxrx.bankingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xorxrx.bankingapi.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
