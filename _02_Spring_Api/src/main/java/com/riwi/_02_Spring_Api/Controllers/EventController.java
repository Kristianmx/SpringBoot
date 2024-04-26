package com.riwi._02_Spring_Api.Controllers;

import com.riwi._02_Spring_Api.Entity.Events;
import com.riwi._02_Spring_Api.Services.EventService;
import com.riwi._02_Spring_Api.Services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@AllArgsConstructor
public class EventController {
    @Autowired
    private final IEventService objEventService;

//    @GetMapping
//    public ResponseEntity<List<Events>> getAll(){return  ResponseEntity.ok(this.objEventService.getAll());}

    @GetMapping(path = "/{id}")
    public ResponseEntity<Events> get(@PathVariable String id){
        return ResponseEntity.ok(this.objEventService.getById(id));
    }
    @PostMapping
    public ResponseEntity<Events> insert(
            @RequestBody Events objEvent
    ){
        ResponseEntity<Events>valid=null;
        if (!LocalDate.now().isAfter(objEvent.getFecha())){
            if (objEvent.getCapacidad()<0){
                objEvent.setCapacidad(0);
                valid= ResponseEntity.ok(this.objEventService.update(objEvent));
            }else {
                valid = ResponseEntity.ok(this.objEventService.update(objEvent));
            }
        }else {
            System.out.println("NO PASA");
        }
       return valid;
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Events> update(@RequestBody Events objEvents,@PathVariable String id){
        objEvents.setId(id);
        ResponseEntity<Events>valid=null;
        if (!LocalDate.now().isAfter(objEvents.getFecha())){
            if (objEvents.getCapacidad()<0){
                objEvents.setCapacidad(0);
                valid= ResponseEntity.ok(this.objEventService.update(objEvents));
            }else {
                valid = ResponseEntity.ok(this.objEventService.update(objEvents));
            }
        }else {
            System.out.println("NO PASA");
        }
        return valid;
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Events> delete(@PathVariable String id){
        this.objEventService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public List<Events> showViewEvents( @RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "3")int size){
        Page<Events> listEvents= this.objEventService.finfAllPaginate(page-1,size);
        return listEvents.getContent();
    }

}
