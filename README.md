# RESTheridApp (Servicio Web)
Servicio web para el seguimiento de heridas en consultas de enfermería.

## Organización

### Autor del proyecto

- Antonio Ruiz Rondán.

### Herramientas-Tecnologías utilizadas

- Java SE Development Kit 8
- Spring Framework 5.0.4
- MySQL Community Server 5.7.21
- Apache Maven 3.5.3
- Apache Tomcat 8.0.50
- Spring Tool Suite 3.9.2
- JSON Web Token
- iText

### Diagrama ER

![Diagrama ER](https://github.com/toninoes/tfg-heridas-rest-servicio-web-spring/blob/master/src/main/resources/static/images/eer.png)

## Manual de instalación y explotación
Las instrucciones de instalación y explotación del sistema se detallan a continuación.


### Requisitos previos
Los requerimientos que el sistema debe tener para el correcto funcionamiento. Entre paréntesis las versiones sobre las que se ha trabajado:

- S.O.: Ubuntu Server (versión 16.04 LTS - 64 bits)
- Lenguajes: Java (v. 8)
- Java SE Development Kit 8
- Apache Tomcat 8 (v. 8.0.32)
- MySQL Community Server 5 (v. 5.7.21)
- Apache Maven 3 (v. 3.3.9)

Para tenerlo todo instalado en Ubuntu, simplemente teclear:

```sh
sudo apt-get install openjdk-8-jdk openjdk-8-doc openjdk-8-jre
sudo apt-get install git mysql-server tomcat8 tomcat8-admin maven
```

### Procedimientos de instalación
En primer lugar debemos obtener el código fuente del proyecto, lo haremos valiéndonos de Git y lo descargamos desde GitHub, lugar elegido para alojar los repositorios.

Seguidamente habrá que configurar el fichero **application.properties** para configurar los parámetros necesarios del servicio de envío de emails, necesario para activar a los usuarios recién registrados o para gestionar el olvido de contraseñas por parte
de estos.

```sh
cd /tmp
git clone https://github.com/toninoes/tfg-heridas-rest-servicio-web-spring.git
cd tfg-heridas-rest-servicio-web-spring
sudo nano src/main/resources/application.properties
```

Necesitamos editar algunas líneas de ese fichero, son las siguientes:

```
# =====================================
# = CONFIGURACION DE CORREO (GMAIL)
# =====================================
spring.mail.username=USERNAME@gmail.com
spring.mail.password=PASSWORD
# =====================================
# CONFIGURACION DE IMPLANTACION
# =====================================
protocolo=http
dominio=localhost
puerto=8080
servicio=restheridapp
```

En cuanto a la configuración del correo decir que es evidente que deberemos disponer de una cuenta de correo para el sistema ( en principio está preconfigurado para una de Gmail) y simplemente debemos indicar el USERNAME y PASSWORD de dicha cuenta. En la configuración de la implantación debemos indicar muy bien:

- protocolo: indicar si es http o https.
- dominio: IP o nombre de HOST donde se encuentra alojado el servicio.
- puerto: número de puerto donde tenemos configurada la escucha del servicio.
- servicio: nombre que recibe el servicio dentro de Tomcat. Si se siguen las instrucciones siguientes será **restheridapp**.

Podría darse el caso de no necesitarse configurar el puerto y/o el servicio, en tal caso no borrar dichas líneas, sino que se dejan así:

```
puerto=
servicio=
```

Una vez clonado el repositorio, y configurado el fichero application.properties, para la correcta instalación y despliegue de la aplicación se necesitará ejecutar el script en bash (RestHeridApp.sh), éste contiene a su vez una llamada a otro fichero SQL (RestHeridApp.sql). Por tanto tecleamos en la terminal el siguiente comando (dentro de /tmp/tfg-heridas-rest-servicio-web-spring):

```
sudo bash RestHeridApp.sh
```

### Procedimientos de operación y nivel de servicio
Es preciso asegurarnos de tener correctamente instalado y configurado nuestro gestor de base de datos MySQL, tal y como aparece en el fichero **application.properties**, debe de haber un usuario llamado **RESTheridApp** cuya contraseña sea **RESTheridApp**.

Obviamente esto debe modificarse en un entorno de producción por los problemas de seguridad que plantearía dejarlo de esta manera, para ello habría que cambiar dicha configuración, las siguientes 2 líneas:

```sh
# ===============================
# = CONFIGURACION DE BBDD
# ===============================
spring.datasource.username=USUARIO_NUEVO
spring.datasource.password=CLAVE_NUEVA
```

Se debe otorgar permisos al usuario **USUARIO_NUEVO** sobre la base de datos: **restheridapp**. Esta asignación de privilegios se consigue con la siguiente orden:

```sh
GRANT ALL ON `restheridapp`.* TO 'USUARIO_NUEVO'@'localhost' IDENTIFIED BY 'CLAVE_NUEVA';
```

### Pruebas de implantación
Tras su ejecución y si todo ha ido bien, la aplicación se encontrará correctamente instalada. Podremos ejecutar ahora la [App Android](https://github.com/toninoes/tfg-heridas-rest-cliente-android "App Android") o utilizar un cliente rest como **Postman**.

A partir de ahí podremos acceder con nuestro usuario de administración creado por defecto **admin@user.es** y cuya contraseña es **admin**. Posteriormente podrá cambiarse su contraseña.

Vemos cómo se carga el panel de administración de la aplicación para el usuario admin@user.es que tiene rol de administrador:

![InicioApp](https://github.com/toninoes/tfg-heridas-rest-servicio-web-spring/blob/master/src/main/resources/static/images/inicioapp.png)
