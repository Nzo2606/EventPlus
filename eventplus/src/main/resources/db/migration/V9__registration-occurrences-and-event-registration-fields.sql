-- =====================================================================
-- V9: adiciona o que a lógica de inscrição atual precisa.
-- Mapeamento confirmado com as entidades Java:
--   Event, EventOccurrence (@Table "event_occurence"), Registration,
--   RegistrationOccurrence (@Table "registration_occurrence").
-- =====================================================================

-- 1) Campos de controle de inscrição no evento.
--    Datas de inscrição são NULL-áveis para não falhar em linhas antigas;
--    o DTO de criação já exige esses valores para novos eventos.
--    registration_mode / pricing_mode têm DEFAULT para linhas antigas.
ALTER TABLE event
    ADD COLUMN registration_start_date DATETIME NULL,
    ADD COLUMN registration_dead_line  DATETIME NULL,
    ADD COLUMN registration_mode VARCHAR(20) NOT NULL DEFAULT 'FULL_EVENT',
    ADD COLUMN pricing_mode      VARCHAR(20) NOT NULL DEFAULT 'FIXED';

-- 2) Ocorrências (dias/sessões) de cada evento.
--    Corresponde a EventOccurrence (@Table(name = "event_occurence")).
CREATE TABLE event_occurence (
    id              BIGINT   NOT NULL AUTO_INCREMENT,
    event_id        BIGINT   NOT NULL,
    start_date_time DATETIME NOT NULL,
    end_date_time   DATETIME NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_event_occurence_event
        FOREIGN KEY (event_id) REFERENCES event(id)
);

-- 3) Data de confirmação de pagamento na inscrição (Registration.paymentConfirmedAt).
ALTER TABLE registration
    ADD COLUMN payment_confirmed_at DATETIME NULL;

-- 4) "attendance" saiu de registration: a presença agora é por ocorrência
--    (fica em registration_occurrence). A entidade Registration não mapeia mais essa coluna.
ALTER TABLE registration
    DROP COLUMN attendance;

-- 5) Vínculo entre uma inscrição e as ocorrências que ela cobre.
--    Corresponde a RegistrationOccurrence (@Table(name = "registration_occurrence")).
CREATE TABLE registration_occurrence (
    id              BIGINT     NOT NULL AUTO_INCREMENT,
    registration_id BIGINT     NOT NULL,
    occurrence_id   BIGINT     NOT NULL,
    attendance      TINYINT    NOT NULL DEFAULT 0,

    PRIMARY KEY (id),
    CONSTRAINT fk_reg_occurrence_registration
        FOREIGN KEY (registration_id) REFERENCES registration(id),
    CONSTRAINT fk_reg_occurrence_occurrence
        FOREIGN KEY (occurrence_id) REFERENCES event_occurence(id)
);
