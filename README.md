# 🏢 Dynamic Java Web Application - Online Quotation System

Este repositório contém o desenvolvimento de uma aplicação web dinâmica nativa em **Java**, estruturada para o gerenciamento e processamento de **Cotações**. O projeto adota a arquitetura clássica de sistemas corporativos da plataforma Java Web, demonstrando a separação entre a inteligência de negócios do lado do servidor (Backend) e as interfaces dinâmicas de renderização de dados do lado do cliente (Frontend).

---

## 🏗️ Estrutura e Arquitetura do Projeto

O workspace do projeto segue o padrão estrutural para empacotamento e deploy de aplicações do tipo Web Archive (WAR) em servidores como Apache Tomcat:

* **`src/`:** Diretório que centraliza o código-fonte Java (`.java`). Armazena os controladores web (Servlets), classes de domínio (Models), componentes de validação de dados e lógicas de negócio estruturadas para o cálculo e processamento de propostas e valores.
* **`WebContent/`:** O núcleo da camada de apresentação e configurações web. Contém páginas dinâmicas (JSPs), arquivos estáticos (HTML, CSS, JavaScript) e o diretório de segurança `WEB-INF/` (onde ficam o arquivo de implantação `web.xml` e as bibliotecas JAR necessárias para a execução da aplicação).
* **`build/`:** Diretório de ciclo de vida gerenciado pelo ambiente de compilação. Armazena as classes compiladas (`.class`) e os artefatos temporários gerados antes do empacotamento final do sistema.

---

## 🛠️ Stack Tecnológica & Padrões

* **Linguagem Core:** Java (Java EE / Jakarta EE Web Profile).
* **Camada Web Server:** Servlets & JavaServer Pages (JSP).
* **Padrões de Projeto Aplicados:** Model-View-Controller (MVC) para ambiente web, Inversão de Controle e Tratamento Transacional de Dados.
* **Formato de Deploy:** Estrutura preparada para geração de pacotes `.war`.

---

## 🎯 Alinhamento de Negócio e Casos de Uso (Pricing & Suprimentos)

Mecanismos automatizados de cotação são peças críticas na cadeia de suprimentos (*Supply Chain*) e no planejamento financeiro de indústrias e corporações de grande porte. Este projeto demonstra capacidade técnica para desenvolver soluções voltadas para:

1. **Motores de Precificação (*Pricing Engines*):** Processamento automatizado de variáveis de mercado, margens de lucro e custos para geração de propostas comerciais em tempo real.
2. **Sistemas de Compras e Suprimentos (*Procurement*):** Plataformas para captação, comparação e auditoria de propostas de múltiplos fornecedores, garantindo a consistência das transações e integridade dos dados financeiros.
3. **Análise de Viabilidade Financeira:** Estruturação de dados de entrada para apoiar cálculos de retorno sobre investimento (ROI) e margens operacionais (EBITDA) antes do fechamento de contratos de fornecimento.

---
## 🚀 Como Compilar e Implantar a Aplicação

Para executar esta aplicação web localmente, você precisará de uma IDE com suporte Java EE (como Eclipse ou IntelliJ) e um servlet container instalado:

1. Importe o repositório como um *Dynamic Web Project* na sua IDE.
2. Certifique-se de que as dependências do servidor (como as APIs de Servlet/JSP) estão mapeadas no build path.
3. Configure e associe um servidor local (ex: Apache Tomcat).
4. Execute o projeto direcionando-o para o servidor (`Run on Server`). A interface web estará acessível na porta local configurada (ex: `http://localhost:8080/Cotacao`).

---
📫 **Contato & Conexões:** [LinkedIn](https://linkedin.com/in/fabiojdluz/) | [Medium](https://medium.com/@fabio.jdluz)
