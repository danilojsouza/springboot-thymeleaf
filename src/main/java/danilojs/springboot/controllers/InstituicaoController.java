package danilojs.springboot.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import danilojs.springboot.entities.Instituicao;
import danilojs.springboot.repository.RepositorioInstituicao;

@Controller
public class InstituicaoController {
	
	@Autowired
	private RepositorioInstituicao repositorioInstituicao;
	
	@RequestMapping("/instituicoes")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("instituicoes/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("instituicao/index");
		resultado.addObject("instituicoes", repositorioInstituicao.findAll());
		return resultado;
	}
	
	@GetMapping("instituicoes/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("instituicao/inserir");
		resultado.addObject("instituicao", new Instituicao());
		return resultado;
	}
	
	@PostMapping("instituicoes/inserir")
	public String inserir(Instituicao instituicao) {
		repositorioInstituicao.save(instituicao);
		return "redirect:/instituicoes/index";
	}
	
	@GetMapping("instituicoes/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView resultado = new ModelAndView("instituicao/editar");
		resultado.addObject(repositorioInstituicao.getOne(id));
		return resultado;
	}
	
	@PostMapping("instituicoes/editar")
	public String editar(Instituicao instituicao) {
		repositorioInstituicao.save(instituicao);
		return "redirect:/instituicoes/index";
	}
	
	@GetMapping("instituicoes/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioInstituicao.deleteById(id);
		return "redirect:/instituicoes/index";
	}
	
	//AJAX
	@GetMapping({"instituicoes/pesquisarPorNome/{nome}", "/pesquisarPorNome"})
	public @ResponseBody List<Instituicao> pesquisarPorNome(@PathVariable Optional<String> nome){
		if(nome.isPresent()) 
			return repositorioInstituicao.findByNomeContaining(nome.get());
		else
			return repositorioInstituicao.findAll();
	}
}