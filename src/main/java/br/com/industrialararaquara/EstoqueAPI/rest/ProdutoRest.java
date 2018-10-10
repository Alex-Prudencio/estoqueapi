package br.com.industrialararaquara.EstoqueAPI.rest;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.industrialararaquara.EstoqueAPI.bean.Produto;
import br.com.industrialararaquara.EstoqueAPI.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/estoque")

public class ProdutoRest {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping(path="/estoque")
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<Produto> recuperarProdutoPorId(@PathVariable("id") Integer id) {
	try {
		Produto produto = produtoRepository.findById(id).get();
			return new ResponseEntity<Produto>(produto, HttpStatus.OK); 
	}catch(NoSuchElementException ex)	{
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<?> excluirProdutoPorId(@PathVariable("id") Integer id) {
		Produto produto = produtoRepository.findById(id).get();
		try {
			produtoRepository.delete(produto);
			return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT); 
		}catch(NoSuchElementException ex)	{
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Produto> inserirProduto(@RequestBody Produto produto){
		produto = produtoRepository.save(produto);
		
		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto){
		produto = produtoRepository.save(produto);
		
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
}
