package br.cesed.facisa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.cesed.facisa.model.Cliente;
import br.cesed.facisa.repository.ClienteRepository;

@CrossOrigin(origins = ("*"))
@RestController
public class ClienteController {
	
	@Autowired
	ClienteRepository repository;
	
	@RequestMapping("/save")
	public String process(){
		repository.save(new Cliente("Vinicio", "83645738"));
		repository.save(new Cliente("Marcelo", "382738560"));
		repository.save(new Cliente("Marcio", "38746347"));
		repository.save(new Cliente("Rayana", "38469483"));
		
		return "Done";
	}
	
	
	@RequestMapping(value="/findall",method = RequestMethod.GET)
	public String findAll(){
		String result = "<html>";
		
		for(Cliente cust : repository.findAll()){
			result += "<div>" + cust.toString() + "</div>";
		}
		return result + "</html>";
	}
	
	@RequestMapping(value="/cliente/{id}",method = RequestMethod.GET)
	public Cliente findOne(@PathVariable("id") long id){
		Cliente cliente = repository.findOne(id);
		return cliente;
		
	}
	
	@RequestMapping(value="/Allclientes",method = RequestMethod.GET)
	public List<Cliente> listAllUsers() {
		return (List<Cliente>) repository.findAll();
				
	}
	
	@RequestMapping(value="/salvaCliente",method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody Cliente cliente) {
		try {
			repository.save(cliente);
			return new ResponseEntity<String>(HttpStatus.CREATED);

		 } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@RequestMapping(value = "findid",method = RequestMethod.GET)
	public String findById(@RequestParam long id){
		String result = "<html>";
		for(Cliente cliente : repository.findAll()){
			if(cliente.getId()== id){
				result = cliente.toString();
			}
		}
		System.out.println("entrou");
		return result;
	}
	
	@RequestMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("nome") String nome){
		String result = "<html>";
		
		for(Cliente cust: repository.findByLastName(nome)){
			result += "<div>" + cust.toString() + "</div>"; 
		}
		
		return result + "</html>";
	}*/
}
