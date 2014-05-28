/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author itakenami
 */
import swing.model.DefaultModel;
import swing.annotation.GridHeader;
import api.wadl.annotation.XMLCast;
import client.crud.Service;
import client.exception.ValidationException;
import client.request.ApacheRequest;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import swing.ui.ModelField;

/**
 *
 * @author itakenami
 */
@XMLCast(thisClassFrom = "models.Produto")
public class Produto implements DefaultModel<Produto> {

    @GridHeader(name = "ID", size = 10)
    public Long id;
    @GridHeader(name = "Nome", size = 50)
    public String nome;
    @GridHeader(name = "Descrição", size = 100)
    public String descricao;
    @GridHeader(name = "Valor", size = 50)
    public float valor;
    @GridHeader(name = "Quantidade", size = 50)
    public int quantidade;
    
    //public static Service<Produto> service = new Service<Produto>(new ApacheRequest("http://localhost:9000/api/Produtos"), Produto.class, new TypeToken<List<Produto>>() {}.getType());
    public static Service<Produto> service = new Service<Produto>(new ApacheRequest("http://localhost:8080/restee/api/produtos"), Produto.class, new TypeToken<List<Produto>>() {}.getType());

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public List<Produto> findStart() {
        
        return service.findAll();

    }
    
    @Override public List<Produto> filterGrid(String filter) {
        
        List<Produto> lista = service.findAll();
        
        if("".equals(filter) || filter.equals("*")){
            return lista;
        }
        
        List<Produto> filtro = new ArrayList<Produto>();
        
        for (Produto produto : lista) {
            if(produto.nome.toUpperCase().startsWith(filter.toUpperCase())){
                filtro.add(produto);
            }
        }
        return filtro;
        
    }

    @Override
    public Produto findById(Long id) {
        return service.findById(id);
    }

    @Override
    public Produto save(Long id, HashMap<String, Object> map) throws ValidationException {
        HashMap<String, String> vo = new HashMap<String, String>();
        vo.put("produto.nome", map.get("Nome").toString());
        vo.put("produto.descricao", map.get("Descrição").toString());
        vo.put("produto.valor", map.get("Valor").toString());
        vo.put("produto.quantidade", map.get("Quantidade").toString());
        return service.save(id, vo);
    }

    @Override
    public boolean delete(Long id) {
        return service.delete(id);
    }

    @Override
    public ModelField getGridFields() {
        ModelField gf = new ModelField();
        gf.addField(id);
        gf.addField(nome);
        gf.addField(descricao);
        gf.addField(valor);
        gf.addField(quantidade);
        return gf;
    }

    @Override
    public ModelField getViewFields() {
        ModelField ff = new ModelField();
        ff.addField("Nome", nome);
        ff.addField("Descrição", descricao);
        ff.addField("Valor", valor);
        ff.addField("Quantidade", quantidade);
        return ff;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public List<? extends DefaultModel> getObj(String campo) {
        return null;
    }
}
