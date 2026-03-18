package com.intercambios.intercambios_api.model;
import com.intercambios.intercambios_api.model.Alimento;
import com.intercambios.intercambios_api.service.AlimentoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {
    private final AlimentoService service;

    public AlimentoController(AlimentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Alimento> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/por-subgrupo/{subgrupoId}")
    public List<Alimento> listarPorSubgrupo(@PathVariable Integer subgrupoId) {
        return service.listarPorSubgrupo(subgrupoId);
    }

    @GetMapping("/{id}/intercambios")
    public List<Alimento> buscarIntercambios(@PathVariable Integer id) {
        return service.buscarIntercambios(id);
    }

}
