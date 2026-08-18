package bo.diplomado.calculadora.service;

import org.springframework.stereotype.Service;

/**
 * Lógica de negocio de la calculadora.
 *
 * Se mantiene deliberadamente simple y sin dependencias de Spring en sus
 * métodos para que pueda probarse con pruebas unitarias puras (JUnit 5),
 * sin necesidad de levantar el contexto de la aplicación.
 */
@Service
public class CalculadoraService {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
     * Divide dos números.
     *
     * @throws IllegalArgumentException si el divisor es cero. La validación es
     *         explícita porque en aritmética de punto flotante dividir entre
     *         cero devuelve Infinity en lugar de lanzar una excepción.
     */
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("No se puede dividir entre cero");
        }
        return a / b;
    }

    public boolean esPar(int numero) {
        return numero % 2 == 0;
    }
}
