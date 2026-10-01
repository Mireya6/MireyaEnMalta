Estudio comparativo de tecnologías del desarrollo móvil.

Se han seleccionado las tecnologías de Android Nativo, Flutter y PWA (Progressive Web App)  porque representan los tres puntos más distantes y representativos entre las diferentes tecnologías.

1) Lenguaje y Herramientas necesarias.
- Android Nativo: Utiliza Kotlin (oficial de Google) en Android Studio.
- Flutter utiliza Dart en VS Code o Android Studio.
- PWA utiliza estándares web (HTML, CSS, Javascript) en VS Code.

2) Plataformas que soporta.
- Android Nativo solo funciona en Android (para IOS se necesitaría hacer otra app con Swift).
- Flutter cubre Android e IOS con una sola base de código.
- PWA es la más versátil: funciona en Android, IOS y PC desde el navegador sin instalar nada.

3) Rendimiento y acceso al hardware.
- Android Nativo ofrece el rendimiento máximo y acceso total a todos los sensores/GPS.
- Flutter ofrece un rendimiento alto, excelente para el 90% de la apps.
- PWA cuenta con el rendimiento más bajo y acceso muy limitado al hardware por seguridad del navegador.

4) Coste de desarrollo y mantenimiento.
- Android Nativo es el más costoso, porque obliga a mantener dos proyectos separados si se quiere usar IOS.
- Flutter está entre un 50% y un 70% más barato que el desarrollo nativo, al compartir código para IOS y Android.
- PWA es la opción más económica y rápida de mantener, puesto que reutiliza desarrollo web y se actualiza al instante en el servidor.

5) Uso real.
Para el mercado comercial real, Flutter sería la tecnología que mejor encaja con esta idea, ya que cumple con el requisito indispensable de llegar a todos los usuarios, tanto de iOs como de Android.
Sin embargo, quizás este proyecto se desarrole con la versión de Android en Android Nativo con Kotlin porque es la opción idónea desde el punto de vista académico, puesto que facilita el aprendizaje de conceptos como la gestiópn del ciclo de vida de las aplicaciones, el manejo de la persistencia de datos...