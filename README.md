# Spring Load Time Weaving Clickstart

This is a "ClickStart" that gets you going with a "Spring & AspectJ Load-time weaving in tomcat7" _seed_ project starting point. You can launch it here:

<a href="https://grandcentral.cloudbees.com/?CB_clickstart=https://raw.github.com/CloudBees-community/spring-load-time-weaving-clickstart/master/clickstart.json"><img src="https://d3ko533tu1ozfq.cloudfront.net/clickstart/deployInstantly.png"/></a>

This will setup a continuous deployment pipeline - a CloudBees Git repository, a Jenkins build compiling and running the test suite (on each commit).
Should the build succeed, this seed app is deployed on a Tomcat 7 container.

You will see in this _seed_

* How to add a jar to the classpath of your CloudBees container (Tomcat7 - `$TOMCAT_HOME/lib`, Glassfish3, Glassfish4) via the `$WAR_HOME/META-INF/lib` folder of your war file
* How to use [9.8.4 Load-time weaving with AspectJ in the Spring Framework](http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/aop.html#aop-aj-ltw) on a Tomcat7 CloudBees container (this would also apply to CloudBees' Glassfish 3 and 4 containers)


# How to add a jar to a CloudBees container with Maven

Sample with Maven and `spring-instrument-tomcat-3.2.3.RELEASE.jar`

```xml
<project>
    <build>
        <plugins>
          <plugin>
                <!--
                (1) Copy spring-instrument-tomcat-3.2.3.RELEASE.jar to 'target/tomcat-extra-lib' folder during 'prepare-package' phase
                -->
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>2.8</version>
                <configuration>
                    <artifactItems>
                        <artifactItem>
                            <groupId>org.springframework.</groupId>
                            <artifactId>spring-instrument-tomcat</artifactId>
                            <version>3.2.3.RELEASE</version>
                            <type>jar</type>
                            <outputDirectory>${project.build.directory}/tomcat-extra-lib
                            </outputDirectory>
                        </artifactItem>
                    </artifactItems>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>copy</goal>
                        </goals>
                        <phase>prepare-package</phase>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <!--
                (1) Copy the content of the folder 'target/tomcat-extra-lib' in '$WAR_HOME/META-INF/lib'
                -->
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.4</version>
                <configuration>
                    <webResources>
                        <resource>
                            <directory>${project.build.directory}/tomcat-extra-lib</directory>
                            <targetPath>META-INF/lib</targetPath>
                        </resource>
                    </webResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

# References

* [9.8.4 Load-time weaving with AspectJ in the Spring Framework](http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/aop.html#aop-aj-ltw)
* [CloudBees Tomcat7 Clickstack](http://developer.cloudbees.com/bin/view/RUN/Tomcat7)