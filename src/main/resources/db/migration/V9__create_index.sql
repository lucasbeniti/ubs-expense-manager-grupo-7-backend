-- Índices para Relacionamentos e Filtros comuns
CREATE INDEX idx_expenses_employee ON expenses(id);
CREATE INDEX idx_expenses_status ON expenses(status);
CREATE INDEX idx_employees_email ON employees(email);

-- Índice composto para buscar taxa histórica rapidamente
CREATE INDEX idx_rates_currency_date ON currency_rates(currency_id, valid_date);