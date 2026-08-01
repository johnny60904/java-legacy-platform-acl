# Architecture

## Overview

This repository is a public reference implementation of an Anti-Corruption Layer (ACL) reconstructed alongside an existing legacy Java platform.

The implementation combines multiple architectural styles that address different responsibilities within the system rather than representing competing architectural choices.

The surrounding execution environment imposes significant constraints on dependency composition and module initialization. Consequently, the repository adopts explicit manual composition while preserving clear architectural boundaries and dependency direction.

The public repository has been intentionally sanitized. Package names, domain terminology, and surrounding platform structures have been generalized while preserving the implementation approach and architectural intent.

---

# Architectural Goals

The repository is designed around the following objectives:

- isolate business logic from legacy platform behavior
- preserve domain boundaries
- maintain deterministic dependency composition
- organize functionality into cohesive modules
- support incremental evolution without modifying the surrounding platform
- keep application flow explicit and observable

---

# Architectural Styles

The repository combines several architectural paradigms.

Each serves a different responsibility within the overall system.

## Anti-Corruption Layer

The Anti-Corruption Layer isolates the domain model from the surrounding legacy platform.

Responsibilities include:

- preventing leakage of legacy models
- translating external concepts into domain concepts
- protecting business rules from legacy implementation details
- reducing coupling to the surrounding platform

The ACL serves as the architectural boundary between the legacy environment and the domain implementation.

---

## Domain-Driven Design

Domain-Driven Design provides the primary organizational model.

Business capabilities are grouped into domain modules with clearly defined responsibilities.

Each module encapsulates:

- domain model
- application services
- infrastructure implementations
- presentation façade

This organization promotes cohesion while minimizing unnecessary dependencies between unrelated business capabilities.

---

## Clean Architecture

Within each DDD module, responsibilities follow Clean Architecture principles.

```text
Presentation                   Infrastructure
        │                             │
        └──────────────┬──────────────┘
                       ▼
                  Application
                       │
                       ▼
                     Domain
```

Dependency direction always points toward the domain model.

The Domain Layer contains business rules without knowledge of application orchestration or infrastructure implementation.

The Application Layer coordinates use cases.

Infrastructure provides implementation details.

Presentation exposes stable entry points for the surrounding legacy platform.

---

## Vertical Slice Architecture

Feature-oriented packages organize shared capabilities around individual business concerns.

Rather than placing all functionality into broad technical layers, related operations are grouped according to feature boundaries where appropriate.

This organization improves locality of change while allowing shared infrastructure to remain reusable across modules.

---

## CQRS

The Application Layer separates command and query responsibilities.

Commands represent operations that modify application state.

Queries represent operations that retrieve information without performing state changes.

CQRS is applied within the application layer only.

It does not imply event sourcing or distributed messaging.

---

# Repository Organization

The repository consists of two primary areas.

```text
com.dxlan.acl
│
├── features
│
└── premiumasset
```

## features

The `features` package contains shared functionality and feature-oriented components used throughout the repository.

It provides reusable infrastructure and supporting capabilities rather than representing a single DDD module.

---

## premiumasset

The `premiumasset` package represents the primary Domain-Driven Design module.

It demonstrates the complete layered architecture consisting of:

- Presentation
- Application
- Domain
- Infrastructure

This module provides the primary reference implementation for the repository's architectural patterns.

---

# Layer Responsibilities

## Presentation Layer

The Presentation Layer exposes stable application entry points for the surrounding legacy platform.

It is not a web layer.

It is not coupled to HTTP.

It does not represent REST controllers.

Presentation classes primarily expose static façade methods.

Typical responsibilities include:

- initial parameter validation
- construction of commands and queries
- delegation to application handlers
- module-level exception routing where applicable

The Presentation Layer provides a stable integration boundary without exposing internal implementation details.

---

## Application Layer

The Application Layer coordinates business use cases.

Responsibilities include:

- command handling
- query handling
- application orchestration
- transaction coordination where required
- interaction with repositories and gateways
- mapping between domain and external models

Application services contain workflow logic but avoid embedding business rules that belong within the domain model.

---

## Domain Layer

The Domain Layer represents the core business model.

Responsibilities include:

- entities
- value objects
- domain services
- business rules
- domain validation
- repository abstractions

The Domain Layer contains no knowledge of infrastructure implementation.

---

## Infrastructure Layer

The Infrastructure Layer contains implementation details required by the application.

Examples include:

- repository implementations
- gateway implementations
- platform adapters
- persistence integration
- logging support
- legacy platform interaction

Infrastructure depends on domain abstractions rather than reversing dependency direction.

---

# Dependency Direction

The repository follows inward dependency flow.

```text
Presentation                   Infrastructure
        │                             │
        └──────────────┬──────────────┘
                       ▼
                  Application
                       │
                       ▼
                     Domain
```

Infrastructure implements abstractions defined by the Domain or Application layers.

Higher-level policies remain independent from lower-level implementation details.

---

# Dependency Composition

The surrounding execution environment determines the dependency composition strategy.

No dependency injection framework is employed.

No runtime dependency scanning is performed.

No reflection-based service discovery is used.

Implementation classes are generally:

- stateless
- immutable after construction
- singleton where appropriate

Dependencies are assembled explicitly within module-specific Container classes.

This approach keeps object construction deterministic, visible, and straightforward to inspect.

---

# Composition Root

Each module defines a dedicated Container class.

Container classes serve as module composition roots.

Their responsibilities include:

- constructing implementation dependencies
- wiring application handlers
- exposing initialized application services
- maintaining explicit object graphs

Container classes are not dependency injection containers.

They are not service locators.

Initialization occurs explicitly from the surrounding legacy platform.

---

# Stable Application Façades

Public entry points are exposed through static façade classes located within the Presentation Layer.

These façades provide a stable integration surface for the surrounding platform while shielding internal architectural changes.

Their responsibilities are limited to coordinating application operations rather than implementing business rules.

---

# Architectural Boundaries

The repository maintains clear separation between:

- legacy platform concerns
- application orchestration
- domain behavior
- infrastructure implementation

Each layer communicates only through well-defined interfaces and dependency direction.

This separation enables incremental evolution of the domain implementation while minimizing coupling to the surrounding legacy platform.

---

# Summary

This repository demonstrates how Domain-Driven Design, Clean Architecture, Vertical Slice Architecture, CQRS, and the Anti-Corruption Layer pattern can coexist within the constraints of a legacy Java execution environment.

Rather than relying on framework-provided composition, the repository adopts explicit manual dependency composition dictated by the surrounding platform while preserving modularity, deterministic initialization, and clear architectural boundaries.