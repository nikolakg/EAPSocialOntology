Test

* In tiles.xml files we have all pages referred in MVC 
** An MVC controller listens to a URL webappName/xxx
** returns a view (with a model) to the dispatcher
** our dispatcher servlet (defined in web.xml) is using Tiles
** in tiles.xml files (parsed during Tomcat startup) there is a mapping between view and actual page

EC2 account 
DNS: ec2-54-186-135-132.us-west-2.compute.amazonaws.com

$> sudo yum install tomcat7 tomcat7-webapps tomcat7-*
$> sudo service tomcat7 start

/usr/share/tomcat7

http://ec2-54-186-135-132.us-west-2.compute.amazonaws.com:8080/eap