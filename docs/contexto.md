# Caso de Estudio: Transformación Digital de “VetDistribuidora SPA”

## Introducción

VetDistribuidora SPA es una empresa chilena en crecimiento dedicada a la **venta
al por mayor y menor de alimentos para mascotas**, además de ofrecer **servicios
veterinarios integrales**. La empresa inició sus operaciones en Santiago,
enfocándose en la distribución de alimentos importados para perros y gatos,
logrando rápidamente posicionarse en el mercado gracias a precios competitivos y
una red sólida de proveedores internacionales.

Con el tiempo, VetDistribuidora SPA expandió su modelo de negocio incorporando
**atención veterinaria**, venta directa a clientes finales mediante una
plataforma web, y servicio de **despacho a domicilio**. Actualmente cuenta con
sucursales en Santiago, Concepción y Valparaíso, además de una red de
distribución a tiendas pequeñas (B2B).

Sin embargo, el crecimiento acelerado ha dejado en evidencia múltiples problemas
en su sistema monolítico actual:

- Lentitud en la gestión de pedidos y despachos
- Falta de integración entre inventario, ventas y atención veterinaria
- Dificultades en la gestión de proveedores e importadores
- Problemas de escalabilidad en la plataforma web

Esto ha generado errores operacionales, pérdida de ventas y baja en la
satisfacción del cliente.

## Objetivo del Caso

El objetivo es diseñar e implementar una solución tecnológica moderna que
permita:

- Escalar el sistema ante el crecimiento de la empresa
- Integrar las áreas de ventas, veterinaria, logística y proveedores
- Mejorar la experiencia del cliente final y de los clientes mayoristas
- Optimizar la gestión de inventario y distribución

El proyecto se desarrollará en tres etapas, culminando en una solución robusta
basada en arquitectura moderna (por ejemplo, microservicios).

---

## Acciones del Sistema para VetDistribuidora SPA

### Perfiles del sistema

### **Administrador del Sistema**

- **Gestionar Usuarios:** Crear, editar y eliminar cuentas (empleados,
  veterinarios, clientes mayoristas).
- **Configurar Permisos:** Definir accesos según roles (ventas, veterinaria,
  logística, etc.).
- **Monitorización del Sistema:** Supervisar rendimiento, detectar fallos y
  gestionar alertas.
- **Respaldar y Restaurar Datos:** Manejo de backups del sistema.
- **Gestión de Integraciones:** Administrar conexiones con sistemas externos
  (importadores, facturación electrónica, etc.).

### **Gerente de Sucursal**

- **Gestionar Inventario:** Controlar stock de alimentos, medicamentos y
  productos veterinarios.
- **Gestionar Servicios Veterinarios:** Supervisar agenda de veterinarios y
  atención en sucursal.
- **Generar Reportes:** Ventas, servicios realizados, productos más vendidos.
- **Gestionar Pedidos Mayoristas:** Aprobar pedidos grandes de clientes empresa.
- **Administrar Personal:** Gestión de empleados y veterinarios de la sucursal.

### **Empleado de Ventas**

- **Registrar Ventas:** Ventas en tienda física o telefónicas.
- **Atender Clientes:** Asesorar sobre productos y servicios veterinarios.
- **Consultar Inventario:** Ver disponibilidad en tiempo real (incluyendo otras
  sucursales).
- **Generar Facturas:** Emisión de boletas/facturas electrónicas.
- **Gestionar Pedidos Web:** Apoyar en pedidos realizados online.

### **Veterinario**

- **Gestionar Fichas Clínicas:** Registro de pacientes (mascotas), historial
  médico.
- **Atender Consultas:** Diagnóstico, tratamientos y recetas.
- **Agendar Horas:** Gestión de citas veterinarias.
- **Emitir Recetas:** Asociadas a medicamentos disponibles en inventario.
- **Seguimiento de Pacientes:** Control de tratamientos y evolución.

### **Logística**

- **Gestionar Envíos:** Coordinación de despachos a domicilio y entregas a
  clientes mayoristas.
- **Optimizar Rutas:** Planificación eficiente de entregas.
- **Actualizar Estado de Pedidos:** Desde preparación hasta entrega final.
- **Gestión de Bodega:** Control de entradas y salidas de productos.

### **Compras / Abastecimiento**

- **Gestionar Proveedores:** Registro de distribuidores e importadores.
- **Gestionar Importaciones:** Control de productos importados (costos, tiempos,
  aduanas).
- **Realizar Órdenes de Compra:** Reabastecimiento de productos.
- **Control de Costos:** Análisis de precios y márgenes.
- **Seguimiento de Pedidos a Proveedores:** Estado de importaciones y entregas.

## Acciones de los Clientes vía Web

### **Clientes Minoristas (Personas)**

- Crear cuenta e iniciar sesión
- Navegar por catálogo de alimentos y productos
- Agregar productos al carrito
- Solicitar atención veterinaria (agendar hora)
- Realizar pedidos con despacho a domicilio
- Ver historial de compras
- Recibir recomendaciones según su mascota

### **Clientes Mayoristas (Empresas)**

- Registro como cliente empresa
- Acceso a precios especiales por volumen
- Realizar pedidos grandes
- Seguimiento de pedidos
- Gestión de facturación empresarial

### **Servicios Online**

- **Agendamiento veterinario online**
- **Seguimiento de envíos en tiempo real**
- **Notificaciones (correo/SMS)** sobre pedidos y citas
- **Integración con pagos online**

---

## Problemas del Sistema Actual

- Sistema monolítico difícil de escalar
- Caídas frecuentes en alta demanda
- Información no sincronizada entre módulos
- Procesos manuales en logística e importaciones
- Mala experiencia en la plataforma web

---

## Propuesta General de Solución

- Migración a **arquitectura de microservicios**
- Separación por dominios:
  - Ventas
  - Inventario
  - Veterinaria
  - Logística
  - Proveedores
- Uso de APIs para integración
- Implementación de sistema de colas para pedidos
- Plataforma web escalable (cloud)
- Base de datos distribuida

## Microservicios

WIP

## Alcance

WIP
