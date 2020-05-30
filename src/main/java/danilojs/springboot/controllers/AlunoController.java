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
import danilojs.springboot.entities.Aluno;
import danilojs.springboot.entities.Instituicao;
import danilojs.springboot.repository.RepositorioAluno;
import danilojs.springboot.repository.RepositorioInstituicao;

@Controller
public class AlunoController {

	@Autowired
	private RepositorioAluno repositorioAluno;
	@Autowired
	private RepositorioInstituicao repositorioInstituicao;
	
	@RequestMapping("/alunos")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("alunos/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("aluno/index");
		resultado.addObject("alunos", repositorioAluno.findAll());
		return resultado;
	}
	
	@GetMapping("alunos/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("aluno/inserir");
		Aluno aluno = new Aluno();
		aluno.setInstituicao(new Instituicao());
		resultado.addObject("aluno", aluno);
		resultado.addObject("instituicoes", repositorioInstituicao.findAll());
		return resultado;
	}
	
	@PostMapping("alunos/inserir")
	public String inserir(Aluno aluno) {
		repositorioAluno.save(aluno);
		return "redirect:/alunos/index";
	}
	
	@GetMapping("alunos/editar/{id}")
	public ModelAndView editar(@PathVariable Long id){
		ModelAndView resultado = new ModelAndView("aluno/editar");
		resultado.addObject("aluno", repositorioAluno.getOne(id));
		resultado.addObject("instituicoes", repositorioInstituicao.findAll());
		return resultado;
	}
	
	@GetMapping("alunos/editar")
	public String editar(Aluno aluno){
		repositorioAluno.save(aluno);
		return "redirect:/alunos/index";
	}
	
	@GetMapping("alunos/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioAluno.deleteById(id);
		return "redirect:/alunos/index";
	}
	
	//AJAX
	@GetMapping({"alunos/pesquisarPorNome/{nome}", "/pesquisarPorNome"})
	public @ResponseBody List<Aluno> pesquisarPorNome(@PathVariable Optional<String> nome){
		if(nome.isPresent())
			return repositorioAluno.findByNomeContaining(nome.get());
		else
			return repositorioAluno.findAll();
	}
}