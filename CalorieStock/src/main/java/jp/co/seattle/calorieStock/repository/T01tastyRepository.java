package jp.co.seattle.calorieStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.seattle.calorieStock.entity.T01tasty;

@Repository
public interface T01tastyRepository extends JpaRepository<T01tasty, Integer>{
}