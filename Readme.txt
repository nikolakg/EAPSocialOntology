Test

* In tiles.xml files we have all pages referred in MVC 
** An MVC controller listens to a URL webappName/xxx
** returns a view (with a model) to the dispatcher
** our dispatcher servlet (defined in web.xml) is using Tiles
** in tiles.xml files (parsed during Tomcat startup) there is a mapping between view and actual page