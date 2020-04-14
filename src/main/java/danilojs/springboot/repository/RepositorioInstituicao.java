package danilojs.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import danilojs.springboot.entities.Instituicao;

public interface RepositorioInstituicao extends JpaRepository<Instituicao, Long>{

	List<Instituicao> findByNomeContaining(String nome);
}