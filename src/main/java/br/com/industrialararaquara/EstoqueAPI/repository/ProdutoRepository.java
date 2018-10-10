package br.com.industrialararaquara.EstoqueAPI.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.industrialararaquara.EstoqueAPI.bean.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

}
