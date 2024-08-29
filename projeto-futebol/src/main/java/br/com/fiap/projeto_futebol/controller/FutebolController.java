package br.com.fiap.projeto_futebol.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.projeto_futebol.model.Jogador;
import br.com.fiap.projeto_futebol.model.ListaUF;
import br.com.fiap.projeto_futebol.model.Time;
import br.com.fiap.projeto_futebol.repository.JogadorRepository;
import br.com.fiap.projeto_futebol.repository.TimeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class FutebolController {

	@Autowired
	private TimeRepository repT;
	@Autowired
	private JogadorRepository repJ;

	@GetMapping("/index")
	public String retornaPagina() {
		return "index";
	}

	@GetMapping("/index2")
	public String retornaPagina2(HttpServletRequest req) {
		req.setAttribute("var", "World");
		return "index";
	}

	@GetMapping("/index3")
	public ModelAndView retornaPagina3() {
		Time t1 = new Time(1L, "São Paulo", ListaUF.SP, "Salve Salve", "Brasão");
		Jogador j1 = new Jogador(1L, "Lucas Moura", t1);

		ModelAndView mv = new ModelAndView("index");

		mv.addObject("var", j1);

		return mv;
	}

	@GetMapping("/retorna_lista_jogadores")
	public ModelAndView retornaListaJogadores() {

		List<Jogador> listaJ = repJ.findAll();

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("jogadores", listaJ);

		return mv;

	}

	@GetMapping("/retornaFormCadTime")
	public ModelAndView retornaFormCadTime() {

		ModelAndView mv = new ModelAndView("form_cad_times");
		mv.addObject("time", new Time());
		mv.addObject("lista_uf", ListaUF.values());
		return mv;

	}

	@PostMapping("/inserir_time")
	public ModelAndView cadastroTime(@Valid Time novo_time, BindingResult bd) {

		if (bd.hasErrors()) {
			ModelAndView mv = new ModelAndView("form_cad_times");
			mv.addObject("lista_uf", ListaUF.values());
			mv.addObject("time", novo_time);
			return mv;
		} else {
			Time t = new Time();
			t.setNome(novo_time.getNome());
			t.setBrasao(novo_time.getBrasao());
			t.setUf(novo_time.getUf());
			t.setHino(novo_time.getHino());

			repT.save(t);

			return new ModelAndView("redirect:/retorna_lista_jogadores");
		}

	}

	@GetMapping("/detalhes_jogador/{id}")
	public ModelAndView retornaDetalhesJogador(@PathVariable Long id) {

		Optional<Jogador> op = repJ.findById(id);

		if (op.isPresent()) {
			Jogador jogador = op.get();

			ModelAndView mv = new ModelAndView("detalhes_jogador");
			mv.addObject("jogador", jogador);
			return mv;

		} else {
			return new ModelAndView("redirect:/retorna_lista_jogadores");
		}

	}

	@GetMapping("/atualizar_jogador/{id}")
	public ModelAndView retornaFormAtualizaJogador(@PathVariable Long id) {

		Optional<Jogador> op = repJ.findById(id);

		if (op.isPresent()) {

			Jogador jogador = op.get();

			ModelAndView mv = new ModelAndView("form_atualiza_jogador");
			mv.addObject("jogador", jogador);
			mv.addObject("lista_uf", ListaUF.values());
			return mv;

		} else {
			return new ModelAndView("redirect:/retorna_lista_jogadores");
		}

	}

	@PostMapping("/atualizar_info_jogador/{id}")
	public ModelAndView atualizarJogador(@PathVariable Long id, @Valid Jogador jogador, BindingResult bd) {

		if (bd.hasErrors()) {

			ModelAndView mv = new ModelAndView("form_atualiza_jogador");
			mv.addObject("jogador", jogador);
			mv.addObject("lista_uf", ListaUF.values());
			return mv;

		} else {

			Optional<Jogador> op = repJ.findById(id);

			if (op.isPresent()) {

				Jogador jogador1 = op.get();

				jogador1.setNome(jogador.getNome());
				jogador1.setNumero(jogador.getNumero());
				jogador1.getTime().setNome(jogador.getTime().getNome());
				jogador1.getTime().setHino(jogador.getTime().getHino());
				jogador1.getTime().setBrasao(jogador.getTime().getBrasao());
				jogador1.getTime().setUf(jogador.getTime().getUf());

				repJ.save(jogador1);

				return new ModelAndView("redirect:/retorna_lista_jogadores");

			} else {
				return new ModelAndView("redirect:/retorna_lista_jogadores");
			}

		}

	}

	@GetMapping("/remover_jogador/{id}")
	public String removerJogador(@PathVariable Long id) {

		Optional<Jogador> op = repJ.findById(id);

		if (op.isPresent()) {

			repJ.deleteById(id);

			return "redirect:/retorna_lista_jogadores";

		} else {
			return "redirect:/retorna_lista_jogadores";
		}

	}

}
