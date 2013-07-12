# Spring Load Time Weaving Clickstart

Demo app to show:

* How to add a jar to a CloudBees container (Tomcat7, Glassfish3, Glassfish4) classpath via the `$WAR_HOME/META-INF/lib` folder
* How to use [9.8.4 Load-time weaving with AspectJ in the Spring Framework](http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/aop.html#aop-aj-ltw) on a Tomcat7 CloudBees container (this would also apply to CloudBees' Glassfish 3 and 4 containers)

To add a jar to the classpath of your CloudBees container (directly under `$TOMCAT_HOME/lib`, ...), you must add it to the `$WAR_HOME/META-INF/lib` folder of your WAR file.

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