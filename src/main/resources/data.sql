-- ==============================
-- CLIENTES
-- ==============================
INSERT INTO clientes (id, nome, cpf, telefone, email,
                      cep, logradouro, numero, complemento, bairro, cidade, estado)
VALUES
(1, 'Maria Souza', '12345678901', '21999990001', 'maria.souza@email.com',
 '23045-200', 'Rua das Palmeiras', '120', 'Casa 2', 'Cosmos', 'Rio de Janeiro', 'RJ'),
(2, 'João Oliveira', '98765432100', '21988887777', 'joao.oliveira@email.com',
 '23045-250', 'Av. Central', '45', NULL, 'Campo Grande', 'Rio de Janeiro', 'RJ'),
(3, 'Carlos Pereira', '45678912300', '21998885555', 'carlos.pereira@email.com',
 '23045-300', 'Rua das Acácias', '210', NULL, 'Recreio', 'Rio de Janeiro', 'RJ'),
(4, 'Fernanda Lima', '78912345699', '21997776666', 'fernanda.lima@email.com',
 '23045-350', 'Av. Atlântica', '880', 'Apto 302', 'Copacabana', 'Rio de Janeiro', 'RJ'),
(5, 'Paulo Henrique', '32165498700', '21996665555', 'paulo.henrique@email.com',
 '23045-400', 'Rua das Laranjeiras', '55', NULL, 'Botafogo', 'Rio de Janeiro', 'RJ');

-- ==============================
-- EQUIPAMENTOS
-- ==============================
INSERT INTO equipamentos (id, nome, valor_diaria, disponivel)
VALUES
(1, 'Furadeira Elétrica Bosch', 80.00, true),
(2, 'Gerador de Energia 220V', 200.00, true),
(3, 'Serra Circular Dewalt', 100.00, true),
(4, 'Transformador 5000W', 150.00, true),
(5, 'Betoneira 400L Schulz', 1150.00, true),
(6, 'Andaime Modular 3m', 90.00, true),
(7, 'Compactador de Solo', 180.00, true);

-- ==============================
-- LOCAÇÕES
-- ==============================
-- Locação geral (cliente + datas + total)
INSERT INTO locacoes (id, cliente_id, data_inicio, data_fim, dias, valor_total, devolvido)
VALUES
-- Locação 1: Maria
(1, 1, '2025-10-01', '2025-10-03', 2, 360.00, false),
-- Locação 2: João
(2, 2, '2025-10-05', '2025-10-10', 5, 1500.00, true),
-- Locação 3: Carlos (mensal)
(3, 3, '2025-09-15', '2025-10-15', 30, 3600.00, false),
-- Locação 4: Fernanda (mensal)
(4, 4, '2025-10-01', '2025-10-31', 30, 2700.00, false),
-- Locação 5: Paulo (mensal)
(5, 5, '2025-09-25', '2025-10-25', 30, 7500.00, true);

-- ==============================
-- LOCACAO_EQUIPAMENTO (itens por locação)
-- ==============================
-- Locação 1: Maria aluga 2 equipamentos
INSERT INTO locacao_equipamento (locacao_id, equipamento_id, quantidade, valor_unitario, valor_total)
VALUES
(1, 1, 1, 80.00, 160.00),  -- Furadeira
(1, 3, 1, 100.00, 200.00); -- Serra Circular

-- Locação 2: João aluga 3 equipamentos
INSERT INTO locacao_equipamento (locacao_id, equipamento_id, quantidade, valor_unitario, valor_total)
VALUES
(2, 2, 1, 200.00, 200.00),  -- Gerador
(2, 4, 1, 150.00, 150.00),  -- Transformador
(2, 5, 1, 1150.00, 1150.00); -- Betoneira

-- Locação 3: Carlos (mensal) aluga 1 equipamento
INSERT INTO locacao_equipamento (locacao_id, equipamento_id, quantidade, valor_unitario, valor_total)
VALUES
(3, 3, 1, 120.00, 3600.00); -- Serra Circular 30 dias

-- Locação 4: Fernanda (mensal) aluga 1 equipamento
INSERT INTO locacao_equipamento (locacao_id, equipamento_id, quantidade, valor_unitario, valor_total)
VALUES
(4, 6, 1, 90.00, 2700.00); -- Andaime 30 dias

-- Locação 5: Paulo (mensal) aluga 1 equipamento
INSERT INTO locacao_equipamento (locacao_id, equipamento_id, quantidade, valor_unitario, valor_total)
VALUES
(5, 5, 1, 250.00, 7500.00); -- Betoneira 30 dias

-- ==============================
-- PAGAMENTOS
-- ==============================
-- Locação 1: Maria
INSERT INTO pagamentos (locacao_id, tipo, valor, referencia, data_pagamento)
VALUES
(1, 'PIX', 360.00, 'pix-maria-2025', '2025-10-02T10:15:00');

-- Locação 2: João
INSERT INTO pagamentos (locacao_id, tipo, valor, referencia, data_pagamento)
VALUES
(2, 'CARTAO', 500.00, 'cartao-joao-1234', '2025-10-06T09:30:00'),
(2, 'BOLETO', 1000.00, 'boleto-joao-2025', '2025-10-08T17:45:00');

-- Locação 3: Carlos
INSERT INTO pagamentos (locacao_id, tipo, valor, referencia, data_pagamento)
VALUES
(3, 'PIX', 1800.00, 'pix-carlos-1-2025', '2025-09-20T08:30:00'),
(3, 'PIX', 1800.00, 'pix-carlos-2-2025', '2025-10-10T08:45:00');

-- Locação 4: Fernanda
INSERT INTO pagamentos (locacao_id, tipo, valor, referencia, data_pagamento)
VALUES
(4, 'BOLETO', 2700.00, 'boleto-fernanda-2025', '2025-10-02T11:00:00');

-- Locação 5: Paulo
INSERT INTO pagamentos (locacao_id, tipo, valor, referencia, data_pagamento)
VALUES
(5, 'CARTAO', 3750.00, 'cartao-paulo-1-2025', '2025-09-26T15:00:00'),
(5, 'CARTAO', 3750.00, 'cartao-paulo-2-2025', '2025-10-10T15:10:00');
