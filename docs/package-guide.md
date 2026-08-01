# Package Guide

## Overview

The repository is organized around a combination of Domain-Driven Design (DDD), Clean Architecture, and feature-oriented organization.

At the highest level, the repository is divided into two primary areas:

```text
com.dxlan.acl
│
├── features
│
└── premiumasset
```

The `features` package provides reusable capabilities shared across the repository.

The `premiumasset` package represents a complete Domain-Driven Design module implementing the repository's primary business capability.

---

# Package Organization

```text
com.dxlan.acl
│
├── features
│   ├── infrastructure
│   ├── inventory
│   ├── notification
│   ├── shared
│   └── userprofile
│
└── premiumasset
    ├── application
    ├── domain
    ├── infrastructure
    └── presentation
```

Each package has a distinct responsibility.

The repository intentionally separates reusable platform capabilities from domain-specific implementation.

---

# Top-Level Packages

## features

The `features` package contains reusable functionality shared throughout the repository.

It does not represent a single Domain-Driven Design module.

Instead, it provides shared infrastructure, reusable components, and feature-oriented capabilities that support one or more business modules.

Responsibilities include:

- shared infrastructure
- reusable utilities
- feature-oriented components
- common abstractions
- cross-cutting support

Packages within `features` remain independent from the internal implementation of individual domain modules whenever practical.

---

## premiumasset

The `premiumasset` package represents the primary Domain-Driven Design module.

It demonstrates the repository's layered architecture and serves as the reference implementation for the architectural patterns adopted throughout the project.

The module follows Clean Architecture by separating:

- Presentation
- Application
- Domain
- Infrastructure

Each layer has clearly defined responsibilities and dependency direction.

---

# features Package

## features.shared

The `shared` package contains components intended for reuse across multiple business areas.

Typical responsibilities include:

- shared contracts
- shared utilities
- reusable value types
- common abstractions
- shared constants

Code within this package should remain broadly applicable and avoid dependencies on individual domain modules.

---

## features.infrastructure

The `infrastructure` package provides shared technical capabilities required across the repository.

Examples include:

- logging support
- infrastructure utilities

This package contains implementation details that are shared between multiple areas of the repository.

Business logic should not reside here.

---

## features.inventory

The `inventory` package groups functionality related to the inventory feature slice.

Responsibilities remain localized to inventory-specific concerns without exposing unrelated implementation details to other features.

---

## features.notification

The `notification` package groups functionality associated with notification-related operations.

The package represents a feature-oriented boundary rather than a traditional technical layer.

---

## features.userprofile

The `userprofile` package contains functionality associated with user profile operations.

Feature-specific implementation remains encapsulated within this package whenever practical.

---

# premiumasset Module

The `premiumasset` module demonstrates a complete layered implementation following Domain-Driven Design and Clean Architecture.

```text
premiumasset
│
├── presentation
├── application
├── domain
└── infrastructure
```

Dependency direction always points toward the domain.

---

# Presentation Layer

```text
premiumasset.presentation
```

The Presentation Layer provides stable public entry points for the surrounding legacy platform.

It is not a web layer.

It is not tied to HTTP.

It does not implement REST endpoints.

Presentation classes primarily expose static facade methods responsible for:

- initial parameter validation
- command and query construction
- delegation to application handlers
- module-level exception routing where applicable

Presentation classes intentionally avoid embedding business rules.

---

# Application Layer

```text
premiumasset.application
```

The Application Layer coordinates use cases.

Responsibilities include:

- command handling
- query handling
- application workflows
- coordination between repositories, gateways, and domain objects
- mapping between external representations and domain concepts

CQRS is implemented within this layer by separating commands from queries.

Business rules remain within the Domain Layer.

---

# Domain Layer

```text
premiumasset.domain
```

The Domain Layer contains the business model.

Typical responsibilities include:

- entities
- value objects
- domain services
- repository abstractions
- business validation
- domain-specific policies

The Domain Layer remains independent from infrastructure implementation.

No infrastructure concerns should leak into this package.

---

# Infrastructure Layer

```text
premiumasset.infrastructure
```

The Infrastructure Layer provides technical implementations required by the application.

Typical responsibilities include:

- repository implementations
- gateway implementations
- persistence integration
- platform adapters
- logging integration
- legacy platform interaction

Infrastructure implements abstractions defined by higher layers while remaining replaceable where practical.

---

# Shared Kernel

The repository adopts a lightweight shared kernel through the `features` package.

Shared functionality is centralized to avoid unnecessary duplication while preserving clear module boundaries.

Only capabilities that are genuinely reusable should be placed within shared packages.

Business-specific implementation should remain inside the owning domain module.

---

# Package Boundaries

The repository follows several boundary rules.

- Business rules belong in the Domain Layer.
- Application workflows belong in the Application Layer.
- Infrastructure details belong in the Infrastructure Layer.
- Stable integration entry points belong in the Presentation Layer.
- Shared technical capabilities belong in the `features` package.
- Feature-specific implementation remains localized whenever possible.

These boundaries reduce coupling while improving maintainability and readability.

---

# Dependency Rules

Package dependencies follow these principles:

- Presentation depends on Application.
- Application depends on Domain.
- Infrastructure depends on Domain abstractions.
- Domain does not depend on Infrastructure.
- Shared packages should not depend on business-specific modules.
- Cross-module dependencies should remain explicit and minimal.

Maintaining these dependency rules preserves architectural consistency throughout the repository.

---

# Summary

The repository combines feature-oriented organization with layered domain modules.

The `features` package provides reusable capabilities shared across the repository, while `premiumasset` demonstrates a complete Domain-Driven Design module following Clean Architecture principles.

This organization balances modularity, explicit dependency direction, and maintainability while remaining compatible with the surrounding legacy execution environment.