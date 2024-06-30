# Logging In Springboot :

Spring Boot supports several logging frameworks, each suited for different scenarios and environments. Here's a table summarizing the different types of loggers available in Spring Boot and their suitability for various scenarios:

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Logger:              Description:                                                Suitable Scenarios:
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Logback:             The default logging framework in Spring Boot. Highly configurable and performant.:            General purpose logging, applications requiring advanced logging features like conditional logging.
                  



Log4j2:              A flexible and powerful logging framework with a rich feature set.:        A flexible and powerful logging framework with a rich feature set.
                    



Java Util Logging (JUL):          The default logging framework in the JDK. Simple and easy to use. :        Small applications or when a simple logging setup is sufficient.
         


Commons Logging (JCL):     A thin bridge between different logging frameworks.Not typically used directly.:         Used as an abstraction layer for other logging frameworks.
              


SLF4J :              A facade for various logging frameworks, allowing the actual implementation to be plugged in.:    When you need to decouple logging API from the actual logging implementation.
                    


Syslog:	           A network-based logging protocol used to send log  messages to a remote server.:       Centralized logging in distributed systems, security-sensitive applications requiring remote log storage
                  



------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

General Spring Boot Logging Properties

Root logging level (default: INFO)

         logging.level.root=INFO

Logging level for specific packages

     logging.level.org.springframework.web=DEBUG
     logging.level.com.example=DEBUG

Path to the log file

    logging.file.name=application.log
     logging.file.path=/var/logs/myapp

Log pattern format

    logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
    logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n




Configuration in application.properties

    Logback

     logging.level.root=INFO
     logging.level.org.springframework.web=DEBUG
     logging.file.name=application.log


    Log4j2:

     logging.config=classpath:log4j2.xml
     # Set logging level for Log4j2
     logging.level.com.example=DEBUG


    Syslog :
    logging.level.root=INFO
    logging.file.name=application.log
    logging.syslog.host=localhost
    logging.syslog.port=514

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



1. Logback

     
       import org.slf4j.Logger;
       import org.slf4j.LoggerFactory;
       import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.RestController;

       @RestController
       public class LogbackController {

        private static final Logger logger = LoggerFactory.getLogger(LogbackController.class);

       @GetMapping("/logback")
       public String logbackExample() {
        logger.info("Info log from Logback");
        logger.debug("Debug log from Logback");
        logger.error("Error log from Logback");
        return "Check the logs for Logback messages.";
             }
           }

2. Log4j2

Example Log4j2 configuration file (log4j2.xml):



     import org.apache.logging.log4j.LogManager;
     import org.apache.logging.log4j.Logger;
     import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

       @RestController
       public class Log4j2Controller {

    private static final Logger logger = LogManager.getLogger(Log4j2Controller.class);

    @GetMapping("/log4j2")
    public String log4j2Example() {
        logger.info("Info log from Log4j2");
        logger.debug("Debug log from Log4j2");
        logger.error("Error log from Log4j2");
        return "Check the logs for Log4j2 messages.";
    }
    }


3. Java Util Logging (JUL)

 No specific properties needed, configuration is typically done in code or with a logging.properties file


    import java.util.logging.Logger;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class JULController {
 
    private static final Logger logger = Logger.getLogger(JULController.class.getName());

    @GetMapping("/jul")
    public String julExample() {
        logger.info("Info log from JUL");
        logger.fine("Debug log from JUL");  // Note: JUL 'fine' is equivalent to 'debug'
        logger.severe("Error log from JUL");
        return "Check the logs for JUL messages.";
    }
     } 


4. Syslog (Using Logback configuration for demonstration)


        logging.level.root=INFO
        logging.level.com.example=DEBUG
        logging.file.name=syslog-application.log
        logging.syslog.host=localhost
        logging.syslog.port=514



        <configuration>
       <appender name="SYSLOG" class="ch.qos.logback.classic.net.SyslogAppender">
        <syslogHost>localhost</syslogHost>
        <port>514</port>
        <facility>USER</facility>
        <suffixPattern>[%thread] %-5level %logger{35} - %msg%n</suffixPattern>
       </appender>
        <root level="info">
        <appender-ref ref="SYSLOG" />
        </root>
       <logger name="com.example" level="debug" additivity="false">
        <appender-ref ref="SYSLOG" />
       </logger>
      </configuration>




          import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
       import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RestController;

         @RestController
        public class SyslogController {

         private static final Logger logger = LoggerFactory.getLogger(SyslogController.class);

       @GetMapping("/syslog")
        public String syslogExample() {
        logger.info("Info log from Syslog");
        logger.debug("Debug log from Syslog");
        logger.error("Error log from Syslog");
        return "Check the syslogs for Syslog messages.";
        }
        }

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

   Lombok provides several annotations to simplify logging in Java. Here are the main logging annotations provided by Lombok and examples of how to use them in a Spring Boot controller:

1. @Slf4j


       import lombok.extern.slf4j.Slf4j;
        import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.RestController;

        @RestController
       @Slf4j
       public class LombokSlf4jController {

       @GetMapping("/lombok/slf4j")
       public String slf4jExample() {
        log.info("Info log from SLF4J");
        log.debug("Debug log from SLF4J");
        log.error("Error log from SLF4J");
        return "Check the logs for SLF4J messages.";
       }
        }


2. @Log4j2


        import lombok.extern.log4j.Log4j2;
       import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.RestController;

        @RestController
        @Log4j2
       public class LombokLog4j2Controller {

       @GetMapping("/lombok/log4j2")
       public String log4j2Example() {
        log.info("Info log from Log4j2");
        log.debug("Debug log from Log4j2");
        log.error("Error log from Log4j2");
        return "Check the logs for Log4j2 messages.";
       }
        }



3. @Log


        import lombok.extern.java.Log;
        import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.RestController;

         @RestController
       @Log
         public class LombokJulController {

       @GetMapping("/lombok/jul")
       public String julExample() {
        log.info("Info log from JUL");
        log.fine("Debug log from JUL");  // Note: JUL 'fine' is equivalent to 'debug'
        log.severe("Error log from JUL");
        return "Check the logs for JUL messages.";
       }
        }



4. @CommonsLog


        import lombok.extern.apachecommons.CommonsLog;
        import org.springframework.web.bind.annotation.GetMapping;
       import org.springframework.web.bind.annotation.RestController;

       @RestController
       @CommonsLog
        public class LombokCommonsLogController {

        @GetMapping("/lombok/commons")
       public String commonsLogExample() {
        log.info("Info log from Commons Logging");
        log.debug("Debug log from Commons Logging");
        log.error("Error log from Commons Logging");
        return "Check the logs for Commons Logging messages.";
        }
        }


5. @Log4j



       import lombok.extern.log4j.Log4j;
         import org.springframework.web.bind.annotation.GetMapping;
          import org.springframework.web.bind.annotation.RestController;

       @RestController
        @Log4j
         public class LombokLog4jController {

       @GetMapping("/lombok/log4j")
        public String log4jExample() {
        log.info("Info log from Log4j");
        log.debug("Debug log from Log4j");
        log.error("Error log from Log4j");
        return "Check the logs for Log4j messages.";
        }
        }


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Example of configuring Logback using an XML configuration file (logback-spring.xml). 

 
     <?xml version="1.0" encoding="UTF-8"?>
    <configuration>
    <!-- Define properties for reuse throughout the configuration -->
    <property name="LOG_PATH" value="/var/logs/myapp"/>
    <property name="LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"/>

    <!-- Console appender: Writes logs to the console (standard output) -->
    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <!-- File appender: Writes logs to a file with rolling policy -->
    <appender name="File" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_PATH}/application.log</file>
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- Roll the logs daily and keep 30 days of logs -->
            <fileNamePattern>${LOG_PATH}/application.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
    </appender>

    <!-- Syslog appender: Sends logs to a remote syslog server -->
    <appender name="Syslog" class="ch.qos.logback.classic.net.SyslogAppender">
        <syslogHost>localhost</syslogHost>
        <port>514</port>
        <facility>USER</facility>
        <suffixPattern>${LOG_PATTERN}</suffixPattern>
    </appender>

    <!-- Root logger configuration: Defines the default logging level and appenders -->
    <root level="info">
        <appender-ref ref="Console"/>
        <appender-ref ref="File"/>
        <!-- Uncomment the next line to enable syslog logging -->
        <!-- <appender-ref ref="Syslog"/> -->
    </root>

    <!-- Logger for specific packages: More granular control over logging levels for different packages -->
    <logger name="com.example" level="debug" additivity="false">
        <appender-ref ref="Console"/>
        <appender-ref ref="File"/>
    </logger>

    <!-- Logger for SQL logging: Useful for debugging Hibernate or JDBC -->
    <logger name="org.hibernate.SQL" level="debug" additivity="false">
        <appender-ref ref="Console"/>
        <appender-ref ref="File"/>
    </logger>

    <!-- Logger for Spring Security: Useful for debugging authentication and authorization issues -->
    <logger name="org.springframework.security" level="debug" additivity="false">
        <appender-ref ref="Console"/>
        <appender-ref ref="File"/>
    </logger>
     </configuration>



 Additional Features We Can Add:

Async Appender: Improve performance by logging asynchronously.


    <appender name="Async" class="ch.qos.logback.classic.AsyncAppender">
     <appender-ref ref="File"/>
    </appender>


SMTP Appender: Send log messages via email.

    <appender name="Email" class="ch.qos.logback.classic.net.SMTPAppender">
    <smtpHost>smtp.example.com</smtpHost>
    <to>admin@example.com</to>
    <from>noreply@example.com</from>
    <subject>Application Log - %d{yyyy-MM-dd HH:mm:ss}</subject>
    <encoder>
        <pattern>${LOG_PATTERN}</pattern>
    </encoder>
    </appender>


Filters: Filter log messages based on conditions.

     <appender name="File" class="ch.qos.logback.core.rolling.RollingFileAppender">
       <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <level>ERROR</level>
        <onMatch>ACCEPT</onMatch>
        <onMismatch>DENY</onMismatch>
    </filter>
    < /appender>

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Log4j2 XML Configuration, Log4j2 uses the log4j2.xml format.


    <?xml version="1.0" encoding="UTF-8"?>
    <Configuration status="WARN">
    <!-- Define properties for reuse -->
    <Properties>
        <Property name="LOG_PATH">/var/logs/myapp</Property>
        <Property name="LOG_PATTERN">%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n</Property>
    </Properties>

    <!-- Console Appender -->
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${LOG_PATTERN}"/>
        </Console>

        <!-- File Appender with Rolling Policy -->
        <RollingFile name="File" fileName="${LOG_PATH}/application.log" filePattern="${LOG_PATH}/application-%d{yyyy-MM-dd}.log">
            <PatternLayout pattern="${LOG_PATTERN}"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
            </Policies>
            <DefaultRolloverStrategy max="30"/>
        </RollingFile>
    </Appenders>

    <!-- Root Logger -->
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Root>

        <!-- Specific Logger -->
        <Logger name="com.example" level="debug" additivity="false">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Logger>
    </Loggers>
    </Configuration>

     
    
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------











    




   













