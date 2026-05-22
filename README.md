# Grafos — Projeto de Algoritmos de Grafos

Repositório com implementações e exemplos de algoritmos sobre grafos, criado para estudos e testes.

## Descrição
Implementações em Java de estruturas e algoritmos básicos para grafos, incluindo busca em largura (BFS) e busca em profundidade (DFS), além de exemplos de uso.

## Estrutura do projeto

- `src/` — código-fonte Java
	- `Main.java` — ponto de entrada (exemplos/main)
	- `TestRun.java` — classe auxiliar para testes rápidos
	- `algorithms/` — implementações dos algoritmos (`BreadthFirstSearch.java`, `DepthFirstSearch.java`)
	- `examples/` — exemplos e casos de teste (`GraphExamples.java`)
	- `graphstruture/` — estrutura do grafo (`Graph.java`, `Node.java`)
	- `util/` — utilitários (por exemplo, `ConsoleColors.java`)

## Requisitos

- JDK instalado (Java 8+ recomendado)
- `javac` e `java` no `PATH`

## Compilação e execução

1. Compilar todo o código (exemplo simples):

```bash
javac -d out src\Main.java src\TestRun.java src\graphstruture\Graph.java src\graphstruture\Node.java src\algorithms\BreadthFirstSearch.java src\algorithms\DepthFirstSearch.java src\examples\GraphExamples.java
```

2. Executar a classe principal (`Main`) ou `TestRun`:

```bash
java -cp out Main
```

ou (executar testes/exemplos):

```bash
java -cp out TestRun
```

Observação: se você preferir, abra o projeto no VS Code ou em uma IDE (IntelliJ/Eclipse) e execute as classes diretamente.

## Como usar

- Edite ou crie novos exemplos em `src/examples/` para testar grafos e algoritmos.
- Importe/instancie `graphstruture.Graph` e utilize as classes de `algorithms`.

## Contribuições

- Sinta-se à vontade para abrir issues ou pull requests com melhorias, novos algoritmos ou correções de bugs.

## Licença

- Este projeto está disponível para uso educacional. Adicione uma licença se desejar compartilhar publicamente.
