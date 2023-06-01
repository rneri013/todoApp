package br.com.todoApp.controller;

import br.com.todoApp.CreateStatusCardInput;
import br.com.todoApp.GrpcServerServiceGrpc;
import br.com.todoApp.ResponseDefault;
import br.com.todoApp.UpdateStatusCardInput;
import br.com.todoApp.service.StatusService;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Singleton;

@Singleton
public class StatusController extends GrpcServerServiceGrpc.GrpcServerServiceImplBase {

    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @Override
    public void createStatus(CreateStatusCardInput request, StreamObserver<ResponseDefault> responseObserver) {
        ResponseDefault status = statusService.createStatus(request);
        responseObserver.onNext(status);
        responseObserver.onCompleted();
    }

    @Override
    public void updateStatus(UpdateStatusCardInput request, StreamObserver<ResponseDefault> responseObserver) {
        ResponseDefault responseDefault = statusService.updateStatus(request);
        responseObserver.onNext(responseDefault);
        responseObserver.onCompleted();
    }
}
