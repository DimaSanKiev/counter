package io.dimasan.service.impl;

import io.dimasan.domain.Event;
import io.dimasan.repository.EventRepository;
import io.dimasan.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<?> listAll() {
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    @Override
    public Event getById(Integer id) {
        return eventRepository.findOne(id);
    }

    @Override
    public Event saveOrUpdate(Event domainObject) {
        return eventRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        Event event = eventRepository.findOne(id);
        eventRepository.delete(event);
    }
}
