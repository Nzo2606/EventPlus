package com.eventplus.domain.registration.validations;

import com.eventplus.domain.event.ocurrence.EventOccurrenceRepository;
import com.eventplus.domain.registration.Registration;
import com.eventplus.domain.registration.dto.RegistrationCreateData;
import com.eventplus.domain.registration.enums.RegistrationMode;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class OccurrenceSelectedDaysValidator implements RegistrationCreationData{

    @Autowired
    private EventOccurrenceRepository occurrenceRepository;

    @Override
    public void validate( RegistrationCreateData data, Registration registration) throws ValidationException {

        // 1. Pega os valores vindos do DTO
        var occurrenceIds = data.occurrenceIds();

        // 2. Verifica se o modo de registro do evento são os dias selecionados ("SELECTED_DAYS")
        if (registration.getEvent().getRegistrationMode().equals(RegistrationMode.SELECTED_DAYS)){

            // 2.1 Verifica se o campo "occurrencesIds" não está presente (null), ou está vazio (.isEmpty)
            if (occurrenceIds == null || occurrenceIds.isEmpty()) {
                throw new ValidationException(
                        "At least one occurrence must be selected."
                );
            }
        }else{
            return;
        }

        // 3. Filtrando os ‘IDs’, não permitindo dados duplicados.
            //  O "HashSet" é uma implementação de "set" que elimina elementos repetidos.
        var uniqueIds = new HashSet<>(occurrenceIds);


        // 4. Comparando o tamanho do objeto dos ‘IDs’ filtrados com o original
            // Caso o tamanho for diferente, significa que há ocorrências duplicadas nas ocorrências oferecidas pelo usuário.
        if (uniqueIds.size() != occurrenceIds.size()) {
            throw new ValidationException(
                    "Duplicate occurrences are not allowed."
            );
        }

        //5. Obtemos os registros no banco conforme os ‘IDs’ das ocorrências oferecidas.
        var occurrences = occurrenceRepository.findAllById(occurrenceIds);
            /// Por exemplo:
            /// occurrenceIds = [10, 11, 12]
            ///
            ///
            /// O Repository procura essas três ocorrências no banco.
            ///
            /// Imagine que somente duas existam:
            /// occurrences = [Occurrence10, Occurrence11]
            ///

        //6. Comparamos: Quantidade de entidades encontradas | Quantidade de ‘IDs’ únicos solicitados.
            // Se solicitamos três ocorrências, mas somente duas foram encontradas, sabemos que pelo menos uma não existe.
        if (occurrences.size() != uniqueIds.size()) {
            throw new ValidationException(
                    "One or more selected occurrences do not exist."
            );
        }

        // 7. Verificando se pertencem ao evento
        boolean belongsToAnotherEvent = occurrences.stream()
                .anyMatch(occurrence ->
                        !occurrence.getEvent().getId().equals(data.eventId())
                );
        ///
            /// '.stream()'
                /// Transforma a coleção em um fluxo de elementos, permitindo utilizar operações funcionais do Java.
        ///
            /// '.anyMatch()'
                /// Verifica se pelo menos um elemento da coleção atende à condição informada.
                    /// Ele retorna um boolean:
                    /// - true: encontrou pelo menos um elemento que atende à condição.
                    /// - false: nenhum elemento atende à condição.
        ///
            /// 'occurrence ->'
                /// É uma expressão lambda.
                    /// Representa cada ocorrência que está sendo analisada.
        ///
            /// 'occurrence.getEvent().getId()'
                /// Acessa o evento relacionado à ocorrência e obtém seu identificador.
        ///
            /// 'data.eventId()'
                /// Obtém o identificador do evento informado no DTO.
        ///
            /// '!'
                /// É o operador lógico de negação.
                    /// Portanto, estamos perguntando:
                    /// Existe alguma ocorrência cujo evento relacionado seja diferente do evento informado na inscrição?

        // 8. Caso a variável 'belongsToAnotherEvent' for verdadeira, lançar uma exceção
            // No caso, significa que encontramos pelo menos uma ocorrência pertencente a outro evento.
        if (belongsToAnotherEvent) {
            throw new ValidationException(
                    "Selected occurrences must belong to the informed event."
            );
        }
    }

}
