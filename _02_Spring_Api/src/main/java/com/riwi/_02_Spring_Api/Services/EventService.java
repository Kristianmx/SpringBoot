package com.riwi._02_Spring_Api.Services;

import com.riwi._02_Spring_Api.Entity.Events;
import com.riwi._02_Spring_Api.Repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService implements  IEventService {
    @Autowired
    private final EventRepository objEventRepository;
    @Override
    public Events save(Events objEvents) {
        return this.objEventRepository.save(objEvents);
    }

    @Override
    public List<Events> getAll() {
        return this.objEventRepository.findAll();
    }

    @Override
    public Events getById(String id) {
        return this.objEventRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Events> finfAllPaginate(int page, int size) {
        if (page<0){
            page=1;
        }
        Pageable objPage = PageRequest.of(page,size);
        return this.objEventRepository.findAll(objPage);
    }

    @Override
    public void delete(String id) {
    Events events = this.objEventRepository.findById(id).orElseThrow();
    this.objEventRepository.delete(events);
    }

    @Override
    public Events update(Events objEvents) {
        return this.objEventRepository.save(objEvents);
    }

    public static boolean dateValid(LocalDate dataString, String format){

        DateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dataString.toString());
            String formattedDate = sdf.format(date);
            return formattedDate.equals(dataString);
        }catch (ParseException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
