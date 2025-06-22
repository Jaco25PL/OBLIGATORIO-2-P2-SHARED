/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package observer;

public interface SistemaObserver {
    void onClienteEliminado();
    void onClienteCreado();
    
    void onVehiculoEliminado();
    void onVehiculoCreado();
    
    void onEmpleadoEliminado();
    void onEmpleadoCreado();

    void onContratoEliminado();
    void onContratoCreado();

    void onEntradaCreada();
    void onSalidaCreada();

}
    