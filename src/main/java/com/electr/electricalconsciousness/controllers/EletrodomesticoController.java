package com.electr.electricalconsciousness.controllers;

import com.electr.electricalconsciousness.domain.dto.eletrodomesticos.EletrodomesticoFullDTO;
import com.electr.electricalconsciousness.domain.services.EletrodomesticoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/eletrodomesticos")
@RestController
@RequiredArgsConstructor
public class EletrodomesticoController {

    private final EletrodomesticoService eletrodomesticoService;

    @GetMapping
    public List<EletrodomesticoFullDTO> buscarTodosEletrodomesticos() {
        return eletrodomesticoService.buscarTodosEletrodomesticos();
    }

    @GetMapping("/{eletroId}")
    public EletrodomesticoFullDTO buscarEletrodomesticoPorId(@PathVariable Long eletroId){
        return eletrodomesticoService.buscarEletrodomesticoPorId(eletroId);
    }

    @PostMapping
    public EletrodomesticoFullDTO salvarEletrodomestico(@RequestParam("avatar") MultipartFile avatar,
                                                    @RequestParam("nome") String nome,
                                                    @RequestParam("potencia") Integer potencia,
                                                    @RequestParam("tempoDeUso") Integer tempo,
                                                    @RequestParam("quantidade") Integer quantidade,
                                                    @RequestParam("diasPorMes") Integer diasPorMes
                                    ) throws IOException {

        return eletrodomesticoService.salvarEletrodomestico(avatar, nome, potencia, tempo, quantidade, diasPorMes);
    }

    @PutMapping("/{eletroId}")
    public EletrodomesticoFullDTO atualizarEletrodomestico(@PathVariable Long eletroId, @RequestParam("nome") String nome,
                                                       @RequestParam("potencia") Integer potencia,
                                                       @RequestParam("avatar") MultipartFile avatar) {

        return eletrodomesticoService.atualizarEletrodomestico(eletroId, nome, potencia, avatar);
    }

    @DeleteMapping("/{eletroId}")
    public ResponseEntity<Void> deletarEletrodomestico(@PathVariable Long eletroId){
        return eletrodomesticoService.deletarEletrodomestico(eletroId);
    }
}
