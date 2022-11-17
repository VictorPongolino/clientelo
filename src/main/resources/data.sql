INSERT INTO Categoria (id, nome, status) VALUES
    (1, 'INFORMÁTICA', 'ATIVA'),
    (2, 'MÓVEIS','ATIVA'),
    (3, 'LIVROS','ATIVA'),
    (4, 'CELULARES','ATIVA'),
    (5, 'AUTOMOTIVA', 'ATIVA');

INSERT INTO Produto (id, nome, descricao, preco, quantidade_estoque, categoria_id) VALUES
    (1, 'Notebook Samsung', 'Notebook Samsung', 3523.00, 1, 1),
    (2, 'Sofá 3 lugares','Sofá 3 lugares', 2500.00, 1, 2),
    (3, 'Clean Architecture','Clean Architecture', 102.9, 2, 3),
    (4, 'Mesa de jantar 6 lugares','Mesa de jantar 6 lugares', 3678.98, 1, 2),
    (5, 'iPhone 13 Pro','iPhone 13 Pro', 9176.00, 6, 4),
    (6, 'Monitor Dell 27','Monitor Dell 27', 1889.00, 3, 1),
    (7, 'Implementing Domain-Driven Design','Implementing Domain-Driven Design', 144.07, 3, 3),
    (8, 'Jogo de pneus','Jogo de pneus', 1276.79, 1, 5),
    (9, 'Clean Code','Clean Code', 95.17, 1, 3),
    (10, 'Galaxy S22 Ultra','Galaxy S22 Ultra', 8549.1, 5, 4),
    (11, 'Macbook Pro 16','Macbook Pro 16', 31752.00, 1, 1),
    (12, 'Refactoring Improving the Design of Existing Code','Refactoring Improving the Design of Existing Code', 173.9, 1, 3),
    (13, 'Cama queen size','Cama queen size', 3100.00, 2, 2),
    (14, 'Central multimidia','Central multimidia', 711.18, 1, 5),
    (15, 'Building Microservices','Building Microservices', 300.28, 2, 3),
    (16, 'Galaxy Tab S8','Galaxy Tab S8', 5939.1, 4, 1);

INSERT INTO Cliente (id, nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) VALUES
    (1, "ANA", "11111111111",  "111111111", "Rua 01", "A", "Portão de Madeira", "Bairro 01", "Itaguaí", "RJ"),
    (2, "ELI", "22222222222",  "222222222", "Rua 02", "B", "casa amarela", "Bairro 02", "Parnamirim", "RN"),
    (3, "DANI", "33333333333", "333333333", "Rua 03", "C", "portão verde", "Bairro 03", "Joinville", "SC"),
    (4, "CAIO", "444444444444", "444444444", "Rua 04", "D", "apartamento", "Bairro 04", "CAMPINAS", "SP"),
    (5, "GABI", "55555555555", "555555555", "Rua 05", "E", "Não tem", "Bairro 05", "Abadia dos Dourados", "MG"),
    (6, "BIA", "66666666666", "666666666", "Rua 06", "D", "Não tem", "Bairro 06", "Abadia dos Dourados", "MG");

INSERT INTO Pedido (id, categoria_id, cliente_id, data, desconto_total, tipo_desconto) VALUES
    (1, 1, 1, "2022/01/01", 0.0, "NENHUM"),
    (2, 2, 1, "2022/01/05", 0.0, "NENHUM"),
    (3, 3, 1, "2022/01/08", 0.0, "NENHUM"),
    (4, 2, 2, "2022/01/06", 0.0, "NENHUM"),
    (5, 4, 1, "2022/01/13", 0.0, "NENHUM"),
    (6, 1, 3, "2022/01/04", 0.0, "NENHUM"),
    (7, 3, 5, "2022/01/10", 0.0, "NENHUM"),
    (8, 5, 6, "2022/01/15", 0.0, "NENHUM"),
    (9, 3, 6, "2022/01/09", 0.0, "NENHUM"),
    (10, 4, 3, "2022/01/14", 0.0, "NENHUM"),
    (11, 1, 4, "2022/01/03", 0.0, "NENHUM"),
    (12, 3, 3, "2022/01/12", 0.0, "NENHUM"),
    (13, 2, 3, "2022/01/07", 0.0, "NENHUM"),
    (14, 5, 4, "2022/01/16", 0.0, "NENHUM"),
    (15, 3, 4, "2022/01/11", 0.0, "NENHUM"),
    (16, 1, 6, "2022/01/02", 0.0, "NENHUM");

INSERT INTO item_pedido (id, preco_unitario, quantidade, pedido_id, produto_id, desconto, tipo_desconto) VALUES
    (1, 3523.00, 1, 1, 1, 0.0, "NENHUM"),
    (2, 2500.00, 1, 2, 2, 0.0, "NENHUM"),
    (3, 102.9, 2, 3, 3, 0.0, "NENHUM"),
    (4, 3678.98, 1, 4, 4, 0.0, "NENHUM"),
    (5, 9176.00, 6, 5, 5, 0.0, "NENHUM"),
    (6, 1889.00, 3, 6, 6, 0.0, "NENHUM"),
    (7, 144.07, 3, 7, 7, 0.0, "NENHUM"),
    (8, 1276.79, 1, 8, 8, 0.0, "NENHUM"),
    (9, 95.17, 1, 9, 9, 0.0, "NENHUM"),
    (10, 8549.1, 5, 10, 10, 0.0, "NENHUM"),
    (11, 31752, 1, 11, 11, 0.0, "NENHUM"),
    (12, 173.9, 1, 12, 12, 0.0, "NENHUM"),
    (13, 3100.00, 2, 13, 13, 0.0, "NENHUM"),
    (14, 711.18, 1, 14, 14, 0.0, "NENHUM"),
    (15, 300.28, 2, 15, 15, 0.0, "NENHUM"),
    (16, 5939.1, 4, 16, 16, 0.0, "NENHUM");