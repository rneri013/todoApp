package br.com.todoApp.repository;

import br.com.todoApp.entity.Status;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
