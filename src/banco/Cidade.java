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
public class Cidade {
    private int códigoCid;
    private String nomeCid;
    private String sigla;
    
    public Cidade(){
    }

    public Cidade(int códigoCid, String nomeCid, String sigla) {
        this.códigoCid = códigoCid;
        this.nomeCid = nomeCid;
        this.sigla = sigla;
    }
    
    @Override
    public String toString() {
        return "Cidade= " + "código:" + códigoCid + ", nomeCid:" + nomeCid + ", sigla:" + sigla + '}';
    }

    public int getCódigoCid() {
        return códigoCid;
    }

    public void setCódigoCid(int códigoCid) {
        this.códigoCid = códigoCid;
    }

    public String getNomeCid() {
        return nomeCid;
    }

    public void setNomeCid(String nomeCid) {
        this.nomeCid = nomeCid;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
}
