/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class Movimentacao {
    private String tipo;
    private double valorMov,saldoAnt,saldoDep;
    Date dataHoraAtual = new Date();
    String dataatual = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
    String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
    
    public Movimentacao () {}

    public Movimentacao(String tipo, String data, String horario, double valorMov, double saldoAnt, double saldoDep) {
        this.tipo = tipo;
        this.dataatual = data;
        this.hora = horario;
        this.valorMov = valorMov;
        this.saldoAnt = saldoAnt;
        this.saldoDep = saldoDep;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return dataatual;
    }

    public void setData(String data) {
        this.dataatual = data;
    }

    public String getHorario() {
        return hora;
    }

    public void setHorario(String horario) {
        this.hora = horario;
    }

    public double getValorMov() {
        return valorMov;
    }

    public void setValorMov(double valorMov) {
        this.valorMov = valorMov;
    }

    public double getSaldoAnt() {
        return saldoAnt;
    }

    public void setSaldoAnt(double saldoAnt) {
        this.saldoAnt = saldoAnt;
    }

    public double getSaldoDep() {
        return saldoDep;
    }

    public void setSaldoDep(double saldoDep) {
        this.saldoDep = saldoDep;
    }

    @Override
    public String toString() {
        return "tipo=" + tipo + ", data=" + dataatual + ", horario=" + hora + ", valorMov=" + valorMov + ", saldoAnt=" + saldoAnt + ", saldoDep=" + saldoDep;
    }
    
    
    
    
}
