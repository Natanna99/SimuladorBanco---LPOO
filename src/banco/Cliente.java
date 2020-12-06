package banco;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Cliente {
    private int código;
    private String nome;
    private String dataCadastro;
    private String telefone;
    private Cidade cidade;

    public Cliente(int código, String nome, String dataCadastro, String telefone, Cidade cidade) {
        this.código = código;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.telefone = telefone;
        this.cidade = cidade;
    }
    
    public Cliente(){}

    @Override
    public String toString() {
        return "Cliente{" + "c\u00f3digo=" + código + ", nome=" + nome + ", dataCadastro=" + dataCadastro + ", telefone=" + telefone + ", cidade=" + cidade + '}';
    }

    public int getCódigo() {
        return código;
    }

    public void setCódigo(int código) {
        this.código = código;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
}
