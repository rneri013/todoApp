package br.com.todoApp.service;

import br.com.todoApp.CardId;
import br.com.todoApp.CreateCardInput;
import br.com.todoApp.ResponseDefault;
import br.com.todoApp.UpdateCardInput;
import br.com.todoApp.dto.CardDTO;
import br.com.todoApp.entity.Card;
import br.com.todoApp.repository.CardRepository;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Singleton;

@Singleton
public class CardService {

    private CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public ResponseDefault createCard(CreateCardInput input) throws Exception {

        isValidCreateCardInputs(input);
        CardDTO build = CardDTO.builder()
                .withTitle(input.getTitle())
                .withDescription(input.getDescription())
                .withStatus(input.getStatus()).build();

        Card card = new Card();
        card.setTitle(build.getTitle());
        card.setDescription(build.getDescription());
        card.setStatus(build.getStatus());
        cardRepository.save(card);

        return ResponseDefault.newBuilder()
                .setStatus("OK")
                .setMessage("Created card").build();

    }

    public void delete(CardId cardId) {

        Card card = new Card();
        card.setId(cardId.getId());
        cardRepository.delete(card);
    }

    public void updateCard(UpdateCardInput updateCardInput) {

        Card card = new Card();
        card.setId(updateCardInput.getId());
        card.setStatus(updateCardInput.getStatus());
        card.setDescription(updateCardInput.getDescription());
        card.setTitle(updateCardInput.getTitle());
        cardRepository.update(card);

    }

    private void isValidCreateCardInputs(CreateCardInput input) throws Exception {

        String message = "INCORRECT PARAMS: %s";
        if (StringUtils.isEmpty(input.getTitle())) {
            throw new Exception(String.format(message, "title"));
        }

        if (StringUtils.isEmpty(input.getDescription())) {
            throw new Exception(String.format(message, "description"));
        }

        if (StringUtils.isEmpty(input.getStatus())) {
            throw new Exception(String.format(message, "status"));
        }

    }

    public void find() {
        cardRepository.findAll();
    }
}
