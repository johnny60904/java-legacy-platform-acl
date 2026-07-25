package com.dxlan.acl.features.shared.common;

/**
 * <h1>Context Resolvable Contract</h1>
 *
 * <p>This interface indicates that the implementing class possesses <b>self-resolution capabilities</b>.
 * The implementing instance usually acts as a lightweight token, intent, or configuration metadata
 * that knows how to resolve or evaluate itself into a fully-functional target object.</p>
 *
 * <h3>Design Intent:</h3>
 * <ul>
 *   <li><b>Lazy & Dynamic Resolution:</b> The conversion process might involve complex factory patterns,
 *       dynamic registry lookups, or environment-specific conditional logic.</li>
 *   <li><b>Separation of Intent and Execution:</b> The implementing object defines "what should be done" (Intent),
 *       while the resolved object acts as the actual mechanism that "executes the work" (Executor).</li>
 * </ul>
 *
 * <h3>Typical Use Cases:</h3>
 * <ul>
 *   <li>Resolving a retry strategy configuration enum ({@code RetryStrategy}) into a concrete strategy pattern executor ({@code RetryExecutor}).</li>
 *   <li>Resolving a dynamic cache policy token ({@code CachePolicy}) into a concrete infrastructure-specific cache engine loader.</li>
 * </ul>
 *
 * <h3>Code Example:</h3>
 * <pre>{@code
 * public enum CachePolicy implements ContextResolvable<CacheLoader> {
 *     LOCAL_MEMORY,
 *     REMOTE_REDIS;
 *
 *     @Override
 *     public CacheLoader resolve() {
 *         // Contains dynamic factory logic based on current application context
 *         return this == LOCAL_MEMORY ? new CaffeineCacheLoader() : new RedisCacheLoader();
 *     }
 * }
 * }</pre>
 *
 * @param <T> the type of the resolved target object or executor
 * @author DXLAN
 * @since 1.0.0
 */
public interface ContextResolvable<T> {

    /**
     * Executes the resolution logic to resolve this declarative token into a concrete domain entity or executor.
     *
     * @return the resolved target instance, never {@code null}
     * @throws RuntimeException if resolution fails due to environmental misconfiguration or invalid business states
     */
    T resolve();

}
