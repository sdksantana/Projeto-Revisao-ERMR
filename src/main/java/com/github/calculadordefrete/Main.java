package com.github.calculadordefrete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Produtos> produtosSelecionados = SelecionarProdutos(); // Seleção de produtos

        System.out.println("Selecione a cidade de origem:");
        int cidadeOrigem = RetornaCidade();

        System.out.println("Selecione a cidade de destino:");
        int cidadeDestino = RetornaCidade();

        System.out.println("Cidades selecionadas: Origem - " + getCidadeNome(cidadeOrigem) + ", Destino - " + getCidadeNome(cidadeDestino));

        String caminho = "distancia.csv";
        int distancia = LerArquivo(caminho, cidadeOrigem, cidadeDestino);

        ArrayList<Caminhoes> tiposCaminhoes = new ArrayList<>();
        tiposCaminhoes.add(new Caminhoes("pequeno", 5.83, 1)); // R$ 5,83 por km, 1 tonelada.
        tiposCaminhoes.add(new Caminhoes("medio", 13.42, 4));
        tiposCaminhoes.add(new Caminhoes("grande", 20.21, 10));

        double pesoTotal = calcularPesoTotal(produtosSelecionados);

        System.out.println("Custo do transporte:");
        double custo = CalcularCustoTransporte(pesoTotal, distancia, tiposCaminhoes);

        System.out.println("O melhor modelo de caminhão para a entrega é: " + SelecionarCaminhao(pesoTotal, tiposCaminhoes));
        System.out.println("Custo do transporte: R$" + custo);

        // Metodos para dados estatisticos (Não foi utilizado o stream, porque ao adicionar o array de caminhões, quebrou o código)
        double custoPorTrecho = custo / 2;
        double custoMedioPorKm = custo / distancia;
        double custoTotalPorTrecho = custoPorTrecho * 2;
        int numeroDeItensTransportados = produtosSelecionados.size();
        int numeroDeVeiculos = tiposCaminhoes.size();


        // Exibir o relatório
        System.out.println("Relatório de Transporte:");
        System.out.println("Custo Total: R$" + custo);
        System.out.println("Custo por Trecho: R$" + custoPorTrecho);
        System.out.println("Custo Médio por Km: R$" + custoMedioPorKm);
        System.out.println("Custo Total por Trecho: R$" + custoTotalPorTrecho);
        System.out.println("Número Total de Itens Transportados: " + numeroDeItensTransportados);
        System.out.println("Número Total de veículos deslocados:" + numeroDeVeiculos);
    }


    static ArrayList<Produtos> SelecionarProdutos() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Produtos> produtosLoja = new ArrayList<>();
        produtosLoja.add(new Produtos("Celular", 0.7));
        produtosLoja.add(new Produtos("Geladeira", 50));
        produtosLoja.add(new Produtos("Air Fryer", 3.5));
        produtosLoja.add(new Produtos("Cadeira", 5.0));
        produtosLoja.add(new Produtos("Luminária", 0.8));
        produtosLoja.add(new Produtos("Lavadora de Roupa", 15));
        produtosLoja.add(new Produtos("Playstation 5", 3.9));
        produtosLoja.add(new Produtos("Nintendo Switch", 0.3));

        ArrayList<Produtos> produtosSelecionados = new ArrayList<>();
        System.out.println("Escolha a quantidade de produtos que deseja transportar:");
        int quantidade = scanner.nextInt();

        Thread exitThread = new Thread(() -> {
            while (true) {
                try {
                    if (System.in.available() > 0) {
                        char inputChar = (char) System.in.read();
                        if (inputChar == 'q' || inputChar == 'Q') {
                            System.out.println("Programa encerrado pelo usuário.");
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        exitThread.setDaemon(true);
        exitThread.start();

        for (int i = 0; i < quantidade; i++) {
            System.out.println("(Se deseja encerrar o programa, digite [24] a qualquer momento.)");
            System.out.println("Selecione o produto:");
            for (int j = 0; j < produtosLoja.size(); j++) {
                Produtos produto = produtosLoja.get(j);
                System.out.println("[" + (j + 1) + "] " + produto.getNome() + " (" + produto.getPeso() + "kg)");

            }

            int escolhaProduto = scanner.nextInt();


            switch (escolhaProduto) {
                case 0:
                    // Sair do loop
                    break;
                case 1:
                    System.out.println("Digite a quantidade de Celulares:");
                    int quantidadeCelulares = scanner.nextInt();
                    Produtos celular = produtosLoja.get(0);
                    celular.setPeso(celular.getPeso() * quantidadeCelulares);
                    produtosSelecionados.add(celular);
                    break;
                case 2:
                    System.out.println("Digite a quantidade de Geladeiras:");
                    int quantidadeGeladeiras = scanner.nextInt();
                    Produtos geladeira = produtosLoja.get(1);
                    geladeira.setPeso(geladeira.getPeso() * quantidadeGeladeiras);
                    produtosSelecionados.add(geladeira);
                    break;
                case 3:
                    System.out.println("Digite a quantidade de Air Fryers:");
                    int quantidadeAirFryers = scanner.nextInt();
                    Produtos airFryer = produtosLoja.get(2);
                    airFryer.setPeso(airFryer.getPeso() * quantidadeAirFryers);
                    produtosSelecionados.add(airFryer);
                    break;
                case 4:
                    System.out.println("Digite a quantidade de Cadeiras:");
                    int quantidadeCadeiras = scanner.nextInt();
                    Produtos Cadeira = produtosLoja.get(3);
                    Cadeira.setPeso(Cadeira.getPeso() * quantidadeCadeiras);
                    produtosSelecionados.add(Cadeira);
                    break;
                case 5:
                    System.out.println("Digite a quantidade de Luminarias:");
                    int quantidadeLuminarias = scanner.nextInt();
                    Produtos Luminaria = produtosLoja.get(4);
                    Luminaria.setPeso(Luminaria.getPeso() * quantidadeLuminarias);
                    produtosSelecionados.add(Luminaria);
                    break;
                case 6:
                    System.out.println("Digite a quantidade de Lavadora de Roupas:");
                    int quantidadeLavadoras = scanner.nextInt();
                    Produtos LavadoradeRoupa = produtosLoja.get(5);
                    LavadoradeRoupa.setPeso(LavadoradeRoupa.getPeso() * quantidadeLavadoras);
                    produtosSelecionados.add(LavadoradeRoupa);
                    break;
                case 7:
                    System.out.println("Digite a quantidade de Playstation 5:");
                    int quantidadePS5 = scanner.nextInt();
                    Produtos PS5 = produtosLoja.get(6);
                    PS5.setPeso(PS5.getPeso() * quantidadePS5);
                    produtosSelecionados.add(PS5);
                    break;
                case 8:
                    System.out.println("Digite a quantidade de Nintendo Switch:");
                    int quantidadeNintendoS = scanner.nextInt();
                    Produtos NintendoS = produtosLoja.get(7);
                    NintendoS.setPeso(NintendoS.getPeso() * quantidadeNintendoS);
                    produtosSelecionados.add(NintendoS);
                    break;
                case 24:
                    System.out.println("Encerrando o programa...");
                    System.exit(0);
                    break;
                default:
                    if (escolhaProduto != 0) {
                        System.out.println("Opção de produto inválida.");
                    }
                    break;
            }

        }
        return produtosSelecionados;


    }

    static int RetornaCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[0] ARACAJU");
        System.out.println("[1] BELEM");
        System.out.println("[2] BELO HORIZONTE");
        System.out.println("[3] BRASILIA");
        System.out.println("[4] CAMPO GRANDE");
        System.out.println("[5] CUIABA");
        System.out.println("[6] CURITIBA");
        System.out.println("[7] FLORIANOPOLIS");
        System.out.println("[8] FORTALEZA");
        System.out.println("[9] GOIANIA");
        System.out.println("[10] JOAO PESSOA");
        System.out.println("[11] MACEIO");
        System.out.println("[12] MANAUS");
        System.out.println("[13] NATAL");
        System.out.println("[14] PORTO ALEGRE");
        System.out.println("[15] PORTO VELHO");
        System.out.println("[16] RECIFE");
        System.out.println("[17] RIO BRANCO");
        System.out.println("[18] RIO DE JANEIRO");
        System.out.println("[19] SALVADOR");
        System.out.println("[20] SAO LUIS");
        System.out.println("[21] SAO PAULO");
        System.out.println("[22] TERESINA");
        System.out.println("[23] VITORIA");


        int cidade = scanner.nextInt();

        return cidade;
    }

    static String getCidadeNome(int cidade) {
        String[] cidades = {
                "ARACAJU", "BELEM", "BELO HORIZONTE", "BRASILIA", "CAMPO GRANDE", "CUIABA", "CURITIBA", "FLORIANOPOLIS",
                "FORTALEZA", "GOIANIA", "JOAO PESSOA", "MACEIO", "MANAUS", "NATAL", "PORTO ALEGRE", "PORTO VELHO",
                "RECIFE", "RIO BRANCO", "RIO DE JANEIRO", "SALVADOR", "SAO LUIS", "SAO PAULO", "TERESINA", "VITORIA"
        };

        if (cidade >= 0 && cidade < cidades.length) {
            return cidades[cidade];
        } else {
            return "Cidade inválida";
        }
    }

    static String SelecionarCaminhao(double pesoTotal, ArrayList<Caminhoes> tiposCaminhoes) {
        String modeloCaminhao = "";

        for (Caminhoes caminhao : tiposCaminhoes) {
            if (pesoTotal <= caminhao.getCapacidade()) {
                modeloCaminhao = caminhao.getTipo();
                break;
            }
        }

        if (modeloCaminhao.isEmpty()) {
            int caminhoesNecessarios = (int) Math.ceil(pesoTotal / 10);
            modeloCaminhao = "Grande (x" + caminhoesNecessarios + ")";
        }

        return modeloCaminhao;
    }

    static double CalcularCustoTransporte(double pesoTotal, int distancia, ArrayList<Caminhoes> tiposCaminhoes) {
        double custo = 0;

        for (Caminhoes caminhao : tiposCaminhoes) {
            if (pesoTotal <= caminhao.getCapacidade()) {
                custo = distancia * caminhao.getPrecoKM();
                break;
            }
        }

        if (custo == 0) {
            int caminhoesNecessarios = (int) Math.ceil(pesoTotal / 10);
            custo = caminhoesNecessarios * distancia * 29.21; // Preço para caminhões grandes
        }

        return custo;
    }

    static int LerArquivo(String arquivo, int x, int y) {
        int[][] distancias = null;
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int linhaCount = 0;
            while ((linha = br.readLine()) != null) {
                String[] numerosString = linha.split(";");
                if (distancias == null) {
                    distancias = new int[numerosString.length][numerosString.length];
                }
                for (int i = 0; i < numerosString.length; i++) {
                    distancias[linhaCount][i] = Integer.parseInt(numerosString[i]);
                }
                linhaCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distancias[x][y];
    }

    static double calcularPesoTotal(ArrayList<Produtos> produtosSelecionados) {
        double pesoTotal = 0;
        for (Produtos produto : produtosSelecionados) {
            pesoTotal += produto.getPeso();
        }
        return pesoTotal;
    }

}
