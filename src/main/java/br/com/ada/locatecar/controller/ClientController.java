package br.com.ada.locatecar.controller;

import br.com.ada.locatecar.model.Client;
import br.com.ada.locatecar.service.ClientService;
import br.com.ada.locatecar.model.ClientDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("")
public class ClientController {
    @Autowired
    ClientService clientService;

    //Paginas

    @GetMapping("/clients")
    public ModelAndView clientes(
            @RequestParam(defaultValue = "1", value = "page") Integer numeroPagina,
            @RequestParam(defaultValue = "5", value = "size") Integer tamanhoPagina) {

        ModelAndView modelAndView = new ModelAndView("clients.html");
        Page<Client> clientPage = this.clientService.listarPaginado(numeroPagina - 1, tamanhoPagina);
        modelAndView.addObject("clients", clientPage.getContent());
        modelAndView.addObject("totalPages", clientPage.getTotalPages());
        modelAndView.addObject("currentPage", numeroPagina);
        modelAndView.addObject("pageSize", clientPage.getSize());
        return modelAndView;
    }
//    @GetMapping("/clients")
//    public String clients(Model model){
//    List<Client> clients = this.clientService.listarTodos();
//        model.addAttribute("clients", clients);
//        return "clients.html";
//    }

    @GetMapping("/client/add")
    public String addClient(Model model,Client clientDTO) {
        model.addAttribute("client", new Client());
        model.addAttribute("veiculo", Objects.nonNull(clientDTO) ? clientDTO : new ClientDTO());
        return "client-add.html";
    }
    @PostMapping("/client/add")
    public String createClient(@Valid @ModelAttribute("client") Client client,
                               BindingResult result,
                               Model model) {
        if(result.hasErrors()) {
            return addClient(model, client);
        }
        this.clientService.createClient(client);
        return "redirect:/clients";
    }
    @GetMapping("/client/{clientId}/delete")
    public String deleteClient(@PathVariable("clientId") Long clientId) {
        this.clientService.deleteClientByrId(clientId);
        return "redirect:/clients";
    }

    @GetMapping("/client/{clientId}/edit")
    public String showEditClient(Model model, @PathVariable("clientId") Long clientId) {
        Optional<Client> optionalClient = this.clientService.buscarVeiculoPorId(clientId);
        optionalClient.ifPresent(client -> model.addAttribute("client", client));
        model.addAttribute("add", Boolean.FALSE);
        return "client-add";
    }

    @PutMapping("/client/{clientId}/edit")
    public String editarVeiculo(@ModelAttribute("client") Client client,
                                @PathVariable("clientId") Long clientId) {
        client.setId(clientId);
        this.clientService.createClient(client);
        return "redirect:/veiculos";
    }

}