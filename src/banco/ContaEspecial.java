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
public class ContaEspecial extends ContaCorrente{
    private double limite;
    
    public ContaEspecial(){}

    public ContaEspecial(double saldo, String dataAbertura, int codConta, double linite) {
        super(saldo, dataAbertura, codConta);
        this.limite= limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
    
     public void depositar(double valor){
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        super.addMovimentacao("Deposito", data, hora, valor, super.getSaldo(), super.getSaldo()+valor);
        super.setSaldo(super.getSaldo()+valor);
    }
    
    @Override
    public boolean sacar(double valor){
        double total=super.getSaldo()+this.limite;
        if (valor == total ||valor < total ){
            addMovimentacao("Saque", super.getData(), super.getHora(), valor, super.getSaldo(), super.getSaldo()-valor);
            double novoS = total - valor;
            super.setSaldo(super.getSaldo()-valor);
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public void transferir(ContaCorrente conta, double valor) {
        if (valor <= super.getSaldo()) {
            boolean retirou = this.sacar(valor);
            if (retirou != false){
                conta.depositar(valor);
                conta.setSaldo(super.getSaldo()+valor);   
            }
        }
    }

    @Override
    public String toString() {
        return "ContaEspecial{" + "limite=" + limite + '}';
    }
    
}
