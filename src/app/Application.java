package app;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import swing.ui.DefaultList;
import service.Produto;

/**
 *
 * @author itakenami
 */
public class Application {
    
    private static Application instance;
    public DefaultList listProduto;
    public DefaultList listCotacao;
    public Main main;
    
    private Application(){
        main = new Main();
        listProduto = new DefaultList(main,new Produto());
    }
    
    public static Application getInstance(){
        if(instance==null){
            instance = new Application();
        }
        return instance;
    }
    
}
