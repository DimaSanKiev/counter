package io.dimasan.service.impl;

import io.dimasan.domain.Activity;
import io.dimasan.service.ActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ActivityServiceImplTest {

    private ActivityService activityService;

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Test
    public void listAll() throws Exception {
        List<Activity> activities = (List<Activity>) activityService.listAll();

        assertNotNull(activities);
        assertEquals(4, activities.size());
    }

    @Test
    public void saveOrUpdate() throws Exception {
        Activity activity = new Activity();
        activity.setTitle("TestActivity");
        Activity savedActivity = activityService.saveOrUpdate(activity);
        Integer id = savedActivity.getId();

        assertNotNull(savedActivity);
        assertEquals("TestActivity", activityService.getById(id).getTitle());
    }
}