package dev.jgvictors.JustACrud.controller;

import dev.jgvictors.JustACrud.exceptions.RegistroFuncionarioAlreadyExistException;
import dev.jgvictors.JustACrud.exceptions.RegistroFuncionarioNotFoundException;
import dev.jgvictors.JustACrud.model.Funcionario;
import dev.jgvictors.JustACrud.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("listaFuncionarios", funcionarioService.getAll());
        return "funcionarios";
    }

    @GetMapping("/novo")
    public String showCreateForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("tituloDaPagina", "Adicionar Novo Funcionário");
        model.addAttribute("createForm", true);
        return "funcionarioForm";
    }

    @PostMapping("/novo")
    public String create(Funcionario funcionario, RedirectAttributes ra) {
        try {
            funcionarioService.create(funcionario);
            ra.addFlashAttribute("message", "Funcionário adicionado com sucesso.");
            return "redirect:/funcionarios";
        } catch (RegistroFuncionarioAlreadyExistException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/funcionarios/novo";
        }
    }

    @GetMapping("/editar/{registro}")
    public String showUpdateForm(@PathVariable Long registro, Model model, RedirectAttributes ra) {
        try {
            model.addAttribute("funcionario", funcionarioService.get(registro));
            model.addAttribute("tituloDaPagina", "Atualizando Funcionário (" + registro + ")");
            model.addAttribute("updateForm", true);
            return "funcionarioForm";
        } catch (RegistroFuncionarioNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/funcionarios";
    }

    @PostMapping("/editar")
    public String update(Funcionario funcionario, RedirectAttributes ra) {
        try {
            funcionarioService.update(funcionario);
            ra.addFlashAttribute("message", "Dados do funcionário com registro " + funcionario.getRegistro() + " atualizados com sucesso.");
        } catch (RegistroFuncionarioNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/funcionarios";
    }

    @GetMapping("/remover/{registro}")
    public String delete(@PathVariable Long registro, RedirectAttributes ra) {
        try {
            funcionarioService.delete(registro);
            ra.addFlashAttribute("message", "Funcionário com registro " + registro + " removido com sucesso.");
        } catch (RegistroFuncionarioNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/funcionarios";
    }

}