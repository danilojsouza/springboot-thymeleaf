package danilojs.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import danilojs.springboot.entities.Aluno;

public interface RepositorioAluno extends JpaRepository<Aluno, Long>{

	List<Aluno> findByNomeContaining(String nome);
}