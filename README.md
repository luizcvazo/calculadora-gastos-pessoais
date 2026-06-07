# 💰 Calculadora de Gastos Pessoais

Aplicação de linha de comando desenvolvida em Java para gerenciamento de gastos mensais pessoais.

## 📋 Funcionalidades

- Navegação por todos os 12 meses do ano
- Adição de gastos com nome, valor e quantidade de parcelas
- Distribuição automática de gastos parcelados nos meses subsequentes
- Listagem de gastos do mês com exibição diferenciada para gastos à vista e parcelados
- Zeragem de todos os gastos de um mês

## 🚀 Como executar

**Pré-requisitos:** Java 11 ou superior instalado.

```bash
# Clone o repositório
git clone https://github.com/luizcvazo/calculadora-gastos-pessoais.git

# Acesse a pasta do projeto
cd calculadora-gastos-pessoais

# Compile os arquivos
javac src/*.java -d out

# Execute o programa
java -cp out Main
```

## 🗂️ Estrutura do projeto

```
src/
 ├── Gasto.java   → Representa um gasto (nome, valor, parcelas)
 ├── Mes.java     → Representa um mês com sua lista de gastos
 └── Main.java    → Lógica principal e menus de navegação
```

## 🖥️ Como usar

Ao iniciar o programa, selecione o mês desejado:

```
=== Calculadora de Gastos Mensais ===
Selecione o mês:
1 - Janeiro
2 - Fevereiro
...
9 - Próximo
0 - Sair
```

Dentro de cada mês, você pode:

```
=== Janeiro - Gastos ===
1 - Adicionar gasto
2 - Ver gastos
3 - Zerar gastos
4 - Voltar
0 - Sair
```

Ao adicionar um gasto, informe:
- **Nome** do gasto (ex: Água)
- **Valor** da parcela (ex: 49.90)
- **Quantidade de parcelas** (0 = à vista)

Os gastos parcelados são distribuídos automaticamente nos meses seguintes.

## 📌 Exemplo de listagem

```
=== Janeiro - Gastos ===
Água - R$49.9
Internet 1/12 - R$119.9
Curso 1/3 - R$150.0
```

## 🛠️ Tecnologias

- Java
- IntelliJ IDEA

## 📚 Sobre o projeto

Projeto desenvolvido como exercício de aprendizado em Java, cobrindo conceitos como classes, ArrayList, loops, switch/case, métodos estáticos e lógica de programação.
