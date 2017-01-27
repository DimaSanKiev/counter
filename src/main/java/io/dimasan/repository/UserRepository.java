package io.dimasan.repository;

import io.dimasan.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    User findByName(String name);
}
