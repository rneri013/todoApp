package br.com.todoApp.repository;

import br.com.todoApp.entity.Card;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Override
    List<Card> findAll();
}
