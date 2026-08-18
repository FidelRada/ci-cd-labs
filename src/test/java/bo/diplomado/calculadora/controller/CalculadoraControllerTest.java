package bo.diplomado.calculadora.controller;

import bo.diplomado.calculadora.service.CalculadoraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias del controlador REST.
 *
 * Se instancia el controlador directamente con su dependencia real en lugar de
 * levantar el contexto web completo: así la prueba sigue siendo unitaria y
 * rápida, y verifica que el controlador delega correctamente en el servicio.
 */
@DisplayName("CalculadoraController")
class CalculadoraControllerTest {

    private CalculadoraController controlador;

    @BeforeEach
    void inicializar() {
        controlador = new CalculadoraController(new CalculadoraService());
    }

    @Test
    @DisplayName("expone la suma")
    void sumar() {
        assertEquals(9.0, controlador.sumar(4, 5), 0.0001);
    }

    @Test
    @DisplayName("expone la resta")
    void restar() {
        assertEquals(1.0, controlador.restar(4, 3), 0.0001);
    }

    @Test
    @DisplayName("expone la multiplicación")
    void multiplicar() {
        assertEquals(20.0, controlador.multiplicar(4, 5), 0.0001);
    }

    @Test
    @DisplayName("expone la división")
    void dividir() {
        assertEquals(2.0, controlador.dividir(10, 5), 0.0001);
    }

    @Test
    @DisplayName("propaga el error de división entre cero")
    void dividirEntreCero() {
        assertThrows(IllegalArgumentException.class, () -> controlador.dividir(1, 0));
    }

    @Test
    @DisplayName("expone la verificación de paridad")
    void esPar() {
        assertTrue(controlador.esPar(10));
        assertFalse(controlador.esPar(7));
    }
}
