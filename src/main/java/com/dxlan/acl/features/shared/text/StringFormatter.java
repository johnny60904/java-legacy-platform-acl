package com.dxlan.acl.features.shared.text;

import com.dxlan.acl.features.shared.arrays.ArrayValidator;

import java.util.Objects;

public final class StringFormatter {

    private StringFormatter() {
        throw new AssertionError();
    }

    private static void validateNotNull(
            final Object target,
            final String name
    ) {
        Objects.requireNonNull(target, name + " must not be null.");
    }

    public static String format(
            final String pattern,
            final Object candidate
    ) {
        TextValidator.validateHasText(pattern, "Pattern");
        validateNotNull(candidate, "Candidate");
        return String.format(pattern, candidate);
    }

    public static String format(
            final String pattern,
            final Object candidateLeft,
            final Object candidateRight
    ) {
        TextValidator.validateHasText(pattern, "Pattern");
        validateNotNull(candidateLeft, "CandidateLeft");
        validateNotNull(candidateRight, "CandidateRight");
        return String.format(pattern, candidateLeft, candidateRight);
    }

    public static String format(
            final String pattern,
            final Object candidateLeft,
            final Object candidateMiddle,
            final Object candidateRight
    ) {
        TextValidator.validateHasText(pattern, "Pattern");
        validateNotNull(candidateLeft, "CandidateLeft");
        validateNotNull(candidateMiddle, "CandidateMiddle");
        validateNotNull(candidateRight, "CandidateRight");
        return String.format(pattern, candidateLeft, candidateMiddle, candidateRight);
    }

    public static String format(
            final String pattern,
            final Object... candidates
    ) {
        TextValidator.validateHasText(pattern, "Pattern");
        ArrayValidator.validateNoneNull(candidates, "Candidates");
        ArrayValidator.validateLengthBelowSecurityBound(candidates, "Candidates");
        return String.format(pattern, candidates);
    }

}
