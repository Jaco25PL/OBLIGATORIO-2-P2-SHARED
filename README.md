# Roadmap

## Fase 1: Análisis y Diseño (20%)
1. Análisis del Dominio (1-2 días)
  - Lee la consigna completa 2-3 veces
  - Identifica las entidades principales: Cliente, Vehículo, Empleado, Contrato, Entrada, Salida, ServicioAdicional
  - Define las relaciones entre entidades
  - Lista todos los atributos de cada clase
2. Diagrama UML (1 día)
  - Diseña el diagrama de clases del dominio
  - Incluye solo clases de dominio (no interfaz ni pruebas)
  - Especifica atributos, métodos y relaciones
  - Usa notación UML correcta

## Fase 2: Estructura Base (25%)
3. Configuración del Proyecto (medio día)
  - Crea proyecto ANT en NetBeans
  - Estructura de carpetas: dominio, interfaz
  - Agrega nombres de autores en cada archivo
4. Clases de Dominio (2-3 días)
5. Clase Sistema (1 día)
  - Implementa listas para todas las entidades
  - Métodos básicos de alta, baja, búsqueda
  - Validaciones de unicidad

## Fase 3: Interfaz Básica (30%)
6. Ventana Principal (1 día)
  - Menú principal con todas las opciones
  - Título con nombres de estudiantes
  - Botón modo claro/oscuro
7. Gestión - Ventanas CRUD (3-4 días)
  - Orden prioritario:
    1. Gestión de Clientes (alta, baja, visualización)
    2. Gestión de Vehículos (alta, visualización)
    3. Gestión de Empleados (alta, visualización)
    4. Gestión de Contratos (alta con combos, visualización)
8. Validaciones y Actualización (1 día)
  - Implementa todas las validaciones
  - Patrón Observer para actualización automática

## Fase 4: Movimientos (20%)
9. Funcionalidades de Movimiento (2-3 días)
  1. Entradas: Lista vehículos disponibles, registro
  2. Salidas: Lista entradas sin salida, cálculo de tiempo
  3. Servicios Adicionales: Combo de servicios, registro y consulta

## Fase 5: Reportes (15%)
10. Ventana de Reportes con Pestañas (2-3 días)
  1. Historial por vehículo: Tabla, ordenamiento, filtros, exportación TXT
  2. Grilla de movimientos: Botones 4x3, colores según cantidad, ventanas emergentes
  3. Estadísticas generales: Cálculos y visualización

## Fase 6: Funcionalidades Complementarias (10%)
11. Persistencia (1 día)
  - Serialización en DATOS.ser
  - Recuperación y grabación con confirmación
12. Mini Juego (1 día)
  - Implementa algo simple (puede usar ChatGPT)
  - Uso de mouse/teclado
13. Información de Autores (medio día)
  - Ventana con datos completos y fotos
14. Modo Oscuro (medio día)
  - Implementa cambio de tema en todas las ventanas

## Fase 7: Documentación y Entrega (tiempo paralelo)
15. Video de YouTube (1 día)
  - Máximo 2 minutos
  - Mostrar funcionalidades principales
  - Ambos integrantes visibles
16. Documentación PDF (1 día)
  - Carátula con fotos
  - Diagrama UML
  - Link del video
17. Testing y JAR (1 día)
  - Pruebas exhaustivas
  - Generación del JAR sin datos precargados
  - Verificación de funcionamiento
