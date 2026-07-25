package com.dxlan.acl.features.shared.boundaries;

import com.dxlan.acl.features.shared.boundaries.metadata.ExternalFieldMetadata;
import com.dxlan.acl.features.shared.boundaries.metadata.ExternalMetadata;
import com.dxlan.acl.features.shared.boundaries.metadata.InternalMetadata;
import com.dxlan.acl.features.shared.collections.CollectionValidator;
import com.dxlan.acl.features.shared.numeric.*;
import com.dxlan.acl.features.shared.objects.ObjectValidator;
import com.dxlan.acl.features.shared.text.TextValidator;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public final class BoundaryValidator {

    private final Class<?> callerClass;
    private final String BASE_PREFIX;

    private BoundaryValidator(
            final Class<?> callerClass
    ) {
        Objects.requireNonNull(callerClass, "CallerClass must not be null.");
        this.callerClass = callerClass;
        this.BASE_PREFIX = "Boundary Defense failure at [" +
                callerClass.getSimpleName() + "]: ";
    }

    private String messagePrefix(
            final ExternalMetadata metadata
    ) {
        return BASE_PREFIX +
                BoundaryMetadataDetailsFactory.createForExternalType(metadata);
    }

    private String messagePrefix(
            final ExternalFieldMetadata metadata
    ) {
        return BASE_PREFIX +
                BoundaryMetadataDetailsFactory.createForExternalField(metadata);
    }

    private String messagePrefix(
            final InternalMetadata metadata
    ) {
        return BASE_PREFIX +
                BoundaryMetadataDetailsFactory.createForInternalType(metadata);
    }

    public <T> BoundaryValidator requireExternalTypeNotNull(
            final T type,
            final ExternalMetadata metadata
    ) {
        ObjectValidator.validateNotNull(type, messagePrefix(metadata));
        return this;
    }

    public <T> BoundaryValidator requireExternalTypeSpecified(
            final T type,
            final ExternalMetadata metadata
    ) {
        ObjectValidator.validateSpecified(type, messagePrefix(metadata));
        return this;
    }

    public BoundaryValidator requireInternalTypeNotNull(
            final Object type,
            final InternalMetadata metadata
    ) {
        ObjectValidator.validateNotNull(type, messagePrefix(metadata));
        return this;
    }

    public BoundaryValidator requireInternalTypeSpecified(
            final Object type,
            final InternalMetadata metadata
    ) {
        ObjectValidator.validateSpecified(type, messagePrefix(metadata));
        return this;
    }

    public BoundaryValidator requireFieldNotNull(
            final Object field,
            final ExternalFieldMetadata metadata
    ) {
        ObjectValidator.validateNotNull(field, messagePrefix(metadata));
        return this;
    }

    public BoundaryValidator requireFieldSpecified(
            final Object field,
            final ExternalFieldMetadata metadata
    ) {
        ObjectValidator.validateSpecified(field, messagePrefix(metadata));
        return this;
    }

    public BoundaryValidator requireFieldPositive(
            final Number value,
            final ExternalFieldMetadata metadata
    ) {
        NumericValidator.validatePositive(value, messagePrefix(metadata));
        return this;
    }

    public BoundaryValidator requireFieldPositiveOrZero(
            final Number value,
            final ExternalFieldMetadata metadata
    ) {
        NumericValidator.validatePositiveOrZero(value, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldAtMost(
            final T value,
            final T upperBound,
            final ExternalFieldMetadata metadata
    ) {
        NumericValidator.validateAtMost(
                value,
                upperBound,
                messagePrefix(metadata)
        );
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldLessThan(
            final T value,
            final T upperBound,
            final ExternalFieldMetadata metadata
    ) {
        NumericValidator.validateLessThan(
                value,
                upperBound,
                messagePrefix(metadata)
        );
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldAtLeast(
            final T value,
            final T lowerBound,
            final ExternalFieldMetadata metadata
    ) {
        NumericValidator.validateAtLeast(
                value,
                lowerBound,
                messagePrefix(metadata)
        );
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldGreaterThan(
            final T value,
            final T lowerBound,
            final ExternalFieldMetadata metadata
    ) {
        NumericValidator.validateGreaterThan(
                value,
                lowerBound,
                messagePrefix(metadata)
        );
        return this;
    }

    public BoundaryValidator requireFieldHasText(
            final String value,
            final ExternalFieldMetadata metadata
    ) {
        TextValidator.validateHasText(
                value,
                messagePrefix(metadata)
        );
        return this;
    }

    public <T extends Number & Comparable<T>> BoundaryValidator requireFieldInClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final ExternalFieldMetadata metadata
    ) {
        NumberRangeValidator.validateInClosedRange(
                value,
                lowerBound,
                upperBound,
                messagePrefix(metadata)
        );
        return this;
    }

    public <T extends Number & Comparable<T>> BoundaryValidator requireFieldInOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final ExternalFieldMetadata metadata
    ) {
        NumberRangeValidator.validateInOpenRange(
                value,
                lowerBound,
                upperBound,
                messagePrefix(metadata)
        );
        return this;
    }

    public <T extends Number & Comparable<T>> BoundaryValidator requireFieldInClosedOpenRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final ExternalFieldMetadata metadata
    ) {
        NumberRangeValidator.validateInClosedOpenRange(
                value,
                lowerBound,
                upperBound,
                messagePrefix(metadata)
        );
        return this;
    }

    public <T extends Number & Comparable<T>> BoundaryValidator requireFieldInOpenClosedRange(
            final T value,
            final T lowerBound,
            final T upperBound,
            final ExternalFieldMetadata metadata
    ) {
        NumberRangeValidator.validateInOpenClosedRange(
                value,
                lowerBound,
                upperBound,
                messagePrefix(metadata)
        );
        return this;
    }

    public <T extends Number & Comparable<T>> BoundaryValidator requireFieldInAnyClosedRanges(
            final T value,
            final T firstLowerBound,
            final T firstUpperBound,
            final T secondLowerBound,
            final T secondUpperBound,
            final ExternalFieldMetadata metadata
    ) {
        NumberRangeValidator.validateInAnyClosedRanges(
                value,
                firstLowerBound,
                firstUpperBound,
                secondLowerBound,
                secondUpperBound,
                messagePrefix(metadata)
        );
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldDigitCount(
            final T value,
            final int allowedDigitCount,
            final DigitStrategy strategy,
            final ExternalFieldMetadata metadata
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCount(allowedDigitCount, strategy, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldDigitCount(
            final T value,
            final int allowedDigitCount,
            final ExternalFieldMetadata metadata
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCount(allowedDigitCount, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldDigitCountWithin(
            final T value,
            final int allowedMinDigit,
            final int allowedMaxDigit,
            final DigitStrategy strategy,
            final ExternalFieldMetadata metadata
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountWithin(
                        allowedMinDigit, allowedMaxDigit, strategy, messagePrefix(metadata)
                );
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldDigitCountWithin(
            final T value,
            final int allowedMinDigit,
            final int allowedMaxDigit,
            final ExternalFieldMetadata metadata
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountWithin(allowedMinDigit, allowedMaxDigit, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldDigitCountAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts,
            final DigitStrategy strategy,
            final ExternalFieldMetadata metadata
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, strategy, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldDigitCountAnyOf(
            final T value,
            final Set<Integer> allowedDigitCounts,
            final ExternalFieldMetadata metadata
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldDigitCountAnyOf(
            final T value,
            final int[] allowedDigitCounts,
            final DigitStrategy strategy,
            final ExternalFieldMetadata metadata
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, strategy, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldDigitCountAnyOf(
            final T value,
            final int[] allowedDigitCounts,
            final ExternalFieldMetadata metadata
    ) {
        NumericDigitCountValidator
                .of(value)
                .validateDigitCountAnyOf(allowedDigitCounts, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldFirstDigit(
            final T value,
            final int allowedDigit,
            final LeadingDigitStrategy strategy,
            final ExternalFieldMetadata metadata
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigit(allowedDigit, strategy, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldFirstDigit(
            final T value,
            final int allowedDigit,
            final ExternalFieldMetadata metadata
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigit(allowedDigit, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldFirstDigitWithin(
            final T value,
            final int allowedMinDigit,
            final int allowedMaxDigit,
            final LeadingDigitStrategy strategy,
            final ExternalFieldMetadata metadata
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitWithin(
                        allowedMinDigit, allowedMaxDigit, strategy, messagePrefix(metadata)
                );
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldFirstDigitWithin(
            final T value,
            final int allowedMinDigit,
            final int allowedMaxDigit,
            final ExternalFieldMetadata metadata
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitWithin(allowedMinDigit, allowedMaxDigit, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldFirstDigitAnyOf(
            final T value,
            final Set<Integer> allowedFirstDigits,
            final LeadingDigitStrategy strategy,
            final ExternalFieldMetadata metadata
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedFirstDigits, strategy, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldFirstDigitAnyOf(
            final T value,
            final Set<Integer> allowedFirstDigits,
            final ExternalFieldMetadata metadata
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedFirstDigits, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldFirstDigitAnyOf(
            final T value,
            final int[] allowedFirstDigits,
            final LeadingDigitStrategy strategy,
            final ExternalFieldMetadata metadata
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedFirstDigits, strategy, messagePrefix(metadata));
        return this;
    }

    public <T extends Number> BoundaryValidator requireFieldFirstDigitAnyOf(
            final T value,
            final int[] allowedFirstDigits,
            final ExternalFieldMetadata metadata
    ) {
        NumericLeadingDigitValidator
                .of(value)
                .validateFirstDigitAnyOf(allowedFirstDigits, messagePrefix(metadata));
        return this;
    }

    public <T extends Collection<?>> BoundaryValidator requireFieldHasElements(
            final T collection,
            final ExternalFieldMetadata metadata
    ) {
        CollectionValidator.validateNotEmpty(collection, messagePrefix(metadata));
        return this;
    }

    public <T extends Collection<?>> BoundaryValidator requireExternalTypeHasElements(
            final T collection,
            final ExternalMetadata metadata
    ) {
        CollectionValidator.validateNotEmpty(collection, messagePrefix(metadata));
        return this;
    }

    public <E, T extends Collection<E>> BoundaryValidator requireFieldNoneNullElements(
            final T collection,
            final ExternalFieldMetadata metadata
    ) {
        CollectionValidator.validateNoneNull(collection, messagePrefix(metadata));
        return this;
    }

    public <E, T extends Collection<E>> BoundaryValidator requireExternalTypeNoneNullElements(
            final T collection,
            final ExternalMetadata metadata
    ) {
        CollectionValidator.validateNoneNull(collection, messagePrefix(metadata));
        return this;
    }

    public static BoundaryValidator forClass(
            final Class<?> callerClass
    ) {
        return new BoundaryValidator(
                callerClass
        );
    }

}
