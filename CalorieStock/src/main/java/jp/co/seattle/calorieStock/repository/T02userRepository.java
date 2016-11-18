package jp.co.seattle.calorieStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.seattle.calorieStock.entity.T02user;

@Repository
public interface T02userRepository extends JpaRepository<T02user, Integer>{

}