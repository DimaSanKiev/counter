package io.dimasan.service.impl;

import io.dimasan.domain.Activity;
import io.dimasan.repository.ActivityRepository;
import io.dimasan.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<?> listAll() {
        List<Activity> activities = new ArrayList<>();
        activityRepository.findAll().forEach(activities::add);
        return activities;
    }

    @Override
    public Activity getById(Integer id) {
        return activityRepository.findOne(id);
    }

    @Override
    public Activity saveOrUpdate(Activity domainObject) {
        return activityRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Activity activity = activityRepository.findOne(id);
        activityRepository.delete(activity);
    }
}
