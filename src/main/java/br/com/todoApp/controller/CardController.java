package br.com.todoApp.controller;


import br.com.todoApp.CardId;
import br.com.todoApp.CreateCardInput;
import br.com.todoApp.EmptyResponse;
import br.com.todoApp.GrpcServerServiceGrpc;
import br.com.todoApp.ResponseDefault;
import br.com.todoApp.UpdateCardInput;
import br.com.todoApp.service.CardService;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CardController extends GrpcServerServiceGrpc.GrpcServerServiceImplBase {

    private final CardService cardService;

    @Inject
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void createCard(CreateCardInput request, StreamObserver<ResponseDefault> responseObserver) {

        try {

            ResponseDefault card = cardService.createCard(request);
            responseObserver.onNext(card);
            responseObserver.onCompleted();

        } catch (Exception ex) {
            Throwable exception = new StatusRuntimeException(
                    Status.INTERNAL.withDescription(ex.getMessage()));
            responseObserver.onError(exception);
        }
    }

    @Override
    public void deleteCard(CardId request, StreamObserver<EmptyResponse> responseObserver) {

        try {
            cardService.delete(request);
            responseObserver.onNext(EmptyResponse.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception ex) {
            Throwable exception = new StatusRuntimeException(
                    Status.INTERNAL.withDescription(ex.getMessage()));
            responseObserver.onError(exception);
        }
    }

    @Override
    public void updateCard(UpdateCardInput request, StreamObserver<EmptyResponse> responseObserver) {

        try {
            cardService.updateCard(request);
            responseObserver.onNext(EmptyResponse.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception ex) {
            Throwable exception = new StatusRuntimeException(
                    Status.INTERNAL.withDescription(ex.getMessage()));
            responseObserver.onError(exception);
        }
    }
}
