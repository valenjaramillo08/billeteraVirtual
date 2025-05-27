# 💳 Billetera Virtual

**Billetera Virtual** es una aplicación desarrollada en Java que simula una billetera digital, permitiendo a los usuarios realizar operaciones financieras básicas como consultar saldo, realizar transferencias y gestionar su cuenta. Este proyecto fue desarrollado como parte de una asignatura en la Universidad del Quindío.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje de programación:** Java
- **Entorno de desarrollo:** IntelliJ IDEA
- **Versión de Java:** 17 o superior
- **Sistema de construcción:** Gradle
- **Control de versiones:** Git

## 📁 Estructura del Proyecto

El proyecto sigue una estructura de paquetes organizada de la siguiente manera:

```
billeteraVirtual/
├── co.edu.uniquindio.billeteraVirtual/
│   ├── app/
│   │   └── Main.java
│   ├── model/
│   │   ├── Usuario.java
│   │   └── Transaccion.java
│   ├── service/
│   │   └── BilleteraService.java
│   └── util/
│       └── Utilidades.java
├── .gitignore
├── build.gradle
└── README.md
```

- `app/`: Contiene la clase principal que inicia la aplicación.
- `model/`: Define las clases de modelo, como `Usuario` y `Transaccion`.
- `service/`: Implementa la lógica de negocio de la billetera.
- `util/`: Incluye clases utilitarias para funciones auxiliares.

## 🚀 Instalación y Ejecución

Sigue estos pasos para clonar y ejecutar el proyecto en tu máquina local:

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/valenjaramillo08/billeteraVirtual.git
   cd billeteraVirtual
   ```

2. **Importar el proyecto en IntelliJ IDEA:**

   - Abre IntelliJ IDEA.
   - Selecciona "Open" y elige la carpeta del proyecto clonado.
   - IntelliJ detectará automáticamente el archivo `build.gradle` y configurará el proyecto.

3. **Compilar y ejecutar la aplicación:**

   - En el panel lateral, navega hasta `Main.java` ubicado en `co.edu.uniquindio.billeteraVirtual.app`.
   - Haz clic derecho sobre `Main.java` y selecciona "Run 'Main.main()'".

## 📚 Funcionalidades

- **Registro de usuarios:** Permite crear nuevos usuarios con información básica.
- **Consulta de saldo:** Muestra el saldo actual del usuario.
- **Transferencias:** Realiza transferencias entre usuarios registrados.
- **Historial de transacciones:** Muestra un registro de las transacciones realizadas.

## 🧑‍💻 Contribuciones

¡Las contribuciones son bienvenidas! Si deseas mejorar el proyecto, sigue estos pasos:

1. **Fork del repositorio.**
2. **Crea una nueva rama:**

   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```

3. **Realiza tus cambios y haz commit:**

   ```bash
   git commit -m "Agrega nueva funcionalidad"
   ```

4. **Haz push a tu rama:**

   ```bash
   git push origin feature/nueva-funcionalidad
   ```

5. **Abre un Pull Request.**

## 📄 Licencia

Este proyecto se encuentra bajo la licencia [MIT](https://opensource.org/licenses/MIT).