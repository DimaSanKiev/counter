package io.dimasan.repository;

import io.dimasan.domain.Activity;
import io.dimasan.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    User findByName(String name);

}