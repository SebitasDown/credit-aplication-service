package com.CoopCredit.credit_application_service.infraestructure.in.web.exception;

import com.CoopCredit.credit_application_service.domain.exception.BusinessException;
import com.CoopCredit.credit_application_service.domain.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String MDC_TRACE_ID_KEY = "traceId";
    private static final String MDC_ENDPOINT_KEY = "endpoint";
    private static final String PROBLEM_TYPE_BASE_URI = "https://api.coopcredit.com/errors/";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String endpoint = getEndpoint(request);
        String traceId = getTraceId();
        String errorType = "validation-error";

        log.warn("VALIDATION_ERROR endpoint={} errorType={} traceId={} fieldErrors={}",
                endpoint, errorType, traceId, ex.getBindingResult().getFieldErrors().size());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setType(URI.create(PROBLEM_TYPE_BASE_URI + errorType));
        problem.setTitle("Validation Error");
        problem.setDetail("There are validation errors in the submitted data");
        problem.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        problem.setProperty("errors", errors);

        return enrichProblemDetail(problem, traceId);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return handleException(HttpStatus.NOT_FOUND, "Resource Not Found", ex.getMessage(),
                "resource-not-found", request, ex);
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex, HttpServletRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, "Business Rule Violation", ex.getMessage(),
                "business-rule-violation", request, ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        return handleException(HttpStatus.NOT_FOUND, "Entity Not Found", ex.getMessage(),
                "entity-not-found", request, ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        return handleException(HttpStatus.CONFLICT, "Data Integrity Violation",
                "A database constraint was violated.", "data-integrity-violation", request, ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        // Mapping IllegalArgumentException to Bad Request (often used for validation)
        return handleException(HttpStatus.BAD_REQUEST, "Invalid Argument", ex.getMessage(),
                "invalid-argument", request, ex);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex, HttpServletRequest request) {
        String endpoint = getEndpoint(request);
        String traceId = getTraceId();
        String errorType = "internal-server-error";

        log.error("INTERNAL_SERVER_ERROR endpoint={} errorType={} traceId={} exception={} message={}",
                endpoint, errorType, traceId, ex.getClass().getSimpleName(), ex.getMessage(), ex);

        return createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                "An unexpected error occurred.", errorType, request, traceId);
    }

    private ProblemDetail handleException(HttpStatus status, String title, String detail,
            String errorType, HttpServletRequest request, Exception ex) {
        String endpoint = getEndpoint(request);
        String traceId = getTraceId();

        log.warn("EXCEPTION_HANDLED endpoint={} errorType={} traceId={} status={} exception={} message={}",
                endpoint, errorType, traceId, status.value(), ex.getClass().getSimpleName(), ex.getMessage());

        return createProblemDetail(status, title, detail, errorType, request, traceId);
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String title, String detail,
            String errorType, HttpServletRequest request, String traceId) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setType(URI.create(PROBLEM_TYPE_BASE_URI + errorType));
        problem.setTitle(title);
        problem.setDetail(detail);
        problem.setInstance(URI.create(request.getRequestURI()));
        return enrichProblemDetail(problem, traceId);
    }

    private ProblemDetail enrichProblemDetail(ProblemDetail problem, String traceId) {
        problem.setProperty("timestamp", LocalDateTime.now());
        problem.setProperty("traceId", traceId);
        return problem;
    }

    private String getTraceId() {
        String traceId = MDC.get(MDC_TRACE_ID_KEY);
        return traceId != null ? traceId : "NO_TRACE";
    }

    private String getEndpoint(HttpServletRequest request) {
        String endpoint = MDC.get(MDC_ENDPOINT_KEY);
        if (endpoint == null) {
            endpoint = request.getMethod() + " " + request.getRequestURI();
        }
        return endpoint;
    }
}