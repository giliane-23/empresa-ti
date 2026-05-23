package com.giliane.empresa_ti.controller;

import com.giliane.empresa_ti.model.Orcamento;
import com.giliane.empresa_ti.service.OrcamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    private final OrcamentoService service;

    public OrcamentoController(OrcamentoService service) {
        this.service = service;
    }

    // ➕ Criar
    @PostMapping
    public ResponseEntity<Orcamento> salvar(@RequestBody Orcamento orcamento) {
        Orcamento novoOrcamento = service.salvar(orcamento);
        return ResponseEntity.status(201).body(novoOrcamento);
    }

    // 🔎 Listar todos
    @GetMapping
    public ResponseEntity<List<Orcamento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // 🔎 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> buscarPorId(@PathVariable Long id) {
        Orcamento orcamento = service.buscarPorId(id);
        return ResponseEntity.ok(orcamento);
    }

    // ✏ Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Orcamento> atualizar(@PathVariable Long id, @RequestBody Orcamento orcamento) {
        Orcamento atualizado = service.atualizar(id, orcamento);
        return ResponseEntity.ok(atualizado);
    }
    // ✅ Marcar orçamento como concluído
    @PutMapping("/{id}/concluir")
    public ResponseEntity<Orcamento> concluir(@PathVariable Long id) {

        Orcamento orcamento = service.buscarPorId(id);

        orcamento.setConcluido(true);

        Orcamento atualizado = service.salvar(orcamento);

        return ResponseEntity.ok(atualizado);
    }

    // ❌ Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}