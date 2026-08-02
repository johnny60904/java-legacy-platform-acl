# Composition Model

**Version:** 2.0

**Status:** Stable

**Document Type:** Repository Architecture Specification

---

# 1. Purpose

This document describes the dependency composition model adopted throughout the repository.

Rather than relying on a dependency injection framework, the repository performs explicit dependency composition that is fully visible within the source code.

The composition strategy is determined by the execution environment rather than architectural preference.

---

# 2. Architectural Context

The repository operates inside an existing legacy Java platform.

The surrounding platform:

- owns the application lifecycle
- defines the application startup sequence
- does not provide dependency injection
- does not provide runtime service discovery
- does not provide managed object lifecycles

Introducing an external dependency injection framework would require invasive modifications throughout the surrounding platform.

Consequently, the repository adopts an explicit composition model that integrates naturally with the host environment while preserving architectural clarity.

---

# 3. Design Goals

The composition model is designed to provide:

- deterministic object construction
- explicit dependency graphs
- framework independence
- observable startup behavior
- stable object lifecycles
- simple debugging
- compatibility with the surrounding platform

The repository intentionally favors transparency over automation.

---

# 4. Composition Philosophy

Object construction is considered an architectural responsibility rather than an infrastructure concern.

Dependency graphs should be understandable by reading the source code alone.

No hidden runtime behavior participates in dependency construction.

Every dependency is assembled explicitly.

This approach allows engineers to reason about initialization order, dependency relationships, and application startup without requiring framework-specific knowledge.

---

# 5. Composition Strategy

The repository follows an explicit constructor composition strategy.

Implementation objects are created manually inside dedicated composition roots.

Dependencies are supplied directly through constructors.

For example:

```text
Infrastructure Implementation
            │
            ▼
Application Handler
            │
            ▼
Module Container
            │
            ▼
Public Facade
```

Object ownership is therefore explicit and deterministic.

---

# 6. Constructor Injection

Constructor injection is the exclusive dependency injection mechanism used throughout the repository.

Dependencies are supplied during object construction.

Application components never resolve dependencies dynamically after construction.

This guarantees that every component enters a fully initialized state before becoming observable by the rest of the system.

---

# 7. Implementation Lifetime

Most implementation classes follow the same lifecycle strategy.

They are designed to be:

- stateless
- immutable after construction
- thread-safe by design where practical
- reusable throughout the application lifetime

Because these implementations do not maintain mutable business state, a single shared instance is generally sufficient.

This reduces unnecessary allocations while simplifying lifecycle management.

---

# 8. Singleton Strategy

Most implementation classes expose a singleton instance.

Singleton lifetime is used because implementation objects typically represent reusable infrastructure adapters or stateless services rather than business state.

The repository intentionally distinguishes singleton service objects from singleton domain state.

Business state remains external to singleton implementations.

---

# 9. Bill Pugh Holder Pattern

Singleton implementations use the Bill Pugh Holder pattern.

This approach provides:

- lazy initialization
- thread-safe initialization
- JVM-managed synchronization
- simple implementation
- deterministic semantics

The repository prefers this approach because it avoids explicit synchronization while remaining easy to understand.

---

# 10. Composition Root

Every independently deployable architectural unit owns a dedicated Composition Root.

Composition Roots exist for:

- each DDD module
- each Shared Vertical Slice

Each Composition Root is responsible only for assembling object graphs.

Business logic is intentionally excluded.

---

# 11. Container Classes

Composition Roots are implemented using dedicated `Container` classes.

A Container is responsible for:

- constructing infrastructure implementations
- wiring application handlers
- exposing initialized application services
- owning dependency composition for its architectural unit

Containers do not perform business operations.

They exist solely to assemble dependencies.

---

# 12. Containers Are Not IoC Containers

Despite their name, repository Containers should not be confused with dependency injection containers.

They are not:

- IoC containers
- dependency injection frameworks
- service locators
- runtime registries

A repository Container performs explicit object construction.

Every dependency relationship is visible in source code.

No runtime registration mechanism exists.

---

# 13. Startup Sequence

Application initialization follows a deterministic startup sequence.

```text
Platform Entry Point
        │
        ▼
Container.initialize()
        │
        ▼
Composition Root Construction
        │
        ▼
Application Ready
```

The surrounding platform owns startup.

The repository initializes only its own architectural units.

---

# 14. Repository Initialization

Repository initialization occurs explicitly.

During startup, the platform invokes the initialization method of each architectural unit.

Initialization ensures that:

- singleton instances are created
- dependency graphs are assembled
- application handlers become available
- repository services are ready before first use

No component performs lazy dependency discovery during normal execution.

---

# 15. Public Facades

Each module exposes a stable public facade.

Public facades represent the integration boundary between the repository and the surrounding platform.

Responsibilities include:

- validating public inputs
- constructing commands
- constructing queries
- delegating execution
- translating failures

Public facades intentionally do not own dependency construction.

Instead, they obtain fully initialized application services from the corresponding Composition Root.

---

# 16. Static Entry Points

Public facade operations are exposed as static methods.

This design reflects the surrounding execution environment.

Because the surrounding platform does not provide managed application services or dependency injection, static facades provide stable repository entry points without requiring external object management.

Static facades should therefore be interpreted as architectural boundaries rather than utility classes.

---

# 17. Handler Construction

Application handlers are constructed exactly once during composition.

Handlers receive all required dependencies through their constructors.

After construction, handlers remain immutable.

Execution therefore consists only of invoking existing application services rather than repeatedly constructing dependency graphs.

---

# 18. Dependency Ownership

Dependency ownership follows a simple hierarchy.

```text
Platform
    │
    ▼
Container
    │
    ▼
Application Handler
    │
    ▼
Infrastructure Implementation
```

Ownership always flows downward.

Lower layers never construct higher layers.

Business execution never performs dependency assembly.

---

# 19. Object Lifetime

Object lifetimes remain stable throughout application execution.

Typical lifetimes include:

| Component | Lifetime |
|-----------|----------|
| Container | Application lifetime |
| Infrastructure Implementation | Application lifetime |
| Application Handler | Application lifetime |
| Public Facade | Static entry point |
| Command | Per request |
| Query | Per request |
| Domain Model | Per operation |

This separation keeps reusable services long-lived while allowing business objects to remain short-lived and isolated.

---

# 20. Advantages

The composition model provides several engineering advantages.

- deterministic startup
- explicit dependency graphs
- transparent object ownership
- framework independence
- straightforward debugging
- minimal runtime overhead
- compatibility with constrained execution environments

Most importantly, dependency relationships remain directly observable within the source code.

---

# 21. Trade-offs

The explicit composition model also introduces trade-offs.

Dependency graphs must be maintained manually.

Adding new services requires updating the corresponding Composition Root.

Unlike dependency injection frameworks, no automatic dependency discovery exists.

The repository accepts this trade-off because explicit composition provides predictable behavior and aligns naturally with the surrounding legacy platform.

---

# 22. Architectural Characteristics

The repository composition model emphasizes:

- explicit construction
- constructor injection
- deterministic initialization
- immutable service composition
- framework independence
- transparent dependency graphs
- stable application lifecycles

These characteristics are applied consistently across both DDD modules and Shared Vertical Slices.

---

# 23. Summary

The repository adopts an explicit dependency composition model tailored to its execution environment.

Dependencies are assembled manually inside dedicated Composition Roots using constructor injection.

Most implementation classes are stateless singleton services implemented using the Bill Pugh Holder pattern.

Container classes function as deterministic Composition Roots, assembling application object graphs while remaining entirely separate from dependency injection frameworks or service locator patterns.

The resulting architecture preserves transparency, maintainability, and predictable runtime behavior while integrating cleanly with the surrounding legacy platform.