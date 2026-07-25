package com.dxlan.acl.features.shared.common;

/**
 * <h1>Equivalent Representation Contract</h1>
 *
 * <p>This interface indicates that the implementing class can be represented in another
 * system, framework, or standard as a <b>fully equivalent, lossless, and identical</b> counterpart.</p>
 *
 * <h3>Design Intent:</h3>
 * <ul>
 *   <li><b>Absolute Equivalence:</b> The mapping guarantees 100% identity in terms of value, meaning,
 *       and mathematical/physical scale. They can be interchanged without any loss of business semantics.</li>
 *   <li><b>Standardization & Alignment:</b> Used primarily to align internal custom types with
 *       official international standards or third-party API specifications.</li>
 * </ul>
 *
 * <h3>Typical Use Cases:</h3>
 * <ul>
 *   <li>Mapping a custom country enum ({@code Country}) to the official ISO 3166 country standard object.</li>
 *   <li>Mapping an internal currency enum ({@code PlatformCurrency}) to Java's native {@link java.util.Currency}.</li>
 * </ul>
 *
 * <h3>Code Example:</h3>
 * <pre>{@code
 * public enum PlatformCurrency implements EquivalentRepresentable<java.util.Currency> {
 *     TWD(java.util.Currency.getInstance("TWD")),
 *     USD(java.util.Currency.getInstance("USD"));
 *
 *     private final java.util.Currency javaCurrency;
 *     PlatformCurrency(java.util.Currency javaCurrency) { this.javaCurrency = javaCurrency; }
 *
 *     @Override
 *     public java.util.Currency toEquivalent() { return this.javaCurrency; }
 * }
 * }</pre>
 *
 * @param <T> the type of the equivalent representation
 * @author DXLAN
 * @since 1.0.0
 */
public interface EquivalentRepresentable<T> {

    /**
     * Converts or maps this object to its fully equivalent representation in the target system.
     *
     * @return the equivalent target object representation, never {@code null}
     */
    T toEquivalent();

}
