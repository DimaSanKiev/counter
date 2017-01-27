package io.dimasan.service;

import io.dimasan.domain.User;

public interface UserService extends CrudService<User> {

    User findByEmail(String email);

    User findByName(String name);
}
