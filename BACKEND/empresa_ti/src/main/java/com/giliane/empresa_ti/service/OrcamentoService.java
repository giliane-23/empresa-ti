package com.giliane.empresa_ti.service;

import com.giliane.empresa_ti.model.Orcamento;
import com.giliane.empresa_ti.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrcamentoService {

 private final OrcamentoRepository repository;

 public OrcamentoService(OrcamentoRepository repository) {
  this.repository = repository;
 }

 public Orcamento salvar(Orcamento orcamento) {

  repository.findByEmail(orcamento.getEmail())
          .ifPresent(c -> {
           if(!c.getId().equals(orcamento.getId())){
            throw new RuntimeException("Email já cadastrado.");
           }
          });

  return repository.save(orcamento);
 }
 public List<Orcamento> listar() {
  return repository.findAll();
 }

 public Orcamento buscarPorId(Long id) {
  return repository.findById(id)
          .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
 }

 public Orcamento atualizar(Long id, Orcamento orcamentoAtualizado) {
  Orcamento orcamento = buscarPorId(id);

  // Verifica se o email já existe em outro cliente
  repository.findByEmail(orcamentoAtualizado.getEmail())
          .ifPresent(c -> {
           if (!c.getId().equals(id)) {
            throw new RuntimeException("Email já cadastrado.");
           }
          });

  orcamento.setNome(orcamentoAtualizado.getNome());
  orcamento.setEmail(orcamentoAtualizado.getEmail());
  orcamento.setTelefone(orcamentoAtualizado.getTelefone());

  return repository.save(orcamento);
 }

 public void deletar(Long id) {
  Orcamento orcamento = buscarPorId(id);
  repository.delete(orcamento);
 }

}

