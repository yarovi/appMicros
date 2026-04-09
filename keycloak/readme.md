# Configuración de Cliente Confidencial en Keycloak

Este documento describe los pasos necesarios para crear un cliente **confidencial** en Keycloak y obtener sus credenciales (`client_id` y `client_secret`), necesarias para la autenticación máquina a máquina (flujo `client_credentials`) o para proteger un API Gateway.

## Requisitos previos

- Keycloak debe estar en ejecución y accesible (ej. `http://localhost:9991`).
- Debes haber iniciado sesión en la **consola de administración** como usuario con permisos (ej. `admin` / `admin`).
- Debes tener creado o seleccionado el **Realm** donde deseas configurar el cliente.

---

## Pasos para crear y configurar el cliente

### 1. Acceder a la sección de Clientes

Desde la consola de administración:

- En el menú lateral izquierdo, despliega la sección **Manage**.
- Haz clic en **Clients**.

![Clients menu](https://i.imgur.com/placeholder.png) *(Reemplazar con captura real si se desea)*

### 2. Crear un nuevo cliente

- Haz clic en el botón **Create client** (parte superior derecha).

#### Pantalla "General Settings"

- **Client type**: Selecciona `OpenID Connect`.
- **Client ID**: Ingresa un identificador único para tu cliente. Ejemplo: `api-gateway`.
- **Name** (opcional): Un nombre descriptivo. Ejemplo: `API Gateway Service`.
- **Description** (opcional): Una breve descripción del propósito.

Haz clic en **Next**.

#### Pantalla "Capability config"

- **Client authentication**: **Actívalo (ON)**. Esto convertirá al cliente en **confidencial** y permitirá usar un `client_secret`.
- **Authorization**: Déjalo como `Off` a menos que necesites políticas de autorización avanzadas.
- **Authentication flow**: Selecciona los flujos que necesites. Para un API Gateway típico:
    - ✅ `Standard flow` (Authorization Code)
    - ✅ `Direct access grants` (Resource Owner Password)
    - ✅ `Service accounts roles` (Client Credentials)
    - ❌ `Implicit flow` (desaconsejado para OAuth2 moderno)

Haz clic en **Next**.

#### Pantalla "Login settings"

- **Root URL**, **Home URL**, **Valid redirect URIs**, **Valid post logout redirect URIs**: Configura según los requisitos de tu aplicación. Para un Gateway en desarrollo local:
    - **Valid redirect URIs**: `http://localhost:8080/*`
    - **Valid post logout redirect URIs**: `http://localhost:8080/*`
    - **Web origins**: `http://localhost:8080`

Haz clic en **Save**. Se mostrará la página de detalles del cliente.

### 3. Obtener las credenciales del cliente

Una vez creado el cliente, para ver y copiar el `client_secret`:

1. Dentro de la página del cliente, selecciona la pestaña **Credentials**.

![Credentials tab](https://i.imgur.com/placeholder.png)

2. En la sección **Client Authenticator**, verifica que sea `Client Id and Secret`.

3. En el campo **Client Secret**, encontrarás un texto enmascarado. Puedes:
    - Hacer clic en el botón **Copy to clipboard** (ícono de dos hojas superpuestas) para copiarlo.
    - Hacer clic en **Regenerate** para generar un nuevo secreto (invalida el anterior).

> **Nota**: El `client_secret` debe ser tratado como una contraseña. Guárdalo en un lugar seguro (variable de entorno, gestor de secretos, etc.). No lo incluyas en código fuente.

### 4. Verificar el client_id

El `client_id` es el valor que asignaste en el paso 2 (ej. `api-gateway`). Puedes confirmarlo en la tabla de clientes o en la pestaña **Settings** del cliente.

---

## Configuración adicional (opcional pero recomendada)

### Asignar roles a la cuenta de servicio (Service Account)

Si tu cliente usará el flujo `client_credentials` (autenticación máquina a máquina), puedes asignarle roles directamente:

1. Dentro del cliente, selecciona la pestaña **Service account roles**.
2. Haz clic en **Assign role**.
3. Elige el rol o roles que necesite tu servicio. Por ejemplo:
    - `view-users` para consultar información de usuarios.
    - `manage-users` para gestionar usuarios.
    - Roles específicos de tu realm o cliente.

### Configurar validez del token (opcional)

En la pestaña **Advanced** del cliente puedes ajustar:
- `Access Token Lifespan`
- `Client Session Idle`
- `Client Session Max`

---

## Ejemplo de uso: Obtener token con client_credentials

Una vez configurado el cliente confidencial, puedes obtener un token usando el flujo `client_credentials`:

### Petición HTTP (curl)

```bash
curl -X POST http://localhost:9991/realms/tu-realm/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=api-gateway" \
  -d "client_secret=TU_CLIENT_SECRET" \
  -d "grant_type=client_credentials"