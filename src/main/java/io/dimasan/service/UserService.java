package io.dimasan.service;

import io.dimasan.domain.Activity;
import io.dimasan.domain.User;

import java.util.List;

public interface UserService extends CrudService<User> {

    User findByEmail(String email);

    User findByName(String name);

    List<Activity> findUserActivities(User user);
}
