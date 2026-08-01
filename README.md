# Java Legacy Platform Anti-Corruption Layer

A public reference implementation of an Anti-Corruption Layer (ACL) reconstructed from integration work performed alongside a legacy Java platform.

The repository demonstrates how modern architectural principles can be applied within the constraints of an existing monolithic system without requiring invasive changes to the surrounding execution environment.

The public repository is intentionally sanitized. Domain terminology, package names, and surrounding platform structures have been generalized while preserving the architectural intent, dependency relationships, and implementation approach.

This repository is intended as an architectural reference implementation and engineering portfolio project rather than a production-ready framework.

---

## Repository Objectives

This repository demonstrates a practical implementation of:

- Anti-Corruption Layer (ACL)
- Domain-Driven Design (DDD)
- Clean Architecture
- Vertical Slice Architecture
- CQRS within the Application Layer
- Explicit manual dependency composition

The implementation focuses on maintaining clear architectural boundaries while integrating with a tightly coupled legacy platform.

---

## Architectural Overview

The repository combines multiple architectural styles, each serving a distinct responsibility.

| Architecture | Responsibility |
|--------------|----------------|
| Anti-Corruption Layer | Isolates the domain from legacy platform models and behaviors. |
| Domain-Driven Design | Organizes business capabilities into cohesive domain modules. |
| Clean Architecture | Separates domain, application, infrastructure, and presentation responsibilities. |
| Vertical Slice Architecture | Groups feature-specific workflows around individual use cases. |
| CQRS | Separates command and query responsibilities within the application layer. |

These approaches are complementary rather than interchangeable. Together they provide a structured approach for integrating modern application logic with an existing legacy execution environment.

---

## Execution Environment

The repository targets:

- Java 21
- Maven

The surrounding legacy platform determines the dependency composition strategy.

Because the host environment does not provide dependency injection infrastructure, application dependencies are composed explicitly within module-specific composition roots.

This repository documents that composition model rather than replacing it with an alternative framework.

---

## Repository Structure

```text
src/main/java/com/dxlan/acl
│
├── features/
│   ├── infrastructure/
│   ├── inventory/
│   ├── notification/
│   ├── shared/
│   └── userprofile/
│
└── premiumasset/
    ├── application/
    ├── domain/
    ├── infrastructure/
    └── presentation/
```

The `features` package provides shared capabilities and feature-oriented components used throughout the repository.

The `premiumasset` package represents the primary Domain-Driven Design module and demonstrates the complete layered architecture.

---

## Dependency Composition

Dependencies are composed explicitly.

Implementation classes are generally designed as stateless, immutable after construction, and exposed through singleton access where appropriate.

Each module defines its own Container class that serves as the module composition root.

Object construction is:

- explicit
- deterministic
- observable
- free from runtime scanning and reflection-based service discovery

This composition strategy is dictated by the surrounding execution environment.

---

## Project Characteristics

- Frameworkless Java implementation
- Manual dependency composition
- Stable application façades
- Layered domain boundaries
- Feature-oriented organization
- Defensive integration with a legacy platform
- Sanitized public reference implementation

---

## Building

### Requirements

- Java 21
- Maven

### Compile

```bash
mvn clean package
```

---

## Documentation

Additional documentation is available under the `docs` directory.

| Document | Description |
|----------|-------------|
| `architecture.md` | Overall architecture, dependency direction, composition model, and architectural styles. |
| `package-guide.md` | Package responsibilities and repository organization. |
| `constraints.md` | Environmental constraints and their architectural implications. |
| `design-decisions.md` | Significant engineering decisions and associated trade-offs. |

---

## AI Collaboration

AI was used as an engineering collaborator during the development of this repository for architectural discussion, documentation refinement, design review, and iterative reasoning.

Implementation decisions and final engineering judgment remain human-directed.

---

## License

This project is released under the MIT License.

See the `LICENSE` file for details.