package io.dimasan.bootstrap;

import io.dimasan.domain.Activity;
import io.dimasan.domain.User;
import io.dimasan.service.ActivityService;
import io.dimasan.service.RoleService;
import io.dimasan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    }
    
    private void loadActivities() {
        Activity activity1 = new Activity();
        activity1.setTitle("Coffee");
        activity1.setDescription("I'd like to count how many cups of coffee I drink.");
        activity1.setDateCreated(new Date());
        activityService.saveOrUpdate(activity1);

        Activity activity2 = new Activity();
        activity2.setTitle("Tea");
        activity2.setDescription("How many cups of tea I drink.");
        activity2.setDateCreated(new Date());
        activityService.saveOrUpdate(activity2);

        Activity activity3 = new Activity();
        activity3.setTitle("Posts");
        activity3.setDescription("How many posts I wrote?");
        activity3.setDateCreated(new Date());
        activityService.saveOrUpdate(activity3);
    }
}
