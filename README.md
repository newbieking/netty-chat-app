This is a chat application developed based on the Netty framework. The project provides a simple chat application where
users can conduct real-time communication.

### core feature

- Implement real-time chat functionality based on the Netty framework.
- Support multiple users to chat online simultaneously.
- Provide a simple and user-friendly chat interface.

### techs

- Java
- Netty framework

### core deps

```xml

<dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-all</artifactId>
    <version>4.1.86.Final</version>
</dependency>
```

### compiling

#### for client compiling

1. setup configuration
   ```xml
   
   <build>
       <finalName>netty-chat-app-cli</finalName>
       <plugins>
           <plugin>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-shade-plugin</artifactId>
               <version>3.6.0</version>
               <executions>
                   <execution>
                       <phase>package</phase>
                       <goals>
                           <goal>shade</goal>
                       </goals>
                       <configuration>
                           <transformers>
                               <transform
                                       implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                   <mainClass>com.newbieking.ChatClient</mainClass>
                               </transform>
                           </transformers>
                       </configuration>
                   </execution>
               </executions>
           </plugin>
   
       </plugins>
   </build>
   ```
2. packaging
   ```cmd
   mvn package
   ```

#### for server compiling

1. setup configuration
   ```xml
   
   <build>
       <finalName>netty-chat-app-srv</finalName>
       <plugins>
           <plugin>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-shade-plugin</artifactId>
               <version>3.6.0</version>
               <executions>
                   <execution>
                       <phase>package</phase>
                       <goals>
                           <goal>shade</goal>
                       </goals>
                       <configuration>
                           <transformers>
                               <transform
                                       implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                   <mainClass>com.newbieking.ChatServer</mainClass>
                               </transform>
                           </transformers>
                       </configuration>
                   </execution>
               </executions>
           </plugin>
   
       </plugins>
   </build>
   ```
2. packaging
   ```bash
   mvn package
   ```

### usage

1. start server first

    ```bash
    java -jar netty-chat-app-srv.jar
    ```

2. start client (you can start more than one instance)

    ```bash
    java -jar netty-chat-app-cli.jar
    ```

### license

 MIT

