package jp.co.seattle.colorieStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.seattle.colorieStock.entity.counter1;

@Repository
public interface counter1Repository extends JpaRepository<counter1, Integer>{
}