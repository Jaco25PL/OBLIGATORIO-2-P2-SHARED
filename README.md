# Roadmap

## Fase 1: Análisis y Diseño (20%)

1. **LISTO** Análisis del Dominio (1-2 días)

- **LISTO** Lee la consigna completa 2-3 veces
- **LISTO** Identifica las entidades principales: Cliente, Vehículo, Empleado, Contrato, Entrada, Salida, ServicioAdicional
- **LISTO** Define las relaciones entre entidades
- **LISTO** Lista todos los atributos de cada clase

2. **LISTO** Diagrama UML (1 día)

- **LISTO** Diseña el diagrama de clases del dominio
- **LISTO** Incluye solo clases de dominio (no interfaz ni pruebas)
- **LISTO** Especifica atributos, métodos y relaciones
- **LISTO** Usa notación UML correcta

## Fase 2: Estructura Base (25%)

3. **LISTO** Configuración del Proyecto (medio día)

- **LISTO** Crea proyecto ANT en NetBeans
- **LISTO** Estructura de carpetas: dominio, interfaz
- **LISTO** Agrega nombres de autores en cada archivo

4. **LISTO** Clases de Dominio (2-3 días)
5. **LISTO** Clase Sistema (1 día)

- **LISTO** Implementa listas para todas las entidades
- **LISTO** Métodos básicos de alta, baja, búsqueda
- **LISTO** Validaciones de unicidad

## Fase 3: Interfaz Básica (30%)

6. **LISTO** Ventana Principal (1 día)

- **LISTO** Menú principal con todas las opciones
- **LISTO** Título con nombres de estudiantes
- **LISTO** Botón modo claro/oscuro

7. Gestión - Ventanas CRUD (3-4 días)

- Orden prioritario:
  1. **LISTO** Gestión de Clientes (alta, baja, visualización)
  2. **LISTO** Gestión de Vehículos (alta, visualización)
  3. **LISTO** Gestión de Empleados (alta, visualización)
  4. **LISTO** Gestión de Contratos (alta con combos, visualización)

8. Validaciones y Actualización (1 día)

- **LISTO** Implementa todas las validaciones
- **LISTO** Patrón Observer para actualización automática

## Fase 4: Movimientos (20%)

9. **LISTO** Funcionalidades de Movimiento (2-3 días)
1. **LISTO** Entradas: Lista vehículos disponibles, registro
1. **LISTO** Salidas: Lista entradas sin salida, cálculo de tiempo
1. **LISTO** Servicios Adicionales: Combo de servicios, registro y consulta

## Fase 5: Reportes (15%)

10. **LISTO** Ventana de Reportes con Pestañas (2-3 días)
1. **LISTO** Historial por vehículo: Tabla, ordenamiento, filtros, exportación TXT
1. **LISTO** Grilla de movimientos: Botones 4x3, colores según cantidad, ventanas emergentes
1. **LISTO** Estadísticas generales: Cálculos y visualización

## Fase 6: Funcionalidades Complementarias (10%)

11. Persistencia (1 día)

- Serialización en DATOS.ser
- Recuperación y grabación con confirmación

12. **LISTO** Mini Juego (1 día)

- **LISTO** Implementa algo simple (puede usar ChatGPT)
- **LISTO** Uso de mouse/teclado

13. **LISTO** Información de Autores (medio día)

- **LISTO** Ventana con datos completos y fotos

14. **LISTO** Modo Oscuro (medio día)

- **LISTO** Implementa cambio de tema en todas las ventanas

## Fase 7: Documentación y Entrega (tiempo paralelo)

15. Video de YouTube (1 día)

- Máximo 2 minutos
- Mostrar funcionalidades principales
- Ambos integrantes visibles

16. Documentación PDF (1 día)

- **LISTO** Carátula con fotos
- Diagrama UML
- Link del video

17. Testing y JAR (1 día)

- Pruebas exhaustivas
- Generación del JAR sin datos precargados
- Verificación de funcionamiento
