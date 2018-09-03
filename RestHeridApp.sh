#!/bin/bash

printf "[INFO] Creando base de datos para RESTheridApp...\n"
printf "[MySQL] Introduzca su password de root de MySQL\n"
if mysql -u root -p < RestHeridApp.sql; then
  printf "[OK] Base de datos creada correctamente\n"
else
  printf "[ERROR] No se ha podido crear la base de datos\n"
fi

printf "[INFO] Creando directorio de imágenes.\n"
if mkdir /var/lib/tomcat8/RESTheridAppIMG -p; then
  chown -R tomcat8:tomcat8 /var/lib/tomcat8/RESTheridAppIMG
  printf "[RestHeridApp] Directorio de imágenes creado correctamente.\n"
fi

printf "[INFO] Copiando configuración usuarios de Tomcat.\n"
if cp tomcat-users.xml /var/lib/tomcat8/conf; then
  chown -R root:tomcat8 /var/lib/tomcat8/conf/tomcat-users.xml
  printf "[Tomcat] Fichero configuración copiado correctamente.\n"
fi

printf "[INFO] Creando paquete restheridapp.war. Espere......\n"
if mvn clean package; then
  printf "[OK] Creado paquete.\n"
  cp target/restheridapp.war /var/lib/tomcat8/webapps
  chown -R tomcat8:tomcat8 /var/lib/tomcat8/webapps/restheridapp.war
else
  printf "[ERROR] Error al crear el paquete.\n"
fi

printf "[INFO] Reiniciando Tomcat. Espere......\n"
if service tomcat8 restart; then
  sleep 25
  printf "[OK] Tomcat reiniciado\n"
  printf "Vaya a http://localhost:8080/manager/html para administrar.\n"
  printf "USUARIO: tomcat  PASSWORD: tomcat\n"
  printf "[INFO] Servicio en http://localhost:8080/restheridapp\n"
else
  printf "[ERROR] Ha habido un problema al reiniciar Tomcat.\n"
fi
