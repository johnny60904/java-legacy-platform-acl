# Java Legacy Platform Anti-Corruption Layer

A public engineering repository demonstrating how an Anti-Corruption Layer (ACL) can be integrated into a large Java legacy platform while preserving explicit architectural boundaries, deterministic dependency composition, and modular business capabilities.

The repository is reconstructed from a production-inspired architecture and published in a fully sanitized form. Domain terminology, package names, and surrounding platform structures have been generalized without changing the architectural model.

---

## Repository Goals

This repository exists to document and demonstrate practical software architecture rather than framework usage.

Its primary objectives are to illustrate:

- Anti-Corruption Layer architecture
- Domain-Driven Design (DDD)
- Clean Architecture
- Vertical Slice Architecture
- CQRS within the Application Layer
- Explicit dependency composition without a dependency injection framework
- Repository-scale Shared Kernel design
- Structured diagnostic architecture
- Defensive engineering practices for legacy integration

The implementation intentionally emphasizes maintainability, observability, explicit dependency relationships, and architectural consistency.

---

## Architecture Diagram

```txt
                 Legacy Platform
                        │
                        ▼
             Anti-Corruption Layer
                        │
        ┌───────────────┴───────────────┐
        │                               │
   Shared Kernel                 DDD Modules
        │                               │
        └───────────────┬───────────────┘
                        │
                 Explicit Composition
```

---

## Architecture at a Glance

The repository combines multiple architectural paradigms, each serving a distinct engineering responsibility.

| Architecture | Primary Responsibility |
|--------------|------------------------|
| Domain-Driven Design | Business capability modeling |
| Clean Architecture | Dependency direction and separation of concerns |
| Vertical Slice Architecture | Reusable application capabilities |
| CQRS | Separation of application commands and queries |
| Anti-Corruption Layer | Isolation from the surrounding legacy platform |

These architectural styles complement one another rather than compete.

A detailed explanation is available in the documentation.

---

## Repository Organization

```text
src/main/java
│
├── com.dxlan.acl
│        ├── features/
│        │       ├── shared/
│        │       ├── infrastructure/
│        │       ├── inventory/
│        │       ├── notification/
│        │       └── userprofile/
│        │
│        └── premiumasset/
│
└── net/legacy/platform/
```

The repository is organized around three major areas.

- **Repository Shared Kernel**

  Shared engineering capabilities used across business modules.

- **Business Modules**

  Independent DDD modules encapsulating business behavior.

- **Legacy Platform Stub**

  A simplified execution environment providing only the contracts required by the repository.

---

## Dependency Composition

The surrounding execution environment determines the repository composition strategy.

Dependencies are assembled explicitly through dedicated module composition roots.

The repository intentionally avoids:

- runtime dependency scanning
- reflection-based service discovery
- dependency injection frameworks

Object construction remains deterministic, explicit, and fully observable.

Further details are described in `docs/composition-model.md`.

---

## Building

### Requirements

- Java 21
- Maven

### Build

```bash
mvn clean package -DskipTests
```

### Test Status

The original host platform from which this architectural approach was reconstructed does not include an automated testing infrastructure.

This public repository intentionally extracts only the Anti-Corruption Layer together with a minimal stub platform, making comprehensive integration tests impractical in its current form.

The absence of automated tests should therefore be interpreted as an environmental constraint rather than an architectural preference.

The repository has been designed to support future JUnit 5 adoption through:

- explicit dependency composition
- interface-oriented design
- deterministic initialization
- isolated business modules
- stable architectural boundaries

Incremental test coverage is planned as the repository continues to evolve.

---

## Documentation

| Document | Description |
|----------|-------------|
| `docs/terminology.md` | Repository terminology and architectural vocabulary |
| `docs/architecture.md` | Overall repository architecture |
| `docs/shared-kernel.md` | Repository Shared Kernel |
| `docs/composition-model.md` | Dependency composition model |
| `docs/module-model.md` | DDD module architecture |
| `docs/diagnostic-architecture.md` | Structured diagnostic architecture |
| `docs/package-guide.md` | Package organization and responsibilities |
| `docs/constraints.md` | Environmental constraints and architectural implications |
| `docs/design-decisions.md` | Architectural decision records (ADRs) |

The documentation is organized so that each document describes a single architectural concern without duplicating information contained elsewhere.

---

## Repository Status

This repository should be regarded as:

- a public engineering showcase
- an architectural reference implementation
- a learning-driven software engineering project
- a practical Anti-Corruption Layer implementation

It is intended to demonstrate architectural design and engineering practices rather than represent a complete production system.

---

## License

This project is released under the MIT License.

See the `LICENSE` file for details.