package com.dxlan.acl.features.shared.numeric;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

public final class RangeSetFactory {

    private RangeSetFactory() {
        throw new AssertionError();
    }

    private static BigDecimal toBigDecimal(
            final Number number
    ) {
        return switch(number) {
            case BigDecimal bd -> bd.stripTrailingZeros();
            case BigInteger bi -> new BigDecimal(bi);
            case Integer i     -> BigDecimal.valueOf(i);
            case Long l        -> BigDecimal.valueOf(l);
            case Double d      -> {
                DoubleNumberValidator.validateFinite(d);
                yield BigDecimal.valueOf(d).stripTrailingZeros();
            }
            case Float f       -> {
                FloatNumberValidator.validateFinite(f);
                yield BigDecimal.valueOf(f.doubleValue()).stripTrailingZeros();
            }
            case Short s       -> BigDecimal.valueOf(s);
            case Byte b        -> BigDecimal.valueOf(b);
            default            -> BigDecimal.valueOf(number.intValue());
        };
    }

    private static <T extends Number> T convertToTargetType(
            final BigDecimal value,
            final Class<T> targetClass
    ) {
        if (targetClass == Integer.class)    return targetClass.cast(value.intValue());
        if (targetClass == Long.class)       return targetClass.cast(value.longValue());
        if (targetClass == Double.class)     return targetClass.cast(value.doubleValue());
        if (targetClass == Float.class)      return targetClass.cast(value.floatValue());
        if (targetClass == Short.class)      return targetClass.cast(value.shortValue());
        if (targetClass == Byte.class)       return targetClass.cast(value.byteValue());
        if (targetClass == BigInteger.class) return targetClass.cast(value.toBigInteger());
        return targetClass.cast(value);
    }

    private static <T extends Number> void validateRangeDefinitionValid(
            final T min,
            final T max
    ) {
        if (NumberComparator.compare(min, max) >= 0) {
            throw new IllegalArgumentException(
                    "Invalid range definition: min (" +
                    min + ") cannot be greater than or equal to max (" +
                    max + ")."
            );
        }
    }

    private static <T extends Number> SequencedSet<T> createRange(
            final T min,
            final T max,
            final T step,
            final Class<T> targetClass,
            final boolean includeMin,
            final boolean includeMax
    ) {
        Objects.requireNonNull(min, "Minimum bound must not be null.");
        Objects.requireNonNull(max, "Maximum bound must not be null.");
        Objects.requireNonNull(step, "Step value must not be null.");
        Objects.requireNonNull(targetClass, "Target class must not be null.");
        validateRangeDefinitionValid(min, max);

        if (NumberComparator.compare(step, 0) <= 0) {
            throw new IllegalArgumentException("Step value must be positive (> 0).");
        }

        final BigDecimal bdMin = toBigDecimal(min);
        final BigDecimal bdMax = toBigDecimal(max);
        final BigDecimal bdStep = toBigDecimal(step);
        final BigDecimal actualStart = includeMin ? bdMin : bdMin.add(bdStep);

        int startVsMax = NumberComparator.compare(
                convertToTargetType(actualStart, targetClass),
                max
        );
        if (startVsMax > 0 || (!includeMax && startVsMax == 0)) {
            return Collections.emptyNavigableSet();
        }

        final int computedSize;
        try {
            BigDecimal distance = bdMax.subtract(actualStart);
            BigDecimal rawSize = distance
                    .divide(bdStep, 0, RoundingMode.FLOOR)
                    .add(BigDecimal.ONE);

            if (!includeMax && distance.remainder(bdStep).compareTo(BigDecimal.ZERO) == 0) {
                rawSize = rawSize.subtract(BigDecimal.ONE);
            }

            if (rawSize.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) > 0) {
                throw new IllegalArgumentException(
                        "The range size is too large to fit in a Java Set (Overflow)."
                );
            }
            computedSize = rawSize.intValue();
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException(
                    "Invalid arithmetic range scale constraints.",
                    exception
            );
        }

        return new SequencedSet<>() {

            @Override
            public int size() { return computedSize; }

            @Override public boolean isEmpty() { return computedSize == 0; }

            @Override
            public boolean contains(
                    final Object obj
            ) {
                if (!(obj instanceof Number value)) return false;

                final int minComp = NumberComparator.compare(value, min);
                final int maxComp = NumberComparator.compare(value, max);

                final boolean leftValid = includeMin ? minComp >= 0 : minComp > 0;
                final boolean rightValid = includeMax ? maxComp <= 0 : maxComp < 0;

                if (!leftValid || !rightValid) return false;

                final BigDecimal distance = toBigDecimal(value).subtract(actualStart);
                return distance.remainder(bdStep).compareTo(BigDecimal.ZERO) == 0;
            }

            @Override
            public @NotNull Iterator<T> iterator() {
                return new Iterator<>() {
                    private BigDecimal current = actualStart;

                    @Override public boolean hasNext() { return hasMore(current); }

                    @Override
                    public T next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        final BigDecimal result = current;
                        current = current.add(bdStep);
                        return convertToTargetType(result, targetClass); // 精準還原型態
                    }
                };
            }

            private boolean hasMore(
                    final BigDecimal current
            ) {
                T currentObj = convertToTargetType(current, targetClass);
                int comp = NumberComparator.compare(currentObj, max);
                return includeMax ? comp <= 0 : comp < 0;
            }

            @Override public T getFirst() { return convertToTargetType(actualStart, targetClass); }

            @Override
            public T getLast() {
                BigDecimal lastValue = actualStart
                        .add(BigDecimal
                                .valueOf(computedSize - 1)
                                .multiply(bdStep));
                return convertToTargetType(lastValue, targetClass);
            }

            @Override public @NotNull SequencedSet<T> reversed() {
                throw new UnsupportedOperationException();
            }
            @Override public boolean add(
                    final T t
            ) {
                throw new UnsupportedOperationException();
            }
            @Override public boolean remove(
                    final Object o
            ) {
                throw new UnsupportedOperationException();
            }
            @Override public boolean retainAll(
                    @NotNull final Collection<?> c
            ) {
                throw new UnsupportedOperationException();
            }
            @Override public boolean removeAll(
                    @NotNull final Collection<?> c
            ) {
                throw new UnsupportedOperationException();
            }
            @Override public void clear() {
                throw new UnsupportedOperationException();
            }
            @Override public boolean addAll(
                    @NotNull final Collection<? extends T> c
            ) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Object @NotNull [] toArray() {
                final List<T> list = new ArrayList<>(computedSize);
                // noinspection UseBulkOperation
                this.forEach(list::add);
                return list.toArray();
            }

            @Override
            public <E> E @NotNull [] toArray(
                    final E @NotNull [] array
            ) {
                final List<T> list = new ArrayList<>(computedSize);
                // noinspection UseBulkOperation
                this.forEach(list::add);
                return list.toArray(array);
            }

            @Override
            public boolean containsAll(
                    final @NotNull Collection<?> collection
            ) {
                for (final Object element : collection) { if (!contains(element)) return false; }
                return true;
            }
        };
    }

    public static <T extends Number> SequencedSet<T> closed(
            final T min,
            final T max,
            final T step,
            final Class<T> targetClass
    ) {
        return createRange(min, max, step, targetClass, true, true);
    }

    public static <T extends Number> SequencedSet<T> open(
            final T min,
            final T max,
            final T step,
            final Class<T> targetClass
    ) {
        return createRange(min, max, step, targetClass, false, false);
    }

    public static <T extends Number> SequencedSet<T> openClosed(
            final T min,
            final T max,
            final T step,
            final Class<T> targetClass
    ) {
        return createRange(min, max, step, targetClass, false, true);
    }

    public static <T extends Number> SequencedSet<T> closedOpen(
            final T min,
            final T max,
            final T step,
            final Class<T> targetClass
    ) {
        return createRange(min, max, step, targetClass, true, false);
    }

}
