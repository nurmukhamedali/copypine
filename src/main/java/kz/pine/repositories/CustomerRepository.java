package kz.pine.repositories;

import kz.pine.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<User, String> {
}
