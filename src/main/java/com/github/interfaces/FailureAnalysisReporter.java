package com.github.interfaces;

import com.github.model.FailureAnalysis;

/**
 * @author 康盼Java开发工程师
 */
@FunctionalInterface
public interface FailureAnalysisReporter {
    /**
     * Reports the given {@code failureAnalysis} to the user.
     * @param analysis the analysis
     */
    void report(FailureAnalysis analysis);
}
