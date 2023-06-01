package br.com.todoApp.service;

import br.com.todoApp.CreateStatusCardInput;
import br.com.todoApp.ResponseDefault;
import br.com.todoApp.UpdateCardInput;
import br.com.todoApp.UpdateStatusCardInput;
import br.com.todoApp.entity.Status;
import br.com.todoApp.repository.StatusRepository;
import jakarta.inject.Singleton;

@Singleton
public class StatusService {

    private StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public ResponseDefault createStatus(CreateStatusCardInput createStatusCardInput) {

        statusRepository.save(
                new Status(createStatusCardInput.getStatus(), createStatusCardInput.getDescription())
        );

        return ResponseDefault.newBuilder()
                .setStatus("OK")
                .setMessage("Created status").build();
    }

    public ResponseDefault updateStatus(UpdateStatusCardInput updateStatusCardInput) {
        statusRepository.update(new Status(updateStatusCardInput.getId(), updateStatusCardInput.getStatus(),
                updateStatusCardInput.getDescription()));

        return ResponseDefault.newBuilder()
                .setStatus("OK")
                .setMessage("Created status").build();
    }

}
