 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author User
 */
public class ContaCorrente {
    private ArrayList<Movimentacao>ListaMov;
    private ArrayList<Cliente> clientes;
    private double saldo;
    private String dataAbertura;
    private int codConta;
    private Date dataHoraAtual = new Date();
    private String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
    private String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
    private Movimentacao movimentacao;

    public ContaCorrente(){
    clientes = new ArrayList();
    ListaMov = new ArrayList();
    }
    
    public ContaCorrente(double saldo, String dataAbertura, int codConta) {
        this.saldo = saldo;
        this.dataAbertura = dataAbertura;
        this.codConta = codConta;
        ListaMov = new ArrayList();
        clientes = new ArrayList();
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public ArrayList<Movimentacao> getListaMov() {
        return ListaMov;
    }
    
    public void insereMovimentacao( Movimentacao movimetacao){
        ListaMov.add(movimetacao);
    }
    
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    public void insereCliente(Cliente cli){
        clientes.add(cli);
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public int getCodConta() {
        return codConta;
    }

    public void setCodConta(int codConta) {
        this.codConta = codConta;
    }

    public Date getDataHoraAtual() {
        return dataHoraAtual;
    }

    public void setDataHoraAtual(Date dataHoraAtual) {
        this.dataHoraAtual = dataHoraAtual;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public void  addMovimentacao(String tipo, String data, String hora, 
        double valor, double saldoAntes, double saldoDepois){
         ListaMov.add(new Movimentacao(tipo, data, hora, valor, saldoAntes, saldoDepois));
    } 
    
    
    public void depositar(double valor){
        addMovimentacao("Deposito", data, hora, valor, this.saldo, this.saldo+valor);
        this.saldo = this.saldo+valor;
    }
   

    public boolean sacar(double valor){
        if (valor <= this.saldo){
            addMovimentacao("Saque", data, hora, valor, this.saldo, this.saldo-valor);
            this.saldo=this.saldo-valor;
            return true;
        }
        else{
            return false;
        }
    }
    
    public void transferir(ContaCorrente conta, double valor) {
        if (valor <= this.saldo) {
            boolean retirou = this.sacar(valor);
            if (retirou != false){
                conta.depositar(valor);
                conta.setSaldo(this.saldo=this.saldo+valor);   
            }
        }
    }

    @Override
    public String toString() {
        return "ContaCorrente{" + "ListaMov=" + ListaMov + ", clientes=" + clientes + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura + ", codConta=" + codConta + ", dataHoraAtual=" + dataHoraAtual + ", data=" + data + ", hora=" + hora + '}';
    }
    
}
