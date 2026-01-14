-- =========================
-- 1. MOEDA E DEPARTAMENTOS
-- =========================
INSERT INTO currencies (code, name, active)
VALUES ('BRL', 'Real Brasileiro', TRUE) ON CONFLICT (code) DO NOTHING;

INSERT INTO departments (name, monthly_budget)
VALUES
    ('Tecnologia', 50000),
    ('Financeiro', 20000),
    ('Comercial', 20000) ON CONFLICT DO NOTHING;

-- =========================
-- 2. CATEGORIA
-- =========================
INSERT INTO categories (name, daily_limit, monthly_limit)
VALUES ('Viagem', 500, 5000) ON CONFLICT DO NOTHING;

-- =========================
-- 3. USUÁRIOS (Tabela Principal de Login)
-- =========================
-- Inserindo primeiro na tabela de users para gerar os IDs
-- Role 0 = MANAGER, 1 = FINANCE, 2 = EMPLOYEE
INSERT INTO users (login, password, role)
VALUES
    ('manager@ubs.com', '$2a$10$Mip9p..W8t6OV0l7.OS65eBJgxdn6cDDL4Lm95TW9KvjS7Jt.KcOK', 0),
    ('finance@ubs.com', '$2a$10$Mip9p..W8t6OV0l7.OS65eBJgxdn6cDDL4Lm95TW9KvjS7Jt.KcOK', 1),
    ('employee@ubs.com', '$2a$10$Mip9p..W8t6OV0l7.OS65eBJgxdn6cDDL4Lm95TW9KvjS7Jt.KcOK', 2)
    ON CONFLICT (login) DO NOTHING;

-- =========================
-- 4. MANAGER (Funcionário)
-- =========================
INSERT INTO employees (name, cpf, email, role, department_id, user_id)
SELECT
    'Manager Principal',
    '11111111111',
    u.login,
    'MANAGER',
    d.id,
    u.id
FROM users u
         JOIN departments d ON d.name = 'Tecnologia'
WHERE u.login = 'manager@ubs.com'
    ON CONFLICT (email) DO NOTHING;

-- =========================
-- 5. FINANCE (Funcionário)
-- =========================
INSERT INTO employees (name, cpf, email, role, department_id, user_id)
SELECT
    'Financeiro Central',
    '22222222222',
    u.login,
    'FINANCE',
    d.id,
    u.id
FROM users u
         JOIN departments d ON d.name = 'Financeiro'
WHERE u.login = 'finance@ubs.com'
    ON CONFLICT (email) DO NOTHING;

-- =========================
-- 6. EMPLOYEE (Funcionário vinculado ao Manager)
-- =========================
INSERT INTO employees (name, cpf, email, role, department_id, user_id, manager_id)
SELECT
    'Funcionário Padrão',
    '33333333333',
    u.login,
    'EMPLOYEE',
    d.id,
    u.id,
    m.id
FROM users u
         JOIN departments d ON d.name = 'Tecnologia'
         JOIN employees m ON m.email = 'manager@ubs.com'
WHERE u.login = 'employee@ubs.com'
    ON CONFLICT (email) DO NOTHING;

-- =========================
-- 7. DESPESA, ALERTA E LOG (Mantidos)
-- =========================
INSERT INTO expenses (description, date, receipt_url, status, need_review, amount, currency_id, exchange_rate_snapshot, employee_id, category_id)
SELECT 'Viagem para reunião em São Paulo', '2026-01-10 10:30', 'https://recibos.com/viagem.pdf', 'MANAGER_APPROVED', false, 1200.00, c.id, 1.0, e.id, cat.id
FROM currencies c, employees e, categories cat
WHERE c.code = 'BRL' AND e.email = 'employee@ubs.com' AND cat.name = 'Viagem'
    ON CONFLICT DO NOTHING;

INSERT INTO alerts (message, severity, status, type, expense_id)
SELECT 'Despesa de viagem excede o limite diário da categoria', 'WARNING', 'NEW', 'CATEGORY', ex.id
FROM expenses ex WHERE ex.description = 'Viagem para reunião em São Paulo'
    ON CONFLICT DO NOTHING;

INSERT INTO expense_logs (action, comments, expense_id, employee_id)
SELECT 'CREATED', 'Despesa registrada pelo funcionário', ex.id, e.id
FROM expenses ex, employees e WHERE ex.description = 'Viagem para reunião em São Paulo' AND e.email = 'employee@ubs.com'
    ON CONFLICT DO NOTHING;