package bo.diplomado.calculadora.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias de CalculadoraService.
 *
 * No levantan el contexto de Spring: instancian la clase directamente, por lo
 * que se ejecutan en milisegundos. Son el núcleo del Quality Gate del pipeline.
 */
@DisplayName("CalculadoraService")
class CalculadoraServiceTest {

    private CalculadoraService calculadora;

    @BeforeEach
    void inicializar() {
        calculadora = new CalculadoraService();
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "2, 3, 5",
            "-1, 1, 0",
            "0, 0, 0",
            "2.5, 2.5, 5.0"
    })
    @DisplayName("suma correctamente")
    void sumar(double a, double b, double esperado) {
        assertEquals(esperado, calculadora.sumar(a, b), 0.0001);
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "5, 3, 2",
            "0, 4, -4",
            "-2, -2, 0"
    })
    @DisplayName("resta correctamente")
    void restar(double a, double b, double esperado) {
        assertEquals(esperado, calculadora.restar(a, b), 0.0001);
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "3, 4, 12",
            "-2, 5, -10",
            "7, 0, 0"
    })
    @DisplayName("multiplica correctamente")
    void multiplicar(double a, double b, double esperado) {
        assertEquals(esperado, calculadora.multiplicar(a, b), 0.0001);
    }

    @Test
    @DisplayName("divide correctamente")
    void dividir() {
        assertEquals(4.0, calculadora.dividir(8, 2), 0.0001);
        assertEquals(-3.0, calculadora.dividir(9, -3), 0.0001);
    }

    @Test
    @DisplayName("lanza excepción al dividir entre cero")
    void dividirEntreCeroLanzaExcepcion() {
        IllegalArgumentException error = assertThrows(
                IllegalArgumentException.class,
                () -> calculadora.dividir(10, 0)
        );
        assertEquals("No se puede dividir entre cero", error.getMessage());
    }

    @ParameterizedTest(name = "{0} es par")
    @ValueSource(ints = {0, 2, 4, 100, -8})
    @DisplayName("identifica números pares")
    void esPar(int numero) {
        assertTrue(calculadora.esPar(numero));
    }

    @ParameterizedTest(name = "{0} es impar")
    @ValueSource(ints = {1, 3, 7, 99, -5})
    @DisplayName("identifica números impares")
    void noEsPar(int numero) {
        assertFalse(calculadora.esPar(numero));
    }
}
