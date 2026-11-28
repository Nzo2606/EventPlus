-- =====================================================================
-- V10: corrige o tamanho da coluna ssn.
-- A V2 criou ssn como VARCHAR(6), mas o DTO de participante aceita 11 dígitos
-- (@Pattern "\\d{11}"), então qualquer cadastro real falharia com "Data too long".
-- Criada como migration separada porque a V9 já havia sido aplicada no banco.
-- =====================================================================
ALTER TABLE participant
    MODIFY ssn VARCHAR(11) NOT NULL;
