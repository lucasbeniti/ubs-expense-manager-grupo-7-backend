-- =========================
-- 1. MOEDA
-- =========================
INSERT INTO currencies (code, name, active)
VALUES ('BRL', 'Real Brasileiro', TRUE)
    ON CONFLICT (code) DO NOTHING;

-- =========================
-- 2. DEPARTAMENTOS
-- =========================
INSERT INTO departments (name, monthly_budget)
VALUES
    ('Engenharia', 50000),
    ('Financeiro', 20000)
    ON CONFLICT DO NOTHING;

-- =========================
-- 3. CATEGORIA
-- =========================
INSERT INTO categories (name, daily_limit, monthly_limit)
VALUES ('Viagem', 500, 5000)
    ON CONFLICT DO NOTHING;

-- =========================
-- 4. MANAGER
-- =========================
INSERT INTO employees (name, cpf, email, role, department_id)
SELECT
    'Ana Gestora',
    '11111111111',
    'ana.gestora@empresa.com',
    'MANAGER',
    d.id
FROM departments d
WHERE d.name = 'Engenharia'
    ON CONFLICT (email) DO NOTHING;

-- =========================
-- 5. FINANCE
-- =========================
INSERT INTO employees (name, cpf, email, role, department_id)
SELECT
    'Bruno Financeiro',
    '22222222222',
    'bruno.financeiro@empresa.com',
    'FINANCE',
    d.id
FROM departments d
WHERE d.name = 'Financeiro'
    ON CONFLICT (email) DO NOTHING;

-- =========================
-- 6. EMPLOYEE
-- =========================
INSERT INTO employees (name, cpf, email, role, department_id, manager_id)
SELECT
    'Carlos Funcionário',
    '33333333333',
    'carlos@empresa.com',
    'EMPLOYEE',
    d.id,
    m.id
FROM departments d
         JOIN employees m ON m.email = 'ana.gestora@empresa.com'
WHERE d.name = 'Engenharia'
    ON CONFLICT (email) DO NOTHING;

-- =========================
-- 7. DESPESA
-- =========================
INSERT INTO expenses (
    description,
    date,
    receipt_url,
    status,
    need_review,
    amount,
    currency_id,
    exchange_rate_snapshot,
    employee_id,
    category_id
)
SELECT
    'Viagem para reunião em São Paulo',
    TIMESTAMP '2024-01-10 10:30',
    'https://recibos.com/viagem.pdf',
    'MANAGER_APPROVED',
    false,
    1200.00,
    c.id,
    1.0,
    e.id,
    cat.id
FROM currencies c
         JOIN employees e ON e.email = 'carlos@empresa.com'
         JOIN categories cat ON cat.name = 'Viagem'
WHERE c.code = 'BRL'
    ON CONFLICT DO NOTHING;

-- =========================
-- 8. ALERTA
-- =========================
INSERT INTO alerts (message, severity, status, type, expense_id)
SELECT
    'Despesa de viagem excede o limite diário da categoria',
    'WARNING',
    'NEW',
    'CATEGORY',
    ex.id
FROM expenses ex
WHERE ex.description = 'Viagem para reunião em São Paulo'
    ON CONFLICT DO NOTHING;

-- =========================
-- 9. LOG
-- =========================
INSERT INTO expense_logs (action, comments, expense_id, employee_id)
SELECT
    'CREATED',
    'Despesa registrada pelo funcionário',
    ex.id,
    e.id
FROM expenses ex
         JOIN employees e ON e.email = 'carlos@empresa.com'
WHERE ex.description = 'Viagem para reunião em São Paulo'
    ON CONFLICT DO NOTHING;
