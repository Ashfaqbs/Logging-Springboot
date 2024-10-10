## Understand the following logs defined in application.yml file
```
logging:
  level:
    ROOT: INFO
    org.hibernate.SQL: DEBUG
    fr.codecake.airbnbcloneback: DEBUG

```
---

### Logging in Spring Boot

**1. Logging Levels:**
Logging levels define the severity of log messages and their visibility. The common levels, in order of detail, are:
- **TRACE**: Most detailed, used for very fine-grained information.
- **DEBUG**: Detailed information useful for debugging.
- **INFO**: General operational messages about the application's state.
- **WARN**: Potential issues that could become problems.
- **ERROR**: Serious problems that may allow the application to continue running.
- **FATAL**: Very severe issues that may cause the application to terminate.

**2. ROOT Logger:**
- The **ROOT** logger is the default logger that applies to the entire application.
- By setting `ROOT: INFO`, all logs will default to the INFO level, showing messages of INFO severity and above (INFO, WARN, ERROR, FATAL).
- TRACE and DEBUG logs will be suppressed unless specified otherwise.

**3. Base Package Logger:**
- The base package (e.g., `com.ashfaq.airbnb.airbnb_clone_backend`) is where the main application logic resides, including the main class.
- Setting the base package to `DEBUG` allows you to see all DEBUG-level logs and higher (DEBUG, INFO, WARN, ERROR, FATAL) specifically from this package.
- This configuration is helpful for monitoring the application’s internal behavior during development.

**4. Logging Hierarchy:**
- Logging levels work in a hierarchy. A log level of DEBUG will capture all logs of that level and above (including INFO and WARN).
- However, TRACE logs will not appear when the package is set to DEBUG, as TRACE is a lower level.

**5. Usage of TRACE Logs:**
- If you use `log.trace` in your base package while it’s configured at the DEBUG level, those TRACE logs will not be displayed.
- To see TRACE logs, you need to change the base package's log level to TRACE.

---

This documentation captures the key concepts about logging in our Spring Boot(air bnb clone) application.
