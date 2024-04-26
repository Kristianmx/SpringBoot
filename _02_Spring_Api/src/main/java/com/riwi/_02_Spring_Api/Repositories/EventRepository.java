package com.riwi._02_Spring_Api.Repositories;

import com.riwi._02_Spring_Api.Entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Events,String> {

}
