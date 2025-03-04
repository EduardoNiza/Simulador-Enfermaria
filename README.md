# **Enfermaria (EP1)**

Projeto em Java que simula um ambiente de enfermaria para estudar infecções, medicamentos e eventos médicos, utilizando conceitos de **Design Patterns** (especialmente o padrão **Controller**).

---

## **Estrutura Geral**

O projeto está dividido em quatro exercícios, cada um em um **pacote** específico, com classes e testes próprios:

1. **usp.mac321.ep1.ex1**:  
   - Implementa as classes base `Event` e `Controller` (inspiradas no padrão do *Greenhouse Controls* de Bruce Eckel).  
   - Inclui testes unitários para validar o funcionamento básico dos eventos.  

2. **usp.mac321.ep1.ex2**:  
   - Define as classes de domínio: `Medico`, `Paciente` e `Droga`.  
   - Cada classe possui atributos e métodos relevantes para simular infecções e tratamentos.  
   - Inclui testes unitários para verificar comportamentos individuais (temperatura, concentração de PAC etc.).  

3. **usp.mac321.ep1.ex3**:  
   - Cria a classe `Simulador1`, que estende `Controller` para simular um médico “incompetente” que **não** administra medicamentos.  
   - Usa uma **lista de eventos** para disparar ações que monitoram o paciente até o óbito.  
   - Inclui testes para verificar a saída exata do simulador (eventos e impressões).  

4. **usp.mac321.ep1.ex4**:  
   - Cria a classe `Simulador2`, estendendo as funcionalidades do simulador para um médico que aplica **drogas experimentais** e administra seu paciente de forma dinâmica.  
   - Simula variações de temperatura e concentração de PAC (proteína anticoagulante) ao longo do tempo.  
   - Exibe o resultado final no `main` da classe `Simulador2` (sem de novos testes unitários).

---

## **Funcionalidades Principais**
- **Evento e Controlador**: Garante um sistema reativo onde cada evento é executado apenas quando “pronto”.
- **Simulação**:
  - **Monitoramento** de paciente (temperatura, PAC, óbito).
  - **Surto de infecção** com elevação de temperatura.
  - **Administração de drogas** que reduzem temperatura, mas também podem afetar a saúde do paciente.
  - **Cronograma de eventos** para disparar ações, como consultas periódicas do médico.
- **Testes**:
  - Uso de **JUnit** para validar funções básicas de eventos, pacientes, médicos e lançamentos.

---

## **Como Executar**

1. **Compilação**  
   No diretório raiz do projeto, compile as classes Java:
   ```sh
   javac -cp . src/usp/mac321/ep1/ex1/*.java src/usp/mac321/ep1/ex2/*.java src/usp/mac321/ep1/ex3/*.java src/usp/mac321/ep1/ex4/*.java
   Recomendo que compile no Eclipse IDE
