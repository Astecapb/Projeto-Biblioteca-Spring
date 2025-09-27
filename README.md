DESENVOLVIMENTO WEB FULL STACK. DISCIPLINA: Programação Back-End II

-Grupo formado por: - Artur Freitas Palmeira RGM-42900590 - Geraldo Camilo Valencia RGM-44194153 - Robson Lima Palmeira RGM-44214341

1.Desenvolver uma API REST seguindo o diagrama de classes fornecido e atendendo aos requisitos funcionais descritos abaixo.
Requisitos Funcionais
•Implementar operações de CRUD (Cadastrar, Remover, Alterar e Listar) para:
- Livros
- Exemplares de livros
- Autores
•Implementar busca de livros por título utilizando query string.
•Criar funcionalidade para consultar a quantidade de exemplares de um livro, incluindo filtros para:
. Quantidade disponível
. Quantidade emprestada
. Quantidade reservada

Dicas de Implementação
•Utilize relacionamentos unidirecionais entre as entidades:
- Exemplo: @ManyToMany de Livro para Autor
- @OneToMany de Livro para ExemplarLivro
Observações
•Data de entrega: 26/09, às 23:59
. O exercício pode ser realizado em equipes de até três pessoas.
. A entrega deve ser feita através do e-mail southiagorm@gmail.com, seguindo as instruções abaixo:
. Assunto: Projeto Pós Graduação BackEnd 2 – 2025
. Corpo do e-mail: informar o nome e matrícula de cada membro da equipe, além do link do GitHub do projeto.

  
  
<img width="622" height="517" alt="image" src="https://github.com/user-attachments/assets/6476180d-436d-4cdf-a871-2613989fb984" />
