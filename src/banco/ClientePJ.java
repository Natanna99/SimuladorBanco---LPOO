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
public class ClientePJ extends Cliente {
    private String Cnpj;

    public ClientePJ(int código, String nome, String dataCadastro, String telefone, Cidade cidade) {
        super(código, nome, dataCadastro, telefone, cidade);
    }

    public ClientePJ() {
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    @Override
    public String toString() {
        return "ClientePJ{" + "Cnpj=" + Cnpj + '}';
    }

    
}
