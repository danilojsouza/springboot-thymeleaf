package danilojs.springboot.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String senha;

	private String papeis;

	private String permissoes;
	
	private int ativo;

    public Usuario(String usuario, String senha, String papeis, String permissoes, int ativo){
        this.usuario = usuario;
        this.senha = senha;
        this.papeis = papeis;
        this.permissoes = permissoes;
        this.ativo = 1;

    }

    protected Usuario(){}

    public long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
    
    public int getAtivo() {
    	return ativo;
    }
    
    public List<String> getRoleList(){
        if(this.papeis.length() > 0){
            return Arrays.asList(this.papeis.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissoes.length() > 0){
            return Arrays.asList(this.permissoes.split(","));
        }
        return new ArrayList<>();
    }    
}