-- Índices para buscas rápidas via API (pelos UUIDs)
CREATE INDEX idx_departments_uuid ON departments(department_uuid);
CREATE INDEX idx_categories_uuid ON categories(category_uuid);
CREATE INDEX idx_employees_uuid ON employees(employee_uuid);
CREATE INDEX idx_expenses_uuid ON expenses(expense_uuid);
CREATE INDEX idx_currencies_uuid ON currencies(currency_uuid);

-- Índices para Relacionamentos e Filtros comuns
CREATE INDEX idx_expenses_employee ON expenses(expense_id);
CREATE INDEX idx_expenses_status ON expenses(status);
CREATE INDEX idx_employees_email ON employees(email);

-- Índice composto para buscar taxa histórica rapidamente
CREATE INDEX idx_rates_currency_date ON currency_rates(fk_currency_id, valid_date);