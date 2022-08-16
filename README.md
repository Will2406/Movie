# Movie


1. ¿En qué consiste el principio de responsabilidad única? 

El principio nos dice que un objeto debe realizar una sola cosa, su proposito es evitar acomplamiento de metodos en una sola clase,  eso mejorara la mantenibilidad y escalamiento de la misma. 

2. ¿Qué características tiene, según su opinión, un “buen” código o código limpio?

Pues en mi opinion un buen codigo es legible, se puede entender lo que realiza sin ir mas alla de revisando parte por parte, tambien creo que respeta los principios de SOLID, ya que ayuda a que exista bajo acomplamiento entre clases y alta cohesion de las mismas 

3. Detalla cómo harías todo aquello que no hayas llegado a completar

El apartado de filtro por idioma y año de lanzamiento lo realizaria con chips de material design y con un recyclerview donde su layoutmanager sea un grid especificando el nro de columnas .

Acerca de la funcionabilidad de “cache” lo realizaria guardando el instance en el viewmodel asi como lo indica en su documentacion oficial ([https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate](https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate)) 

Para las pruebas unitarias utilizaria mockK ya que esta optimizado para kotlin y para el uso de coroutines
