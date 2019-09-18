CONVERTOR API is spring boot application which expose 3 endpoint and being used to get the current time, get the amount based on target currency, validate the country code based on vatnumber.

1) First import the maven project.
2) Update the project, maven clean and install
   2.1) Since i am using lombak libary, 
        For eclipse user: please add in eclipse folder and add the line in eclipse.ini file
        -javaagent:/Users/neel/Documents/lib/lombok-1.16.10.jar
        For reference please find the eclipse.ini file:
        
 /*
        -startup
../Eclipse/plugins/org.eclipse.equinox.launcher_1.5.300.v20190213-1655.jar
--launcher.library
/Users/neel/.p2/pool/plugins/org.eclipse.equinox.launcher.cocoa.macosx.x86_64_1.1.1000.v20190125-2016
-product
org.eclipse.epp.package.java.product
-showsplash
org.eclipse.epp.package.common
--launcher.defaultAction
openFile
--launcher.appendVmargs
-vm
/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/bin
-vmargs
-Dosgi.requiredJavaVersion=1.8
-Dosgi.instance.area.default=@user.home/eclipse-workspace
-XX:+UseG1GC
-XX:+UseStringDeduplication
--add-modules=ALL-SYSTEM
-XstartOnFirstThread
-Dorg.eclipse.swt.internal.carbon.smallFonts
-Dosgi.requiredJavaVersion=1.8
-javaagent:/Users/neel/Documents/lib/lombok-1.16.10.jar
-Dosgi.dataAreaRequiresExplicitInit=true
-Xms256m
-Xmx1024m
--add-modules=ALL-SYSTEM
-Xdock:icon=../Resources/Eclipse.icns
-XstartOnFirstThread
-Dorg.eclipse.swt.internal.carbon.smallFonts
-Declipse.p2.max.threads=10
-Doomph.update.url=http://download.eclipse.org/oomph/updates/milestone/latest
-Doomph.redirection.index.redirection=index:/->http://git.eclipse.org/c/oomph/org.eclipse.oomph.git/plain/setups/

        
 */
 
 For Intellij users: 
 Add the lombak libary directly in IDE.
        
3) To deploy the application:
  3.1) by docker file:
     3.1.1) Build: docker build --file=DockerFile --tag=convertor-service:latest --rm=true .
     3.1.2) Run: docker run --name=convertor-service --publish=8081:8081 convertor-service:latest
  3.2) by docker compose file:
     3.2.1) docker-compose up

4) Hit the swagger url
API exposed on 8081 port number, below is the swagger ui url for this API:
http://localhost:8081/swagger-ui.html


