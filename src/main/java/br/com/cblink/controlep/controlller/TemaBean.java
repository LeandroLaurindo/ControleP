/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author Leandro Laurindo
 */
@Named(value = "temaBean")
@SessionScoped
public class TemaBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String tema = "delta";
    
    
    private boolean exibirT = false;
    
    @PostConstruct
    public void init() {
       
    }
    
  
    
    
    public void salvar() {
      
    }
    
    public void atualizar() {
        
    }

    public String getTema() {
        return tema;
    }
    
    public void setTema(String tema) {
        this.tema = tema;
    }
    
    public String[] getTemas() {
        
        return new String[]{"afterdark", "afternoon", "afterwork", "aristo",
            "black-tie", "blitzer", "bluesky", "bootstrap", "casablanca",
            "cupertino", "cruze", "dark-hive", "delta", "dot-luv",
            "eggplant", "excite-bike", "flick", "glass-x", "home",
            "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
            "overcast", "pepper-grinder", "redmond", "rocket", "sam",
            "smoothness", "south-street", "start", "sunny", "swanky-purse",
            "trontastic", "ui-darkness", "ui-lightness", "vader"};
    }
    
    

    public boolean isExibirT() {
        return exibirT;
    }

    public void setExibirT(boolean exibirT) {
        this.exibirT = exibirT;
    }
    
}
