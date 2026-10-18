\# Sprint 1 Retrospective



Goal : User can view events, User can view event details, admin can create events



\## Working \& tools

&#x20; - Layered Architecture

&#x09;- controller

&#x09;- service

&#x09;- repository

&#x20;	- entity

&#x09;- exception

&#x09;- DTO

&#x20; - Flyway Migration

&#x20; - Postman



\## Learned

&#x20;  - Database changes using Flyway (modifying executed migration script risks or problem(checksums and version))

&#x20;  - difference between flyway and migration

&#x20;  - Postman collection

&#x20;  - GlobalExceptionHandler

&#x09;- Learned Spring Exception Hierarchy

&#x09;	- MethodArgumentNotValidException extends BindException

&#x09;	- BindException extends Exception implements BindingResults

&#x09;	- BindingResults extends Error

&#x09;	- FieldError

&#x20;  - Swagger UI and OpenAPI description (documenting api)

&#x09;	Application

&#x09;	    |

&#x09;	OpenAPI Descripton

&#x09;	    |

&#x09;	Swagger UI

