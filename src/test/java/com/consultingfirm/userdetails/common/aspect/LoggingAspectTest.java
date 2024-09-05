//package com.learnspring.userdetailsapi.common.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class LoggingAspectTest {
//
//    @InjectMocks
//    private LoggingAspect loggingAspect;
//
//    @Mock
//    private Logger mockLogger;
//
//    @Mock
//    private JoinPoint mockJoinPoint;
//
//    @Mock
//    private ProceedingJoinPoint mockProceedingJoinPoint;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testLogAfterThrowing() {
//        // Arrange
//        Throwable exception = new RuntimeException("Test exception");
//        when(mockJoinPoint.getSignature()).thenReturn(mock(org.aspectj.lang.Signature.class));
//        when(mockJoinPoint.getSignature().getDeclaringTypeName()).thenReturn("com.learnspring.userdetailsapi.benchprofiles.controller.BenchProfilesController");
//        when(mockJoinPoint.getSignature().getName()).thenReturn("fetchBenchProfileDetails");
//
//        // Act
//        loggingAspect.logAfterThrowing(mockJoinPoint, exception);
//
//        // Assert
//        verify(mockLogger).error(eq("Exception in {}.{}() with cause = {}"),
//                eq("com.learnspring.userdetailsapi.benchprofiles.controller.BenchProfilesController"),
//                eq("fetchBenchProfileDetails"),
//                any(Throwable.class)); // Use any(Throwable.class) for matching any Throwable
//    }
//
//    @Test
//    void testLogAround() throws Throwable {
//        // Arrange
//        Object result = "result";
//        Object[] args = {"arg1", 2};
//        when(mockLogger.isDebugEnabled()).thenReturn(true);
////        when(mockProceedingJoinPoint.proceed()).thenReturn(result);
////        when(mockProceedingJoinPoint.getArgs()).thenReturn(args);
////        when(mockProceedingJoinPoint.getSignature()).thenReturn(mock(org.aspectj.lang.Signature.class));
////        when(mockProceedingJoinPoint.getSignature().getDeclaringTypeName()).thenReturn("com.learnspring.userdetailsapi.benchprofiles.controller.BenchProfilesController");
////        when(mockProceedingJoinPoint.getSignature().getName()).thenReturn("fetchBenchProfileDetails");
//
////        when(mockLogger.debug()).thenReturn("Enter: {}.{}() with argument[s]"))
//        // Act
//        Object returnedResult = loggingAspect.logAround(mockProceedingJoinPoint);
//
//        // Assert
//        verify(mockLogger).debug(eq("Enter: {}.{}() with argument[s] = {}"),
//                eq("com.learnspring.userdetailsapi.benchprofiles.controller.BenchProfilesController"),
//                eq("fetchBenchProfileDetails"),
//                argThat(arguments -> arguments.equals("[arg1, 2]"))); // Use argThat for more complex checks
//        verify(mockLogger).debug(eq("Exit: {}.{}() with result = {}"),
//                eq("com.learnspring.userdetailsapi.benchprofiles.controller.BenchProfilesController"),
//                eq("fetchBenchProfileDetails"),
//                eq(result));
//        assertEquals(result, returnedResult);
//    }
//
//    @Test
//    void testLogAroundWithException() throws Throwable {
//        // Arrange
//        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
//        when(mockProceedingJoinPoint.proceed()).thenThrow(exception);
//        when(mockProceedingJoinPoint.getArgs()).thenReturn(new Object[]{"arg1"});
//        when(mockProceedingJoinPoint.getSignature()).thenReturn(mock(org.aspectj.lang.Signature.class));
//        when(mockProceedingJoinPoint.getSignature().getDeclaringTypeName()).thenReturn("com.learnspring.userdetailsapi.benchprofiles.controller.BenchProfilesController");
//        when(mockProceedingJoinPoint.getSignature().getName()).thenReturn("fetchBenchProfileDetails");
//
//        // Act & Assert
//        try {
//            loggingAspect.logAround(mockProceedingJoinPoint);
//        } catch (IllegalArgumentException e) {
//            // Expected
//        }
//
//        // Verify that the logger was called with the expected parameters
//        verify(mockLogger).error(eq("Illegal argument: {} in {}.{}()"),
//                eq("[arg1]"),
//                eq("com.learnspring.userdetailsapi.benchprofiles.controller.BenchProfilesController"),
//                eq("fetchBenchProfileDetails"));
//    }
//
//    @Test
//    public void testLogAfterThrowingWithNullCause() {
//        // Arrange
//        JoinPoint joinPoint = mock(JoinPoint.class);
//        Throwable exception = new RuntimeException("Test exception");
//        when(joinPoint.getSignature().getDeclaringTypeName()).thenReturn("com.learnspring.userdetailsapi.service.BenchProfilesService");
//        when(joinPoint.getSignature().getName()).thenReturn("getUserDetails");
//        when(exception.getCause()).thenReturn(null);
//
//        LoggingAspect loggingAspect = new LoggingAspect();
//
//        // Act
//        loggingAspect.logAfterThrowing(joinPoint, exception);
//
//        // Assert
//        verify(loggingAspect.LOGGER).error("Exception in com.learnspring.userdetailsapi.service.BenchProfilesService.getUserDetails() with cause = NULL");
//    }
//}
