/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cblink.controlep.controlller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Filipe
 */

@Named
@SessionScoped
public class ProgressBarView implements Serializable {

    private Integer progress;

    public Integer getProgress() {
        progress = ImportacaoBean.progress;
        if (progress > 100) {
            progress = 100;
        }
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public void onComplete() {
        progress = 0;
        org.primefaces.PrimeFaces.current().ajax().update("Progr");
    }

    public void cancel() {
        progress = null;
    }
}

