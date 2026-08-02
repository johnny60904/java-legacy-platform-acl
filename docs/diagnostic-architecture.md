# Diagnostic Architecture

**Version:** 2.0

**Status:** Stable

**Document Type:** Repository Architecture Specification

---

# 1. Purpose

This document describes the repository's diagnostic architecture.

Rather than treating exception handling as an isolated implementation concern, the repository models diagnostics as an architectural capability spanning multiple layers.

The objective is to provide:

- deterministic failure handling
- high operational observability
- explicit architectural boundaries
- stable user-facing behavior
- structured engineering diagnostics

Diagnostic behavior is therefore considered part of the repository architecture rather than an implementation detail.

---

# 2. Architectural Philosophy

The repository distinguishes between two fundamentally different concerns.

**Engineering Diagnostics**

Engineering diagnostics exist to explain *why* an operation failed.

Their audience is software engineers.

---

**User Communication**

User communication exists to explain *what* happened in an appropriate form for the surrounding platform.

Its audience is the external consumer.

---

These concerns intentionally remain independent.

Engineering diagnostics should not dictate user-facing messages.

Likewise, user-facing messages should not determine engineering diagnostics.

---

# 3. Diagnostic Pipeline

Failures progress through a deterministic architectural pipeline.

```text
Boundary Defense

        │
        ▼

Application Validation

        │
        ▼

Domain Invariant Enforcement

        │
        ▼

Infrastructure Failures

        │
        ▼

Presentation Translation

        │
        ▼

Legacy Platform
```

Each stage protects a different architectural boundary.

Each stage is responsible for a different category of failure.

---

# 4. Diagnostic Layers

The repository organizes failures into four architectural categories.

| Layer | Responsibility |
|--------|----------------|
| Boundary Defense | Architectural correctness |
| Application Validation | Application workflow correctness |
| Domain Integrity | Business correctness |
| Presentation Translation | Stable external behavior |

Each category answers a different engineering question.

---

# 5. Boundary Defense

Boundary Defense protects architectural boundaries before execution enters business logic.

Its responsibility is verifying assumptions regarding external and internal dependencies.

Typical checks include:

- missing dependencies
- invalid infrastructure contracts
- missing external objects
- missing internal objects
- invalid metadata
- integration assumptions

Boundary Defense intentionally executes before business validation.

If an architectural boundary has already been violated, business execution should never begin.

---

# 6. Boundary Defense Philosophy

Boundary Defense is not business validation.

Instead, it verifies that execution can safely enter the repository.

Typical examples include:

- required dependency references
- required infrastructure objects
- required metadata
- required platform contracts

Business concepts remain outside the responsibility of Boundary Defense.

---

# 7. Fluent Validation

Boundary validation follows a fluent programming model.

A validator is created for the current implementation type and subsequently performs one or more defensive checks.

Conceptually:

```text
BoundaryValidator

↓

Select Context

↓

Validate Dependency

↓

Validate External State

↓

Validate Internal State

↓

Continue Execution
```

This approach allows multiple architectural assumptions to be verified through a single validation pipeline while preserving readability.

---

# 8. Application Validation

Application Validation protects application workflows.

Its responsibility is validating requests before business execution begins.

Typical responsibilities include:

- command validation
- query validation
- application parameters
- application preconditions

Application Validation answers:

> "Can this use case begin?"

It intentionally does not answer whether business rules are satisfied.

---

# 9. Validation Taxonomy

Application validation failures are represented using structured validation models.

Rather than communicating failures through textual messages alone, validation information is represented through explicit metadata describing:

- validation module
- validation category
- validation target
- validation rule
- validation cause
- error code

This structure improves diagnostic precision while keeping validation behavior consistent across application workflows.

---

# 10. Domain Integrity

The Domain Layer protects business correctness.

Business rules are enforced through domain invariants.

An invariant represents a rule that must always remain true regardless of the execution path that produced the current state.

Invariant enforcement remains entirely within the Domain Layer.

Neither Presentation nor Infrastructure is responsible for maintaining business correctness.

---

# 11. Domain Violation Model

Domain failures are represented through structured domain violations.

A violation captures engineering information describing:

- business module
- invariant category
- violated invariant
- violation target
- violation cause
- error code

Domain failures therefore communicate far more than a textual exception message.

They explain the precise business rule that has been violated.

---

# 12. Separation of Validation and Domain Integrity

Application Validation and Domain Integrity intentionally remain separate.

Application Validation verifies whether a request is suitable for execution.

Domain Integrity verifies whether business state remains valid.

For example:

```text
Application Validation

↓

"Is this command acceptable?"

----------------------------

Domain Integrity

↓

"Is this business state acceptable?"
```

Separating these responsibilities produces clearer failure semantics and simplifies reasoning about repository behavior.

---

# 13. Structured Diagnostic Model

The repository represents diagnostic information through structured components rather than free-form strings.

Diagnostic information typically includes:

- module
- category
- target
- cause
- violated rule
- invariant
- error code

The resulting diagnostic model provides significantly greater precision than conventional exception hierarchies alone.

---

# 14. Cause Objects

Diagnostic causes describe *why* a failure occurred.

Rather than embedding explanations directly into exception classes, causes are modeled as independent diagnostic objects.

Typical causes include:

- missing values
- invalid values
- out-of-range values
- unsupported states
- inconsistent states

Separating causes from exception types improves reuse while keeping exception hierarchies relatively small.

---

# 15. Exception Types

Exception classes communicate architectural intent.

They distinguish different categories of failure rather than simply representing unexpected program termination.

Typical categories include:

- boundary failures
- validation failures
- invariant violations

Each exception category corresponds to a distinct architectural responsibility.

---

# 16. Exception Translation

The Presentation Layer owns exception translation.

Internal failures are translated into stable responses appropriate for the surrounding legacy platform.

Translation intentionally prevents repository implementation details from leaking beyond module boundaries.

Presentation Translation therefore represents an integration concern rather than a business concern.

---

# 17. Translation Strategy

Exception translation follows a deterministic strategy.

```text
Structured Exception

↓

Determine Failure Category

↓

Select Translation

↓

Produce Stable Platform Response

↓

Log Engineering Details
```

Engineering diagnostics and external communication remain independent throughout the process.

---

# 18. Operational Logging

Operational logging is performed independently of user-facing communication.

Repository logging serves engineering investigation.

Typical log entries may include:

- structured diagnostics
- stack traces
- contextual metadata
- affected components

The surrounding platform receives only the information appropriate for its integration boundary.

---

# 19. Observability

Observability is a primary architectural objective.

The repository emphasizes:

- structured failures
- deterministic diagnostics
- contextual information
- explicit architectural metadata
- stable diagnostic models

High observability improves maintenance by reducing ambiguity during failure investigation.

---

# 20. Failure Containment

Failures are intentionally contained within architectural boundaries.

Business failures should remain within business layers.

Infrastructure failures should remain identifiable as infrastructure failures.

Presentation Translation prevents internal failures from propagating directly into external integration surfaces.

Failure containment therefore preserves both architectural clarity and stable public behavior.

---

# 21. Architectural Characteristics

The repository diagnostic architecture emphasizes:

- explicit diagnostics
- structured metadata
- deterministic failure handling
- clear architectural ownership
- observable execution
- reusable diagnostic models
- separation of engineering diagnostics and user communication

These characteristics apply consistently throughout the repository.

---

# 22. Summary

The repository treats diagnostics as a first-class architectural concern.

Boundary Defense, Application Validation, Domain Integrity, and Presentation Translation each protect different architectural boundaries while remaining independent responsibilities.

Structured diagnostic models provide rich engineering information for investigation, whereas Presentation Translation exposes only stable integration behavior appropriate for the surrounding legacy platform.

This separation improves maintainability, observability, and architectural consistency without coupling engineering diagnostics to user-facing communication.