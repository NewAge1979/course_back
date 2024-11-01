package pl.anigram.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.anigram.backend.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}