package bo.diplomado.calculadora.controller;

import bo.diplomado.calculadora.service.CalculadoraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expone las operaciones de la calculadora como una API REST.
 */
@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {

    private final CalculadoraService servicio;

    public CalculadoraController(CalculadoraService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/sumar")
    public double sumar(@RequestParam double a, @RequestParam double b) {
        return servicio.sumar(a, b);
    }

    @GetMapping("/restar")
    public double restar(@RequestParam double a, @RequestParam double b) {
        return servicio.restar(a, b);
    }

    @GetMapping("/multiplicar")
    public double multiplicar(@RequestParam double a, @RequestParam double b) {
        return servicio.multiplicar(a, b);
    }

    @GetMapping("/dividir")
    public double dividir(@RequestParam double a, @RequestParam double b) {
        return servicio.dividir(a, b);
    }

    @GetMapping("/es-par")
    public boolean esPar(@RequestParam int numero) {
        return servicio.esPar(numero);
    }
}
