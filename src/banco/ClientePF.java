/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

/**
 *
 * @author User
 */
public class ClientePF extends Cliente {
    private String CPF;

    public ClientePF(int código, String nome, String dataCadastro, String telefone, Cidade cidade) {
        super(código, nome, dataCadastro, telefone, cidade);
    }

    public ClientePF() {
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    @Override
    public String toString() {
        return "ClientePF{" + "CPF=" + CPF + '}';
    }

  
}    
