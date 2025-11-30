# 📚 Gerenciador de Bootcamps e XP (Java POO)

## 🎯 Objetivo do Projeto

Este projeto consiste em um sistema de gerenciamento de programas de ensino (Bootcamps) e controle de progresso e experiência (XP) de Desenvolvedores (Devs), implementado em Java utilizando os pilares da Programação Orientada a Objetos (POO).

O objetivo principal é simular o fluxo de criação de conteúdos (`Cursos` e `Mentorias`), a montagem de `Bootcamps`, a inscrição de `Devs` e o cálculo de sua experiência total de forma polimórfica.

## ✨ Destaques Técnicos

O projeto foi estruturado em camadas para garantir a **Separação de Preocupações (SoC)**, resultando em um código limpo, testável e manutenível:

1.  **Domínio (Entidades):** Contém a lógica de negócio principal e a hierarquia de objetos (ex: `Dev`, `Bootcamp`, `Content`).
2.  **Serviços (Business Logic):** Orquestra as operações entre as entidades e implementa regras de negócio complexas.
3.  **Interface (UI):** Responsável pela interação com o usuário (entrada e saída de dados via console).

### 📐 Arquitetura do Domínio e POO

| Entidade/Conceito | Descrição | Pilar da POO |
| :--- | :--- | :--- |
| **`Content` (Abstrata)** | Classe base para todo material de ensino, define o método `calculationXP()`. | Abstração |
| **`Course`** | Estende `Content`. Implementa `calculationXP()` baseado na **duração** do curso. | Polimorfismo |
| **`Mentoring`** | Estende `Content`. Implementa `calculationXP()` com um valor **fixo + bônus**. | Polimorfismo |
| **`Dev`** | Gerencia os conteúdos inscritos e concluídos (`subscribedContent`, `finishedContent`). Possui os métodos `signUpBootcamp()` e `progress()`. | Encapsulamento |
| **`Bootcamp`** | Agrupa um conjunto de `Content` e `Devs`. | Associação |

## 🛠️ Funcionalidades Implementadas

O sistema é operado através de um menu de console que oferece 7 opções:

| Opção | Descrição | Método Principal (Serviço) |
| :--- | :--- | :--- |
| `[ 1 ]` | **Adicionar Conteúdo:** Cria um novo `Curso` ou `Mentoria`. | `ServiceProgram.addContent()` |
| `[ 2 ]` | **Adicionar Desenvolvedor:** Cadastra um novo `Dev` no sistema. | `ServiceProgram.addDev()` |
| `[ 3 ]` | **Adicionar Bootcamp:** Cria um novo `Bootcamp`. | `ServiceProgram.addBootcamp()` |
| `[ 4 ]` | **Ligar Conteúdo a Bootcamp:** Associa um `Content` existente a um `Bootcamp` existente. | `ServiceProgram.linkContentToBootcamp()` |
| `[ 5 ]` | **Inscrever Dev em Bootcamp:** Matricula um `Dev` em um `Bootcamp`. O Dev herda **todos os conteúdos** do Bootcamp em sua lista de inscritos. | `ServiceProgram.registerDevInBootcamp()` |
| `[ 6 ]` | **Progredir Dev:** Move o **primeiro** conteúdo da lista de inscritos para a lista de concluídos do `Dev`. | `ServiceProgram.devProgress()` |
| `[ 7 ]` | **Calcular XP do Dev:** Soma os XPs de todos os conteúdos concluídos, utilizando o cálculo polimórfico de cada `Content`. | `ServiceProgram.calculateDevTotalXp()` |

## ⚙️ Como Executar o Projeto

### Pré-requisitos

* **Java Development Kit (JDK):** Versão 17 ou superior.
* **IDE (Recomendado):** IntelliJ IDEA ou VS Code com extensão Java.

### Passos

1.  **Clone o Repositório:** (Assumindo que o código será versionado)
    ```bash
    git clone https://docs.github.com/pt/migrations/importing-source-code/using-the-command-line-to-import-source-code/adding-locally-hosted-code-to-github
    cd nome-do-projeto
    ```
2.  **Abrir na IDE:** Importe a estrutura do projeto para sua IDE.
3.  **Executar:** Encontre o arquivo principal e execute a aplicação:
    * Arquivo: `Main.java`
    * Método: `main(String[] args)`

A aplicação será iniciada no console, exibindo o menu de interação.

-----

## 📂 Estrutura de Arquivos

```
src/
└── org/example/
    ├── domain/
    │   ├── entities/          # Classes de Domínio (Dev, Bootcamp, Content, Course, Mentoring)
    │   ├── exceptions/        # Exceções Customizadas (ElementNotFoundException, etc.)
    │   └── ...
    ├── services/
    │   ├── ServiceProgram.java  # Camada de Orquestração de Regras de Negócio
    │   ├── BootcampService.java # Serviço de Persistência/Busca de Bootcamps
    │   ├── ContentService.java  # Serviço de Persistência/Busca de Conteúdos
    │   └── DevService.java      # Serviço de Persistência/Busca de Devs
    ├── ui/
    │   └── ProgramUI.java       # Lógica de Interface do Usuário (Menu, Inputs/Outputs)
    ├── utility/
    │   └── Utilities.java       # Métodos auxiliares (Validação de Input, Formatação)
    └── Main.java                # Ponto de entrada da aplicação
```

## ⚠️ Tratamento de Erros

O sistema utiliza exceções customizadas (`ElementAlreadyExistsException`, `ElementNotFoundException`) para lidar com situações inesperadas, garantindo que o programa não pare de forma abrupta e ofereça feedback claro ao usuário. Por exemplo, ao tentar inscrever um Dev que não existe, o `DevService.findDev()` lançará uma exceção tratada na `ProgramUI`.

----

## 🧑‍💻 Autor

Este projeto foi desenvolvido por **Bruno Teixeira** como parte de um mini-projeto de consolidação de habilidades em Java:

**Bruno Teixeira**

**Técnico em Desenvolvimento de Sistemas**

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/brunotxrs)
