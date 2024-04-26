package com.riwi._02_Spring_Api.Services;

import com.riwi._02_Spring_Api.Entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEventService {
    public Events save(Events objEvents);

    public List<Events> getAll();

    public Events getById(String id);
    public Page<Events> finfAllPaginate(int page, int size);


    public void delete(String id);

    public Events update(Events objEvents);
}
