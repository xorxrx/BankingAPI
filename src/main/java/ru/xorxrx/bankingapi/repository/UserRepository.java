package ru.xorxrx.bankingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xorxrx.bankingapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
