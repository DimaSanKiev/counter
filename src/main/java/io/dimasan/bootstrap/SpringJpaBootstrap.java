package io.dimasan.bootstrap;

import io.dimasan.domain.Activity;
import io.dimasan.domain.User;
import io.dimasan.domain.security.Role;
import io.dimasan.service.ActivityService;
import io.dimasan.service.RoleService;
import io.dimasan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ActivityService activityService;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadActivities();
        loadRoles();
        loadUsers();
        assignUsersToDefaultRole();
        assignUsersToAdminRole();
        assignActivitiesToUsers();
    }

    private void loadActivities() {
        Activity activity1 = new Activity();
        activity1.setTitle("Coffee");
        activity1.setDescription("How many cups of coffee I drunk.");
        activity1.setDateCreated(new Date());
        activityService.saveOrUpdate(activity1);

        Activity activity2 = new Activity();
        activity2.setTitle("Tea");
        activity2.setDescription("How many cups of tea I drunk.");
        activity2.setDateCreated(new Date());
        activityService.saveOrUpdate(activity2);

        Activity activity3 = new Activity();
        activity3.setTitle("Posts");
        activity3.setDescription("How many posts I wrote.");
        activity3.setDateCreated(new Date());
        activityService.saveOrUpdate(activity3);

        Activity activity4 = new Activity();
        activity4.setTitle("Topics");
        activity4.setDescription("How many topics I learnt.");
        activity4.setDateCreated(new Date());
        activityService.saveOrUpdate(activity4);
    }

    private void loadRoles() {
        Role userRole = new Role();
        userRole.setRole("USER");
        roleService.saveOrUpdate(userRole);

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
    }

    private void loadUsers() {
        User user1 = new User();
        user1.setName("Jim");
        user1.setEmail("jim@gmail.com");
        user1.setPassword("password1");
        user1.setDateRegistered(new Date());
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setName("Adriana");
        user2.setEmail("adriana@yahoo.com");
        user2.setPassword("password2");
        user2.setDateRegistered(new Date());
        userService.saveOrUpdate(user2);
    }

    private void assignUsersToDefaultRole() {
        List<User> users = (List<User>) userService.listAll();
        List<Role> roles = (List<Role>) roleService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void assignUsersToAdminRole() {
        List<User> users = (List<User>) userService.listAll();
        List<Role> roles = (List<Role>) roleService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getName().equals("Jim")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    private void assignActivitiesToUsers() {
        List<User> users = (List<User>) userService.listAll();
        List<Activity> activities = (List<Activity>) activityService.listAll();
        List<Activity> jimActivities = activities.subList(0, activities.size() / 2);
        List<Activity> adrianaActivities = activities.subList(activities.size() / 2, activities.size());
        users.get(0).setActivities(jimActivities);
        users.get(1).setActivities(adrianaActivities);
        userService.saveOrUpdate(users.get(0));
        userService.saveOrUpdate(users.get(1));
    }

}
