package com.github.calculadordefrete;



public class Caminhoes {
    String tipo;
    double precoKM;
    double capacidade;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecoKM() {
        return precoKM;
    }

    public void setPrecoKM(double precoKM) {
        this.precoKM = precoKM;
    }

    public Caminhoes(String tipo, double precoKM, double capacidade) {
        this.tipo = tipo;
        this.precoKM = precoKM;
        this.capacidade = capacidade;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(double capacidade) {
        this.capacidade = capacidade;
    }


}