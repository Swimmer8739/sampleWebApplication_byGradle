package jp.co.seattle.greeting.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.seattle.greeting.entity.counter1;

@Repository
public interface Counter1Repository extends JpaRepository<counter1, Integer>{
}