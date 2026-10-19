# Filmoteca


		

			Práctica I - ADA


 	Un programa JAXB que permita al usuario realizar operaciones CRUD sobre un documento XML de cuatro capas, creado por nosotros mismos, de una filmoteca. 
	Todo el proyecto está en un repositorio de GitHub cuya arquitectura se explicará más adelante. Las tareas se reparten sistemáticamente aunque en la práctica no se hayan seguido a rajatabla; Héctor se encarga sobre todo del backend, la rama modelo - gestión de la lógica de datos y de su lectura; Ulisses se encarga del frontend, branch vista, y de la documentación y explicación del código. El controlador lo trabaja sobre todo Héctor pero Ulisses también tendrá participación.

Un programa JAXB que permite al usuario hacer operaciones CRUD sobre un documento XML de cuatro niveles, creado por nosotros, de una filmoteca. Todo el proyecto está en un repositorio de GitHub y su arquitectura se explica más adelante. Las tareas se reparten de forma sistemática, aunque en la práctica no se hayan seguido a rajatabla. Héctor se encarga sobre todo del backend, la rama modelo: la lógica de datos y su lectura. Ulisses se encarga del frontend, la rama vista, y de la documentación y explicación del código. El controlador lo trabaja sobre todo Héctor, pero Ulisses también participa.
Documento XML
Una filmoteca guarda dos cosas distintas: películas y libros. Cada una tiene sus propios datos y no se mezclan. El documento tiene cuatro niveles. La raíz es filmoteca. Dentro hay dos contenedores, peliculas y libros con los campos titulo, categoria y los propios de cada tipo - duración y director para pelicula; numero de paginas y escritor para libro. Tomamos tres decisiones y todas tienen su razón: id como atributo, no elemento - de esa forma es realmente identificativo y no descriptivo. Lo usamos para el CRUD. Segunda: dos contenedores separados, evitádonse campos vacíos y código feo. Esta decisión condiciona todo el proyecto, porque cada operación existe dos veces, una para libros y otra para películas. Tercera: la duración lleva atributo. El elemento duracion guarda el número, 159, y un atributo tipo con el valor minutos. Son dos datos en un solo elemento. Es lo más delicado del documento y por eso tiene su propia clase para JAXB.
Arquitectura y repositorio
El programa sigue el patrón MVC y cada capa vive en su propio paquete. La vista es la interfaz con la interactúa el usuario: no toca el XML. Es la vista del console. Entrada y salida. El controlador gestiona las peticiones de la vista; intermediario entre vista y modelo, que es la capa que gestiona la lógica de los datos y acceso a ellos y operaciones con ellos. El paquete Clases guarda los objetos de datos y sus equivalentes con anotaciones JAXB.
Ramas del proyecto - modelo (H), vista(U), controlador(H/U), develop(H/U),  main(U/H): en un principio se trabajaban por separado pero se hace merge de modelo a controlador primero, luego la verizon final de cada rama se sube a develop, y por último todo a la rama main.
Las clases, una a una
Paquete Clases. Libro y Pelicula son objetos de datos simples, sin anotaciones, y son lo que usa el resto del programa. Pelicula guarda la duración en dos campos, duracionValor y duracionTipo. Filmoteca es un contenedor con la lista de películas y la de libros, y es lo que devuelve la lectura al controlador. FilmotecaXml es la raíz del documento y, con la anotación de envoltorio, genera el nivel intermedio de peliculas y libros. LibroXml y PeliculaXml son copias de las clases de datos con las anotaciones de JAXB: el id como atributo, los campos como elementos y el orden fijado. Se construyen a partir de un Libro o una Pelicula y tienen un método para volver a convertirse. DuracionXml mapea el elemento de duración: el número como contenido y el tipo como atributo.
Separamos los objetos de datos de los objetos con anotaciones a propósito. El programa trabaja con clases limpias y solo el modelo conoce JAXB. Si mañana cambia el formato de almacenamiento, se toca una capa y nada más.
Paquete Modelo. RepositorioXML es la única clase que toca el fichero. Su método cargar lee el XML y lo convierte en objetos. Su método guardar hace el camino inverso y escribe el XML. LecturaLibros y LecturaPeliculas leen el documento y devuelven la lista de objetos. ModificarLibro y ModificarPelicula guardan un elemento: si ya existe uno con ese id lo sustituyen y si no lo añaden al final, así que cubren el alta y la modificación. BorrarRegistro elimina un elemento por su id dentro del contenedor indicado. Todas guardan el motivo del fallo en errorMessage y devuelven false o una lista vacía, para que el controlador se lo enseñe al usuario sin que el programa se caiga.
Paquete Vista. EntradaUsuario lee del teclado con un único Scanner para todo el programa. Valida que los textos no estén vacíos y que los números estén dentro de un rango, y permite dejar un valor como está pulsando Enter al modificar. ConsoleView es el menú principal y los mensajes generales, y crea las dos vistas hijas compartiendo la misma entrada. VistaLibros y VistaPeliculas son el submenú, el listado en tabla y los formularios de alta y modificación. Devuelven lo que el usuario escribió y qué hacer con ello lo decide el controlador.
Paquete Controlador. AppController lleva el bucle del menú principal y de los submenús. Calcula el siguiente id libre, que es el máximo existente más uno, y comprueba que el elemento existe antes de modificarlo o borrarlo. Lectura, Modificar y Borrar son los intermediarios entre el controlador principal y el modelo. Devuelven true o false y exponen el mensaje de error.
Proceso de desarrollo
Primero, el diseño del XML. Lo acordamos antes de escribir una línea de código, porque todo lo demás depende de él. Fue lo único que hicimos entre los dos desde el principio.Puse libros y pelis que me gustaban la vrd. 
Segundo, trabajo en paralelo por ramas. Héctor empezó el modelo y Ulisses la vista. Para que la vista avanzara sin esperar al modelo, solo habla con objetos Libro y Pelicula, nunca con el XML.
Tercero, la integración en develop: se juntaron las ramas y aquí hubo diversos problemas - me di cuenta de que mi código tenía errores tontísimos por todo lado, y Hector igualmente. Nombres de paquetes, métodos con letra myúscula, javadocs mal hecho.
Cuarto, las librerías de JAXB. Al compilar todo junto, las anotaciones no se encontraban. Desde Java 11, JAXB ya no viene incluido en el JDK y hay que añadirlo como dependencia en el pom.xml. Además existen dos familias de librerías incompatibles entre sí, javax.xml.bind y jakarta.xml.bind. Nuestro código usa javax, así que dejamos una sola dependencia, jaxb-runtime 2.3.9, y eliminamos las de jakarta que se habían mezclado en la carpeta lib. ESo fue más complicado de lo que debería para mí. 
Se gestionó muy mal el tiempo, e ya con retraso en la entrega se dió cuenta de que el programa realmente no implementa JAXB sino que sigue usando DOM. Lamentablemente.
	Se hicieron las pruebas y más correcciones, se crea un main en condiciones, y se acaba la práctica.
Reparto de tareas y justificación
El reparto no se siguió a rajatabla. Héctor, además del modelo, dejó subido el controlador y arregló el XML. Ulisses, además de la vista, se ocupó de la integración en develop, del Main, del pom.xml y de la limpieza del repositorio.
Dificultades y decisiones destacables
Dos tipos de elemento en un solo programa. Tener libros y películas duplica cada operación. Lo resolvimos manteniendo dos clases simétricas en lugar de una genérica, que habría sido más difícil de entender y de documentar. Lo simple se entiende y lo que se entiende se puede mantener.

Ramas y commits pequeños. Trabajar por ramas evitó pisarnos, pero obligó a coordinar bien las integraciones. Los commits pequeños y con mensaje claro son lo que nos permite justificar el reparto de este documento.
Por cuestiones de organización no nos fue posible cumplir con todos los objetivos propuestos.

	
 
	
	
