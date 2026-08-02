# Repository Terminology

**Version:** 2.0

**Status:** Stable

**Document Type:** Repository Vocabulary Specification

---

# 1. Purpose

This document defines the terminology used throughout this repository.

The objective is to establish a consistent engineering vocabulary so that architectural discussions, documentation, and implementation use identical meanings for the same concepts.

Terms defined in this document should be interpreted according to their repository-specific meaning.

Where a term also exists in general software engineering literature, the repository definition takes precedence when describing this implementation.

---

# 2. Terminology Principles

The repository follows several vocabulary principles.

- One concept should have one preferred term.
- Similar concepts should remain clearly distinguishable.
- Terminology should describe engineering responsibilities rather than implementation details.
- Documentation should prioritize consistency over synonym variety.

The repository intentionally avoids using multiple interchangeable names for the same architectural responsibility.

---

# 3. Repository

The **Repository** refers to the complete public project.

It includes:

- the Repository Shared Kernel
- one or more Domain-Driven Design (DDD) modules
- repository-wide engineering infrastructure
- documentation
- build configuration

The repository is the architectural boundary for all documentation.

Documentation should avoid describing a single DDD module as though it represents the entire repository.

---

# 4. Repository Shared Kernel

The **Repository Shared Kernel** is the collection of reusable capabilities shared across DDD modules.

It provides common engineering functionality while remaining independent of individual business domains.

The Shared Kernel currently contains:

- shared engineering components
- shared infrastructure
- reusable vertical slices
- reusable abstractions
- reusable defensive programming utilities

The Shared Kernel exists to support multiple independent DDD modules.

It is not a miscellaneous utility package.

---

# 5. DDD Module

A **DDD Module** is an independently evolvable business capability implemented using the repository's architectural conventions.

Each module owns its own:

- domain model
- application layer
- infrastructure layer
- presentation layer
- dependency composition
- public facade

Modules communicate through stable shared abstractions rather than directly depending upon one another.

A module should remain understandable in isolation.

---

# 6. Shared Vertical Slice

A **Shared Vertical Slice** represents a reusable application capability that can be consumed by multiple DDD modules.

Unlike a DDD module, a shared vertical slice does not represent a business domain.

Instead, it encapsulates a reusable application operation.

Examples include:

- client notification
- inventory synchronization
- user profile retrieval

These slices expose stable interfaces while hiding platform-specific implementation details.

---

# 7. Shared Infrastructure

Shared Infrastructure contains repository-wide technical capabilities that are independent of business domains.

Examples include:

- logging
- platform adapters
- technical services

Shared Infrastructure exists to support repository operation rather than business behavior.

---

# 8. Shared Engineering Components

Shared Engineering Components provide reusable implementation building blocks used throughout the repository.

Examples include:

- guard APIs
- boundary defense
- structured diagnostic models
- time abstractions
- numeric utilities
- functional abstractions

These components improve consistency across the repository.

They do not represent business concepts.

---

# 9. Presentation Layer

The Presentation Layer is the public entry point of a DDD module.

Within this repository it is responsible for:

- exposing stable public APIs
- constructing commands and queries
- coordinating application execution
- translating exceptions
- protecting integration boundaries

The Presentation Layer is not a web layer.

It is not coupled to HTTP.

It is not coupled to REST.

Its responsibility is exposing stable integration APIs to the surrounding legacy platform.

---

# 10. Application Layer

The Application Layer coordinates use cases.

Within this repository it is responsible for:

- command handling
- query handling
- application orchestration
- dependency coordination

Business rules remain within the Domain Layer.

---

# 11. Domain Layer

The Domain Layer contains business concepts, business rules, and invariants.

Its responsibility is maintaining domain correctness independent of infrastructure concerns.

The Domain Layer has no knowledge of:

- logging
- networking
- persistence technologies
- legacy platform implementation details
- user interface concerns

---

# 12. Infrastructure Layer

The Infrastructure Layer adapts external systems into repository abstractions.

Examples include:

- legacy platform repositories
- legacy platform gateways
- persistence adapters
- external integrations

Infrastructure implementations satisfy interfaces defined by higher architectural layers.

---

# 13. Composition Root

A **Composition Root** is the location where object graphs are assembled.

Within this repository, every DDD module and every reusable vertical slice owns an explicit composition root.

Composition roots perform deterministic dependency composition.

They are responsible only for object construction.

Business logic never resides within composition roots.

---

# 14. Container

A **Container** is the implementation of a module composition root.

Container classes:

- construct implementation dependencies
- wire application handlers
- expose initialized application services
- own module dependency composition

Container classes are not dependency injection containers.

They are not service locators.

They perform explicit constructor composition determined by the execution environment.

---

# 15. Explicit Composition

Explicit Composition is the repository strategy for assembling object graphs.

Dependencies are constructed directly inside composition roots using constructor injection.

Object creation remains:

- explicit
- deterministic
- observable
- framework-independent

No runtime dependency discovery is performed.

---

# 16. Dependency Composition

Dependency Composition refers to constructing implementation graphs from interface implementations.

The repository performs dependency composition during application startup.

Dependencies remain stable after initialization.

---

# 17. Constructor Injection

Constructor Injection is the mechanism used to provide implementation dependencies.

Dependencies are supplied explicitly by composition roots.

Injection does not require a dependency injection framework.

---

# 18. Public Facade

A Public Facade is the stable API exposed by a DDD module.

Public facades:

- validate entry conditions
- create commands or queries
- delegate execution
- translate exceptions where appropriate

Public facades intentionally expose static operations because runtime-managed object lifecycles are unavailable within the surrounding execution environment.

Static facades should not be interpreted as utility classes.

They represent stable module entry points.

---

# 19. Boundary Defense

Boundary Defense is the repository strategy for validating architectural boundaries.

Boundary Defense verifies correctness before execution crosses architectural or platform boundaries.

Typical responsibilities include validating:

- dependencies
- external objects
- external fields
- infrastructure contracts
- integration assumptions

Boundary Defense is distinct from business validation.

---

# 20. Structured Diagnostics

Structured Diagnostics refers to the repository's structured error reporting model.

Rather than communicating failures through unstructured exception messages alone, diagnostic information is represented through strongly typed components describing:

- module
- category
- cause
- target
- rule
- invariant
- error code

This structure improves observability while remaining independent of presentation concerns.

---

# 21. Domain Invariant

A Domain Invariant is a business rule that must always remain true.

Violation of an invariant indicates that a domain model has entered an unacceptable business state.

Invariant violations originate within the Domain Layer.

---

# 22. Application Validation

Application Validation verifies application-level inputs before domain execution begins.

Typical validation includes:

- command validation
- query validation
- parameter validation

Application validation protects application workflows rather than business correctness.

---

# 23. Exception Translation

Exception Translation converts internal engineering failures into stable integration responses suitable for the surrounding legacy platform.

Translation may:

- simplify internal failures
- preserve user-facing consistency
- prevent infrastructure details from leaking outside module boundaries

Translation is performed by the Presentation Layer.

---

# 24. Observability

Observability is the repository's ability to explain operational behavior through structured diagnostic information.

Observability includes:

- structured diagnostics
- deterministic logging
- contextual failure reporting
- explicit engineering metadata

Observability serves engineering investigation rather than end-user communication.

---

# 25. Legacy Platform

The Legacy Platform is the surrounding execution environment into which the repository integrates.

The repository does not own the lifecycle of the legacy platform.

Architectural decisions throughout the repository are constrained by the capabilities and limitations of this environment.

---

# 26. Stub Platform

The public repository replaces the original surrounding platform with a simplified stub implementation.

The stub platform preserves:

- architectural relationships
- dependency directions
- execution flow
- integration contracts

while removing proprietary implementation details.

---

# 27. Sanitization

Sanitization is the process of removing repository-specific or proprietary information prior to publication.

Sanitization affects:

- names
- packages
- surrounding platform terminology
- identifiers

Sanitization does not alter the repository's architectural intent or implementation strategy.

---

# 28. Module Independence

Module Independence means that DDD modules do not directly depend upon one another.

Reusable capabilities are obtained through the Repository Shared Kernel.

Cross-module dependencies are intentionally avoided.

This design allows modules to evolve independently while maintaining repository-wide consistency.

---

# 29. Hybrid Architecture

Hybrid Architecture describes the repository's architectural style.

The repository intentionally combines multiple architectural approaches.

Each approach addresses a different engineering concern.

These approaches include:

- Domain-Driven Design
- Clean Architecture
- Vertical Slice Architecture
- CQRS within the Application Layer
- Anti-Corruption Layer

These approaches are complementary rather than competing.

---

# 30. Anti-Corruption Layer

Within this repository, the Anti-Corruption Layer isolates business logic from legacy platform implementation details.

The ACL translates between repository concepts and surrounding platform concepts without allowing legacy implementation concerns to propagate into the domain model.

The Anti-Corruption Layer represents the repository's primary integration strategy rather than its sole architectural concern.