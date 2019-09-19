package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author guilh
 */
public class Veiculo {
    
    private String id;
    private String placa;
    private String vaga;
    private String dataEntrada;

    public Veiculo(String id, String placa, String vaga, String dataEntrada) {
        this.id = id;
        this.placa = placa;
        this.vaga = vaga;
        this.dataEntrada = dataEntrada;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public String getEntrada() {
        return dataEntrada;
    }

    public void setEntrada(String entrada) {
        this.dataEntrada = entrada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
    
}
