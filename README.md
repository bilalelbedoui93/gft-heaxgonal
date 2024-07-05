# PRUEBA TÉCNICA USANDO ARQUITECTURA HEXAGONAL

![Project Logo](static/001_img_project.jpg)

## Descripción: 
  Proyecto Java/SpringBoot multimodulo dockerizado usuando arquitectura hexagonal, specificacion API FIRST y test e2e.
  Se usa una base de datos H2.

## Funcionalidades: 

  - Recuperar el precio de un producto de una tienda del grupo a una fecha dada. Pudiendo tener varios precios disponible para ese instante, usando la prioridad     como Desambiguador.

## Installación

### Herramientas para la instalación del proyecto

 - eclipse (IDE): https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2024-06/R/eclipse-jee-2024-06-R-win32-x86_64.zip&mirror_id=1045
 - java SE 21: https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.zip
 - Maven 3.9.6 : https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/

### Configuracion de los proyectos (DOMINIO y PROYECTO WEB)

- Configurar el jdk en nuestro workspace de ecplise
![Project Logo](static/002_config_jdk_eclipse.jpg)

- Configurar de  maven, y también del parmateros del usuario(configuracion del setting.xml, repositorio local)
  ![Project Logo](static/003_config_mvn.JPG)
  ![Project Logo](static/004_config_mvn_usersettings.JPG)

- Una vez tenemos listo a nuestro workspace, importamos EL DOMINIO que es un proyecto aislado y en el núcleo que contiene nuestra lógica de negocio

  ![Project Logo](static/005_import_domain.JPG)

- Configurar el BUILD (clean install) de nuestra libreria
  ![Project Logo](static/006_config_build_domain.JPG)

- Ahora ya que tenemos ya tenemos al dominio funcional, configuraremos el Proyecto web
  
  ![Project Logo](static/007_import_project.JPG)

- Configurar el BUILD del proyecto web también, pero esta vez DESACTIVANDO LOS TESTS para que no se lance el módulo de testing-e2e
  ![Project Logo](static/008_config_build_project_web.JPG)

- Este deberia ser el resultado  que deberíamos obtener una vez configurado el proyecto el local
  ![Project Logo](static/009_resultado_Build.JPG)

- Ejecutar el proyecto!! Siendo una api springboot, lleva incorporado las libreria de tomcat, con ejecutar el MAIN como cualquier proyecto java es suficiente.
  Se ejecutará sobre el puerto 8082 
  ![Project Logo](static/010-ejecutar_api.JPG)

- Una vez el proyecto en ejecución. Lanzar los tests end-to-end des del modulo de testing.
  EJECUTAR LA CLASE KarateUniTest con JUNIT. En la captura, podemos ver que karate tambien nos permite consultar el informe de los test en el navegador gracias al enlace que vemos.
  ![Project Logo](static/011_tests_karate_corriendo.JPG)

- Para acceder a la BDD en memoria {url_server:puerto}/h2_console
  la configuración de acceso la entraréis el application.properties
  ![Project Logo](static/012_BDD_en_memoria.JPG)

  

  

  
