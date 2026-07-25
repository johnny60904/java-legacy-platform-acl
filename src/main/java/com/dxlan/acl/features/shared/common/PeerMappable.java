package com.dxlan.acl.features.shared.common;

/**
 * <h1>Peer Mappable Contract</h1>
 *
 * <p>This interface indicates that the implementing class has a 1:1, non-hierarchical,
 * and closely-coupled <b>peer counterpart object</b> in an independent domain or business context.</p>
 *
 * <h3>Design Intent:</h3>
 * <ul>
 *   <li><b>Non-Absolute Equivalence:</b> Represents a logical mapping rather than physical identity.
 *       The two components may hold completely different business logic but share the same domain granularity.</li>
 *   <li><b>Cross-Context Bridge:</b> Establishes a typesafe, compile-time mapping channel between
 *       two parallel evolving domain models.</li>
 * </ul>
 *
 * <h3>Typical Use Cases:</h3>
 * <ul>
 *   <li>Mapping a presentation layer calendar unit ({@code CalendarUnit.DAYS}) to a rich business calculation logic unit ({@code DurationUnit.DAY}).</li>
 *   <li>Mapping a frontend UI status enum ({@code UserInterfaceStatus}) to a backend core business status enum ({@code CoreBizStatus}).</li>
 * </ul>
 *
 * <h3>Code Example:</h3>
 * <pre>{@code
 * public enum CalendarUnit implements PeerMappable<DurationUnit> {
 *     DAYS(DurationUnit.DAY),
 *     HOURS(DurationUnit.HOUR);
 *
 *     private final DurationUnit peer;
 *     CalendarUnit(DurationUnit peer) { this.peer = peer; }
 *
 *     @Override
 *     public DurationUnit getPeer() { return this.peer; }
 * }
 * }</pre>
 *
 * @param <T> the type of the peer counterpart object
 * @author DXLAN
 * @since 1.0.0
 */
public interface PeerMappable<T> {

    /**
     * Retrieves the peer counterpart object bound tightly to this instance.
     *
     * @return the corresponding peer object in the target context, never {@code null}
     */
    T getPeer();

}
